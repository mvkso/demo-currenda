package com.example.demo.partyData;

import com.example.demo.addressData.Address;
import com.example.demo.addressData.AddressRepository;
import com.example.demo.caseData.CaseEntity;
import com.example.demo.caseData.CaseRepository;
import com.example.demo.caseData.CaseState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class PartyService {
    private final PartyRepository partyRepository;
    private final CaseRepository caseRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository, CaseRepository caseRepository, AddressRepository addressRepository) {
        this.partyRepository = partyRepository;
        this.caseRepository = caseRepository;
        this.addressRepository = addressRepository;
    }

    public PartyEntity addParty(PartyEntity party) {
        return partyRepository.save(party);
    }

    public void addAll(List<PartyEntity> partyEntityList) {
        for (PartyEntity partyEntity : partyEntityList) {
            partyRepository.save(partyEntity);
            for (Address address : partyEntity.getAddresses()) {
                address.setPartyEntity(partyEntity);
                addressRepository.save(address);
            }
        }
    }

    public Optional<PartyEntity> getPartyById(Long id) {
        return partyRepository.findById(id);
    }

    public Optional<PartyEntity> getPartyByName(String name) {
        return partyRepository.findByName(name);
    }

    public List<PartyEntity> getAllParties() {
        return (List<PartyEntity>) partyRepository.findAll();
    }

    public PartyEntity updateParty(Long id, PartyEntity updatedParty) {
        PartyEntity originalParty = partyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Party with id: " + id + " not found"));

        if (updatedParty.getName() != null && !updatedParty.getName().isEmpty()) {
            originalParty.setName(updatedParty.getName());
        }

        if (updatedParty.getPartyType() != null) {
            originalParty.setPartyType(updatedParty.getPartyType());
        }

        if (updatedParty.getAddresses() != null && !updatedParty.getAddresses().isEmpty()) {
            originalParty.setAddresses(updatedParty.getAddresses());
        }
        return partyRepository.save(originalParty);
    }

    public void deleteParty(Long id) {
        partyRepository.deleteById(id);
    }

    public List<Address> getAddressesFromActivePartiesByCaseState(CaseState caseState) {
        List<PartyEntity> activeParties = partyRepository.findAllByActive(true);

        Set<Long> caseIds = activeParties.stream()
                .map(PartyEntity::getCaseId)
                .collect(Collectors.toSet());

        List<CaseEntity> properCases = caseRepository.findAllByCaseIdInAndCaseState(caseIds, caseState);

        return activeParties.stream()
                .filter(p -> properCases.stream()
                        .anyMatch(c -> c.getCaseId().equals(p.getCaseId())))
                .flatMap(p -> p.getAddresses().stream())
                .collect(Collectors.toList());
    }


}
