package com.stubee.rollingexternal.persistence.domain.member.repository;

import com.stubee.rollingdomains.domain.member.consts.LoginType;
import com.stubee.rollingexternal.persistence.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {

    Optional<MemberEntity> findBySocialIdAndLoginType(Integer socialId, LoginType loginType);

}