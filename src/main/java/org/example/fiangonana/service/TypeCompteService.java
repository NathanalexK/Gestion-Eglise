package org.example.fiangonana.service;

import org.example.fiangonana.model.TypeCompte;
import org.example.fiangonana.repository.TypeCompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCompteService {

    private final TypeCompteRepository typeCompteRepository;

    public TypeCompteService(TypeCompteRepository typeCompteRepository) {
        this.typeCompteRepository = typeCompteRepository;
    }

    public List<TypeCompte> getAllTypeComptes() {
        return typeCompteRepository.findAll();
    }
}
