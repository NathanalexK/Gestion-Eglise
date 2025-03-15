package org.example.fiangonana.service;

import org.example.fiangonana.model.GroupeCompteRecap;
import org.example.fiangonana.repository.GroupeCompteRecapRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeCompteService {

    private final GroupeCompteRecapRepository groupeCompteRecapRepository;

    public GroupeCompteService(GroupeCompteRecapRepository groupeCompteRecapRepository) {
        this.groupeCompteRecapRepository = groupeCompteRecapRepository;
    }

    public GroupeCompteRecap enregistrer(GroupeCompteRecap g){
        return groupeCompteRecapRepository.save(g);
    }

    public List<GroupeCompteRecap> getAllGroupesCompteRecaps() {
        return groupeCompteRecapRepository.findAll();
    }
}
