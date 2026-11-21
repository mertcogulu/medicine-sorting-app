// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.Medicine;

// Класс "Стратегия сортировки вставками"
public class InsertionSortStrategy implements SortStrategy {

    // Метод сортировки объектов класса в порядке возрастания / в алфавитном порядке
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    //      comparator (Comparator<Medicine>): Компаратор, указывающий,
    //      по какому полю класса "Medicine" нужно сортировать список medicines
    @Override
    public void sort(List<Medicine> medicines, Comparator<Medicine> comparator) {
        // Перебираем каждый элемент списка
        for(int left = 0; left < medicines.size(); left++) {
            // Получаем значение текущего элемента списка
            Medicine value = medicines.get(left);
            // Сравниваем текущий элемент списка с элементами из
            // отсортированной части списка (элементы, стоящие перед текущим)
            int i = left - 1;
            for(; i >= 0; i--) {
                // Если текущий элемент списка меньше i-го элемента списка
                if(comparator.compare(value, medicines.get(i)) < 0) {
                    // i-ый элемент списка нужно сдвинуть вправо
                    medicines.set(i + 1, medicines.get(i));
                }
                // Если текущий элемент списка больше i-го элемента списка или равен ему
                else {
                    // Переходим к сравнению текущего элемента списка
                    // со следующим элементом из отсортированной части списка
                    break;
                }
            }
            // Когда нашли подходящее место в массиве для текущего элемента, вставляем его туда
            medicines.set(i + 1, value);
        }
    }

    // Метод сортировки только объектов класса с четными значениями числового поля "shelfLifeDays"
    // (объекты с нечетными значениями числового поля остаются на месте)
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    @Override
    public void sortEvenShelfLifeDays(List<Medicine> medicines) {
        // Получаем список только объектов класса с четными значениями
        // числового поля "shelfLifeDays" и список их индексов в списке medicines
        EvenMedicines evenMedicines = SortStrategy.getEvenShelfLifeDays(medicines);
        List<Medicine> medicinesEven = evenMedicines.medicines();
        List<Integer> indicesEven = evenMedicines.indices();
        // Выбираем стратегию сортировки пузырьком
        SortStrategy sortStrategy = SortStrategyRegistry.get("insertion");
        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);
        // Сортируем по сроку годности
        sorter.sort(medicinesEven, MedicineComparators.byShelfLifeDays);
        // Расставляем отсортированные элементы
        for(int i = 0; i < medicinesEven.size(); i++) {
            medicines.set(indicesEven.get(i), medicinesEven.get(i));
        }
    }

}
