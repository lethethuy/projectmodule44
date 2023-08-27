package ra.demo6.controller;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.demo6.DTO.request.FormLoginDTO;
import ra.demo6.DTO.request.FormRegisterDTO;
import ra.demo6.model.User;
import ra.demo6.service.CartService;
import ra.demo6.service.ProductService;
import ra.demo6.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class WebsiteController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/web/index";
    }



//    @GetMapping("/products")
//    public String products() {
//        return "/web/products";
//    }
//    @GetMapping("/products")
//    public String product2(Model model) {
//        model.addAttribute("products", productService.findAll());
//        return "/web/index";
//    }

    @GetMapping("articles")
    public String articles() {
        return "/web/articles";
    }

    @GetMapping("merchantGuide")
    public String merchantGuide() {
        return "/web/merchantGuide";
    }

    @GetMapping("questions")
    public String questions() {
        return "/web/questions";
    }

    @GetMapping("about")
    public String about() {
        return "/web/about";
    }

    @GetMapping("contact")
    public String contact() {
        return "/web/contact";
    }

    @GetMapping("chefDirectory")
    public String chefDirectory() {
        return "/web/chefDirectory";
    }


    @GetMapping("channelDetail")
    public String channelDetail() {
        return "/web/channelDetail";
    }

    @GetMapping("/articleDetails")
    public String articleDetails() {
        return "/web/articleDetails";
    }

    @GetMapping("/registerMerchant")
    public String registerMerchant() {
        return "/web/registerMerchant";
    }
    @GetMapping("/shoppingCart")
    public String shoppingCart() {
        return "/web/shoppingCart";
    }

//    @GetMapping("/checkout1")
//    public String checkout1() {
//        return "/web/checkout1";
//    }
    @GetMapping("/checkout2")
    public String checkout2() {
        return "/web/checkout2";
    }

    @GetMapping("/checkout3")
    public String checkout3() {
        return "/web/checkout3";
    }
    @GetMapping("/thankyou")
    public String thankyou() {
        return "/web/thankyou";
    }



    // ============================== GET REGISTER ==============================
    @GetMapping("/register")
    public ModelAndView register() {

        return new ModelAndView("/web/register", "registerForm", new FormRegisterDTO());
    }
    // ============================== POST REGISTER ==============================
    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") FormRegisterDTO formRegisterDTO) {
        userService.formToModel(formRegisterDTO);
        return "redirect:/login";
    }


    // ============================== GET LOGIN ==============================
    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("/web/login", "loginForm", new FormLoginDTO());
    }
    // ============================== POST LOGIN ==============================
    @PostMapping("/login")
    public String login(HttpSession session, @ModelAttribute("loginForm") FormLoginDTO formLoginDTO) {
        User user = userService.login(formLoginDTO);
        if (user==null){
            // taif khoanr ko ddungs
            return "redirect:/login?error=fail-login";
        }else {
            // save in session
            session.setAttribute("user_login",user);

            // check role
            if(user.getRole()==1){
                // admin
                return "redirect:/admin/index2";
            }else {
                //  check status
                return "redirect:/";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user_login");
        return "redirect:/";
    }

//    @PostMapping("/web/login")
//    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
//        User user = userService.login(email, password);
//        System.out.println(user);
//        if (user != null) {
//            session.setAttribute("userLogin", user);
//            System.out.println(user.getRole());
//            if (user.getRole() == 1) {
//                return "/admin/index2";
//            } else {
//                return "/web/index";
//            }
//        }
//
//        return "redirect:/login";
//    }


//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }@GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//    @GetMapping("articleDetails.html")
//    public String articleDetails(){
//        return "articleDetails";
//    }
//


}
