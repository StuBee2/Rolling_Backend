package com.stubee.companyapplication.outports.query;

import java.util.UUID;

public interface CheckCompanyExistencePort {

    boolean check(UUID companyId);

}