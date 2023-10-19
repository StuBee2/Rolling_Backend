package com.stubee.employmentapplication.outports;

public interface CheckEmploymentExistencePort {

    boolean check(Long employeeId, Long employerId);

}