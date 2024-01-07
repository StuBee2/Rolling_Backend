package rolling.security.oauth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberId;

@Component
class MemberSecurityContextAdapter implements MemberSessionPort {

    @Override
    public Member current() {
        return getMemberDetails().getMember();
    }

    @Override
    public MemberId currentId() {
        return getMemberDetails().getMemberId();
    }

    private MemberDetailsAdapter getMemberDetails() {
        return ((MemberDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}