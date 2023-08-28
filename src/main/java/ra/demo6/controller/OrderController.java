package ra.demo6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.demo6.model.CartItem;
import ra.demo6.model.Order;
import ra.demo6.model.OrderDetail;
import ra.demo6.model.User;
import ra.demo6.service.OrderDetailService;
import ra.demo6.service.OrderService;
import ra.demo6.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @PostMapping("/checkout")
    public String home(HttpSession session, @RequestParam("phone") int phone, @RequestParam("address") String address, @RequestParam("fullname") String fullName)  {
        // Lay ra tai khoan dang dang nhap tu session
        // va ep kieu ve dang User
        // Vi tren session dang luu duoi dang object
        User userLogin = (User) session.getAttribute("user_login");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        // Tính tổng tiền trong giỏ hàng
        double total = 0;
        for (CartItem c:cartItems
             ) {
            total = total + c.getProduct().getPrice()*c.getQuantity();
        }
        Order o = new Order();
        o.setUserId(userLogin.getId());
        o.setOrderAt(new Date());
        o.setTotalPrice((int) total);
        o.setPhone(phone);
        o.setAddress(address);
        o.setfullName(fullName);
        o.setStatus(1);
        int newOrderId = orderService.save(o);
        for (CartItem c:cartItems
             ) {
            orderDetailService.save(new OrderDetail(c.getProduct().getId(),c.getProduct().getPrice(), c.getQuantity(),newOrderId));
        }
        return "redirect: checkout2";
    }


    @GetMapping("/checkout2")
    public String thankyou(Model model) {
        model.addAttribute("thankyou", orderService.findAll());
        System.out.println(orderService.findAll());
        return "web/thankyou";
    }


}
