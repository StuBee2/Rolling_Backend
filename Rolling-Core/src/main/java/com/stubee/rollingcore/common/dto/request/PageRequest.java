package com.stubee.rollingcore.common.dto.request;

public record PageRequest(
        Long page,
        Long size) {}