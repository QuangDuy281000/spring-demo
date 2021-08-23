package springcrud.springcrud.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;

    private String productname;

    private String  amount;

    private String price;

    private String status;
}
