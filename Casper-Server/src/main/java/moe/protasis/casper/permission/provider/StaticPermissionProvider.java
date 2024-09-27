package moe.protasis.casper.permission.provider;

import moe.protasis.casper.SpringContext;
import moe.protasis.casper.api.permission.IPermissionProvider;
import moe.protasis.casper.util.JsonWrapper;

import java.util.List;

public class StaticPermissionProvider implements IPermissionProvider {
    @Override
    public List<String> GetAccountPermissions(String accountId) {
        return SpringContext.getBean(JsonWrapper.class).GetList("permissionProvider.config.static.account", String.class);
    }

    @Override
    public List<String> GetPackagePermissions(String accountId, String packageId) {
        return SpringContext.getBean(JsonWrapper.class).GetList("permissionProvider.config.static.package", String.class);
    }
}
