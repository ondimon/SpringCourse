package lessonone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "prototype")
public class Cart {

    private ProductRepository productRepository;
    private final List<Product> products = new ArrayList<>();

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProduct(long id) {
        Product product = productRepository.get(id);
        if ( product != null ) {
            return products.add(product);
        } else {
            return false;
        }
    }

    public boolean removeProduct(long id) {
        Product product = productRepository.get(id);
        if(product != null) {
            return  products.remove(product);
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }

}
