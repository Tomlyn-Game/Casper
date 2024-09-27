package moe.protasis.casper.api.permission;

import java.util.List;

/**
 * Interface for permission providers.
 * Implement this interface to provide custom permission handling.
 */
public interface IPermissionProvider {
    /**
     * Get the repository-scope permissions for an account.
     * @param accountId The account ID.
     * @return A list of permissions. If the account has no permissions or does not exist, return an empty list.
     * If all permissions are granted, return <code>null</code>.
     */
    List<String> GetAccountPermissions(String accountId);

    /**
     * Get the package-scope permissions for a specific package relative to an account.
     * @param accountId The account ID.
     * @param packageId The package ID.
     * @return A list of permissions. If the account and package combination has no permissions or does not exist, return an empty list.
     * If all permissions are granted, return <code>null</code>.
     */
    List<String> GetPackagePermissions(String accountId, String packageId);
}
