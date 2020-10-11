package com.kifiya.kobiri.services;

import com.kifiya.kobiri.exception.ExpiryTokenException;
import com.kifiya.kobiri.exception.InvalidTokenException;
import com.kifiya.kobiri.models.*;
import com.kifiya.kobiri.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class ClientService {
    private Random random = new Random();
    private StringBuilder sb = new StringBuilder();

    private final BeneficiaireRepository beneficiaireRepository;

    private final EmailService emailService;

    private final ClientRepository clientRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final TransfertRepository transfertRepository;

    private final IndexService indexRepository;

    public ClientService(ClientRepository clientRepository, EmailService emailService,
                         VerificationTokenRepository verificationTokenRepository,
                         BeneficiaireRepository beneficiaireRepository,
                         TransfertRepository transfertRepository,
                         IndexService indexRepository) {
        this.clientRepository = clientRepository;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.beneficiaireRepository = beneficiaireRepository;
        this.transfertRepository = transfertRepository;
        this.indexRepository = indexRepository;
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

    public List<Beneficiaire> listerBeneficiares(String email){
        return beneficiaireRepository.listerBeneficiaires(email);
    }

    public Transfert ajouterTransfert(Transfert transfert) {
        transfert.setCode(getHexa(8));
        Boolean beneficiaireExist = beneficiaireRepository.beneficiaireExists(transfert.getBeneficiaire().getTelephone(), transfert.getClient().getEmail());
        if(!beneficiaireExist){
            Beneficiaire beneficiaire = transfert.getBeneficiaire();
            beneficiaire.setClientId(transfert.getClient().getEmail());
            String telephone = beneficiaireRepository.ajouterBeneficiaire(beneficiaire);
            transfert.getBeneficiaire().setTelephone(telephone);
        }
        return transfertRepository.ajouterTransfert(transfert);
    }

    private String getHexa(int nombreCaractere){
        while(sb.length() < nombreCaractere){
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, nombreCaractere);
    }

    public Map<String, Object> obtenirParametre() {
        return indexRepository.obtenirPrametre();
    }
}
