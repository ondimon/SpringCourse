package lessonfour.jpa.repositories;

import lessonfour.jpa.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product>  findByCostGreaterThan(Float minCost, Pageable page);
    List<Product>  findByCostLessThan(Float maxCost, Pageable page);
    List<Product>  findByCostBetween(Float minCost, Float maxCost, Pageable page);
}
