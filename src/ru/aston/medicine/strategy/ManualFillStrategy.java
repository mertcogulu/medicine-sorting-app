package ru.aston.medicine.strategy;

import ru.aston.medicine.model.Medicine;
import ru.aston.medicine.validation.InputValidation;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ManualFillStrategy implements FillStrategy {

    private final Scanner scanner;
    private final int count;

    public ManualFillStrategy(Scanner scanner, int count) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    "Количество лекарств не может быть отрицательным"
            );
        }

        this.scanner = scanner;
        this.count = count;
    }

    @Override
    public List<Medicine> fill() {
        return IntStream.range(0, count)
                .mapToObj(index -> readMedicine())
                .toList();
    }

    private Medicine readMedicine() {
        while (true) {
            try {
                System.out.print("Название: ");
                String name = InputValidation.validateName(scanner.nextLine());

                System.out.print("Срок годности в днях: ");
                int days = InputValidation.parseShelfLifeDays(scanner.nextLine());

                System.out.print("Цена: ");
                double price = InputValidation.parsePrice(scanner.nextLine());

                return new Medicine.Builder()
                        .name(name)
                        .shelfLifeDays(days)
                        .price(price)
                        .build();

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Введите данные этого лекарства заново.");
            }
        }
    }
}