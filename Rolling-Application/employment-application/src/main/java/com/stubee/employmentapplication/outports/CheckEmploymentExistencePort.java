package com.stubee.employmentapplication.outports;

import java.util.UUID;

public interface CheckEmploymentExistencePort {

    boolean check(UUID employeeId, UUID employerId);

}