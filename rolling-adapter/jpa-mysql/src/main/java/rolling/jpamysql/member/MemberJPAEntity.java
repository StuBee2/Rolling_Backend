package rolling.jpamysql.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import rolling.domain.member.consts.LoginType;
import rolling.domain.member.consts.MemberRole;
import rolling.jpamysql.common.entity.base.BaseTSIDEntity;

@Entity
@Table(name = "tbl_member")
@Getter
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJPAEntity extends BaseTSIDEntity {

    @Column(unique = true)
    private String nickName;

    @NotNull
    @Size(max = 64)
    private String socialId;

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