package com.stubee.authapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.authapplication.usecases.CertifyAlumniUseCase;
import com.stubee.rollingdomains.domain.auth.services.CertifyAlumniService;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CertifyAlumniApi implements CertifyAlumniUseCase {

    private final CertifyAlumniService certifyAlumniService;

    @Override
    public void certify(final String housemaster) {
        certifyAlumniService.certify(housemaster);
    }

}