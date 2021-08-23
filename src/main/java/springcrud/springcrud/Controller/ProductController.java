package springcrud.springcrud.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String index(Model model) {
        List<ProductEntity> list = iproductSevice.select();
        model.addAttribute("listproduct", list);
        return "home";
    }

    @GetMapping("/adduser")
    public String adduser(Model model) {
        model.addAttribute("productEntity", new ProductEntity());
        return "addregister";
    }

    @PostMapping("/adduser")
    public String register(Model model, @ModelAttribute ProductEntity productEntity) {
        boolean kt = iproductSevice.add(productEntity,productEntity.getProductid());
        if (kt) {
            model.addAttribute("mess", "Dang ki thanh cong");
            return "addregister";
        } else {
            model.addAttribute("mess", "Dang ki that bai");
            return "addregister";
        }
//        return "redirect:/v1/adduser";
    }

    @GetMapping("/deleteuser/{productid}")
    public String deleteuser(Model model, @PathVariable Integer productid) {
        boolean kt = iproductSevice.remove(productid);
        if (kt) {
            model.addAttribute("mess", "xoa thanh cong");
            List<ProductEntity> list = iproductSevice.select();
            model.addAttribute("listproduct", list);
            return "redirect:/v1/home";
        } else {
            model.addAttribute("mess", "xoa that bai");
            List<ProductEntity> list = iproductSevice.select();
            model.addAttribute("listproduct", list);
            return "redirect:/v1/home";
        }
    }

    @GetMapping("/edituser/{productid}")
    public String edituser(Model model, @PathVariable Integer productid) {
        ProductEntity productEntity1 = iproductSevice.selectFindBy(productid);
        model.addAttribute("productct", productEntity1);
        return "test";
    }

    @PostMapping("/update")
    public String updateproduct(Model model, @ModelAttribute ProductEntity productEntity1) {
        iproductSevice.update(productEntity1);
        model.addAttribute("mess", "updatesuccess");
        List<ProductEntity> list = iproductSevice.select();
        model.addAttribute("listproduct", list);
        return "redirect:/v1/home";
    }
}
