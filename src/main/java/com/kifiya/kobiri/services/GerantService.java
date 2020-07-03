package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.repositories.GerantRepository;
import com.kifiya.kobiri.repositories.TransfertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GerantService {

    private static final String DEFAULT_PASSWORD = "kobiri";

    private final GerantRepository gerantRepository;

    private final TransfertRepository transfertRepository;

    public GerantService(GerantRepository gerantRepository,TransfertRepository transfertRepository) {
        this.gerantRepository = gerantRepository;
        this.transfertRepository = transfertRepository;
    }

    public void validerTransfert(Transfert transfert) {
        /**
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) authentication.getPrincipal();
        Gerant gerant= new Gerant();
        gerant.setId(utilisateur.getId());
        transfert.setGerant(gerant);
         */
        transfertRepository.rendreTransfert(transfert.getCode(),"gerantId");
    }

    public List<Transfert> rechercherTransfert(String code) {
        Long id = (long) 1;
        return gerantRepository.rechercherTransfert(id);
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
