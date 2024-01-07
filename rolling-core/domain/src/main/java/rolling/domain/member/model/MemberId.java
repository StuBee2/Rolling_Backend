package rolling.domain.member.model;

import rolling.domain.common.model.BaseId;

public class MemberId extends BaseId {

    protected MemberId(Long id) {
        super(id);
    }

    public static MemberId of(final Long id) {
        return new MemberId(id);
    }

    public static MemberId of(final BaseId baseId) {
        return new MemberId(baseId.getId());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}