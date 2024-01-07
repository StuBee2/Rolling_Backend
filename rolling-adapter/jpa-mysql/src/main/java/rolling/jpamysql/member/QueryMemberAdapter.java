package rolling.jpamysql.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.member.outport.QueryMemberPort;
import rolling.domain.member.model.Member;

import java.util.Optional;

import static rolling.jpamysql.member.MemberMapper.*;

@Component
@RequiredArgsConstructor
class QueryMemberAdapter implements QueryMemberPort {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findBy(final Long memberId) {
        return Optional.ofNullable(toDomain(memberJpaRepository.findById(memberId).orElse(null)));
    }

    @Override
    public boolean existsBy(final String nickname) {
        return memberJpaRepository.existsByNickName(nickname);
    }

}