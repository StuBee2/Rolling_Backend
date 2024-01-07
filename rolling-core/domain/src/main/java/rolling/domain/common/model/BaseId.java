package rolling.domain.common.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import rolling.domain.common.error.exception.NotMatchedIdException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public abstract class BaseId {

    private final Long id;

    public void isEqual(final BaseId baseId) {
        if(!this.equals(baseId)) {
            throw NotMatchedIdException.EXCEPTION;
        }
    }

}