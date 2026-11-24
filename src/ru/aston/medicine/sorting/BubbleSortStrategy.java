// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.Medicine;

// Класс "Стратегия сортировки пузырьком"
public class BubbleSortStrategy implements SortStrategy {

    // Метод сортировки объектов класса в порядке возрастания / в алфавитном порядке
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    //      comparator (Comparator<Medicine>): Компаратор, указывающий,
    //      по какому полю класса "Medicine" нужно сортировать список medicines
    @Override
    public void sort(List<Medicine> medicines, Comparator<Medicine> comparator) {
        // Инициализируем флаг, который показывает, нужно ли еще раз пройтись
        // по списку (true) или нет (false) (список отсортирован (true) или нет (false))
        boolean needIterateList = true;
        // Проходимся по списку, пока не отсортируем его
        while(needIterateList) {
            needIterateList = false;
            for(int i = 1; i < medicines.size(); i++) {
                if(comparator.compare(medicines.get(i), medicines.get(i - 1)) < 0) {
                    swap(medicines, i - 1, i);
                    needIterateList = true;
                }
            }
        }
    }

    // Метод "поменять 2 элемента списка местами"
    // Args:
    //      list (List<T>): Список, в котором нужно поменять местами элементы
    //      index1 (int): Индекс первого элемента
    //      index2 (int): Индекс второго элемента
    private static <T> void swap(List<T> list, int index1, int index2) {
        T copyElement1 = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, copyElement1);
    }

}
