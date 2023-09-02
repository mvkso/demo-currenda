package com.example.demo.caseData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CaseServiceTest {

    @InjectMocks
    private CaseService caseService;

    @Mock
    private CaseRepository caseRepository;

    private final CaseEntity dummyCaseEntity1 = new CaseEntity();
    private final CaseEntity dummyCaseEntity2 = new CaseEntity();
    private final CaseEntity dummyCaseEntity3 = new CaseEntity();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Calendar calendar = Calendar.getInstance();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        calendar.set(2022, Calendar.JULY, 1);
        dummyCaseEntity1.setCaseId(1L);
        dummyCaseEntity1.setCaseType(CaseType.C);
        dummyCaseEntity1.setCaseState(CaseState.OPEN);
        dummyCaseEntity1.setDateOfEntry(calendar.getTime());

        dummyCaseEntity2.setCaseId(2L);
        dummyCaseEntity2.setCaseType(CaseType.C);
        dummyCaseEntity2.setCaseState(CaseState.CLOSED);
        dummyCaseEntity2.setDateOfEntry(calendar.getTime());

        calendar.set(2020, Calendar.JANUARY, 1);
        dummyCaseEntity3.setCaseId(3L);
        dummyCaseEntity3.setCaseType(CaseType.C);
        dummyCaseEntity3.setCaseState(CaseState.CLOSED);
        dummyCaseEntity3.setDateOfEntry(calendar.getTime());
    }

    @Test
    public void testIfCasesAreGroupedByStateAndDateRangeWithOpenCases() {
        //given
        List<CaseEntity> cases = Arrays.asList(dummyCaseEntity1, dummyCaseEntity2, dummyCaseEntity3);
        calendar.set(2022, Calendar.JANUARY, 1);
        Date startDate = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date endDate = calendar.getTime();

        //when
        when(caseRepository.findByCaseTypeAndDateOfEntryBetween(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenAnswer(invocation -> {
                    CaseType caseType = invocation.getArgument(0);
                    Date startDateArgument = invocation.getArgument(1);
                    Date endDateArgument = invocation.getArgument(2);

                    return cases.stream()
                            .filter(c -> c.getCaseType() == caseType
                                    && c.getDateOfEntry().compareTo(startDateArgument) >= 0
                                    && c.getDateOfEntry().compareTo(endDateArgument) <= 0)
                            .collect(Collectors.toList());
                });

        //then
        Map<CaseState, List<CaseEntity>> resultMap =
                caseService.groupCasesByStateAndDateRange(CaseType.C, startDate, endDate);

        assertEquals(2, resultMap.size());
        assertTrue(resultMap.containsKey(CaseState.OPEN));
        assertTrue(resultMap.containsKey(CaseState.CLOSED));
        assertEquals(1, resultMap.get(CaseState.OPEN).size());
        assertEquals(1, resultMap.get(CaseState.CLOSED).size());
    }


}
