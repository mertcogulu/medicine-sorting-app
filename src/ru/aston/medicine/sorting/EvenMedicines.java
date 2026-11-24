// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.List;
import ru.aston.medicine.model.Medicine;

// Класс для хранения:
// 1) списка только объектов класса "ru.aston.medicine.model.Medicine"
// с четными значениями числового поля "shelfLifeDays";
// 2) списка индексов этих объектов в исходном списке
public record EvenMedicines(List<Medicine> medicines, List<Integer> indices) {}
