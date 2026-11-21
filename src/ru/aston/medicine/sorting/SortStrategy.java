// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.*;
import ru.aston.medicine.model.Medicine;

// Интерфейс "Стратегия сортировки объектов класса "ru.aston.medicine.model.Medicine""
public interface SortStrategy {

    // Метод сортировки объектов класса в порядке возрастания / в алфавитном порядке
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    //      comparator (Comparator<Medicine>): Компаратор, указывающий,
    //      по какому полю класса "Medicine" нужно сортировать список medicines
    void sort(List<Medicine> medicines, Comparator<Medicine> comparator);

    // Метод сортировки только объектов класса с четными значениями числового поля "shelfLifeDays"
    // (объекты с нечетными значениями числового поля остаются на месте)
    // Args:
    //      medicines (List<Medicine>): Список лекарств, который нужно отсортировать
    void sortEvenShelfLifeDays(List<Medicine> medicines);

    // Метод получения списка только объектов класса с четными значениями
    // числового поля "shelfLifeDays" и списка их индексов в исходном списке
    // Args:
    //      medicines (List<Medicine>): Список лекарств, из которого нужно получить
    //      список только объектов класса с четными значениями поля "shelfLifeDays"
    // Return:
    //      (EvenMedicines): Список только объектов класса с четными значениями
    //      поля "shelfLifeDays" и список их индексов в списке medicines
    static EvenMedicines getEvenShelfLifeDays(List<Medicine> medicines) {
        // Создаем список, в котором будут храниться только объекты класса с четными значениями поля "shelfLifeDays"
        List<Medicine> medicinesEven = new ArrayList<>();
        // Создаем список, в котором будут храниться индексы элементов из medicinesEven в medicines
        List<Integer> indicesEven = new ArrayList<>();
        // Проверяем каждый элемент medicines на четность его поля "shelfLifeDays"
        for(int i = 0; i < medicines.size(); i++) {
            // Если поле "shelfLifeDays" текущего объекта четное
            if(medicines.get(i).shelfLifeDays() % 2 == 0) {
                // Добавляем текущий элемент в medicinesEven
                medicinesEven.add(medicines.get(i));
                // Добавляем индекс текущего элемента в списке medicines в indexEven
                indicesEven.add(i);
            }
        }
        // Возвращаем списки
        return new EvenMedicines(medicinesEven, indicesEven);
    }

}
