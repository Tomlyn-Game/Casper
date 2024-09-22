package moe.protasis.casper.services;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.api.packages.IPackageProvider;
import moe.protasis.casper.api.packages.PackageProviderType;
import moe.protasis.casper.packages.provider.LocalFilePackageProvider;
import moe.protasis.casper.util.JsonWrapper;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class PackageProviderService {
    @Autowired
    private JsonWrapper config;
    @Getter
    private IPackageProvider provider;

    @PostConstruct
    private void Init() {
        var providerType = config.GetEnum(PackageProviderType.class, "packageProvider", PackageProviderType.FILE);
        switch (providerType) {
            case FILE -> {
                try {
                    provider = new LocalFilePackageProvider(new File(System.getProperty("user.dir") + "/packages"));
                } catch (IOException e) {
                    log.error("An error occurred while initializing the local file package provider.");
                    throw new RuntimeException(e);
                }
            }

            default -> {
                throw new NotImplementedException("Unsupported pacakge provider type: %s".formatted(providerType));
            }
        }

        log.info("Using package provider: {}", providerType);
    }

    @Bean
    private IPackageProvider packageProvider() {
        return provider;
    }

}
