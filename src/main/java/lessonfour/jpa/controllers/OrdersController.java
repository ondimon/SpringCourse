package lessonfour.jpa.controllers;

import lessonfour.jpa.model.Order;
import lessonfour.jpa.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllOrder(Model model) {
        List<Order> orderList = ordersService.getAllOrders();
        model.addAttribute("orders", orderList);
        return "orders/all_orders";
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String showInfoOrderForm(@PathVariable long id, Model model) {
        model.addAttribute("order", ordersService.get(id));
        return "orders/info_order";
    }
}
