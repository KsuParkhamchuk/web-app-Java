package by.kurs.Controllers;

import by.kurs.domain.Basket;
import by.kurs.domain.ProdToBuy;
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

import java.util.ArrayList;
import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    @GetMapping("/myGift")
    public String myGift(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ){
        Basket basket = basketRepository.findByBasketOwner(user);
        ArrayList<ProdToBuy> prod = new ArrayList<ProdToBuy>();
        double price = 0;

        boolean found = false;
        for(Product product : basket.getProducts()){
            price += product.getCost();
            found = false;
            for(ProdToBuy prodt : prod) {
                if (prod.get(prod.indexOf(prodt)).getProduct().getId() == product.getId()) {
                    prod.get(prod.indexOf(prodt)).setAmount(prod.get(prod.indexOf(prodt)).getAmount() + 1);
                    found = true;
                }
            }
            if (!found) {
                prod.add(new ProdToBuy(product,1));
            }
        }
        String empty = new String();
        if(!(price == 0)) {
            empty ="";
        } else empty ="not";
        if(user != null){
            model.put("user", user.getUsername());
        } else model.put("user", "");
        model.put("em", empty);
        model.put("price", price);
        model.put("basket", prod);
        return "Basket";
    }



    @PostMapping("addProd")
    public String addProduct(
            @AuthenticationPrincipal User user,
            @RequestParam(required = true) Integer id,
            Basket basket
    ){
        Product product = productRepository.findById(id);

        basket = basketRepository.findByBasketOwner(user);
        basket.add(product);
        basketRepository.save(basket);
        return "redirect:/Catalog";
    }


}
