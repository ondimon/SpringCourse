package lessonone;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(1, "product 1", 1.0f));
        products.add(new Product(2, "product 2", 1.0f));
        products.add(new Product(3, "product 3", 1.0f));
        products.add(new Product(4, "product 4", 1.0f));
        products.add(new Product(5, "product 5", 1.0f));
    }

    @Override
    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product get(long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean add(Product product) {
        return products.add(product);
    }
}
