package lessontwo.mvc.model;

import java.util.Objects;

public class Product {
    private Long id;
    private String title;
    private float cost;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Product(Long id, String title, float cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {       if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
