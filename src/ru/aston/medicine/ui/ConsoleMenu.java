package ru.aston.medicine.ui;

import ru.aston.medicine.concurrent.MedicineCounter;
import ru.aston.medicine.io.MedicineFileWriter;
import ru.aston.medicine.model.Medicine;
import ru.aston.medicine.search.MedicineSearch;
import ru.aston.medicine.sorting.MedicineComparators;
import ru.aston.medicine.sorting.SortStrategy;
import ru.aston.medicine.sorting.SortStrategyRegistry;
import ru.aston.medicine.sorting.Sorter;
import ru.aston.medicine.strategy.FileFillStrategy;
import ru.aston.medicine.strategy.FillStrategy;
import ru.aston.medicine.strategy.ManualFillStrategy;
import ru.aston.medicine.strategy.RandomFillStrategy;
import ru.aston.medicine.validation.InputValidation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private static final String RESULT_FILE = "result.txt";
    private static final String SOURCE_FILE = "medicines.txt";

    private final Scanner scanner = new Scanner(System.in);
    private List<Medicine> medicines = new ArrayList<>();

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            switch (readChoice(1, 8)) {
                case 1 -> fillList();
                case 2 -> showList();
                case 3 -> sortList();
                case 4 -> sortEvenShelfLifeDays();
                case 5 -> searchMedicine();
                case 6 -> countOccurrences();
                case 7 -> writeToFile();
                case 8 -> running = false;
            }
        }
        System.out.println("Выход из программы.");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Меню ===");
        System.out.println("1 - Заполнить список лекарств");
        System.out.println("2 - Показать список");
        System.out.println("3 - Отсортировать список");
        System.out.println("4 - Отсортировать только четные сроки годности");
        System.out.println("5 - Найти лекарство (бинарный поиск)");
        System.out.println("6 - Посчитать вхождения лекарства");
        System.out.println("7 - Записать список в файл");
        System.out.println("8 - Выход");
    }

    private int readChoice(int min, int max) {
        while (true) {
            System.out.print("Введите номер: ");
            try {
                return InputValidation.parseMenuChoice(scanner.nextLine(), min, max);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void fillList() {
        System.out.println("1 - Случайное заполнение");
        System.out.println("2 - Ввод с клавиатуры");
        System.out.println("3 - Чтение из файла " + SOURCE_FILE);
        int source = readChoice(1, 3);

        FillStrategy strategy;
        if (source == 1) {
            strategy = new RandomFillStrategy(readCount());
        } else if (source == 2) {
            strategy = new ManualFillStrategy(scanner, readCount());
        } else {
            strategy = new FileFillStrategy(Path.of(SOURCE_FILE));
        }

        medicines = new ArrayList<>(strategy.fill());
        System.out.println("Список заполнен, лекарств: " + medicines.size());
    }

    private int readCount() {
        System.out.println("Сколько лекарств (от 1 до 100):");
        return readChoice(1, 100);
    }

    private void showList() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < medicines.size(); i++) {
            System.out.println((i + 1) + ") " + medicines.get(i));
        }
    }

    private void sortList() {
        if (isEmpty()) {
            return;
        }
        SortStrategy strategy = readStrategy();
        if (strategy == null) {
            return;
        }
        new Sorter(strategy).sort(medicines, readComparator());
        System.out.println("Список отсортирован.");
        showList();
    }

    private void sortEvenShelfLifeDays() {
        if (isEmpty()) {
            return;
        }
        SortStrategy strategy = readStrategy();
        if (strategy == null) {
            return;
        }
        new Sorter(strategy).sortEvenShelfLifeDays(medicines);
        System.out.println("Четные сроки годности отсортированы.");
        showList();
    }

    private SortStrategy readStrategy() {
        System.out.println("Доступные стратегии: " + SortStrategyRegistry.getNamesAllStrategies());
        System.out.print("Название стратегии: ");
        try {
            return SortStrategyRegistry.get(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Comparator<Medicine> readComparator() {
        System.out.println("1 - по названию");
        System.out.println("2 - по сроку годности");
        System.out.println("3 - по цене");
        return switch (readChoice(1, 3)) {
            case 1 -> MedicineComparators.byName;
            case 2 -> MedicineComparators.byShelfLifeDays;
            default -> MedicineComparators.byPrice;
        };
    }

    private void searchMedicine() {
        if (isEmpty()) {
            return;
        }
        new Sorter(SortStrategyRegistry.get("merge")).sort(medicines, MedicineComparators.byName);
        System.out.println("Список отсортирован по названию для поиска.");
        System.out.print("Введите название лекарства: ");
        String name = scanner.nextLine();

        Medicine key = new Medicine.Builder()
                .name(name)
                .shelfLifeDays(1)
                .price(1)
                .build();

        int index = MedicineSearch.binarySearch(medicines, key, MedicineComparators.byName);
        if (index < 0) {
            System.out.println("Лекарство не найдено.");
        } else {
            System.out.println("Найдено под номером " + (index + 1) + ": " + medicines.get(index));
        }
    }

    private void countOccurrences() {
        if (isEmpty()) {
            return;
        }
        showList();
        System.out.println("Выберите номер лекарства из списка:");
        Medicine target = medicines.get(readChoice(1, medicines.size()) - 1);
        MedicineCounter.countOccurrences(medicines, target);
    }

    private void writeToFile() {
        if (isEmpty()) {
            return;
        }
        MedicineFileWriter.appendAll(medicines, RESULT_FILE);
        System.out.println("Список записан в файл " + RESULT_FILE);
    }

    private boolean isEmpty() {
        if (medicines.isEmpty()) {
            System.out.println("Список пуст, сначала заполните его.");
            return true;
        }
        return false;
    }
}