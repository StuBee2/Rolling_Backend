package rolling.domain.common.error;

public record ErrorResponse(
        int status,
        String message) {
    public static ErrorResponse of(final ErrorCode error) {
        return new ErrorResponse(error.getStatusValue(), error.getMessage());
    }

    public static ErrorResponse of(final int status, final String message) {
        return new ErrorResponse(status, message);
    }
}