package tn.iteam.gestion_rh.controller;
import java.nio.file.Path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.iteam.gestion_rh.entity.MyUser;
import tn.iteam.gestion_rh.service.MyUserService;

@Controller
public class MyUserController {
	
	private MyUserService myuserService;

	public MyUserController(MyUserService myuserService) {
		super();
		this.myuserService = myuserService;
	}
	

	@GetMapping("/employee")
    public String contactPage(Model model) {
		model.addAttribute("myuser", new MyUser());
		model.addAttribute("myusers", myuserService.getAllMyUsers());
		
		
        return "contacts"; // Nom du fichier HTML de la page contact
    }
	@PostMapping("/myusers")
    public String saveMyUser(@ModelAttribute MyUser myUser, 
                           @RequestParam("imageFile") MultipartFile imageFile) {
        
        if (!imageFile.isEmpty()) {
            try {
                // Générer un nom de fichier unique
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                
                // Définir le chemin où sauvegarder l'image
                String uploadDir = "src/main/resources/static/images/user/";
                Path uploadPath = Paths.get(uploadDir);
                
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                // Sauvegarder le fichier
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                
                // Définir le chemin de l'image dans l'entité
                myUser.setImage("/images/user/" + fileName);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (myUser.getRole() == null || myUser.getRole().isEmpty()) {
			myUser.setRole(("USER"));
		}
        myuserService.saveMyUser(myUser);
        return "redirect:/employee";
    }


    @GetMapping("/api/users/{id}")
    public ResponseEntity<MyUser> getUserDetails(@PathVariable Long id) {
        MyUser user = myuserService.getMyUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	@GetMapping("/employee/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        MyUser myUser = myuserService.getMyUserById(id);
        if (myUser != null) {
            model.addAttribute("myuser", myUser);
            return "edit-user";
        }
        return "redirect:/employee";
    }
   // Traiter la mise à jour

   @PostMapping("/employee/update")
   public String updateUser(@ModelAttribute MyUser myUser,
							@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
	   // Récupérez l'utilisateur existant
	   MyUser existingUser = myuserService.getMyUserById(myUser.getId());
   
	   if (existingUser != null) {
		   // Conservez le rôle existant si aucun rôle n'est fourni
		   if (myUser.getRole() == null || myUser.getRole().isEmpty()) {
			   myUser.setRole(existingUser.getRole());
		   }
   
		   // Conservez le mot de passe existant si aucun mot de passe n'est fourni
		   if (myUser.getPassword() == null || myUser.getPassword().isEmpty()) {
			   myUser.setPassword(existingUser.getPassword());
		   }
   
		   // Gérer l'image si une nouvelle image est fournie
		   if (imageFile != null && !imageFile.isEmpty()) {
			   try {
				   String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
				   String uploadDir = "src/main/resources/static/images/user/";
				   Path uploadPath = Paths.get(uploadDir);
   
				   if (!Files.exists(uploadPath)) {
					   Files.createDirectories(uploadPath);
				   }
   
				   Path filePath = uploadPath.resolve(fileName);
				   Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
   
				   myUser.setImage("/images/user/" + fileName);
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
		   } else {
			   // Conserver l'image existante
			   myUser.setImage(existingUser.getImage());
		   }
   
		   // Sauvegarder les modifications
		   myuserService.saveMyUser(myUser);
	   }
   
	   return "redirect:/employee";
   }
   
   // Supprimer un utilisateur
   @GetMapping("/employee/delete/{id}")
   public String deleteUser(@PathVariable Long id) {
	   myuserService.deleteMyUserById(id);
	   return "redirect:/employee";
   }



























/* 

	@GetMapping("/myusers/new")
	public String createMyUserForm(Model model) {
		
		// create myuser object to hold myuser form data
		MyUser myuser = new MyUser();
		model.addAttribute("myuser", myuser);
		return "test";
		
	} */
	
	/* @PostMapping("/myuser")
	public String saveMyUser(@ModelAttribute("myuser") MyUser myuser) {
		myuserService.saveMyUser(myuser);
		return "redirect:/myusers";
	}
	 */

/* 
	@GetMapping("/myusers/edit/{id}")
	public String editMyUserForm(@PathVariable Long id, Model model) {
		model.addAttribute("myuser", myuserService.getMyUserById(id));
		return "edit_myusernbn ";
	}

	@PostMapping("/myusers/{id}")
	public String updateMyUser(@PathVariable Long id,
			@ModelAttribute("myuser") MyUser myuser,
			Model model) {
		
		// get myuser from database by id
		MyUser existingMyUser = myuserService.getMyUserById(id);
		existingMyUser.setId(id);
		existingMyUser.setName(myuser.getName());
		existingMyUser.setUsername(myuser.getUsername());
		existingMyUser.setImage(myuser.getImage());
		existingMyUser.setPoste(myuser.getPoste());
		existingMyUser.setDep(myuser.getDep());
		existingMyUser.setEmb(myuser.getEmb());
		existingMyUser.setStatut(myuser.getStatut());





		
		// save updated myuser object
		myuserService.updateMyUser(existingMyUser);
		return "redirect:/myusers";		
	}
	
	// handler method to handle delete myuser request
	
	@GetMapping("/myusers/{id}")
	public String deleteMyUser(@PathVariable Long id) {
		myuserService.deleteMyUserById(id);
		return "redirect:/myusers";
	} */
	
}