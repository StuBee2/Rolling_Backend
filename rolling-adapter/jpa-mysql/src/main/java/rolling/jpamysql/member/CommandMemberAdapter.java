package rolling.jpamysql.member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import rolling.application.member.outport.CommandMemberPort;
import rolling.domain.member.events.MemberRegisteredEvent;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberProfile;

import static rolling.jpamysql.member.MemberMapper.*;

@Component
@RequiredArgsConstructor
class CommandMemberAdapter implements CommandMemberPort {

    private final MemberJpaRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Member saveWithId(final Member member) {
        return save(toEntityWithId(member));
    }

    @Override
    public Member saveExceptId(final Member member) {
        return save(toEntity(member));
    }

    @Override
    public Member saveOrUpdate(final MemberProfile memberProfile) {
        final Member member = repository.findBySocialIdAndLoginType(memberProfile.socialId(),
                        memberProfile.loginType())
                .map(MemberMapper::toDomain)
                .orElse(null);

        if(member == null) {
            publishMemberRegisteredEvent(memberProfile.email());

            return saveExceptId(memberProfile.toMember());
        } else {
            member.modifyLoginId(memberProfile.socialLoginId(), memberProfile.loginType());

            return saveWithId(member);
        }
    }

    private Member save(final MemberJPAEntity memberEntity) {
        return toDomain(repository.save(memberEntity));
    }

    private void publishMemberRegisteredEvent(final String memberEmail) {
        if(memberEmail!=null) {
            applicationEventPublisher.publishEvent(MemberRegisteredEvent.of(memberEmail));
        }
    }

}