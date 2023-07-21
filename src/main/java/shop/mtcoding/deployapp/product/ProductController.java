package shop.mtcoding.deployapp.product;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.deployapp.user.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final HttpSession session;

    // (기능4) 전체 상품 목록 조회
    @GetMapping("/products")
    public ResponseEntity<?> findAll() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null){
            return new ResponseEntity<>("로그인이 필요합니다", HttpStatus.UNAUTHORIZED);
        }

        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok().body(productList);
    }
}