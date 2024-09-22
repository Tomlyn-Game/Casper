package moe.protasis.casper.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.Casper;
import moe.protasis.casper.api.central.ITomlynAPI;
import moe.protasis.casper.exception.APIException;
import moe.protasis.casper.exception.NotFoundException;
import moe.protasis.casper.util.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class TomlynAPIService implements ITomlynAPI {
    @Autowired
    private JsonWrapper config;
    private WebClient client;

    @PostConstruct
    private void Init() {
        var baseUrl = config.GetString("apiUrl");
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();

        log.info("Tomlyn API Service initialized with base URL {}", baseUrl);
    }

    @Override
    public JsonWrapper GetPackageManifest(String packageId) {
        return client
                .get()
                .uri("/api/package/" + packageId)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> response.bodyToMono(String.class)
                                .map(a -> new NotFoundException())
                )
                .bodyToMono(String.class)
                .map(JsonWrapper::Parse)
                .block();
    }

}
