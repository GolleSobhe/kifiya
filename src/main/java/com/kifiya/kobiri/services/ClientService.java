package com.kifiya.kobiri.services;

import com.kifiya.kobiri.exception.ExpiryTokenException;
import com.kifiya.kobiri.exception.InvalidTokenException;
import com.kifiya.kobiri.models.*;
import com.kifiya.kobiri.repositories.BeneficiaireRepository;
import com.kifiya.kobiri.repositories.ClientRepository;
import com.kifiya.kobiri.repositories.TransfertRepository;
import com.kifiya.kobiri.repositories.VerificationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    public ClientService(ClientRepository clientRepository, EmailService emailService,
                         VerificationTokenRepository verificationTokenRepository, BeneficiaireRepository beneficiaireRepository, TransfertRepository transfertRepository) {
        this.clientRepository = clientRepository;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.beneficiaireRepository = beneficiaireRepository;
        this.transfertRepository = transfertRepository;
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

    public void ajouterTransfert(Transfert transfert) {
        //Moidification apres creation de la page de connexion
        /**
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         Utilisateur utilisateurAuth = (Utilisateur) auth.getPrincipal();
         Client client = new Client();
         client.setId(utilisateurAuth.getId());
         client.setPrenom(utilisateurAuth.getPrenom());
         client.setNom(utilisateurAuth.getNom());
         client.setEmail(utilisateurAuth.getEmail());
         client.setPassword(utilisateurAuth.getPassword());
         transfert.setResponsable(client);
         */

        transfert.setBoutique(new Boutique("cosa1", "Conakry", ""));
        Beneficiaire beneficiaire = Beneficiaire.builder().
                nom("Fiya").prenom("Hollo").telephone("623-09-76-13").build();
        transfert.setBeneficiaire(beneficiaire);
        transfert.setMontantEuros((long) 500);
        transfert.setTaux((long) 10600);
        transfert.setFrais((long) 5);
        Client client = new Client();
        client.setEmail("sobhe@gmail.com");
        transfert.setClient(client);
        transfert.setCode(getHexa(8));
        Boolean beneficiaireExist = beneficiaireRepository.beneficiaireExists(transfert.getBeneficiaire().getTelephone(), transfert.getClient().getEmail());
        if(!beneficiaireExist){
            String telephone = beneficiaireRepository.ajouterBeneficiaire(transfert.getBeneficiaire());
            transfert.getBeneficiaire().setTelephone(telephone);
        }
        transfertRepository.creer(transfert);
    }

    private String getHexa(int nombreCaractere){
        while(sb.length() < nombreCaractere){
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, nombreCaractere);
    }
}
