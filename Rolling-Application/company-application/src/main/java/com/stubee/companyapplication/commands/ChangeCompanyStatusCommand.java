package com.stubee.companyapplication.commands;

import java.util.UUID;

public record ChangeCompanyStatusCommand(
        UUID companyId,
        boolean isAccepted) {}