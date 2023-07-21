package shop.mtcoding.deployapp.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="product_tb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private String image;
    private int price; // 톡딜가

    @Builder
    public Product(int id, String productName, String image, int price) {
        this.id = id;
        this.productName = productName;
        this.image = image;
        this.price = price;
    }
}
