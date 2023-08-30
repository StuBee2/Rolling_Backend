package com.stubee.companyapplication.outports;

import java.util.UUID;

public interface CheckCompanyExistencePort {

    boolean check(UUID companyId);

}