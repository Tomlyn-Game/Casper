package moe.protasis.casper.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class APIException extends RuntimeException {
    private int code;
    private int statusCode;
    private String message;

    public APIException(String message) {
        this(400, message);
    }

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;

        if (code >= 500) statusCode = 1;
        else {
            if (code == 403 || code == 401) statusCode = 16;
            else statusCode = 17;
        }
    }

}
