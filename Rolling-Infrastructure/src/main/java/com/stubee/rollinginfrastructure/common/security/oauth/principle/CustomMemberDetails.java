package com.stubee.rollinginfrastructure.common.security.oauth.principle;

import com.stubee.rollingcore.domain.member.model.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class CustomMemberDetails implements UserDetails, OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;

    private CustomMemberDetails(final Member member, final Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    public CustomMemberDetails(final Member member) {
        this.member = member;
    }

    public static CustomMemberDetails create(Member member, Map<String, Object> attributes) {
        return new CustomMemberDetails(member, attributes);
    }

    @Override
    public String getName() {
        return member.id().toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) member.memberRole()::getKey);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.id().toString();
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