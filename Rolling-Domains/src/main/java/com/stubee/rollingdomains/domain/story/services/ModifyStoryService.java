package com.stubee.rollingdomains.domain.story.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.services.commands.ModifyStoryCommand;

public interface ModifyStoryService {

    void modify(ModifyStoryCommand command, MemberId memberId);

}