package ru.aston.medicine.strategy;

import ru.aston.medicine.model.Medicine;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomFillStrategy implements FillStrategy {

    private final int count;

    public RandomFillStrategy(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    "Количество лекарств не может быть отрицательным"
            );
        }
        this.count = count;
    }

    @Override
    public List<Medicine> fill() {
        String[] names = {"Аспирин", "Ибупрофен", "Парацетамол", "Витамин C"};

        return IntStream.range(0, count)
                .mapToObj(index -> {
                    ThreadLocalRandom random = ThreadLocalRandom.current();

                    return new Medicine.Builder()
                            .name(names[random.nextInt(names.length)])
                            .shelfLifeDays(random.nextInt(1, 731))
                            .price(random.nextInt(1, 1001))
                            .build();
                })
                .toList();
    }
}