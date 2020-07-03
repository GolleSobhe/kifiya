package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Beneficiaire;
import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void ajouter(Client client) {
        /**
         if(utilisateur.getPassword() != null){
         utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
         }
         */

        clientRepository.save(client);
    }

    public Client findUtilisateurByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Utilisateur findByConfirmationToken(String confirmationToken) {
        return clientRepository.findByConfirmationToken(confirmationToken);
    }

    public List<Beneficiaire> getBeneficiaires() {
        return clientRepository.getBeneficiaires();
    }
}
