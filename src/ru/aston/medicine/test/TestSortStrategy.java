// Установка пакета
package ru.aston.medicine.test;

// Импорт пакетов
import java.util.stream.Stream;
import ru.aston.medicine.sorting.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.*;
import ru.aston.medicine.model.Medicine;
import static org.junit.jupiter.api.Assertions.*;

// Класс-тестировщик интерфейса "ru.aston.medicine.sorting.SortStrategy"
class TestSortStrategy {

    // Метод, возвращающий поток всех стратегий из реестра (класс "ru.aston.medicine.sorting.SortStrategyRegistry")
    // Return:
    //      (Stream<SortStrategy>): Поток всех стратегий сортировки, указанных в реестре
    private static Stream<SortStrategy> strategyProvider() {
        return SortStrategyRegistry.getNamesAllStrategies().stream()
                .map(SortStrategyRegistry::get);
    }

    // Метод тестирования каждой стратегии сортировки из реестра по полю "name"
    // Args:
    //      sortStrategy (SortStrategy): Стратегия сортировки, которую нужно протестировать
    // @ParameterizedTest - запустить этот метод несколько раз, каждый раз передавая ему новые входные данные
    @ParameterizedTest
    // @MethodSource("methodSource") - входные данные для этого метода взять из метода methodSource
    @MethodSource("strategyProvider")
    void testSortStrategiesByName(SortStrategy sortStrategy) {

        // Создаем 10 объектов класса "ru.aston.medicine.model.Medicine" и помещаем их в список
        List<Medicine> medicines = new ArrayList<>(List.of(
                new Medicine("Ибупрофен", 365, 250),
                new Medicine("Глицин", 30, 78),
                new Medicine("Ацикловир", 70, 1000),
                new Medicine("Метронидазол", 500, 653),
                new Medicine("Кетопрофен", 730, 684),
                new Medicine("Бисопролол", 20, 56),
                new Medicine("Диклофенак", 120, 255),
                new Medicine("Лидокаин", 1460, 2500),
                new Medicine("Верапамил", 180, 200),
                new Medicine("Дротаверин", 300, 285)
        ));

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем список medicines по полю "name"
        sorter.sort(medicines, MedicineComparators.byName);

        // Получим список полей "name" объектов списка medicines в отсортированном по полю "name" порядке
        List<String> actualNames = medicines.stream()
                .map(Medicine::name)
                .toList();

        // Создаем истинный список полей "name"
        List<String> trueNames = List.of(
                "Ацикловир",
                "Бисопролол",
                "Верапамил",
                "Глицин",
                "Диклофенак",
                "Дротаверин",
                "Ибупрофен",
                "Кетопрофен",
                "Лидокаин",
                "Метронидазол"
        );

        // Сравниваем истинный список полей "name" с реальным
        assertEquals(trueNames, actualNames);

    }

    // Метод тестирования каждой стратегии сортировки из реестра по полю "shelfLifeDays"
    // Args:
    //      sortStrategy (SortStrategy): Стратегия сортировки, которую нужно протестировать
    // @ParameterizedTest - запустить этот метод несколько раз, каждый раз передавая ему новые входные данные
    @ParameterizedTest
    // @MethodSource("methodSource") - входные данные для этого метода взять из метода methodSource
    @MethodSource("strategyProvider")
    void testSortStrategiesByShelfLifeDays(SortStrategy sortStrategy) {

        // Создаем 10 объектов класса "ru.aston.medicine.model.Medicine" и помещаем их в список
        List<Medicine> medicines = new ArrayList<>(List.of(
                new Medicine("Ибупрофен", 365, 250),
                new Medicine("Глицин", 30, 78),
                new Medicine("Ацикловир", 70, 1000),
                new Medicine("Метронидазол", 500, 653),
                new Medicine("Кетопрофен", 730, 684),
                new Medicine("Бисопролол", 20, 56),
                new Medicine("Диклофенак", 120, 255),
                new Medicine("Лидокаин", 1460, 2500),
                new Medicine("Верапамил", 180, 200),
                new Medicine("Дротаверин", 300, 285)
        ));

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем список medicines по полю "shelfLifeDays"
        sorter.sort(medicines, MedicineComparators.byShelfLifeDays);

        // Получим список полей "shelfLifeDays" объектов списка medicines в отсортированном по полю "shelfLifeDays" порядке
        List<Integer> actualShelfLifeDays = medicines.stream()
                .map(Medicine::shelfLifeDays)
                .toList();

        // Создаем истинный список полей "shelfLifeDays"
        List<Integer> trueShelfLifeDays = List.of(
                20,
                30,
                70,
                120,
                180,
                300,
                365,
                500,
                730,
                1460
        );

        // Сравниваем истинный список полей "shelfLifeDays" с реальным
        assertEquals(trueShelfLifeDays, actualShelfLifeDays);

    }

    // Метод тестирования каждой стратегии сортировки из реестра по полю "price"
    // Args:
    //      sortStrategy (SortStrategy): Стратегия сортировки, которую нужно протестировать
    // @ParameterizedTest - запустить этот метод несколько раз, каждый раз передавая ему новые входные данные
    @ParameterizedTest
    // @MethodSource("methodSource") - входные данные для этого метода взять из метода methodSource
    @MethodSource("strategyProvider")
    void testSortStrategiesByPrice(SortStrategy sortStrategy) {

        // Создаем 10 объектов класса "ru.aston.medicine.model.Medicine" и помещаем их в список
        List<Medicine> medicines = new ArrayList<>(List.of(
                new Medicine("Ибупрофен", 365, 250),
                new Medicine("Глицин", 30, 78),
                new Medicine("Ацикловир", 70, 1000),
                new Medicine("Метронидазол", 500, 653),
                new Medicine("Кетопрофен", 730, 684),
                new Medicine("Бисопролол", 20, 56),
                new Medicine("Диклофенак", 120, 255),
                new Medicine("Лидокаин", 1460, 2500),
                new Medicine("Верапамил", 180, 200),
                new Medicine("Дротаверин", 300, 285)
        ));

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем список medicines по полю "price"
        sorter.sort(medicines, MedicineComparators.byPrice);

        // Получим список полей "price" объектов списка medicines в отсортированном по полю "price" порядке
        List<Integer> actualPrice = medicines.stream()
                .map(Medicine::price)
                .toList();

        // Создаем истинный список полей "price"
        List<Integer> truePrice = List.of(
                56,
                78,
                200,
                250,
                255,
                285,
                653,
                684,
                1000,
                2500
        );

        // Сравниваем истинный список полей "price" с реальным
        assertEquals(truePrice, actualPrice);

    }

    // Метод тестирования каждой стратегии сортировки из реестра по полю "name" для пустого списка
    // Args:
    //      sortStrategy (SortStrategy): Стратегия сортировки, которую нужно протестировать
    // @ParameterizedTest - запустить этот метод несколько раз, каждый раз передавая ему новые входные данные
    @ParameterizedTest
    // @MethodSource("methodSource") - входные данные для этого метода взять из метода methodSource
    @MethodSource("strategyProvider")
    void testSortStrategiesByNameForEmptyList(SortStrategy sortStrategy) {

        // Создаем пустой список лекарств
        List<Medicine> medicines = new ArrayList<>();

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем список medicines по полю "name"
        sorter.sort(medicines, MedicineComparators.byName);

        // Проверяем, отсортированный список является пустым
        assertTrue(medicines.isEmpty());

    }

    // Метод тестирования каждой стратегии сортировки из реестра по полю "shelfLifeDays" для пустого списка
    // Args:
    //      sortStrategy (SortStrategy): Стратегия сортировки, которую нужно протестировать
    // @ParameterizedTest - запустить этот метод несколько раз, каждый раз передавая ему новые входные данные
    @ParameterizedTest
    // @MethodSource("methodSource") - входные данные для этого метода взять из метода methodSource
    @MethodSource("strategyProvider")
    void testSortStrategiesByShelfLifeDaysForEmptyList(SortStrategy sortStrategy) {

        // Создаем пустой список лекарств
        List<Medicine> medicines = new ArrayList<>();

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем список medicines по полю "shelfLifeDays"
        sorter.sort(medicines, MedicineComparators.byShelfLifeDays);

        // Проверяем, отсортированный список является пустым
        assertTrue(medicines.isEmpty());

    }

    // Метод тестирования каждой стратегии сортировки из реестра по полю "price" для пустого списка
    // Args:
    //      sortStrategy (SortStrategy): Стратегия сортировки, которую нужно протестировать
    // @ParameterizedTest - запустить этот метод несколько раз, каждый раз передавая ему новые входные данные
    @ParameterizedTest
    // @MethodSource("methodSource") - входные данные для этого метода взять из метода methodSource
    @MethodSource("strategyProvider")
    void testSortStrategiesByPriceForEmptyList(SortStrategy sortStrategy) {

        // Создаем пустой список лекарств
        List<Medicine> medicines = new ArrayList<>();

        // Создаем сортировщика
        Sorter sorter = new Sorter(sortStrategy);

        // Сортируем список medicines по полю "price"
        sorter.sort(medicines, MedicineComparators.byPrice);

        // Проверяем, отсортированный список является пустым
        assertTrue(medicines.isEmpty());

    }

}
