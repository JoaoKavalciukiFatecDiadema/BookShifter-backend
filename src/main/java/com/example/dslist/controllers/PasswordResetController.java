package com.example.dslist.controllers;


import com.example.dslist.entities.User;
import com.example.dslist.events.PasswordRecoveryEvent;
import com.example.dslist.services.interfaces.PasswordResetTokenService;
import com.example.dslist.services.interfaces.UserService;
import com.example.dslist.utils.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/forgot-password")
public class PasswordResetController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PasswordResetTokenService service;

    @GetMapping
    public String showForm(){
        return "forgot-password";
    }

    @PostMapping
    public String sendResetPasswordRequest(HttpServletRequest request){
        String email = request.getParameter("email");
        Optional<User> user = userService.findByEmail(email);
        
        if(user.isEmpty()){
            return "redirect:/forgot-password?not_found";
        }
        publisher.publishEvent(new PasswordRecoveryEvent(user.get(), UrlUtil.getApplicationUrl(request)));
        return "redirect:/forgot-password?success";
    }

    @GetMapping("/password-reset")
    public String changePasswordForm(@RequestParam("token") String token, Model model){
        model.addAttribute("token", token);
        return "password-reset";
    }
    @PostMapping("/password-reset")
    public String resetPassword(HttpServletRequest request) {
        String requestToken = request.getParameter("token");
        String requestPassword = request.getParameter("password");

        String tokenValidation = service.validateToken(requestToken);

        if (!tokenValidation.equalsIgnoreCase("VALID")) {
            return "redirect:/error?invalid_token";
        }
        Optional<User> user = service.findUserByToken(requestToken);

        if(user.isPresent()){
            service.resetPassword(user.get(), requestPassword);
            return "redirect:/login?reset_sucess";
        }
        return "redirect:/error";
    }
}
