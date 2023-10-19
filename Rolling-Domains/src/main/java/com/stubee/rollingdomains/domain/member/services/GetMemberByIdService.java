package com.stubee.rollingdomains.domain.member.services;

import com.stubee.rollingdomains.domain.member.model.Member;

public interface GetMemberByIdService {

    Member getById(Long id);

}