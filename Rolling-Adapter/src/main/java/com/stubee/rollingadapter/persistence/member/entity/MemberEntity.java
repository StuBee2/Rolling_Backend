package com.stubee.rollingadapter.persistence.member.entity;

import com.stubee.rollingadapter.common.entity.BaseEntity;
import com.stubee.rollingcore.domain.member.enums.LoginType;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_member")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Column(unique = true)
    private String nickName;

    @NotNull
    @Column(unique = true)
    private String socialId;

    private String name;

    private String email;

    private String imageUrl;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;

}