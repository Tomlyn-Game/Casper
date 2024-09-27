package moe.protasis.casper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.api.central.ITomlynAPI;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.api.permission.IPermissionProvider;
import moe.protasis.casper.api.sever.IServer;

@Slf4j
public class Casper {
    @Getter
    private static Casper instance;

    @Getter
    private final IServer server;
    @Getter
    private final IPackageProvider packageProvider;
    @Getter
    private final ITomlynAPI serverApi;
    @Getter
    private final IPermissionProvider permissionProvider;

    public Casper(
            IServer server,
            IPackageProvider defaultPackageProvider,
            ITomlynAPI serverApi,
            IPermissionProvider permissionProvider
    ) throws Exception {
        this.permissionProvider = permissionProvider;
        if (instance != null) {
            throw new Exception("Casper instance already exists!");
        }
        instance = this;

        this.server = server;
        log.info("Casper server starting...");
        instance = this;
        this.packageProvider = defaultPackageProvider;
        this.serverApi = serverApi;

        log.info("Casper server started!");
    }
}
