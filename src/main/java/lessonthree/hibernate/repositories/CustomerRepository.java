package lessonthree.hibernate.repositories;

import lessonthree.hibernate.model.Customer;
import lessonthree.hibernate.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    private DBService dbService;

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = dbService.executeQuery("SELECT c FROM Customer c", Customer.class);
        return Collections.unmodifiableList(customerList);
    }

    public Customer get(Long id) {
        return dbService.get(Customer.class, id);
    }

    public boolean add(Customer customer) {
        return dbService.saveOrUpdate(customer);
    }

    public boolean update(Customer customer) {
        return dbService.saveOrUpdate(customer);
    }
}
