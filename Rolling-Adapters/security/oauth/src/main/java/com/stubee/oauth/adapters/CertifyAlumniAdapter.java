package com.stubee.oauth.adapters;

import com.stubee.authapplication.outports.CertifyAlumniPort;
import com.stubee.oauth.properties.CertifyProperties;
import com.stubee.oauth.exception.AlumniCertifyFailedException;
import com.stubee.securitycommons.annotations.Adapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adapter
@RequiredArgsConstructor
public class CertifyAlumniAdapter implements CertifyAlumniPort {

    private final CertifyProperties certifyProperties;

    @Override
    public void certify(final String housemaster) {
        final List<String> housemasterList = certifyProperties.getHousemasterList();

        if(!housemasterList.contains(housemaster)) {
            throw AlumniCertifyFailedException.EXCEPTION;
        }
    }

}