package lessontwo.mvc.repositories;

import lessontwo.mvc.model.Product;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private Long maxId;
    @PostConstruct
    public void init() {
        products.add(new Product(1L, "product 1", 1.0f));
        products.add(new Product(2L, "product 2", 1.0f));
        products.add(new Product(3L, "product 3", 1.0f));
        products.add(new Product(4L, "product 4", 1.0f));
        products.add(new Product(5L, "product 5", 1.0f));
        maxId = 5L;
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product get(long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean add(Product product) {
        Long id = product.getId();
        if (id == null) {
            maxId ++;
            product.setId(maxId);
            return products.add(product);
        }else{
            return update(product);
        }
    }

    public boolean update(Product product) {
        int index = products.indexOf(product);
        if(index == -1 ) {
            return add(product);
        }else{
            products.set(index, product);
            return true;
        }
    }
}