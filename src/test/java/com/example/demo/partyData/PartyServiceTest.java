package com.example.demo.partyData;

import com.example.demo.addressData.Address;
import com.example.demo.caseData.CaseEntity;
import com.example.demo.caseData.CaseRepository;
import com.example.demo.caseData.CaseState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class PartyServiceTest {

    @InjectMocks
    private PartyService partyService;

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private CaseRepository caseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIfGetAddressesFromActivePartiesByCaseStateReturnsAddressesForOpenCase() {
        //given
        CaseEntity dummyCaseWithOpenCase = new CaseEntity();
        dummyCaseWithOpenCase.setCaseId(1L);
        dummyCaseWithOpenCase.setCaseState(CaseState.OPEN);

        PartyEntity dummyParty = new PartyEntity();
        dummyParty.setCaseId(1L);
        dummyParty.setActive(true);
        dummyParty.setAddresses(Arrays.asList(new Address(), new Address()));

        PartyEntity dummyParty_2 = new PartyEntity();
        dummyParty_2.setCaseId(2L);
        dummyParty_2.setActive(true);
        dummyParty_2.setAddresses(Arrays.asList(new Address(), new Address()));

        //when
        when(partyRepository.findAllByActive(true)).thenReturn(Arrays.asList(dummyParty, dummyParty_2));
        when(caseRepository.findAllByCaseIdInAndCaseState(Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(dummyCaseWithOpenCase));

        //then
        List<Address> dummyAddressesList = partyService.getAddressesFromActivePartiesByCaseState(CaseState.OPEN);

        Assertions.assertNotNull(dummyAddressesList);
        Assertions.assertEquals(2, dummyAddressesList.size());
    }


}
