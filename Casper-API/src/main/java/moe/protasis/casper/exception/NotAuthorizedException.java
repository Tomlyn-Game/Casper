package moe.protasis.casper.exception;

public class NotAuthorizedException extends APIException {
    public NotAuthorizedException() {
        super(401, 16, "permission denied");
    }
}
