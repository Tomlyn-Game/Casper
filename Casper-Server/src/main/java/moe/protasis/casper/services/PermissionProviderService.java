package moe.protasis.casper.services;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.api.packages.PackageProviderType;
import moe.protasis.casper.api.permission.IPermissionProvider;
import moe.protasis.casper.api.permission.PermissionProviderType;
import moe.protasis.casper.packages.provider.LocalFilePackageProvider;
import moe.protasis.casper.permission.provider.PublicPermissionProvider;
import moe.protasis.casper.permission.provider.StaticPermissionProvider;
import moe.protasis.casper.util.JsonWrapper;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class PermissionProviderService {
    @Autowired
    private JsonWrapper config;
    @Getter
    private IPermissionProvider provider;

    @PostConstruct
    private void Init() {
        var providerType = config.GetEnum(PermissionProviderType.class, "permissionProvider.type", PermissionProviderType.PUBLIC);
        switch (providerType) {
            case PUBLIC -> provider = new PublicPermissionProvider();
            case STATIC -> provider = new StaticPermissionProvider();

            default -> {
                throw new NotImplementedException("Unsupported pacakge provider type: %s".formatted(providerType));
            }
        }

        log.info("Using permission provider: {}", providerType);
    }

    @Bean
    private IPermissionProvider PermissionProvider() {
        return provider;
    }

}
