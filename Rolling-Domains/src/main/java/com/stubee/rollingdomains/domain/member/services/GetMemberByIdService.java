package com.stubee.rollingdomains.domain.member.services;

import com.stubee.rollingdomains.domain.member.model.Member;

import java.util.UUID;

public interface GetMemberByIdService {

    Member getById(UUID id);

}