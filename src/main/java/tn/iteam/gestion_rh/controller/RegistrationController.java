package tn.iteam.gestion_rh.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import tn.iteam.gestion_rh.entity.MyUser;
import tn.iteam.gestion_rh.repository.MyUserRepository;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup/user")
    public RedirectView createUser(
        @RequestParam String username,
        @RequestParam String password
    ) {
        MyUser user = new MyUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        myUserRepository.save(user);
         
        return new RedirectView("/login");

    }
}