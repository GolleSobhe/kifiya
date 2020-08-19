package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.exception.ExpiryTokenException;
import com.kifiya.kobiri.exception.InvalidTokenException;
import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UtilisateurController {

    private final ClientService clientService;


    public UtilisateurController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "utilisateur/inscription")
    public String inscription(Model model){
        model.addAttribute("utilisateur",new Client());
        return "utilisateur/inscription";
    }

    @PostMapping(value = "utilisateur/inscription")
    public String inscription(@Valid @ModelAttribute("utilisateur") Client client,
                               BindingResult result, HttpServletRequest request, Model model){
        if (result.hasErrors()) {
            return "utilisateur/inscription";
        }
        if (clientService.clientExists(client.getEmail())) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "utilisateur/inscription";
        }
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();
        clientService.ajouter(client,appUrl);
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
        model.addAttribute("transfert", transfert);
        model.addAttribute("confirmationMessage", "Argent enoyé et un e-mail de confirmation a été envoyé à ");
        return "transfert/transfert";
    }

    @GetMapping(value="utilisateur/confirmation")
    public String confirmation(Model model, @RequestParam("token") String token){
        try {
            clientService.validerInscription(token);
            model.addAttribute("confirmationToken", token);
        } catch (InvalidTokenException e) {
            model.addAttribute("invalidToken", "Oups! Il s'agit d'un lien de confirmation non valide.");
        } catch (ExpiryTokenException e) {
            model.addAttribute("invalidToken", "Oups! le token a expiré.");
        }
        return "utilisateur/confirmation";
    }

}
