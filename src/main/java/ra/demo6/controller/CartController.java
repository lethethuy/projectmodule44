package ra.demo6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.demo6.model.CartItem;
import ra.demo6.model.Product;
import ra.demo6.service.CartService;
import ra.demo6.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    private
    CartService cartService = new CartService(new ArrayList<>());

    @RequestMapping
    public String getCart(HttpSession session, Model model) {
        session.setAttribute("cart", cartService.findAll());
        // đã có người đăng nhập rồi
        // tính tổng tiền
        double total = cartService.findAll().stream().map(ci -> ci.getQuantity() * ci.getProduct().getPrice()).reduce((double) 0, Double::sum);
        model.addAttribute("total", total);
        return "/web/shoppingCart";
    }
    @GetMapping("/checkout1")
    public String getCartToCheckout1(HttpSession session, Model model) {
        session.setAttribute("check1", cartService.findAll());
        // đã có người đăng nhập rồi
        // tính tổng tiền
        double total = cartService.findAll().stream().map(ci -> ci.getQuantity() * ci.getProduct().getPrice()).reduce((double) 0, Double::sum);
        model.addAttribute("total", total);
        return "/web/checkout1";
    }




    @GetMapping("/addToCart/{idPro}")
    public String addToCart(@PathVariable("idPro") int idPro, HttpSession session) {

        Product p = productService.findByID(idPro);

        // kiểm tra sản phẩm dã có trong giỏ hàng chưa
        CartItem cartItem = cartService.findByIDPro(idPro);

        if (cartItem == null) {
            // tạo mới item
            cartItem = new CartItem(cartService.getNewId(), p, 1);
        } else {
            // cập nhật số lượng thêm 1 đơn vị
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cartService.save(cartItem);
        // lưu vào session
        session.setAttribute("cart", cartService.findAll());
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, HttpSession session) {
        cartService.deleteById(id);
        session.setAttribute("cart", cartService.findAll());
        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String handleUpdate(HttpSession session, @PathVariable("id") int id, @RequestParam("quantity") int quantity) {
        CartItem cartItem = cartService.findByID(id);
        cartItem.setQuantity(quantity);
        cartService.save(cartItem);
        // lưu vào session
        session.setAttribute("cart", cartService.findAll());
        return "redirect:/cart";
    }

}
