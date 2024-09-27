package moe.protasis.casper.permission.provider;

import moe.protasis.casper.api.permission.IPermissionProvider;

import java.util.List;

public class PublicPermissionProvider implements IPermissionProvider {

    @Override
    public List<String> GetAccountPermissions(String accountId) {
        return null;
    }

    @Override
    public List<String> GetPackagePermissions(String accountId, String packageId) {
        return null;
    }
}
