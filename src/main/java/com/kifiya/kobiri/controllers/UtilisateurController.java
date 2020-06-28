package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.ClientService;
import com.kifiya.kobiri.services.EmailService;
import com.kifiya.kobiri.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UtilisateurController {

    private final ClientService clientService;

    private final EmailService emailService;

    private final TransfertService transfertService;

    public UtilisateurController(ClientService clientService, EmailService emailService, TransfertService transfertService) {
        this.clientService = clientService;
        this.emailService = emailService;
        this.transfertService = transfertService;
    }

    @GetMapping(value = "utilisateur/inscription")
    public String inscription(Model model){
        model.addAttribute("utilisateur",new Client());
        return "utilisateur/inscription";
    }



    @PostMapping(value = "utilisateur/inscription")
    public String inscription(@Valid @ModelAttribute("utilisateur") Client client,
                               BindingResult result, HttpServletRequest request, Model model){

        Client existing = clientService.findUtilisateurByEmail(client.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "utilisateur/inscription";
        }

        // Disable utilisateur until they click on confirmation link in email
        //client.setActive(false);
        // Generate random 36-character string token for confirmation link
        //client.setConfirmationToken(UUID.randomUUID().toString());

        clientService.ajouter(client);
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        emailService.sendEmail(appUrl, "qq", client.getEmail());
        //emailService.sendEmail(appUrl, client.getConfirmationToken(), client.getEmail());
        model.addAttribute("confirmationMessage", "Un e-mail de confirmation a été envoyé à " + client.getEmail());
        return "utilisateur/inscription";
    }

    @GetMapping(value = "utilisateur/connexion")
    public String connexion(){
        return "utilisateur/connexion";
    }

    @GetMapping(value = "utilisateur/contact")
    public String contact(){
        return "utilisateur/contact";
    }

    @GetMapping(value = "transferts")
    public String faireTransfert(Model model){
        model.addAttribute("transfert", new Transfert());
        return "transfert/transfert";
    }

    @PostMapping(value =  "transferts")
    public String postHistoric(@Valid @ModelAttribute("transfert") Transfert transfert,
                               BindingResult result, Model model){


        /*if (result.hasErrors() || !result.hasFieldErrors("client") || !result.hasFieldErrors("beneficiaire")) {
            //Ajouter le message d'erreur sur le model
            return"transfert/transfert";
        }*/

        transfertService.ajouter(transfert);

        model.addAttribute("transfert", transfert);
        model.addAttribute("confirmationMessage", "Argent enoyé et un e-mail de confirmation a été envoyé à ");
        return "transfert/transfert";
    }

    // Process confirmation link
    /*@RequestMapping(value="/confirmation", method = RequestMethod.GET)
    public String confirmation(Model model, @RequestParam("token") String token){
        Utilisateur utilisateur = clientService.findByConfirmationToken(token);
        if (utilisateur == null) { // No token found in DB
            model.addAttribute("invalidToken", "Oups! Il s'agit d'un lien de confirmation non valide.");
        } else { // Token found
            model.addAttribute("confirmationToken", utilisateur.getConfirmationToken());
        }
        return "utilisateur/confirmation";
    }

    // Process confirmation link
    @RequestMapping(value="/confirmation", method = RequestMethod.POST)
    public String confirmation(@RequestParam Map<String, String> requestParams, Model model) {

        // Find the utilisateur associated with the reset token
        Utilisateur utilisateur = clientService.findByConfirmationToken(requestParams.get("token"));
        String password = requestParams.get("password");
        // Set utilisateur to enabled
        utilisateur.setActive(true);
        utilisateur.setPassword(password);
        // Save utilisateur
        clientService.ajouter(utilisateur);
        model.addAttribute("successMessage", "Votre mot de passe a été défini!");
        return "utilisateur/confirmation";
    }*/

}
