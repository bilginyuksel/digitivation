package com.bilginyuksel.digitivation.infra.port.response;

public record PersonResponse(
        String name,
        String surname,
        String fatherName,
        String fatherSurname,
        String motherName,
        String motherSurname
) {
}
