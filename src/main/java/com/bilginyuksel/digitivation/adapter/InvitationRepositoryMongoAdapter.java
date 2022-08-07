package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.invitation.InvitationRepository;
import com.bilginyuksel.digitivation.invitation.model.Invitation;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InvitationRepositoryMongoAdapter implements InvitationRepository {
    private MongoTemplate mongoTemplate;

    @Override
    public String save(Invitation i) {
        var invitation = mongoTemplate.save(i);
        return invitation.getId();
    }

    @Override
    public Invitation find(String id) {
        var nullableInvitation = mongoTemplate.findById(new ObjectId(id), Invitation.class);
        return Optional.ofNullable(nullableInvitation).orElseThrow();
    }
}
