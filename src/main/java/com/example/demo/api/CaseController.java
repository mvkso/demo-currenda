package com.example.demo.api;

import com.example.demo.caseData.CaseEntity;
import com.example.demo.caseData.CaseFacade;
import com.example.demo.caseData.CaseState;
import com.example.demo.caseData.CaseType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/case")
public class CaseController {
    private final CaseFacade caseFacade;

    @Autowired
    public CaseController(CaseFacade caseFacade) {
        this.caseFacade = caseFacade;
    }

    @PostMapping
    public ResponseEntity<?> createCase(@Valid @RequestBody CaseEntity caseEntity) {
        CaseEntity savedCase = caseFacade.addCase(caseEntity);
        return new ResponseEntity<>(savedCase, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCases() {
        List<CaseEntity> allCases = caseFacade.getAll();
        return new ResponseEntity<>(allCases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCaseById(@PathVariable long id) {
        Optional<CaseEntity> caseEntity = caseFacade.getCaseById(id);
        return new ResponseEntity<>(caseEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaseById(@PathVariable long id) {
        caseFacade.deleteCase(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCase(@PathVariable long id, @Valid @RequestBody CaseEntity updatedCase) {
        CaseEntity resultCase = caseFacade.updateCase(id, updatedCase);
        return new ResponseEntity<>(resultCase, HttpStatus.OK);
    }

    @GetMapping("/group")
    public ResponseEntity<Map<CaseState, List<CaseEntity>>> groupCasesByStateAndDateRange(
            @RequestParam("caseType") CaseType caseType,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        Map<CaseState, List<CaseEntity>> resultMap =
                caseFacade.groupCasesByStateAndDateRange(caseType, startDate, endDate);

        if (resultMap.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resultMap);
        }
    }


}
