/* Импорт класса для типобезопасной конфигурации задачи shadowJar */
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

/* Плагины для модуля */
plugins {
    id("java")                              /* Плагин для Java компиляции */
    id("application")                       /* Плагин для запуска приложений */
    id("com.github.johnrengelman.shadow")   /* Плагин для создания толстый (fat jar) */
}

/* Конфигурация плагина application - указываем главный класс */
application {
    mainClass.set("ru.otus.HelloOtus")  /* Главный класс приложения */
}

/* Зависимости проекта */
dependencies {
    /* Библиотека Guava от Google - версия управляется в корневом build.gradle.kts */
    implementation("com.google.guava:guava")
}

/* Конфигурация задач Gradle */
tasks {
    /* Конфигурация стандартной задачи jar для создания ТОНКОГО JAR */
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("hw01-gradle")  /* Базовое имя архива */
        archiveVersion.set("1.0")           /* Версия архива */
        archiveClassifier.set("")           /* Классификатор (пустой = основной артефакт) */

        /* Конфигурация манифеста JAR файла */
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.HelloOtus"))  /* Главный класс для запуска */
        }
    }

    /* Модификация стандартной задачи build */
    build {
        /* Задача build теперь зависит от shadowJar - при сборке создается fat jar */
        dependsOn(shadowJar)
    }
}