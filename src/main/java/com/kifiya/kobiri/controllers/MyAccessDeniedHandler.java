package com.kifiya.kobiri.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Component
public class MyAccessDeniedHandler implements ErrorController /**, AccessDeniedHandler */{


    private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @RequestMapping("/404")
    public String handleError() {
        //do something like logging
        return "error/404";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403(){
        return "error/403";
    }

    @Override
    public String getErrorPath() {
        return "404";
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
