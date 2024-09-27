package moe.protasis.casper.api.central;

import moe.protasis.casper.util.JsonWrapper;

public interface ITomlynAPI {
    JsonWrapper GetPackageManifest(String packageId);
    boolean VerifyCOMMToken(String token);
    boolean VerifyCOMMToken(String token, String packageId);
}
