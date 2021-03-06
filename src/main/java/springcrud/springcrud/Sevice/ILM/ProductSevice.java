package springcrud.springcrud.Sevice.ILM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springcrud.springcrud.Entity.ProductEntity;
import springcrud.springcrud.Repository.ProductRepository;
import springcrud.springcrud.Sevice.IProductSevice;

import java.util.List;


@Service
public class ProductSevice implements IProductSevice {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductSevice productSevice;
    @Override
    public List<ProductEntity> select() {
        List<ProductEntity> list = productRepository.findAll();
        return list;
    }

    @Override
    public boolean add(ProductEntity productEntity,int productid) {
        List<ProductEntity> list = productSevice.select();
        for (ProductEntity p : list){
            if(p.getProductid()==productid){
                return false;
            }
        }
        productRepository.save(productEntity);
        return true;
    }

    @Override
    public ProductEntity selectFindBy(Integer productid) {
        if(productid!=null){
            ProductEntity productEntity = productRepository.findById(productid).get();
            return productEntity;
        }
        return null;
    }

    @Override
    public void update(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    public boolean remove(Integer productid) {
        if (productid!=null){
            productRepository.deleteById(productid);
            return true;
        }
        return false;
    }


}
