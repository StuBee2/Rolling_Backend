package com.stubee.rollingdomains.domain.story.services;

import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.model.Story;
import com.stubee.rollingdomains.domain.story.services.commands.RegisterStoryCommand;

public interface WriteStoryService {

    Story write(RegisterStoryCommand command, MemberId memberId);

}