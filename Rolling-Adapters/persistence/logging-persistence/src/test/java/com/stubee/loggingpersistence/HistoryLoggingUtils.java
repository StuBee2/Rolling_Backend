package com.stubee.loggingpersistence;

import com.stubee.rollingdomains.logging.model.HistoryLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public abstract class HistoryLoggingUtils {

    public static HistoryLogging create(String description, String module, MemberId memberId) {
        return HistoryLogging.ExceptIdBuilder()
                .description(description)
                .module(module)
                .memberId(memberId)
                .build();
    }

}