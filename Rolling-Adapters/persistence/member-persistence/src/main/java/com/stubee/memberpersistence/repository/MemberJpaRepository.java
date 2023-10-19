package com.stubee.memberpersistence.repository;

import com.stubee.persistencecommons.entity.MemberEntity;
import com.stubee.rollingdomains.domain.member.consts.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findBySocialIdAndLoginTypeOrEmail(String socialId, LoginType loginType, String email);

    boolean existsByNickName(String nickname);

}