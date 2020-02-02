package com.kifiya.kobiri.controllers;

import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.services.EmailService;
import com.kifiya.kobiri.services.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "user/new")
    public String inscription(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }

    @PostMapping(value = "user")
    public String save(User user){
        this.userService.save(user);
        return "index";
    }

    @GetMapping(value = "user/signIn")
    public String connexion(){
        return "user/signIn";
    }

    @GetMapping(value = "user/contact-us")
    public String contactUs(){
        return "user/contactUs";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result, HttpServletRequest request, Model model){

        User existing = userService.findUserByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "user/register";
        }

        // Disable user until they click on confirmation link in email
        user.setEnabled(false);
        // Generate random 36-character string token for confirmation link
        user.setConfirmationToken(UUID.randomUUID().toString());

        userService.save(user);
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        emailService.sendEmail(appUrl, user.getConfirmationToken(), user.getEmail());
        model.addAttribute("confirmationMessage", "Un e-mail de confirmation a été envoyé à " + user.getEmail());
        return "user/register";
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public String showConfirmationPage(Model model, @RequestParam("token") String token){
        User user = userService.findByConfirmationToken(token);
        if (user == null) { // No token found in DB
            model.addAttribute("invalidToken", "Oups! Il s'agit d'un lien de confirmation non valide.");
        } else { // Token found
            model.addAttribute("confirmationToken", user.getConfirmationToken());
        }
        return "user/confirm";
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public String processConfirmationForm(Model model, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
        Zxcvbn passwordCheck = new Zxcvbn();
        Strength strength = passwordCheck.measure((CharSequence) requestParams.get("password"));

        if (strength.getScore() < 3) {
            bindingResult.reject("password");

            redir.addFlashAttribute("errorMessage", "Votre mot de passe est trop faible. Choisissez-en un plus fort.");
            System.out.println(requestParams.get("token"));
            return "redirect:confirm?token=" + requestParams.get("token");
        }
        // Find the user associated with the reset token
        User user = userService.findByConfirmationToken((String) requestParams.get("token"));
        // Set user to enabled
        user.setEnabled(true);
        // Save user
        userService.save(user);
        model.addAttribute("successMessage", "Votre mot de passe a été défini!");
        return "user/confirm";
    }

}
