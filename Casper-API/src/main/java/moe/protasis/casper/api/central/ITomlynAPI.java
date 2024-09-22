package moe.protasis.casper.api.central;

import moe.protasis.casper.util.JsonWrapper;

public interface ITomlynAPI {
    JsonWrapper GetPackageManifest(String packageId);
}
