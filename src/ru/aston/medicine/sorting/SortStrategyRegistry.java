// Установка пакета
package ru.aston.medicine.sorting;

// Импорт пакетов
import java.util.Map;
import java.util.function.Supplier;

// Класс "Реестр стратегий сортировки"
public class SortStrategyRegistry {

    // Поля
    private static final Map<String, Supplier<SortStrategy>> REGISTRY = Map.of(  // карта, хранящая пары значений
            "bubble", BubbleSortStrategy::new,                                   // "имя стратегии сортировки -
            "insertion", InsertionSortStrategy::new,                             // ссылка на конструктор
            "merge", MergeSortStrategy::new                                      // стратегии сортировки"
    );

    // Конструктор (private, чтобы никто не создал экземпляр класса "SortStrategyRegistry")
    private SortStrategyRegistry() {}

    // Методы

    // Метод получения стратегии сортировки по имени стратегии сортировки
    // Args:
    //      nameSortStrategy (String): Имя стратегии сортировки
    public static SortStrategy get(String nameSortStrategy) {
        Supplier<SortStrategy> supplier = REGISTRY.get(nameSortStrategy);
        if(supplier == null) {
            throw new IllegalArgumentException(
                    "Стратегия сортировки \"" + nameSortStrategy + "\" не поддерживается.\n" +
                            "Поддерживаемые стратегии сортировки: " + REGISTRY.keySet() + ".\n"
            );
        }
        return supplier.get();
    }

}
