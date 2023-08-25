package com.stubee.applicationcommons.dtos.request;

public record PageRequest(
        Long page,
        Long size) {}