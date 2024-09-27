package moe.protasis.casper.api.permission;

public enum PermissionProviderType {
    /**
     * All users have ALL permissions. This repository is completely public.
     */
    PUBLIC,
    /**
     * A JSON file is used to store rule-based permissions.
     * A new file is created if one does not exist.
     */
    FILE,
    /**
     * All accounts and packages will have the same permissions.
     * These are specified in config.json.
     */
    STATIC,
    /**
     * A custom plugin will handle permission logic by returning an
     * IPermissionProvider in its plugin main class.
     * Only once(1) plugin may be a provider. This plugin is specified in config.json.
     */
    PLUGIN
}
