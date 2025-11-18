package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    private final NavigableMap<Customer, String> data = new TreeMap<>(
            /**
             * Comparator.comparingLong() - статический метод для создания компаратора для long-полей
             *             Customer::getScores - method reference (ссылка на метод) к геттеру getScores()
             *              можем использовать Comparator<Customer> comparator = (c1, c2) -> Long.compare(c1.getScores(), c2.getScores());
             */
            Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return copyEntry(data.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        /**
         * Метод higherEntry() класса TreeMap в Java позволяет найти наименьшую пару «ключ-значение»,
         *         которая строго больше заданного ключа. Если такой записи нет, метод возвращает null.
         */
        return copyEntry(data.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        this.data.put(customer, data);
    }

    /**
     * Создаем копию Customer с теми же данными, учитывая изменения в Test
     * @param entry
     * @return
     */
    private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> entry) {
        if (entry == null) {
            return null;
        }
        Customer opriginal = entry.getKey();
        return Map.entry(new Customer(opriginal.getId(), opriginal.getName(), opriginal.getScores()), entry.getValue());
    }
}
