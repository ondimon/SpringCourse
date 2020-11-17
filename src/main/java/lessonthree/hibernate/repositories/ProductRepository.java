package lessonthree.hibernate.repositories;

import lessonthree.hibernate.model.Product;
import lessonthree.hibernate.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private DBService dbService;

    public List<Product> getAllProducts() {
        List<Product> productList = dbService.executeQuery("SELECT p FROM Product p", Product.class);
        return Collections.unmodifiableList(productList);
    }

    public Product get(Long id) {
        return dbService.get(Product.class, id);
    }

    public boolean add(Product product) {
        return dbService.saveOrUpdate(product);
    }

    public boolean update(Product product) {
        return dbService.saveOrUpdate(product);
    }
}