package ru.otus.homework.myclasstest;

import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.myannotations.AfterEach;
import ru.otus.homework.myannotations.BeforeEach;
import ru.otus.homework.myannotations.DisplayName;
import ru.otus.homework.myannotations.Test;

@Slf4j
@DisplayName("@DisplayName - Мой Тестовый класс <MyTest> должен: ")
public class MyTest {

    @BeforeEach
    @DisplayName("@DisplayName - Подготовить тестовое окружение")
    void setUp() {
        log.info("🎬 @BeforeEach - ПОДГОТОВКА. hashCode: {}", hashCode());
        log.info("Открываем ресурсы, инициализируем данные...");
    }

    @Test
    @DisplayName("@DisplayName - успешно выполнять тест 1")
    void myTest1() {
        log.info("🧪 @Test: myTest1 - ВЫПОЛНЕНИЕ. hashCode: {}", hashCode());
        log.info("Тест 1 проходит успешно");
        /** Этот тест должен пройти */
    }

    @Test
    @DisplayName("@DisplayName - тест 2 падает с ошибкой")
    void myTest2() {
        log.info("🧪 @Test: myTest2 - ВЫПОЛНЕНИЕ. hashCode: {}", hashCode());
        log.info("Тест 2 начинает выполняться...");
        throw new IllegalArgumentException("💥 Искусственная ошибка в тесте 2");
        /** Этот тест должен упасть, но не сломать другие */
    }

    @Test
    @DisplayName("@DisplayName - успешно выполнять тест 3 и проверять изоляцию тестов в тесте 3")
    void myTest3() {
        log.info("🧪 @Test: myTest3 - ВЫПОЛНЕНИЕ. hashCode: {}", hashCode());
        log.info("Тест 3 выполняется после ошибки в тесте 2 - проверяем изоляцию");
        /** Этот тест должен пройти, несмотря на падение предыдущего */
    }

    @AfterEach
    @DisplayName("@DisplayName - очищать ресурсы после каждого теста")
    void tearDown() {
        log.info("🧹 @AfterEach - ОЧИСТКА. hashCode: {}", hashCode());
        log.info("Закрываем ресурсы, очищаем данные...");
        log.info("ВАЖНО: Этот метод должен выполниться даже если тест упал!");
    }
}
