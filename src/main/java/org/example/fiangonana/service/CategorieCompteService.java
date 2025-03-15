package org.example.fiangonana.service;

import org.example.fiangonana.model.CategorieCompte;
import org.example.fiangonana.repository.CategorieCompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieCompteService
{

    private final CategorieCompteRepository categorieCompteRepository;

    public CategorieCompteService(CategorieCompteRepository categorieCompteRepository)
    {
        this.categorieCompteRepository = categorieCompteRepository;
    }

    public List<CategorieCompte> getAllCategorieComptes()
    {
        return categorieCompteRepository.findAll();
    }

    public CategorieCompte enregistrer(CategorieCompte cc)
    {
        return categorieCompteRepository.save(cc);
    }
}
