// Установка пакета
package ru.aston.medicine.model;

// Класс "Лекарство"
public record Medicine(String name, int shelfLifeDays, int price) {

    // name - название
    // shelfLifeDays - срок годности (в днях)
    // price - цена

    public static class Builder {
        private String name;
        private int shelfLifeDays;
        private int price;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder shelfLifeDays(int shelfLifeDays) {
            this.shelfLifeDays = shelfLifeDays;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Medicine build() {
            if (this.name == null || name.isBlank()) {
                throw new IllegalArgumentException("Наименование не должно быть пустым или отсутствовать");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Цена должна быть больше 0");
            }

            if (shelfLifeDays <= 0) {
                throw new IllegalArgumentException("Срок годности должен быть больше 0");
            }
            return new Medicine(name, shelfLifeDays, price);
        }


    }
}
