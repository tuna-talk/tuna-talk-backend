package shop.iamhyunjun.tunatalk.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestApiException {
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public RestApiException(ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus().value();
        this.code = errorCode.name();
        this.error = errorCode.getHttpStatus().name();
        this.message = errorCode.getErrorMessage();
    }
}
