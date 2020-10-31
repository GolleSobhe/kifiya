package com.kifiya.kobiri.services;

import com.kifiya.kobiri.exception.ExpiryTokenException;
import com.kifiya.kobiri.exception.InvalidTokenException;
import com.kifiya.kobiri.models.*;
import com.kifiya.kobiri.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private IndexService indexRepository;


    public void ajouter(Client client,String appUrl) {

        if(client.getPassword() != null){
            client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        }else {
            client.setPassword("password");
        }
        client.setActive(false);
        client.setRole("CLIENT");
        clientRepository.ajouter(client);
        VerificationToken verificationToken = new VerificationToken(client.getEmail());
        verificationTokenRepository.creerToken(verificationToken);
        emailService.sendValidationTokenToClient(appUrl,verificationToken.getToken(),client.getEmail());
    }

    public void checkToken(String token) throws InvalidTokenException, ExpiryTokenException{
        VerificationToken verificationToken = verificationTokenRepository.recupererToken(token);
        if(verificationToken == null){
            throw new InvalidTokenException();
        }
        if(LocalDateTime.now().isAfter(verificationToken.getExpirationDate())){
            throw new ExpiryTokenException();
        }
    }

    public void validerInscription(String token, String password) {
        VerificationToken verificationToken = verificationTokenRepository.recupererToken(token);
        clientRepository.validerClient(verificationToken.getIdUser(), bCryptPasswordEncoder.encode(password));
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

    public List<Transfert> mesTransferts(String clientId){
        return transfertRepository.transfertsClient(clientId);
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
