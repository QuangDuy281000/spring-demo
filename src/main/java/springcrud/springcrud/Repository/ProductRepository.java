package springcrud.springcrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcrud.springcrud.Entity.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

}
