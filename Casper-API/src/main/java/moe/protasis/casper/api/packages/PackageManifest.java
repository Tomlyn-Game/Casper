package moe.protasis.casper.api.packages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageManifest {
    private String id;
    private PackageStatus status;
    private String owner;

    private UUID uuid;
    private String fileChecksum;
    private String repositoryAddress;
    private boolean privatePackage;

    private String name;
    private String romanizedName;
    private String author;
    private String authorRomanized;
    private String mapper;
    private List<String> tags = new ArrayList<>();
    private float difficultyMin, difficultyMax;
    private boolean nsfw;
    private boolean test;
}
