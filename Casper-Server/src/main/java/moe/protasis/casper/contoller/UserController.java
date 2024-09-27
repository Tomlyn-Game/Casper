package moe.protasis.casper.contoller;

import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.api.permission.IPermissionProvider;
import moe.protasis.casper.exception.api.ForbiddenException;
import moe.protasis.casper.exception.api.NoContentException;
import moe.protasis.casper.util.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/repo/user", produces = "application/json", consumes = "application/json")
@Slf4j
public class UserController {
    @Autowired
    private IPermissionProvider permissionProvider;

    @GetMapping(value = "/{accountId}/grants", consumes = "*/*")
    public JsonWrapper GetGrants(
            @PathVariable("accountId") String accountId,
            @RequestParam(value = "packageId", required = false, defaultValue = "") String packageId
    ) {
        List<String> grants;
        if (packageId != null && !packageId.isEmpty()) {
            grants = permissionProvider.GetPackagePermissions(accountId, packageId);
        } else {
            grants = permissionProvider.GetAccountPermissions(accountId);
        }

        if (grants == null)
            throw new NoContentException();
        if (grants.isEmpty())
            throw new ForbiddenException();

        return new JsonWrapper()
                .Set("grants", grants);
    }
}
