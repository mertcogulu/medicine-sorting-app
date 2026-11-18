// Установка пакета
package ru.aston.medicine.model;

// Класс "Лекарство"
public record Medicine(String name, int shelfLifeDays, int price) {

    // name - название
    // shelfLifeDays - срок годности (в днях)
    // price - цена

}
