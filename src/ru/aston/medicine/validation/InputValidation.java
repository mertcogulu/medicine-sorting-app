package ru.aston.medicine.validation;

public class InputValidation {

    // Проверка названия
    public static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Наименование не должно быть пустым.");
        }
        return name.trim();
    }

    // Парсинг срока годности
    public static int parseShelfLifeDays(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Срок годности не может быть пустым.");
        }
        try {
            int days = Integer.parseInt(input.trim());
            if (days <= 0) {
                throw new IllegalArgumentException("Срок годности должен быть больше 0.");
            }
            return days;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Срок годности должен быть целым числом.");
        }
    }

    // Парсинг цены с защитой от NaN и Infinity
    public static double parsePrice(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Цена не может быть пустой.");
        }
        try {
            // Меняем запятую на точку
            double price = Double.parseDouble(input.trim().replace(',', '.'));
            
            if (Double.isNaN(price) || Double.isInfinite(price)) {
                throw new IllegalArgumentException("Цена должна быть реальным числом.");
            }
            if (price <= 0.0) {
                throw new IllegalArgumentException("Цена должна быть больше 0.");
            }
            return price;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Цена должна быть числом (например, 150.50).");
        }
    }

    // Проверка выбора пункта меню
    public static int parseMenuChoice(String input, int min, int max) {
        try {
            int choice = Integer.parseInt(input.trim());
            if (choice < min || choice > max) {
                throw new IllegalArgumentException("Выберите пункт от " + min + " до " + max);
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Введите число.");
        }
    }

    // Проверка, что строка из файла имеет правильный формат
    public static void validateFileLineFormat(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Строка пустая.");
        }
        String[] parts = line.split(";");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки. Ожидается: Название;Срок;Цена");
        }
    }
}