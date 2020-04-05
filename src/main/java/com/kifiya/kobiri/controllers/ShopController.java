package com.kifiya.kobiri.controllers;


import com.kifiya.kobiri.models.Shop;
import com.kifiya.kobiri.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "shops", method = RequestMethod.GET)
    public String getShops(Model model){
        model.addAttribute("shops", shopService.findAll());
        model.addAttribute("shop",new Shop());
        return "shop/shop";
    }

    @RequestMapping(value = "shops", method = RequestMethod.POST)
    public String ajouterShop(@Valid @ModelAttribute("user") Shop shop,
                              BindingResult result, HttpServletRequest request, Model model){
        if (result.hasErrors()) {
            //AJouter le modele error de saissie
            return "shop/shop";
        }

        shopService.save(shop);

        return "shop/shop";
    }
}
