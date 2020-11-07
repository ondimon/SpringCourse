package lessonone;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product get(long id);
    boolean add(Product product);
}
