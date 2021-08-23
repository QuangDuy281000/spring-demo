package springcrud.springcrud.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcrud.springcrud.Entity.ProductEntity;

import springcrud.springcrud.Sevice.IProductSevice;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private IProductSevice iproductSevice;

    @GetMapping("/home")
    public String index(Model model){
        List<ProductEntity> list = iproductSevice.select();
        model.addAttribute("listproduct",list);
        return "home";
    }
    @GetMapping(path = {"/adduser","/deleteuser/adduser"})
    public String adduser(Model model){
        model.addAttribute("productEntity",new ProductEntity());
        return "addregister";
    }
    @PostMapping("/adduser")
    public String register(Model model, @ModelAttribute ProductEntity productEntity){
        boolean kt = iproductSevice.add(productEntity);
        if(kt){
            model.addAttribute("mess","Dang ki thanh cong");
        }
        else{
            model.addAttribute("mess","Dang ki that bai");
        }
        return "addregister";
    }

    @GetMapping("/deleteuser/{productid}")
    public String deleteuser(Model model,@PathVariable Integer productid){
        boolean kt = iproductSevice.remove(productid);
        if(kt){
            model.addAttribute("mess","xoa thanh cong");
            List<ProductEntity> list = iproductSevice.select();
            model.addAttribute("listproduct",list);
            return "home";
        }
        else {
            model.addAttribute("mess","xoa that bai");
            List<ProductEntity> list = iproductSevice.select();
            model.addAttribute("listproduct",list);
            return "home";
        }
    }

    @GetMapping(path = {"/edituser/{productid}","/deleteuser/edituser/{productid}"})
    public String edituser(Model model,@PathVariable Integer productid){
        if(productid!=null){
            ProductEntity productEntity1 = new ProductEntity();
            productEntity1 = iproductSevice.selectFindBy(productid);
            model.addAttribute("productct",productEntity1);
        }
        return "test";
    }

    @PostMapping("/update")
    public String updateproduct(Model model,@RequestParam(name = "productid") Integer productid ,@RequestParam(name="productname") String productname,@RequestParam(name = "amount") String amount
            ,@RequestParam(name = "price") String price, @RequestParam(name = "status") String status){
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductid(productid);
        productEntity1.setProductname(productname);
        productEntity1.setAmount(amount);
        productEntity1.setPrice(price);
        productEntity1.setStatus(status);
        iproductSevice.update(productEntity1);
        model.addAttribute("mess","updatesuccess");
        List<ProductEntity> list = iproductSevice.select();
        model.addAttribute("listproduct",list);
        return "home";
    }
}
