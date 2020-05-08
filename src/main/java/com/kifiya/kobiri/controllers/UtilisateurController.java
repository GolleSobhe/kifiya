package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.services.EmailService;
import com.kifiya.kobiri.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "utilisateur/inscription", method = RequestMethod.GET)
    public String inscription(Model model){
        model.addAttribute("utilisateur",new Utilisateur());
        return "utilisateur/inscription";
    }

    @RequestMapping(value = "utilisateur/inscription", method = RequestMethod.POST)
    public String inscription(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
                               BindingResult result, HttpServletRequest request, Model model){

        Utilisateur existing = utilisateurService.findUtilisateurByEmail(utilisateur.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "utilisateur/inscription";
        }

        // Disable utilisateur until they click on confirmation link in email
        utilisateur.setEnabled(false);
        // Generate random 36-character string token for confirmation link
        utilisateur.setConfirmationToken(UUID.randomUUID().toString());

        utilisateurService.ajouter(utilisateur);
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        emailService.sendEmail(appUrl, utilisateur.getConfirmationToken(), utilisateur.getEmail());
        model.addAttribute("confirmationMessage", "Un e-mail de confirmation a été envoyé à " + utilisateur.getEmail());
        return "utilisateur/inscription";
    }

    @RequestMapping(value = "utilisateur/connexion", method = RequestMethod.GET)
    public String connexion(){
        return "utilisateur/connexion";
    }

    @RequestMapping(value = "utilisateur/contact", method = RequestMethod.GET)
    public String contact(){
        return "utilisateur/contact";
    }



    // Process confirmation link
    @RequestMapping(value="utilisateur/confirmation", method = RequestMethod.GET)
    public String confirmation(Model model, @RequestParam("token") String token){
        Utilisateur utilisateur = utilisateurService.findByConfirmationToken(token);
        if (utilisateur == null) { // No token found in DB
            model.addAttribute("invalidToken", "Oups! Il s'agit d'un lien de confirmation non valide.");
        } else { // Token found
            model.addAttribute("confirmationToken", utilisateur.getConfirmationToken());
        }
        return "utilisateur/confirmation";
    }

    // Process confirmation link
    @RequestMapping(value="utilisateur/confirmation", method = RequestMethod.POST)
    public String confirmation(@RequestParam Map<String, String> requestParams, Model model) {

        // Find the utilisateur associated with the reset token
        Utilisateur utilisateur = utilisateurService.findByConfirmationToken(requestParams.get("token"));
        String password = requestParams.get("password");
        // Set utilisateur to enabled
        utilisateur.setEnabled(true);
        utilisateur.setPassword(password);
        // Save utilisateur
        utilisateurService.ajouter(utilisateur);
        model.addAttribute("successMessage", "Votre mot de passe a été défini!");
        return "utilisateur/confirmation";
    }

}
