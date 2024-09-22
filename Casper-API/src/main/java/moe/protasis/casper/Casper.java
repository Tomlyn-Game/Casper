package moe.protasis.casper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.api.central.ITomlynAPI;
import moe.protasis.casper.api.packages.IPackageProvider;

@Slf4j
public class Casper {
    @Getter
    private static Casper instance;

    private IPackageProvider packageProvider;
    private ITomlynAPI serverApi;

    private Casper(

            IPackageProvider defaultPackageProvider,
            ITomlynAPI serverApi

    ) throws Exception {
        log.info("Casper server starting...");
        instance = this;
        this.packageProvider = defaultPackageProvider;
        this.serverApi = serverApi;

        log.info("Casper server started!");
    }

    public static IPackageProvider GetPackageProvider() {
        return getInstance().packageProvider;
    }

    public static ITomlynAPI GetServerAPI() {
        return getInstance().serverApi;
    }

    public static void Init(IPackageProvider defaultPackageProvider, ITomlynAPI tomlynAPI) throws Exception {
        if (instance != null)
            throw new IllegalStateException("Casper has already been initialized!");

        new Casper(defaultPackageProvider, tomlynAPI);
    }
}
