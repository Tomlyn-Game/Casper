package moe.protasis.casper.exception.api;

public class ForbiddenException extends APIException {
    public ForbiddenException() {
        super(403, 16, "operation not permitted");
    }
}
