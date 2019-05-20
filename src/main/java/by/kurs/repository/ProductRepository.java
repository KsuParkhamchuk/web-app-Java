package by.kurs.repository;

import by.kurs.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByName(String name);

    Product findById(Integer id);
}
