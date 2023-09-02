package com.example.demo.partyData;

import com.example.demo.addressData.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "party_entities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyEntity {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "party_type")
    @Enumerated(EnumType.STRING)
    private PartyType partyType;

    @Column(name = "active")
    private boolean active;

    @Column(name = "case_id")
    private Long caseId;

    @OneToMany(mappedBy = "partyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
}
