package com.bilginyuksel.digitivation.invitation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class Invitation {
    private String id;
    private PersonToMarry bride;
    private PersonToMarry groom;
    private Event marriage;
    private Event wedding;
    private Event hennaNight;
    private List<InvitationFile> files;
    private Status status;
    private boolean paid;
    private double price;
    private double paidPrice;

    public void addFile(InvitationFile file) {
        if (files == null) files = new ArrayList<>();
        files.add(file);
    }
}
