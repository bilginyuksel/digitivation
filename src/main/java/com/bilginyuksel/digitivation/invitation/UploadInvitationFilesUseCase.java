package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.invitation.model.InvitationFile;
import com.bilginyuksel.digitivation.invitation.model.UploadInvitationFiles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UploadInvitationFilesUseCase implements BusinessUseCase<UploadInvitationFiles, List<InvitationFile>> {
    private InvitationRepository repository;
    private InvitationFileStorage storage;

    @Override
    public List<InvitationFile> handle(UploadInvitationFiles uploadInvitationFiles) {
        var invitation = repository.find(uploadInvitationFiles.getInvitationId());

        uploadInvitationFiles.getFiles()
                .parallelStream()
                .forEach(f -> invitation.addFile(storage.upload(f)));

        return invitation.getFiles();
    }
}
