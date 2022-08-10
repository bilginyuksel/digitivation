package com.bilginyuksel.digitivation.port;

import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.bilginyuksel.digitivation.invitation.model.InvitationFile;
import com.bilginyuksel.digitivation.invitation.model.UploadInvitationFiles;
import com.bilginyuksel.digitivation.port.request.InvitationRequest;
import com.bilginyuksel.digitivation.port.response.InvitationFileResponse;
import com.bilginyuksel.digitivation.port.response.InvitationResponse;
import com.bilginyuksel.digitivation.BusinessUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invitations")
@AllArgsConstructor
public class InvitationHttpPort {
    private BusinessUseCase<Invitation, Invitation> useCaseCreateInvitation;
    private BusinessUseCase<String, Invitation> useCaseGetInvitation;
    private BusinessUseCase<UploadInvitationFiles, List<InvitationFile>> useCaseUploadInvitation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InvitationResponse> createWeddingInvitation(@RequestBody InvitationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(InvitationResponse.from(useCaseCreateInvitation.handle(request.toInvitation())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvitationResponse> getWeddingInvitation(@PathVariable String id) {
        return ResponseEntity.ok(InvitationResponse.from(useCaseGetInvitation.handle(id)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<InvitationFileResponse>> uploadInvitationFiles(@PathVariable String id, @RequestParam List<MultipartFile> files) throws IOException {
        var uploadInvitationFiles = UploadInvitationFiles.create()
                .invitationId(id)
                .files(mapMultipartFiles(files))
                .build();
        var response = useCaseUploadInvitation.handle(uploadInvitationFiles)
                .stream()
                .map(InvitationFileResponse::from)
                .toList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
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
