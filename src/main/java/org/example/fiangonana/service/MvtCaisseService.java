package org.example.fiangonana.service;

import org.example.fiangonana.dto.tresorerie.MvtCaisseLigneDTO;
import org.example.fiangonana.model.Code;
import org.example.fiangonana.model.MvtCaisse;
import org.example.fiangonana.repository.CodeRepository;
import org.example.fiangonana.repository.MvtCaisseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MvtCaisseService {

    private final MvtCaisseRepository mvtCaisseRepository;
    private final JdbcTemplate jdbcTemplate;

    public MvtCaisseService(MvtCaisseRepository mvtCaisseRepository, JdbcTemplate jdbcTemplate) {
        this.mvtCaisseRepository = mvtCaisseRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


//    public List<MvtCaisse> filtrerEtAjouterDate(List<MvtCaisse> mvtCaisses, LocalDate date) {
//
//    }

    public List<MvtCaisse> enregistrerMvtCaisses(List<MvtCaisse> mvtCaisses) throws Exception {
//        if(mvtCaisses == null )
        return mvtCaisseRepository.saveAll(mvtCaisses);
    }

    public List<String> getLibelles(String motCle) {
        return jdbcTemplate.queryForList("SELECT distinct(libelle) as lib FROM mvt_caisse WHERE libelle ilike '%"+ motCle + "%'")
                .stream()
                .map((ligne) -> String.valueOf(ligne.get("lib")))
                .toList();
    }

    public List<MvtCaisseLigneDTO> getMvtCaissesEntre2Dates(LocalDate dmin, LocalDate dmax) {
        return mvtCaisseRepository.getMvtCaisseEntre2Dates(dmin, dmax);
    }
}
