package com.stubee.applicationcommons.ports.company;

import java.util.UUID;

public interface CheckCompanyExistencePort {

    boolean check(UUID companyId);

}