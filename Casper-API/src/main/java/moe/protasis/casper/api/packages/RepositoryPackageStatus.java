package moe.protasis.casper.api.packages;

public enum RepositoryPackageStatus {
    /**
     * The package has been registered in this repository, but the repository does not have the package file.
     */
    REGISTERED,
    /**
     * The package file has been received by the repository.
     */
    RECEIVED,
    /**
     * The package is not registered in this repository.
     */
    NOT_FOUND
}
