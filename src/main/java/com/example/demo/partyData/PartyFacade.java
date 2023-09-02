package com.example.demo.partyData;

import com.example.demo.addressData.Address;
import com.example.demo.caseData.CaseState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyFacade {

    private final PartyService partyService;

    @Autowired
    public PartyFacade(PartyService partyService) {
        this.partyService = partyService;
    }

    public PartyEntity addParty(PartyEntity partyEntity) {
        return partyService.addParty(partyEntity);
    }

    public void addAll(List<PartyEntity> partyEntityList) {
        partyService.addAll(partyEntityList);
    }


    public Optional<PartyEntity> getPartyById(Long id) {
        return partyService.getPartyById(id);
    }

    public Optional<PartyEntity> getPartyByName(String name) {
        return partyService.getPartyByName(name);
    }

    public List<PartyEntity> getAllParties() {
        return partyService.getAllParties();
    }

    public PartyEntity updateParty(Long id, PartyEntity updatedParty) {
        return partyService.updateParty(id, updatedParty);
    }

    public void deleteParty(Long id) {
        partyService.deleteParty(id);
    }

    public List<Address> getAddressesFromActivePartiesByCaseState(CaseState caseState) {
        return partyService.getAddressesFromActivePartiesByCaseState(caseState);
    }
}
