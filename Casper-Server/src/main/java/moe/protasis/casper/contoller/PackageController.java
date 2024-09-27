package moe.protasis.casper.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.annotation.RepoToken;
import moe.protasis.casper.api.central.ITomlynAPI;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.api.packages.PackageManifest;
import moe.protasis.casper.api.packages.RepositoryPackageStatus;
import moe.protasis.casper.exception.api.APIException;
import moe.protasis.casper.exception.api.NotAuthorizedException;
import moe.protasis.casper.exception.api.NotFoundException;
import moe.protasis.casper.util.JsonWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/repo/package/{packageId}")
@Slf4j
public class PackageController {
    @GetMapping("/status")
    private JsonWrapper GetPackageStatus(
            @PathVariable("packageId") String id,
            IPackageProvider packageProvider
    ) {
        var ret = packageProvider.GetPackageStatus(id);
        if (ret == RepositoryPackageStatus.NOT_FOUND)
            throw new NotFoundException();

        return new JsonWrapper()
                .Set("hasFile", ret == RepositoryPackageStatus.RECEIVED);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    private JsonWrapper UploadPackage(
            @PathVariable("packageId") String id,
            @RepoToken String repoToken,
            @RequestParam("file") MultipartFile file,
            IPackageProvider packageProvider,
            ITomlynAPI tomlynAPI
    ) {
        if (!tomlynAPI.VerifyCOMMToken(repoToken, id))
            throw new NotAuthorizedException();

        PackageManifest data = tomlynAPI.GetPackageManifest(id).GetObject("data", PackageManifest.class);

        if (!data.getFileChecksum().equalsIgnoreCase(Sha256Sum(file)))
            throw new APIException(400, "checksum mismatch");

        // validate package
        try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if ("_pack".equals(entry.getName())) {
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(zipInputStream, StandardCharsets.UTF_8))) {
                        String content = reader.lines().collect(Collectors.joining("\n"));
                        JsonWrapper json = new JsonWrapper(content);

                        if (!data.getUuid().equals(json.GetUuid("uuid")))
                            throw new APIException(400, "package uuid mismatch");

                        // success
                        try {
                            packageProvider.UpdatePackageFile(id, file.getBytes());
                        } catch (IOException e) {
                            throw new APIException(500, "failed to save package");
                        }

                        return new JsonWrapper();
                    }
                }
            }

           throw new APIException(400, "invalid package");
        } catch (JsonProcessingException e) {
            throw new APIException(400, "invalid package descriptor");
        } catch (IOException e) {
            throw new APIException(400, "invalid package file");
        }

    }

    private static String Sha256Sum(MultipartFile file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(file.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error calculating SHA-256 hash", e);
        }
    }

}
