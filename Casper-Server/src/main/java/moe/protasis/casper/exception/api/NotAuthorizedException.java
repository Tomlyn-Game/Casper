package moe.protasis.casper.exception.api;

public class NotAuthorizedException extends APIException {
    public NotAuthorizedException() {
        super(401, 16, "permission denied");
    }
}
