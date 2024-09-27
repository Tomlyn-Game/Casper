package moe.protasis.casper.services;

import jakarta.annotation.PostConstruct;
import moe.protasis.casper.api.plugin.CasperPlugin;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
public class PluginManager {
    @PostConstruct
    private void Init() {

    }

    public CasperPlugin GetPlugin(String name) {
        throw new NotImplementedException();
    }
}
