package lessonthree.hibernate.services;

import lessonthree.hibernate.model.Customer;
import lessonthree.hibernate.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer get(long id) {
        return customerRepository.get(id);
    }

    public boolean add(Customer customer) {
        return customerRepository.add(customer);
    }

    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }
}
