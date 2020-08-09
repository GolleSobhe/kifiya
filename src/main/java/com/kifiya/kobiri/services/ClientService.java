package com.kifiya.kobiri.services;

import com.kifiya.kobiri.exception.ExpiryTokenException;
import com.kifiya.kobiri.exception.InvalidTokenException;
import com.kifiya.kobiri.models.Beneficiaire;
import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.VerificationToken;
import com.kifiya.kobiri.repositories.BeneficiaireRepository;
import com.kifiya.kobiri.repositories.ClientRepository;
import com.kifiya.kobiri.repositories.VerificationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private final BeneficiaireRepository beneficiaireRepository;

    private final EmailService emailService;

    private final ClientRepository clientRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    public ClientService(ClientRepository clientRepository, EmailService emailService,
                         VerificationTokenRepository verificationTokenRepository, BeneficiaireRepository beneficiaireRepository) {
        this.clientRepository = clientRepository;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.beneficiaireRepository = beneficiaireRepository;
    }

    public void ajouter(Client client,String appUrl) {
        /**
         if(utilisateur.getPassword() != null){
         utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
         }
         */
        clientRepository.save(client);
        VerificationToken verificationToken = new VerificationToken(client.getEmail());
        verificationTokenRepository.creerToken(verificationToken);
        emailService.sendValidationTokenToClient(appUrl,verificationToken.getToken(),client.getEmail());
    }

    public void validerInscription(String token) throws InvalidTokenException, ExpiryTokenException {
        VerificationToken verificationToken = verificationTokenRepository.recupererToken(token);
        if(verificationToken == null){
            throw new InvalidTokenException();
        }
        if(LocalDateTime.now().isAfter(verificationToken.getExpirationDate())){
            throw new ExpiryTokenException();
        }
        clientRepository.validerClient(verificationToken.getIdUser());
        verificationTokenRepository.supprimerToken(token);
    }

    public Client findUtilisateurByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public boolean clientExists(String email){
        return clientRepository.clientExists(email);
    }

    public void ajouterBeneficiaire(Beneficiaire beneficiaire){
        beneficiaireRepository.ajouterBeneficiaire(beneficiaire);
    }

    public List<Beneficiaire> listerBeneficiares(){
        //recuperer email du client connecte
        String email = "sobhe@gmail.com";
        return beneficiaireRepository.listerBeneficiaires(email);
    }

    public boolean beneficiaireExists(String telephone) {
        return beneficiaireRepository.beneficiaireExists(telephone);
    }
}
