package com.stubee.rollingadapter.persistence.member.repository;

import com.stubee.rollingadapter.persistence.member.entity.MemberEntity;
import com.stubee.rollingcore.domain.member.enums.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommandMemberJpaRepository extends JpaRepository<MemberEntity, UUID> {

    Optional<MemberEntity> findBySocialIdAndLoginType(String socialId, LoginType loginType);

}