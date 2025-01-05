package tn.iteam.gestion_rh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ContentController {

 



@GetMapping("/login")
public String login(){
    return "login";
}

@GetMapping("/signup")
public String signup(){
    return "signup";

}


/* @GetMapping("/home")
  public String handleWelcome() {
    return "index";
  } */

  @GetMapping("/admin/home")
  public String handleAdminHome() {
    return "index";
  }

  @GetMapping("/user/home")
  public String handleUserHome() {
    return "home_user";
  }

 

}











