package lessonthree.hibernate.repositories;

import lessonthree.hibernate.model.Order;
import lessonthree.hibernate.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private DBService dbService;

    public Order get(Long id) {
        return dbService.get(Order.class, id);
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = dbService.executeQuery("SELECT o FROM Order o ORDER BY o.date", Order.class);
        return Collections.unmodifiableList(orderList);
    }
}
