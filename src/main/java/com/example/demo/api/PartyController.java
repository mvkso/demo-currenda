package com.example.demo.api;

import com.example.demo.addressData.Address;
import com.example.demo.caseData.CaseState;
import com.example.demo.partyData.PartyEntity;
import com.example.demo.partyData.PartyFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/party")
public class PartyController {
    private final PartyFacade partyFacade;

    @Autowired
    public PartyController(PartyFacade partyFacade) {
        this.partyFacade = partyFacade;
    }

    @PostMapping
    public ResponseEntity<?> createParty(@Valid @RequestBody PartyEntity partyEntity) {
        PartyEntity savedParty = partyFacade.addParty(partyEntity);
        return new ResponseEntity<>(savedParty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllParties() {
        List<PartyEntity> allParties = partyFacade.getAllParties();
        return new ResponseEntity<>(allParties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPartyById(@PathVariable long id) {
        Optional<PartyEntity> partyEntity = partyFacade.getPartyById(id);
        return new ResponseEntity<>(partyEntity, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getPartyByName(@PathVariable String name) {
        Optional<PartyEntity> partyEntity = partyFacade.getPartyByName(name);
        return new ResponseEntity<>(partyEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartyById(@PathVariable long id) {
        partyFacade.deleteParty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParty(@PathVariable long id, @Valid @RequestBody PartyEntity updatedParty) {
        PartyEntity resultParty = partyFacade.updateParty(id, updatedParty);
        return new ResponseEntity<>(resultParty, HttpStatus.OK);
    }

    @GetMapping("/addresses")
    public ResponseEntity<?> getAddressesFromActivePartiesByCaseState(
            @RequestParam("caseState") CaseState caseState) {

        List<Address> resultAddressesList = partyFacade.getAddressesFromActivePartiesByCaseState(caseState);

        if (resultAddressesList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resultAddressesList);
        }
    }
}
