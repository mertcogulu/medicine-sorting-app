// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.Medicine;

// Класс "Сортировщик объектов класса "ru.aston.medicine.sorting.model.Medicine""
public final class Sorter {

    // Поля
    private final SortStrategy sortStrategy;  // стратегия сортировки

    // Конструкторы
    public Sorter(SortStrategy sortStrategy) {
        // Проверяем стратегию сортировки sortStrategy на != null
        this.sortStrategy = Objects.requireNonNull(sortStrategy, "sortStrategy");
    }

    // Getters
    public SortStrategy getSortStrategy() {
        return this.sortStrategy;
    }

    // Методы

    // Метод сортировки объектов класса "ru.aston.medicine.model.Medicine" в порядке возрастания / в алфавитном порядке
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    //      comparator (Comparator<Medicine>): Компаратор, указывающий,
    //      по какому полю класса "Medicine" нужно сортировать список medicines
    public void sort(List<Medicine> medicines, Comparator<Medicine> comparator) {
        // Проверяем список medicines на != null
        Objects.requireNonNull(medicines, "medicines");
        // Проверяем компаратор comparator на != null
        Objects.requireNonNull(comparator, "comparator");
        // Сортируем
        this.sortStrategy.sort(medicines, comparator);
    }

    // Метод сортировки только объектов класса с четными значениями числового поля "shelfLifeDays"
    // (объекты с нечетными значениями числового поля остаются на месте)
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    public void sortEvenShelfLifeDays(List<Medicine> medicines) {
        // Проверяем список medicines на != null
        Objects.requireNonNull(medicines, "medicines");
        // Сортируем
        this.sortStrategy.sortEvenShelfLifeDays(medicines);
    }

}
