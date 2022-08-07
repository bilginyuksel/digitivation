package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.invitation.InvitationRepository;
import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class InvitationRepositoryMongoAdapter implements InvitationRepository {
    private MongoRepository<Invitation, ObjectId> mongoRepository;

    @Override
    public String save(Invitation i) {
        var invitation = mongoRepository.save(i);
        return invitation.getId();
    }

    @Override
    public Invitation find(String id) {
        return mongoRepository
                .findById(new ObjectId(id))
                .orElseThrow();
    }
}
