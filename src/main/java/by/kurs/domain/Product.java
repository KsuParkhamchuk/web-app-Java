package by.kurs.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    private double cost;

    @Enumerated(EnumType.STRING)
    private Category category;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product() {
    }

    public Product(String name, String description, double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public void setName(String text){
        this.name = text;
    }

    public void setDescription(String text){
        this.description = text;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @ManyToMany(mappedBy = "products")
    private List<Basket> baskets;

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }
}
