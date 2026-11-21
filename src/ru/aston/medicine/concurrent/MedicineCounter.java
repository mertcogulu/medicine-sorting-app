package ru.aston.medicine.concurrent;

import ru.aston.medicine.model.Medicine;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class MedicineCounter {

    private static final int THREAD_COUNT = 4;

    // Конструктор private, экземпляр класса создавать не нужно
    private MedicineCounter() {}

    public static int countOccurrences(List<Medicine> medicines, Medicine target) {

        Objects.requireNonNull(medicines, "medicines");
        Objects.requireNonNull(target, "target");

        if (medicines.isEmpty()) {
            System.out.println("Количество вхождений: 0");
            return 0;
        }

        AtomicInteger total = new AtomicInteger(0);

        int threadCount = Math.min(THREAD_COUNT, medicines.size());

        int chunkSize = (medicines.size() + threadCount - 1) / threadCount;

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {

            // Границы участка, который обрабатывает этот поток
            int from = i * chunkSize;
            int to = Math.min(from + chunkSize, medicines.size());

            threads[i] = new Thread(() -> {
                // Считаем в локальной переменной, чтобы потоки
                // не мешали друг другу на каждом совпадении
                int localCount = 0;
                for (int j = from; j < to; j++) {
                    if (target.equals(medicines.get(j))) {
                        localCount++;
                    }
                }
                // К общему счетчику обращаемся один раз
                total.addAndGet(localCount);
            });

            threads[i].start();
        }

        // Ждем, пока все потоки закончат, иначе прочитаем неполный результат
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int result = total.get();
        System.out.println("Количество вхождений: " + result);
        return result;
    }
}
