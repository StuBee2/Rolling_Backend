package com.stubee.memberpersistence.adapters;

import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findBySocialIdAndLoginType(String socialId, LoginType loginType);

    boolean existsByNickName(String nickname);

}