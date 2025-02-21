package org.example.fiangonana.service;

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
}
