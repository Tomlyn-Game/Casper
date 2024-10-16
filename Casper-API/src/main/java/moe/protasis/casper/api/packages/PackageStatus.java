package moe.protasis.casper.api.packages;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PackageStatus {
    WAIT,
    STAGING,
    UNDER_REVIEW,
    STANDBY,
    PUBLISHED,
    PARKED,
    PARKED_LOCKED,
    REJECTED_LOCKED,
    ABNORMAL,
    ABNORMAL_REUPLOAD
}
