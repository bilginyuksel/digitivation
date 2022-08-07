package com.bilginyuksel.digitivation.invitation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class PersonToMarry {
    private String name;
    private String surname;
    private String fatherName;
    private String fatherSurname;
    private String motherName;
    private String motherSurname;
}
