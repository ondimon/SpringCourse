package lessonfour.jpa.controllers;

import lessonfour.jpa.model.Product;
import lessonfour.jpa.repositories.specifications.SpecificationImpl;
import lessonfour.jpa.repositories.specifications.SpecificationsBuilder;
import lessonfour.jpa.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllProducts(Model model,
                                  @RequestParam(name = "p", required = false, defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_cost", required = false) Float minCost,
                                  @RequestParam(name = "max_cost", required = false) Float maxCost,
                                  @RequestParam(name = "order", required = false) String order) {

        SpecificationsBuilder<Product> productSpecificationsBuilder = new SpecificationsBuilder<>();
        if(minCost != null) {
            productSpecificationsBuilder.with("cost", ">=", minCost, false);
        }
        if (maxCost != null) {
            productSpecificationsBuilder.with("cost", "<=", maxCost, false);
        }
        Specification<Product> spec = productSpecificationsBuilder.build();
//        Sort sort = Sort.unsorted();
//        if(sortByCost != null) {
//            sort = sortByCost.equalsIgnoreCase("desc") ?
//                    sort.and(Sort.by("cost").descending()):
//                    sort.and(Sort.by("cost").ascending());
//        }
        Sort sort = Sort.unsorted();
        Pattern pattern = Pattern.compile("(\\w+?)(:)(\\w+?),");
        Matcher matcher = pattern.matcher(order + ",");
        while (matcher.find()) {
            Sort.Direction direction = matcher.group(3).equalsIgnoreCase("desc") ?
                                        Sort.Direction.DESC:
                                        Sort.Direction.ASC;
            sort = sort.and(Sort.by(direction, matcher.group(1)));
        }

        List<Product> productList = productsService.getAllProducts(spec, pageNumber, sort);

        model.addAttribute("products", productList);
        return "products/all_products";
    }

    @RequestMapping(value = "/repo", method = RequestMethod.GET)
    public String showAllProductsRepo(Model model,
                                  @RequestParam(name = "p", required = false, defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_cost", required = false) Float minCost,
                                  @RequestParam(name = "max_cost", required = false) Float maxCost,
                                  @RequestParam(name = "sortByCost", required = false, defaultValue = "asc") String sortByCost) {

        List<Product> productList = productsService.getAllProducts(minCost, maxCost, pageNumber, sortByCost);
        model.addAttribute("products", productList);
        return "products/all_products";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditProductForm(@PathVariable long id, Model model) {
        model.addAttribute("product", productsService.get(id));
        return "products/edit_product_form";
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String showInfoProductForm(@PathVariable long id, Model model) {
        model.addAttribute("product", productsService.get(id));
        return "products/info_product";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute Product product) {
        productsService.update(product);
        return "redirect:/products/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/add_product_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product) {
        productsService.add(product);
        return "redirect:/products/";
    }




}
