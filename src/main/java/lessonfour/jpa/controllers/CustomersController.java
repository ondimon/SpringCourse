package lessonfour.jpa.controllers;

import lessonfour.jpa.model.Customer;
import lessonfour.jpa.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomersController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllCustomers(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customers", customerList);
        return "customers/all_customers";
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String showInfoCustomerForm(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "customers/info_customer";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditCustomerForm(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "customers/edit_customer_form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.update(customer);
        return "redirect:/customers/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/add_customer_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.add(customer);
        return "redirect:/customers/";
    }
}
