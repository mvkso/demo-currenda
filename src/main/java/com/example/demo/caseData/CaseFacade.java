package com.example.demo.caseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CaseFacade {

    private final CaseService caseService;

    @Autowired
    public CaseFacade(CaseService caseService) {
        this.caseService = caseService;
    }

    public CaseEntity addCase(CaseEntity caseEntity) {
        return caseService.addCase(caseEntity);
    }

    public void addAll(List<CaseEntity> entities) {
        caseService.addAll(entities);
    }

    public List<CaseEntity> getAll() {
        return caseService.getAll();
    }

    public Optional<CaseEntity> getCaseById(Long id) {
        return caseService.getCaseById(id);
    }

    public void deleteCase(Long id) {
        caseService.deleteCase(id);
    }

    public CaseEntity updateCase(Long id, CaseEntity updatedCase) {
        return caseService.updateCase(id, updatedCase);
    }

    public Map<CaseState, List<CaseEntity>> groupCasesByStateAndDateRange(CaseType caseType, Date startDate, Date endDate) {
        return caseService.groupCasesByStateAndDateRange(caseType, startDate, endDate);
    }
}
