// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.Medicine;

// Класс для сравнения объектов класса "ru.aston.medicine.model.Medicine"
// (хранит компараторы для каждого поля класса "ru.aston.medicine.model.Medicine")
public class MedicineComparators {

    // Конструктор (private, чтобы никто не создал экземпляр класса "MedicineComparators")
    private MedicineComparators() {}

    // Компараторы:
    // - public - чтобы ими можно было пользоваться в любом месте кода
    // - static - чтобы ими можно было пользоваться, не создавая экземпляр класса "MedicineComparators"
    // - final - чтобы никто случайно не мог их изменить

    // Компаратор для поля "name"
    public static final Comparator<Medicine> byName = new Comparator<Medicine>() {

        // Метод сравнения двух объектов
        @Override
        public int compare(Medicine m1, Medicine m2) {
            // Objects.compare() - обеспечивает защиту при сравнении объектов на случай, если один из них равен null:
            // - если оба объекта null - возвращает 0 (они равны)
            // - если первый объект null, а второй нет - возвращает "-" число (null считается < любого реального объекта)
            // - если первый объект не null, а второй null - возвращает "+" число
            // String.CASE_INSENSITIVE_ORDER - компаратор для строк, игнорирующий регистр букв
            return Objects.compare(m1.name(), m2.name(), String.CASE_INSENSITIVE_ORDER);
        }

    };

    // Компаратор для поля "shelfLifeDays"
    public static final Comparator<Medicine> byShelfLifeDays = new Comparator<Medicine>() {

        // Метод сравнения двух объектов
        @Override
        public int compare(Medicine m1, Medicine m2) {
            return Integer.compare(m1.shelfLifeDays(), m2.shelfLifeDays());
        }

    };

    // Компаратор для поля "price"
    public static final Comparator<Medicine> byPrice = new Comparator<Medicine>() {

        // Метод сравнения двух объектов
        @Override
        public int compare(Medicine m1, Medicine m2) {
            return Integer.compare(m1.price(), m2.price());
        }

    };

}
