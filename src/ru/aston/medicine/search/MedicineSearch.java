package ru.aston.medicine.search;

import ru.aston.medicine.model.Medicine;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class MedicineSearch {

    private MedicineSearch() {
    }

    public static int binarySearch(List<Medicine> medicines,
                                   Medicine key,
                                   Comparator<Medicine> comparator) {

        Objects.requireNonNull(medicines, "medicines");
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(comparator, "comparator");

        // Границы той части списка, в которой ещё может находиться элемент
        int left = 0;
        int right = medicines.size() - 1;

        while (left <= right) {
            // Середина текущей части списка
            // Считаем именно так, а не (left + right) / 2, чтобы избежать переполнения int
            int middle = left + (right - left) / 2;

            // Сравниваем средний элемент с искомым
            int compareResult = comparator.compare(medicines.get(middle), key);

            // Элемент найден
            if (compareResult == 0) {
                return middle;
            }

            // Средний элемент меньше искомого, значит искомый правее
            if (compareResult < 0) {
                left = middle + 1;
            }

            // Средний элемент больше искомого, значит искомый левее
            else {
                right = middle - 1;
            }
        }

        // Элемент не найден
        return -1;
    }
}
