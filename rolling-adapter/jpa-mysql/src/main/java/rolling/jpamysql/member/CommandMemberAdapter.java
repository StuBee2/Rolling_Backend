package rolling.jpamysql.member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import rolling.application.member.outport.CommandMemberPort;
import rolling.domain.member.events.MemberRegisteredEvent;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberId;
import rolling.domain.member.model.MemberProfile;

import static rolling.jpamysql.member.MemberMapper.*;

@Component
@RequiredArgsConstructor
class CommandMemberAdapter implements CommandMemberPort {

    private final MemberJpaRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Member save(final Member member) {
        try {
            MemberId id = member.id();
        } catch (NullPointerException e) {
            publishMemberRegisteredEvent(member.details().email());

            return toDomain(repository.save(toEntity(member)));
        }
        return toDomain(repository.save(toEntityWithId(member)));
    }

    @Override
    public Member save(final MemberProfile memberProfile) {
        final Member member = repository.findBySocialIdAndLoginType(memberProfile.socialId(),
                        memberProfile.loginType())
                .map(MemberMapper::toDomain)
                .orElse(memberProfile.toMember());

        if(member.id() != null) {
            member.modify(memberProfile.socialLoginId(), memberProfile.loginType());
        }

        return save(member);
    }

    private void publishMemberRegisteredEvent(final String memberEmail) {
        if(memberEmail!=null) {
            applicationEventPublisher.publishEvent(MemberRegisteredEvent.of(memberEmail));
        }
    }

}