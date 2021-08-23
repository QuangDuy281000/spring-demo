package springcrud.springcrud.Sevice;


import springcrud.springcrud.Entity.ProductEntity;

import java.util.List;
import java.util.Optional;


public interface IProductSevice {
    List<ProductEntity> select();
    boolean add(ProductEntity productEntity,int productid);
    ProductEntity selectFindBy(Integer productid);

    void update(ProductEntity productEntity);

    boolean remove(Integer productid);
}
