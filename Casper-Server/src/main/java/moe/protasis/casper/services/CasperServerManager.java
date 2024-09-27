package moe.protasis.casper.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.Casper;
import moe.protasis.casper.api.central.ITomlynAPI;
import moe.protasis.casper.api.sever.IServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CasperServerManager implements IServer {
    @Autowired
    private PackageProviderService packageProviderService;
    @Autowired
    private ITomlynAPI serverApi;
    @Autowired
    private PermissionProviderService permissionProviderService;

    @PostConstruct
    private void Init() {
        log.info("Starting Casper server...");

        try {
            new Casper(
                    this,
                    packageProviderService.getProvider(),
                    serverApi,
                    permissionProviderService.getProvider()
            );
        } catch (Exception e) {
            log.error("There was an error starting the server");
            throw new RuntimeException(e);
        }

    }
}
