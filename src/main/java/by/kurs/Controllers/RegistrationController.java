package by.kurs.Controllers;

import by.kurs.domain.Basket;
import by.kurs.domain.Role;
import by.kurs.domain.User;
import by.kurs.repository.BasketRepository;
import by.kurs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasketRepository basketRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(required = false) String confirmPassword, User user, Map<String, Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.put("user", "User exists");
            return "registration";
        }
        if(!confirmPassword.equals( user.getPassword())){
            model.put("password", "password does not match");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        Basket basket = new Basket();
        basket.setBasketOwner(user);
        basketRepository.save(basket);
        return "redirect:/login";
    }

}
