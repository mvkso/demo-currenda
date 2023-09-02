package com.example.demo;

import com.example.demo.caseData.CaseEntity;
import com.example.demo.caseData.CaseFacade;
import com.example.demo.partyData.PartyEntity;
import com.example.demo.partyData.PartyFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PartyFacade partyFacade, CaseFacade caseFacade) {
        return args -> {
            ObjectMapper caseMapper = new ObjectMapper();
            TypeReference<List<CaseEntity>> caseTypeReference = new TypeReference<>() {
            };
            InputStream caseInputStream = TypeReference.class.getResourceAsStream("/CaseEntity.json");
            try {
                List<CaseEntity> caseEntities = caseMapper.readValue(caseInputStream, caseTypeReference);
                caseFacade.addAll(caseEntities);
                System.out.println("CaseEntities Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save CaseEntities: " + e.getMessage());
            }

            ObjectMapper partyMapper = new ObjectMapper();
            TypeReference<List<PartyEntity>> partyTypeReference = new TypeReference<>() {
            };
            InputStream partyInputStream = TypeReference.class.getResourceAsStream("/PartyEntity.json");
            try {
                List<PartyEntity> partyEntities = partyMapper.readValue(partyInputStream, partyTypeReference);
                partyFacade.addAll(partyEntities);
                System.out.println("PartyEntities Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save PartyEntities: " + e.getMessage());
            }

        };
    }

}
