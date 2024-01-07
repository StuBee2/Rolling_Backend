package rolling.domain.company.model;

import rolling.domain.common.model.BaseId;

public class CompanyId extends BaseId {

    private CompanyId(Long id) {
        super(id);
    }

    public static CompanyId of(final Long id) {
        return new CompanyId(id);
    }

}