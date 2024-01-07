package rolling.domain.company.model;

public record CompanyLogo(
        String url,
        Integer rgb
) {
    public static CompanyLogo of(String url, Integer rgb) {
        return new CompanyLogo(url, rgb);
    }
}