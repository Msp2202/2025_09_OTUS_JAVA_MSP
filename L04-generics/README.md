ДЗ к теме "Контейнеры и алгоритмы. Применение коллекций"

Надо сдалать todo в классах из пакета homework.

Все тесты должны проходить.

Предполагается использование встроенного в jdk
функционала, поэтому реализация методов должны
быть буквально из нескольких строк


замечание на ревью =>  /** т.к. это стек лучше использовать методы push, pop */

PS
public class CustomerReverseOrder {
private final Deque<Customer> customers = new ArrayDeque<>();

    public void add(Customer customer) {
        customers.push(customer);  // ← более идиоматично для стека
    }

    public Customer take() {
        return customers.isEmpty() ? null : customers.pop();  // ← семантика стека
    }
}
Почему это важно:
Читаемость кода - сразу понятно, что это LIFO

Следование конвенциям - стандартный подход в Java

Самодокументируемость - метод names говорят сами за себя