package lessonthree.hibernate.services;

import lessonthree.hibernate.model.Product;
import lessonthree.hibernate.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product get(long id) {
        return productRepository.get(id);
    }

    public boolean add(Product product) {
        return productRepository.add(product);
    }

    public boolean update(Product product) {
        return productRepository.update(product);
    }

}
