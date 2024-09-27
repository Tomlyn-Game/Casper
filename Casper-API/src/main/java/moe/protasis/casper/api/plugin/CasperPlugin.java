package moe.protasis.casper.api.plugin;

import moe.protasis.casper.Casper;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.api.permission.IPermissionProvider;
import moe.protasis.casper.api.sever.IServer;

/**
 * Base class for developer-created plugins.
 */
public abstract class CasperPlugin implements IPlugin {
    public void OnEnable() {

    }

    public void OnDisable() {

    }

    protected final IServer GetServer() {
        return Casper.getInstance().getServer();
    }

    public IPermissionProvider GetPluginPermissionProvider() {
        return null;
    }

    public IPackageProvider GetPluginPackageProvider() {
        return null;
    }
}
