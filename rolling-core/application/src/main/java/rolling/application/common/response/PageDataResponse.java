package rolling.application.common.response;

public record PageDataResponse<T> (
        T data) {
    public static <T> PageDataResponse <T> of(final T data) {
        return new PageDataResponse<>(data);
    }
}