package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.exception.ExpiryTokenException;
import com.kifiya.kobiri.exception.InvalidTokenException;
import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final ClientService clientService;


    public UtilisateurController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/inscription", method = RequestMethod.GET)
    public String inscription(Model model){
        model.addAttribute("utilisateur",new Client());
        return "utilisateur/inscription";
    }

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String inscription(HttpServletRequest request, @ModelAttribute("utilisateur") Client client,
                               BindingResult result, Model model){
        if (result.hasErrors()) {
            return "utilisateur/inscription";
        }

        if (clientService.clientExists(client.getEmail())) {
            result.rejectValue("email", null, "Un compte est déjà enregistré avec cette adresse e-mail");
            return "utilisateur/inscription";
        }

        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();
        clientService.ajouter(client,appUrl);
        model.addAttribute("confirmationMessage", "Un e-mail de confirmation a été envoyé à " + client.getEmail());

        return "utilisateur/inscription";
    }

    @GetMapping(value = "/connexion")
    public String connexion(){
        return "utilisateur/connexion";
    }

    @GetMapping(value = "/deconnexion")
    public String deconnexion(){
        return "redirect:/index";
    }

    @GetMapping(value = "/contact")
    public String contact(){
        return "utilisateur/contact";
    }

    @GetMapping(value="/confirmation")
    public String confirmation(Model model, @RequestParam("token") String token){
        try {
            clientService.checkToken(token);
            model.addAttribute("confirmationToken", token);
        } catch (InvalidTokenException e) {
            model.addAttribute("invalidToken", "Oups! Il s'agit d'un lien de confirmation non valide.");
        } catch (ExpiryTokenException e) {
            model.addAttribute("invalidToken", "Oups! le token a expiré.");
        }
        return "utilisateur/confirmation";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String setMotDePass(@RequestParam("token") String token, @RequestParam("password") String password){
        clientService.validerInscription(token, password);
        return "redirect:/index";
    }

}
