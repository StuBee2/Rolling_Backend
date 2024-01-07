package rolling.application.member.outport;

import rolling.domain.member.exception.MemberNotFoundException;
import rolling.domain.member.model.Member;

import java.util.Optional;

public interface QueryMemberPort {

    Optional<Member> findBy(Long id);

    default Member getBy(final Long id) {
        return findBy(id)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

    boolean existsBy(String nickname);

}