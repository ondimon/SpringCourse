package lessonfour.jpa.services;

import lessonfour.jpa.model.Product;
import lessonfour.jpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProducts(Float minCost, Float maxCost, Integer pageNumber, String sortByCost) {
        if(!sortByCost.equals("asc") && !sortByCost.equals("desc")) {
            sortByCost = "asc";
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        Sort sort = Sort.by("cost");
        if (sortByCost.equals("desc")) {
            sort = sort.descending();
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5, sort);

        if(minCost != null && maxCost != null) {
            return productRepository.findByCostBetween(minCost, maxCost, pageRequest);
        }else if(minCost != null) {
            return productRepository.findByCostGreaterThan(minCost, pageRequest);
        }else if(maxCost != null) {
            return productRepository.findByCostLessThan(maxCost, pageRequest);
        }else{
            return productRepository.findAll(pageRequest).toList();
        }
    }

    public List<Product> getAllProducts(Specification<Product> spec, Integer pageNumber, Sort sort) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5, sort);
        return productRepository.findAll(spec, pageRequest).toList();
    }


    public Product get(long id) {
        return productRepository.getOne(id);
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

}
