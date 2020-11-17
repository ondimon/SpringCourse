package lessonthree.hibernate.services;

import lessonthree.hibernate.model.Order;
import lessonthree.hibernate.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    public Order get(long id) {
        return orderRepository.get(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}
