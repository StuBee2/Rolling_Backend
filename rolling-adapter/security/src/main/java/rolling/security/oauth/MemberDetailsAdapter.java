package rolling.security.oauth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import rolling.domain.member.model.Member;
import rolling.domain.member.model.MemberId;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class MemberDetailsAdapter implements UserDetails, OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;

    private MemberDetailsAdapter(Member member) {
        this.member = member;
    }

    private MemberDetailsAdapter(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    public static MemberDetailsAdapter of(Member member, Map<String, Object> attributes) {
        return new MemberDetailsAdapter(member, attributes);
    }

    public static MemberDetailsAdapter of(Member member) {
        return new MemberDetailsAdapter(member);
    }

    public MemberId getMemberId() {
        return member.id();
    }

    @Override
    public String getName() {
        return member.id().getId().toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) member.role()::getKey);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.id().getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}