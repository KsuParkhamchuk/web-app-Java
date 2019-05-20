package by.kurs.repository;

import by.kurs.domain.Basket;
import by.kurs.domain.Product;
import by.kurs.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository<Basket, Long> {

    //Iterable<Basket> findByBasketOwner(User user);
    Basket findByBasketOwner(User user);
}
