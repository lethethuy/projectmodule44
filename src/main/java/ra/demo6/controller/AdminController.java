package ra.demo6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.demo6.model.Product;
import ra.demo6.model.ProductDTO;
import ra.demo6.service.ProductService;
import ra.demo6.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/index2")
    public String index2() {

        return "admin/index2";
    }


    // hien thi tat ca san pham
    @GetMapping("/product2")
    public String product2(Model model) {
        model.addAttribute("product2", productService.findAll());
        return "admin/product2";
    }

    // xoa product

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.deleteById(id);
        return "redirect:/admin/product2";
    }
    //    Edit product
    @GetMapping("/edit/{id}")
    public ModelAndView update(@PathVariable("id") int id) {
        return new ModelAndView("/admin/edit", "productUpdate", productService.findByID(id));
    }

    // update product
    @Value("${upload-path}")
    private  String pathUpload;
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("productUpdate") ProductDTO productDTO) {
        File file = new File(pathUpload);
        if (!file.exists()) {
            file.mkdir();
        }
        String urlimage = productDTO.getImgUrl().getOriginalFilename();

        try {
            FileCopyUtils.copy(productDTO.getImgUrl().getBytes(), new File(pathUpload + urlimage));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Product p = new Product();
        p.setId(productDTO.getId());
        p.setProductName(productDTO.getProductName());
        p.setImgUrl(urlimage);
        p.setDescription(productDTO.getDescription());
        p.setStock(productDTO.getStock());
        p.setCatalogId(productDTO.getCatalogId());
        p.setPrice(productDTO.getPrice());
        p.setStatus(productDTO.isStatus());
        productService.save(p);
        return "redirect:/admin/product2";
    }
    @GetMapping("/create")
    public ModelAndView addProduct() {
        return new ModelAndView("/admin/create", "createProduct", new Product());
    }

    // Add prodcut//
    @PostMapping("/create")
    public String create(@ModelAttribute("createProduct") ProductDTO productDTO) {
        File file = new File(pathUpload);
        if (!file.exists()) {
            file.mkdir();
        }
        String freeImage = productDTO.getImgUrl().getOriginalFilename();

        try {
            FileCopyUtils.copy(productDTO.getImgUrl().getBytes(), new File(pathUpload + freeImage));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Product p = new Product();
        p.setProductName(productDTO.getProductName());
        p.setImgUrl(freeImage);
        p.setDescription(productDTO.getDescription());
        p.setStock(productDTO.getStock());
        p.setCatalogId(productDTO.getCatalogId());
        p.setPrice(productDTO.getPrice());
        p.setStatus(productDTO.isStatus());
        productService.save(p);
        return "redirect:/admin/product2";
    }


    @GetMapping("/lock/{id}")
    public String lockUser(@PathVariable("id") int id) {
        userService.lock(id);
        return "redirect:/admin/user";
    }




    // lấy tất cả User
    @GetMapping("/user")
    public String tableUser(Model model) {
        model.addAttribute("listUser", userService.findAll());
        return "/admin/user";
    }


    @GetMapping("/login2")
    public String login2() {
        return "admin/login2";
    }

    @GetMapping("/category")
    public String category() {
        return "admin/category";
    }


}
