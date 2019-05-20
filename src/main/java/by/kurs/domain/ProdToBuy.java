package by.kurs.domain;

public class ProdToBuy{
    private int amount;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProdToBuy() {
    }

    public ProdToBuy(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }
}
