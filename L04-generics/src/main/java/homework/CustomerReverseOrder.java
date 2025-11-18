package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {
    private final Deque<Customer> customers = new ArrayDeque<>();

    /**
     * Для CustomerReverseOrder реализуем принцип LIFO
     * @param customer
     */
    public void add(Customer customer) {
        customers.addFirst(customer);
    }

    public Customer take() {
        return customers.isEmpty() ? null : customers.removeFirst();
        /** т.к. это стек лучше использовать методы push, pop */
    }
}
