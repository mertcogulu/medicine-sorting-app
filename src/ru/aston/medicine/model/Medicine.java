package ru.aston.medicine.model;

// Класс "Лекарство"
public record Medicine(String name, int shelfLifeDays, double price) {

    // name - название
    // shelfLifeDays - срок годности (в днях)
    // price - цена
 
    public Medicine {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Наименование не должно быть пустым или отсутствовать");
        }
        if (price <= 0.0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        if (shelfLifeDays <= 0) {
            throw new IllegalArgumentException("Срок годности должен быть больше 0");
        }
    }

    public static class Builder {
        private String name;
        private int shelfLifeDays;
        private double price;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder shelfLifeDays(int shelfLifeDays) {
            this.shelfLifeDays = shelfLifeDays;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Medicine build() {
            return new Medicine(name, shelfLifeDays, price);
        }
    }
}
