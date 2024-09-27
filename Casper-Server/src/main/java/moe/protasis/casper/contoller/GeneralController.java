package moe.protasis.casper.contoller;

import lombok.extern.slf4j.Slf4j;
import moe.protasis.casper.util.JsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/repo")
public class GeneralController {
    @Autowired
    private JsonWrapper config;

    @GetMapping("/info")
    private JsonWrapper GetRepoGeneralInfo() {
        return config.GetObject("info");
    }
}
