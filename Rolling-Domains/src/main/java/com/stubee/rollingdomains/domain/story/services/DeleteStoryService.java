package com.stubee.rollingdomains.domain.story.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.services.commands.DeleteStoryCommand;

public interface DeleteStoryService {

    void delete(DeleteStoryCommand command, MemberId memberId);

}