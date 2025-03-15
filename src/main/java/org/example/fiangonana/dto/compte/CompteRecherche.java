package org.example.fiangonana.dto.compte;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.model.CategorieCompte;
import org.example.fiangonana.model.Code;
import org.example.fiangonana.model.TypeCompte;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompteRecherche {
    private String code;
    private String libelle;
    private CategorieCompte  categorie;
    private TypeCompte type;

    private List<Code> codes = new ArrayList<>();
}
