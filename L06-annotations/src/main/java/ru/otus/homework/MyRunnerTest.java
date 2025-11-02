package ru.otus.homework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.myannotations.AfterEach;
import ru.otus.homework.myannotations.BeforeEach;
import ru.otus.homework.myannotations.DisplayName;
import ru.otus.homework.myannotations.Test;

@Slf4j
public class MyRunnerTest {

    private MyRunnerTest() {
        throw new IllegalStateException("Utility class");
    }

    public static void runTests(String className) {
        try {
            log.info("🔄 Пытаемся загрузить класс: {}", className);
            Class<?> testClass = Class.forName(className);
            log.info("✅ Класс успешно загружен: {}", testClass.getName());
            rutTests(testClass);
        } catch (ClassNotFoundException e) {
            log.error("Класс с именем: {} не найден", className);
        } catch (Exception e) {
            log.error("💥 Неожиданная ошибка при запуске тестов: {}", e.getMessage());
        }
    }

    public static void rutTests(Class<?> testClass) {
        /** Получаем все методы класса */
        Method[] methods = testClass.getDeclaredMethods();

        /** Получаем DisplayName для класса */
        String classNameDisplay = testClass.getSimpleName();
        if (testClass.isAnnotationPresent(DisplayName.class)) {
            DisplayName classDisplayName = testClass.getAnnotation(DisplayName.class);
            classNameDisplay = classDisplayName.value();
        }

        /** Разбираем методы по анотациям и кешируем(сохраняем в ArrayList для сохранения порядка выполнения методов) */
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(AfterEach.class)) {
                afterMethods.add(method);
            }
        }
        /** Получаем статистику */
        int totalTests = testMethods.size();
        int passedTests = 0;
        int failedTests = 0;

        log.info("Запуск тестов для Класса: {}", classNameDisplay);

        for (Method testMethod : testMethods) {
            /** Получаем DisplayName для метода */
            String testName = testMethod.getName();
            if (testMethod.isAnnotationPresent(DisplayName.class)) {
                DisplayName displayName = testMethod.getAnnotation(DisplayName.class);
                testName = displayName.value();
            }

            log.info("\n--- Запуск теста: {}", testName);
            try {
                /** Создаем новый объект для каждого класса согласно ТЗ
                 * (для каждой такой "тройки" надо создать СВОЙ объект класса-теста.) */
                var testInstance = testClass.getDeclaredConstructor().newInstance();

                /** Выполняем Before методы */
                runMethods(beforeMethods, testInstance, "@BeforeEach");

                /**Выполняем Тест*/
                testMethod.setAccessible(true);
                testMethod.invoke(testInstance);

                /**Выполняем After методы*/
                runMethods(afterMethods, testInstance, "@AfterEach");

                log.info("Тест: {} пройден успешно", testName);
                passedTests++;

            } catch (Exception e) {
                log.error("❌ Тест: {} упал с ошибкой: ", testName, e);
                failedTests++;
            }
        }

        log.info("ИТОГИ для {}", classNameDisplay);
        log.info("Найдено тестов: {}", totalTests);
        log.info("Успешно тестов: {}", passedTests);
        log.info("Не пройденных тестов : {}", failedTests);
    }

    private static void runMethods(List<Method> methods, Object instance, String methodType) {
        for (Method method : methods) {
            try {
                /** Получаем DisplayName для BeforeEach/AfterEach методов */
                String methodName = method.getName();
                if (method.isAnnotationPresent(DisplayName.class)) {
                    DisplayName displayName = method.getAnnotation(DisplayName.class);
                    methodName = displayName.value();
                }

                method.setAccessible(true);
                method.invoke(instance);

                log.info("✅ {} метод '{}' выполнен", methodType, methodName);
                ;

            } catch (Exception e) {
                log.error("❌ Ошибка в методе '{}': ", method.getName(), e);
            }
        }
    }
}
