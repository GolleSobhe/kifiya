package com.kifiya.kobiri.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class GestionErreur implements ErrorController /**, AccessDeniedHandler */{
    @Override
    public String getErrorPath() {
        return "/erreur-http";
    }

    @GetMapping(value = "/erreur-http")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "erreur/404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errreur/500";
            }
        }
        return "erreur/erreur";
    }


/**
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            logger.info("User '" + authentication.getName()
                    + "' attempted to access the protected URL: "
                    + request.getRequestURI());
        }
        response.sendRedirect(request.getContextPath() + "/403");
    }
*/
}
