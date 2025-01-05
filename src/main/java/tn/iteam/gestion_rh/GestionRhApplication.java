package tn.iteam.gestion_rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class GestionRhApplication {

	public static void main(String[] args) { 
		SpringApplication.run(GestionRhApplication.class, args);
	}
	   /*  @Bean
    public CommandLineRunner run( UserRepository userRepository) {
        return args -> {
			
            List<User> userAccounts = List.of(
                    User.builder().accountId().customerId(1L).build(),
                    User.builder().Id(1L).build()
            );
            userRepository.saveAll(userAccounts);
        };
    } */
}
 