package rolling.rollingapi.auth;

import jakarta.validation.constraints.NotBlank;

record CertifyAlumniRequest(@NotBlank String housemaster) {}