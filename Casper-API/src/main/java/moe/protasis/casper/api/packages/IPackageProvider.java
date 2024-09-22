package moe.protasis.casper.api.packages;

import java.io.IOException;

public interface IPackageProvider {
    RepositoryPackageStatus GetPackageStatus(String id);
    PackageManifest GetPackageDescriptor(String id);
    void UpdatePackageFile(String id, byte[] data) throws IOException;
}
