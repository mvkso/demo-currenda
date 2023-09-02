package com.example.demo.caseData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface CaseRepository extends CrudRepository<CaseEntity, Long> {
    List<CaseEntity> findByCaseTypeAndDateOfEntryBetween(CaseType caseType, Date dateOfEntry, Date dateOfEntry2);
    List<CaseEntity> findAllByCaseIdInAndCaseState(Set<Long> caseIds, CaseState caseState);
}
