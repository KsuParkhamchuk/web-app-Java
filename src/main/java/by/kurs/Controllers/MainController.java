package by.kurs.Controllers;

import by.kurs.domain.Product;
import by.kurs.domain.User;
import by.kurs.repository.BasketRepository;
import by.kurs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    @GetMapping("/")
    public String main(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    )
    {
        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        return "index";
    }

    @GetMapping("/Catalog")
    public String catalog(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false,defaultValue = "1") int page,
            Map<String, Object> model)
    {
        Iterable<Product> products = productRepository.findAll();

        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        model.put("products", products);
        return "Catalog";
    }

    @GetMapping("/oneProduct")
    public String oneProduct(
            @AuthenticationPrincipal User user,
            @RequestParam(required = true, defaultValue = "") Integer id,
            Map<String, Object> model){
        Product product = productRepository.findById(id);
        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        model.put("product", product);
        return "oneProduct";
    }

    @GetMapping("/MakeMySelf")
    public String makeMySelf()
    {
        return "Catalog";
    }

    @GetMapping("/Contacts")
    public String contacts(
            @AuthenticationPrincipal User user,
            Map<String,Object> model
    )
    {
        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        return "Contacts";
    }



    @PostMapping("search")
    public String search(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String search, Map<String, Object> model){
        Iterable<Product> products;
        if(search.isEmpty()) {
            products = productRepository.findAll();
        } else products = productRepository.findByName(search);
        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        model.put("products", products);
        return "Catalog";



       /* Iterable<Product> products;
        products = productRepository.findAll();
        for (Product Value:products) {

        }
        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        model.put("products", products);
        return "Catalog";*/
    }


}