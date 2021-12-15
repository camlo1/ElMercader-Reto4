package usa.ElMercader4;

import usa.ElMercader4.modelo.User;
import usa.ElMercader4.repositorios.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages={"usa.ElMercader4"})

public class ElMercader4Application {
    
    @Autowired
    private UserRepository repoUser; 
    //private GadgetRepository repoGadget;
    //private OrderRepository repoOrder;

	public static void main(String[] args) {
		SpringApplication.run(ElMercader4Application.class, args);
	}
        
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            System.out.println("APLICACIÓN INICIADA!.");
            List<User> ur = repoUser.getAll();
            System.out.println("Usuarios: " + ur.size());
            //List<Gadget> gr = repoGadget.getAll();
            //System.out.println("Productos: " + gr.size());
            //List<Order> or = repoOrder.getAll();
            //System.out.println("Ordenes: " + or.size());                       
        };
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}

