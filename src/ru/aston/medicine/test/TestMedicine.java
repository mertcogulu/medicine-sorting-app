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

}
