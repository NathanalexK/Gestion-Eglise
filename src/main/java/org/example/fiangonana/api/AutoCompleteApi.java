package org.example.fiangonana.api;

import org.example.fiangonana.service.MvtCaisseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autocomplete")
@CrossOrigin("*")
public class AutoCompleteApi {


    private final MvtCaisseService mvtCaisseService;

    public AutoCompleteApi(MvtCaisseService mvtCaisseService) {
        this.mvtCaisseService = mvtCaisseService;
    }

    @GetMapping("/libelle/{key}")
    public List<String> autoCompleteLibelle(@PathVariable(name = "key", required = false, value = "") String motCle){
        return mvtCaisseService.getLibelles(motCle);
    }
}
