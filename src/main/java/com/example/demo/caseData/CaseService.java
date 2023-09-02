package com.example.demo.caseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
class CaseService {

    private final CaseRepository caseRepository;

    @Autowired
    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public CaseEntity addCase(CaseEntity caseEntity) {
        return caseRepository.save(caseEntity);
    }

    public void addAll(List<CaseEntity> caseEntities) {
        caseRepository.saveAll(caseEntities);
    }

    public List<CaseEntity> getAll() {
        List<CaseEntity> caseEntities = new ArrayList<>();
        caseRepository.findAll().forEach(caseEntities::add);
        return caseEntities;
    }

    public Optional<CaseEntity> getCaseById(Long id) {
        return caseRepository.findById(id);
    }

    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }

    public CaseEntity updateCase(Long id, CaseEntity updatedCase) {
        CaseEntity originalCase = caseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Case with id: " + id + " not found"));

        if (updatedCase.getCaseNumber() != null && !updatedCase.getCaseNumber().isEmpty()) {
            originalCase.setCaseNumber(updatedCase.getCaseNumber());
        }

        if (updatedCase.getCaseState() != null) {
            originalCase.setCaseState(updatedCase.getCaseState());
        }

        if (updatedCase.getCaseType() != null) {
            originalCase.setCaseType(updatedCase.getCaseType());
        }

        if (updatedCase.getDateOfEntry() != null) {
            originalCase.setDateOfEntry(updatedCase.getDateOfEntry());
        }
        return caseRepository.save(originalCase);
    }

    public Map<CaseState, List<CaseEntity>> groupCasesByStateAndDateRange(CaseType caseType, Date startDate, Date endDate) {
        List<CaseEntity> cases = caseRepository.findByCaseTypeAndDateOfEntryBetween(caseType, startDate, endDate);
        return cases.stream()
                .collect(Collectors.groupingBy(CaseEntity::getCaseState));

    }
}
