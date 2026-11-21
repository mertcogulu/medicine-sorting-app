// Установка пакета
package ru.aston.medicine.test;

// Импорт пакетов
import ru.aston.medicine.model.Medicine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Класс-тестировщик класса "ru.aston.medicine.model.Medicine"
class TestMedicine {

    // Методы

    // Метод тестирования создания объекта класса "ru.aston.medicine.model.Medicine"
    @Test
    void testCreateMedicine() {
        // Создаем объект класса "ru.aston.medicine.model.Medicine"
        Medicine medicine = new Medicine.Builder()
                .name("Парацетамол")
                .shelfLifeDays(730)
                .price(100)
                .build();
        // Проверяем, правильные ли значения были присвоены полям объекта medicine
        // assertEquals(trueValue, actualValue) - сравнивает ЗНАЧЕНИЯ (НЕ ССЫЛКИ) trueValue и actualValue:
        // - если trueValue == actualValue - тест выполняется дальше;
        // - если trueValue != actualValue - тест падает.
        assertEquals("Парацетамол", medicine.name());
        assertEquals(730, medicine.shelfLifeDays());
        assertEquals(100, medicine.price());
    }

    // Метод тестирования создания объекта класса "ru.aston.medicine.model.Medicine" при name = null
    @Test
    void testNameIsNull() {
        // Проверяем, что при name = null будет выброшено исключение
        // assertThrows(exceptionClass, code) - возвращает экземпляр пойманного исключения:
        // - exceptionClass - класс исключения
        // - code - код, выполнение которого должно вызвать исключение класса exceptionClass
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Medicine.Builder()
                        .name(null)
                        .shelfLifeDays(100)
                        .price(100)
                        .build()
        );
        // Проверяем, совпадает ли текст сообщения пойманного исключения с ожидаемым
        assertEquals("Наименование не должно быть пустым или отсутствовать", exception.getMessage());
    }

    // Метод тестирования создания объекта класса "ru.aston.medicine.model.Medicine" при name = "  "
    @Test
    void testNameIsBlank() {
        // Проверяем, что при name = "  " будет выброшено исключение
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Medicine.Builder()
                        .name("  ")
                        .shelfLifeDays(100)
                        .price(100)
                        .build()
        );
        // Проверяем, совпадает ли текст сообщения пойманного исключения с ожидаемым
        assertEquals("Наименование не должно быть пустым или отсутствовать", exception.getMessage());
    }

    // Метод тестирования создания объекта класса "ru.aston.medicine.model.Medicine" при shelfLifeDays <= 0
    @Test
    void testShelfLifeDaysLessOrEqualZero() {
        // Проверяем, что при shelfLifeDays = 0 будет выброшено исключение
        IllegalArgumentException exception1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Medicine.Builder()
                        .name("Парацетамол")
                        .shelfLifeDays(0)
                        .price(100)
                        .build()
        );
        // Проверяем, совпадает ли текст сообщения пойманного исключения с ожидаемым
        assertEquals("Срок годности должен быть больше 0", exception1.getMessage());
        // Проверяем, что при shelfLifeDays < 0 будет выброшено исключение
        IllegalArgumentException exception2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Medicine.Builder()
                        .name("Парацетамол")
                        .shelfLifeDays(-100)
                        .price(100)
                        .build()
        );
        // Проверяем, совпадает ли текст сообщения пойманного исключения с ожидаемым
        assertEquals("Срок годности должен быть больше 0", exception2.getMessage());
    }

    // Метод тестирования создания объекта класса "ru.aston.medicine.model.Medicine" при price <= 0
    @Test
    void testPriceLessOrEqualZero() {
        // Проверяем, что при price = 0 будет выброшено исключение
        IllegalArgumentException exception1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Medicine.Builder()
                        .name("Парацетамол")
                        .shelfLifeDays(100)
                        .price(0)
                        .build()
        );
        // Проверяем, совпадает ли текст сообщения пойманного исключения с ожидаемым
        assertEquals("Цена должна быть больше 0", exception1.getMessage());
        // Проверяем, что при price < 0 будет выброшено исключение
        IllegalArgumentException exception2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Medicine.Builder()
                        .name("Парацетамол")
                        .shelfLifeDays(100)
                        .price(-100)
                        .build()
        );
        // Проверяем, совпадает ли текст сообщения пойманного исключения с ожидаемым
        assertEquals("Цена должна быть больше 0", exception2.getMessage());
    }

}
