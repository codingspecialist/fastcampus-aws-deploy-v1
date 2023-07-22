package shop.mtcoding.deployapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import shop.mtcoding.deployapp.product.Product;
import shop.mtcoding.deployapp.product.ProductRepository;
import shop.mtcoding.deployapp.user.User;
import shop.mtcoding.deployapp.user.UserRepository;

import java.util.Arrays;

@SpringBootApplication
public class DeployAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeployAppApplication.class, args);
    }

    @Profile({"dev"})
    @Bean
    CommandLineRunner devServerStart(UserRepository userRepository, ProductRepository productRepository){
        return args -> {
            userRepository.save(newUser("ssar"));
            productRepository.saveAll(Arrays.asList(
                    newProduct("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전", 1,1000),
                    newProduct("[황금약단밤 골드]2022년산 햇밤 칼집밤700g외/군밤용/생율", 2,2000),
                    newProduct("삼성전자 JBL JR310 외 어린이용/성인용 헤드셋 3종!", 3,30000)
            ));
        };
    }

    private User newUser(String username){
        return User.builder()
                .email(username+"@nate.com")
                .password("meta1234!")
                .username(username)
                .build();
    }

    private Product newProduct(String productName, int imageNumber, int price){
        return Product.builder()
                .productName(productName)
                .image("/images/"+imageNumber+".jpg")
                .price(price)
                .build();
    }
}
