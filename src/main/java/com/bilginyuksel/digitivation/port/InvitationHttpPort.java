package com.bilginyuksel.digitivation.port;

import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.bilginyuksel.digitivation.invitation.model.InvitationFile;
import com.bilginyuksel.digitivation.invitation.model.UploadInvitationFiles;
import com.bilginyuksel.digitivation.port.request.InvitationRequest;
import com.bilginyuksel.digitivation.port.response.InvitationFileResponse;
import com.bilginyuksel.digitivation.port.response.InvitationResponse;
import com.bilginyuksel.digitivation.BusinessUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invitations")
@AllArgsConstructor
public class InvitationHttpPort {
    private BusinessUseCase<Invitation, Invitation> createInvitationUseCase;
    private BusinessUseCase<String, Invitation> getInvitationUseCase;
    private BusinessUseCase<UploadInvitationFiles, List<InvitationFile>> uploadInvitationUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvitationResponse createWeddingInvitation(@RequestBody InvitationRequest request) {
        return InvitationResponse.from(createInvitationUseCase.handle(request.toInvitation()));
    }

    @GetMapping("/{id}")
    public InvitationResponse getWeddingInvitation(@PathVariable String id) {
        return InvitationResponse.from(getInvitationUseCase.handle(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<InvitationFileResponse> uploadInvitationFiles(@PathVariable String id, @RequestParam List<MultipartFile> files) throws IOException {
        return uploadInvitationUseCase.handle(UploadInvitationFiles.create()
                        .invitationId(id)
                        .files(mapMultipartFiles(files))
                        .build())
                .stream()
                .map(InvitationFileResponse::from)
                .toList();
    }

    public List<InvitationFile> mapMultipartFiles(List<MultipartFile> files) throws IOException {
        var invitationFiles = new ArrayList<InvitationFile>();
        for (MultipartFile file : files) {
            invitationFiles.add(InvitationFile.create()
                    .size(file.getSize())
                    .content(file.getBytes())
                    .contentType(file.getContentType())
                    .filename(file.getOriginalFilename())
                    .build()
            );
        }

        return invitationFiles;
    }
}
