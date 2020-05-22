package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.GerantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GerantService {

    private static final String DEFAULT_PASSWORD = "kobiri";

    @Autowired
    private GerantRepository gerantRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    /**
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder
    */
    public Utilisateur ajouter(Utilisateur gerant){
        //gerant.setPassword(bCryptPasswordEncoder.encode(DEFAULT_PASSWORD));
        gerant = utilisateurService.ajouter(gerant);
        return gerant;
    }

    public List<Transfert> trouverTransfertParStatus() {
        return gerantRepository.trouverTransfertParStatus();
    }


    public List<Transfert> rechercherTransfert(String code) {
        if(code != null)
            return  gerantRepository.rechercherTransfert(code);
        return gerantRepository.trouverTransfertParStatus();
    }

    public void validerTransfert(Transfert transfert) {
        /**
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        Gerant gerant= new Gerant();
        gerant.setId(utilisateur.getId());
        transfert.setGerant(gerant);
         */
        transfert.setDateValidation(new Date());
        gerantRepository.validerTransfert(transfert);
    }

    public int nombreDeGerants() {
        return 0;
    }

    public List<Transfert> rechercherTransfert() {
        //recuperer le gerant connecter
        /**
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
         */
        Long id = (long) 1;
        return gerantRepository.rechercherTransfert(id);
    }

    public Map<String, String> obtenirStatistique() {
        //recuperer le gerant connecter
        /**
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
         */
        Long id = (long) 1;
        return gerantRepository.obtenirStatistique(id);
    }
}
