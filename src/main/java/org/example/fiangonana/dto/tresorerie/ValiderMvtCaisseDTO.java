package org.example.fiangonana.dto.tresorerie;

import lombok.Getter;
import lombok.Setter;
import org.example.fiangonana.model.MvtCaisse;

import java.util.List;

@Getter
@Setter
public class ValiderMvtCaisseDTO {
    private List<MvtCaisse> mvtCaisses;
}
