package com.stubee.rollingexternal.persistence.domain.member.entity;

import com.stubee.rollingexternal.persistence.common.entity.BaseEntity;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "tbl_member")
@Getter
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Column(unique = true)
    private String nickName;

    @NotNull
    private Integer socialId;

    @NotNull
    private String socialLoginId;

    private String name;

    @Column(unique = true)
    private String email;

    private String imageUrl;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;

}