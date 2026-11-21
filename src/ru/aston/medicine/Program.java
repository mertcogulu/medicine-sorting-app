// Установка пакета
package ru.aston.medicine;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.*;
import ru.aston.medicine.sorting.*;

public class Program {

    public static void main(String[] args) {

        // Создаём 10 разных лекарств
        Medicine m1  = new Medicine("Азбука",        5,  30);
        Medicine m2  = new Medicine("Бегемот",       10, 20);
        Medicine m3  = new Medicine("Вилка",         20, 10);
        Medicine m4  = new Medicine("Дети",          30, 5);
        Medicine m5  = new Medicine("Ёжик",          7,  45);
        Medicine m6  = new Medicine("Жаропонижающее",3,  99);
        Medicine m7  = new Medicine("Здоровье",      60, 15);
        Medicine m8  = new Medicine("Иммунал",       90, 250);
        Medicine m9  = new Medicine("Корень солодки", 120, 80);
        Medicine m10 = new Medicine("Лимон",         14, 60);

        // Кладём все лекарства в список
        List<Medicine> medicines = new ArrayList<>();
        medicines.add(m9);
        medicines.add(m10);
        medicines.add(m7);
        medicines.add(m8);
        medicines.add(m6);
        medicines.add(m1);
        medicines.add(m2);
        medicines.add(m4);
        medicines.add(m3);
        medicines.add(m5);

        // Выводим список лекарств
        print(medicines);

        // Выбираем стратегию сортировки
        SortStrategy sortStrategy = SortStrategyRegistry.get("bubble");

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем по сроку годности
        // sorter.sort(medicines, MedicineComparators.byShelfLifeDays);

        // Сортируем только четные объекты списка
        sorter.sortEvenShelfLifeDays(medicines);

        // Выводим список лекарств
        print(medicines);

    }

    // Метод вывода объектов списка построчно
    public static void print(List<Medicine> medicines) {
        System.out.print("--------------------------------------------------------------------------------------\n");
        for(Medicine m : medicines) {
            System.out.print(m.toString() + "\n");
        }
        System.out.print("--------------------------------------------------------------------------------------\n");
    }

}
