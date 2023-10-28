package com.stubee.oauth.adapters;

import com.stubee.authapplication.outports.CertifyAlumniPort;
import com.stubee.oauth.properties.CertifyProperties;
import com.stubee.oauth.exception.AlumniCertifyFailedException;
import com.stubee.securitycommons.annotations.Adapter;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class CertifyAlumniAdapter implements CertifyAlumniPort {

    private final CertifyProperties certifyProperties;

    @Override
    public void certify(final String housemaster) {
        if(!certifyProperties.getHousemasterList().contains(housemaster)) {
            throw AlumniCertifyFailedException.EXCEPTION;
        }
    }

}