package com.example.demo.caseData;

import com.example.demo.partyData.PartyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "case_entities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "case_number")
    private String caseNumber;

    @Column(name = "case_type")
    @Enumerated(EnumType.STRING)
    private CaseType caseType;

    @Column(name = "case_state")
    @Enumerated(EnumType.STRING)
    private CaseState caseState;

    @Column(name = "date_of_entry")
    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;

}
