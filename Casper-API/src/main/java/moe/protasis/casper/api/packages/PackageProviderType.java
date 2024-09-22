package moe.protasis.casper.api.packages;

public enum PackageProviderType {
    /**
     * Stores and serves packages from the local filesystem.
     */
    FILE,
    /**
     * Stores and serves packages from an S3 bucket. Bucket URL and properties are stored in config.json.
     */
    S3,
    /**
     * A custom plugin will handle package storage and serving logic by returning an
     * IPackageProvider in its plugin main class.
     * Only once(1) plugin may be a provider. This plugin is specified in config.json.
     */
    PLUGIN
}
