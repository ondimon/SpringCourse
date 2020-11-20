package lessonfour.jpa.services;

import lessonfour.jpa.model.Order;
import lessonfour.jpa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    public Order get(long id) {
        return orderRepository.getOne(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
