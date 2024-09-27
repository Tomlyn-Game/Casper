package moe.protasis.casper.api.permission;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum StandardPermission {
    REPO_READ("std:read"),
    REPO_WRITE("std:write"),
    REPO_UPLOAD("std:upload"),
    REPO_LIST("std:list"),
    REPO_UNLINK("std:unlink"),


    PACKAGE_READ("std:read"),
    PACKAGE_WRITE("std:write"),
    PACKAGE_UPLOAD("std:upload"),
    PACKAGE_UNLINK("std:unlink"),
    ;

    private final String permissionString;

    @Override
    public String toString() {
        return permissionString;
    }

    public static List<String> All() {
        return Arrays.stream(values())
                .map(StandardPermission::toString)
                .toList();
    }
}
