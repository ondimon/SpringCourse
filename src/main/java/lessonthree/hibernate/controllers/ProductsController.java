package lessonthree.hibernate.controllers;

import lessonthree.hibernate.model.Product;
import lessonthree.hibernate.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllProducts(Model model) {
        List<Product> productList = productsService.getAllProducts();
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
