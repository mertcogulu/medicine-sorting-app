// Установка пакета
package ru.aston.medicine.test;

// Импорт пакетов
import java.util.stream.Stream;
import ru.aston.medicine.sorting.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
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
        List<Medicine> medicines = List.of(
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
        );

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

}
