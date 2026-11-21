// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.Medicine;

// Класс "Стратегия сортировки слиянием"
public class MergeSortStrategy implements SortStrategy {

    // Метод сортировки объектов класса в порядке возрастания / в алфавитном порядке
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    //      comparator (Comparator<Medicine>): Компаратор, указывающий,
    //      по какому полю класса "Medicine" нужно сортировать список medicines
    @Override
    public void sort(List<Medicine> medicines, Comparator<Medicine> comparator) {
        mergeSort(medicines, comparator, 0, medicines.size() - 1);
    }

    // Рекурсивный метод сортировки слиянием
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    //      comparator (Comparator<Medicine>): Компаратор, указывающий,
    //      по какому полю класса "Medicine" нужно сортировать список medicines
    //      leftBorder (int): Индекс левой границы сортируемой части списка (подсписка)
    //      rightBorder (int): Индекс правой границы сортируемой части списка (подсписка)
    private static void mergeSort(List<Medicine> medicines, Comparator<Medicine> comparator, int leftBorder, int rightBorder) {
        // Вычислим индекс середины подсписка
        int delimiter = leftBorder + ((rightBorder - leftBorder) / 2) + 1;
        // Если подсписок содержит больше двух элементов, то для каждой
        // половины подсписка рекурсивно вызываем текущий метод
        if(rightBorder > (leftBorder + 1)) {
            mergeSort(medicines, comparator, leftBorder, delimiter - 1);
            mergeSort(medicines, comparator, delimiter, rightBorder);
        }
        // !!! К этому моменту обе половины подсписка отсортированы
        // Создаем временный подмассив размером с подсписок
        // (нужен для удобства слияния двух половин подсписка в один подсписок)
        Medicine[] buffer = new Medicine[rightBorder - leftBorder + 1];
        // Инициализируем индекс текущего элемента в левой половине подсписка
        int currIndLeftSubarray = leftBorder;
        // Инициализируем индекс текущего элемента в правой половине подсписка
        int currIndRightSubarray = delimiter;
        // Заполняем временный подмассив
        for(int i = 0; i < buffer.length; i++) {
            // Если правая половина подсписка исчерпана
            if(currIndRightSubarray > rightBorder) {
                buffer[i] = medicines.get(currIndLeftSubarray);
                currIndLeftSubarray++;
            }
            // Если левая половина подсписка исчерпана
            else if(currIndLeftSubarray > delimiter - 1) {
                buffer[i] = medicines.get(currIndRightSubarray);
                currIndRightSubarray++;
            }
            // Если обе половины подсписка не пусты
            else {
                // Если текущий необработанный элемент левой половины подсписка
                // меньше текущего необработанного элемента правой половины подсписка
                if(comparator.compare(medicines.get(currIndLeftSubarray), medicines.get(currIndRightSubarray)) < 0) {
                    buffer[i] = medicines.get(currIndLeftSubarray);
                    currIndLeftSubarray++;
                }
                // Если текущий необработанный элемент левой половины подсписка
                // больше или равен текущего необработанного элемента правой половины подсписка
                else {
                    buffer[i] = medicines.get(currIndRightSubarray);
                    currIndRightSubarray++;
                }
            }
        }
        // Копируем временный подмассив в подсписок, начиная с индекса leftBorder
        for(int i = leftBorder; i < (leftBorder + buffer.length); i++) {
            medicines.set(i, buffer[i - leftBorder]);
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
        SortStrategy sortStrategy = SortStrategyRegistry.get("merge");
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
