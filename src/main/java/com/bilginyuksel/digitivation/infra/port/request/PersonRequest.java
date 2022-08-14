package com.bilginyuksel.digitivation.infra.port.request;

import com.bilginyuksel.digitivation.invitation.model.PersonToMarry;

public record PersonRequest(
        String name,
        String surname,
        String fatherName,
        String fatherSurname,
        String motherName,
        String motherSurname
) {
    public PersonToMarry toPersonToMarry() {
        return PersonToMarry.create()
                .name(name)
                .surname(surname)
                .fatherName(fatherName)
                .fatherSurname(fatherSurname)
                .motherName(motherName)
                .motherSurname(motherSurname)
                .build();
    }
}
