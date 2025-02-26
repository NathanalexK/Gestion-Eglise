package org.example.fiangonana.service;

import org.example.fiangonana.model.Historique;
import org.example.fiangonana.repository.HistoriqueRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueService {

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueService(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    public Historique enregistrerHistorique(Historique h) {
        historiqueRepository.save(h);
        System.out.println("Enregistrement historique effectué avec succés");
        return h;
    }
}
