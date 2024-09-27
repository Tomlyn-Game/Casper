package moe.protasis.casper.api.packages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moe.protasis.casper.api.sheet.SheetDescriptor;
import moe.protasis.casper.api.sheet.SheetDifficulty;

import java.util.*;

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
    private boolean nsfw;
    private boolean test;

    private Map<SheetDifficulty, SheetDescriptor> sheets = new HashMap<>();
}
