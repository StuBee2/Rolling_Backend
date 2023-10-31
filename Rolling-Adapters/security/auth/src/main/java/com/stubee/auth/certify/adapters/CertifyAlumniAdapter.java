package com.stubee.auth.certify.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.auth.certify.exception.AlumniCertifyFailedException;
import com.stubee.auth.certify.properties.CertifyProperties;
import com.stubee.authapplication.outports.CertifyAlumniPort;
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