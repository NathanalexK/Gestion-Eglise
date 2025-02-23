package org.example.fiangonana.service;

import jakarta.servlet.http.HttpSession;
import org.example.fiangonana.exception.NoUserLoggedException;
import org.example.fiangonana.model.Utilisateur;
import org.example.fiangonana.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final HttpSession httpSession;

    public AuthService(UtilisateurRepository utilisateurRepository, HttpSession httpSession) {
        this.utilisateurRepository = utilisateurRepository;
        this.httpSession = httpSession;
    }

    public Utilisateur authentifier(String identifiant, String motDePasse) {
        return utilisateurRepository.findByIdentifiantAndMotDePasse(identifiant, motDePasse).orElse(null);
    }

    public Utilisateur utilisateurObligatoire() throws NoUserLoggedException {
        Utilisateur u = (Utilisateur) httpSession.getAttribute("u");
        if(u == null) throw new NoUserLoggedException();
        return u;
    }
}
