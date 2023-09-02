package com.example.demo.partyData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyRepository extends CrudRepository<PartyEntity, Long> {
    List<PartyEntity> findAllByActive(boolean isActive);

    Optional<PartyEntity> findByName(String name);
}
