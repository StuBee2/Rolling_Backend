package rolling.jpamysql.member;

import org.springframework.data.jpa.repository.JpaRepository;
import rolling.domain.member.consts.LoginType;

import java.util.Optional;

interface MemberJpaRepository extends JpaRepository<MemberJPAEntity, Long> {

    boolean existsByNickName(String nickname);

    Optional<MemberJPAEntity> findBySocialIdAndLoginType(String socialId, LoginType loginType);

}