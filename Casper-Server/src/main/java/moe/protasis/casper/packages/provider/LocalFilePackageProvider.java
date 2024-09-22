package moe.protasis.casper.packages.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.api.packages.PackageManifest;
import moe.protasis.casper.api.packages.RepositoryPackageStatus;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class LocalFilePackageProvider implements IPackageProvider {
    private final File packageDir;

    public LocalFilePackageProvider(File packageDir) throws IOException {
        this.packageDir = packageDir.getAbsoluteFile();

        if (!packageDir.exists()) packageDir.mkdirs();
        if (!packageDir.isDirectory()) throw new IOException("Package directory is not a directory!");

        log.info("Local file package provider initialized with package directory: {}", packageDir.getAbsolutePath());
    }

    @Override
    public RepositoryPackageStatus GetPackageStatus(String id) {
        return new File(packageDir + "/" + id).exists() ? RepositoryPackageStatus.RECEIVED : RepositoryPackageStatus.NOT_FOUND;
    }

    @Override
    public PackageManifest GetPackageDescriptor(String id) {
        File dir = new File(packageDir + "/" + id);
        if (!dir.isDirectory()) return null;

        try {
            return new ObjectMapper().readValue(new File(dir + "/descriptor.json"), PackageManifest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void UpdatePackageFile(String id, byte[] data) throws IOException{
        File path = new File(packageDir + "/" + id);

        // write the file
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }

    }
}
