package com.stubee.applicationcommons.ports;

import java.util.UUID;

public interface CheckCompanyExistencePort {

    boolean check(UUID companyId);

}