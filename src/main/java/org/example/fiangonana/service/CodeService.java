package org.example.fiangonana.service;

import org.example.fiangonana.dto.compte.CompteRecherche;
import org.example.fiangonana.exception.ExceptionList;
import org.example.fiangonana.model.Code;
import org.example.fiangonana.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public List<Code> getCodesEntrees() {
        return codeRepository.getCodesEntree();
    }

    public List<Code> getCodesSorties() {
        return codeRepository.getCodesSortie();
    }

    public void controller(Code code) throws ExceptionList {
        ExceptionList exception = new ExceptionList();
        if(code.getCode().length() < 3) {
            exception.addMessage("Le numero de compte doit contenir au moins 3 caractères");
        }

        if(code.getLibelle() == null || code.getLibelle().length() < 3) {
            exception.addMessage("Libelle doit contenir au mois 3 caractères");
        }

        if(code.getGroupeCompteRecap() == null) {
            exception.addMessage("Un compte doit avoir un groupe pour faire un rapport");
        }

        if(code.getId() == null) {
            Code _code = codeRepository.findByNumeroCompte(code.getCode());
            if (_code != null) {
                exception.addMessage(String.format("Le numero de compte %s est déjà utilisé pour le rubrique %s", code.getCode(), _code.getLibelle()));
            }
        }


        if(exception.containsMessages()) {
            throw exception;
        }
    }

    public List<Code> getAllCodes() {
        return codeRepository.findAllCodes();
    }

    public Code enregistrerCode(Code code) throws ExceptionList {
        this.controller(code);
        return codeRepository.save(code);
    }

    public CompteRecherche rechercher(CompteRecherche compteRecherche) {
        compteRecherche.setCodes(
                codeRepository.rechercher(
                        compteRecherche.getCode(),
                        compteRecherche.getLibelle(),
                        compteRecherche.getCategorie(),
                        compteRecherche.getType()
                )
        );
        return compteRecherche;
    }
}
