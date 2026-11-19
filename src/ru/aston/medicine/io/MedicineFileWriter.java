package ru.aston.medicine.io;

import ru.aston.medicine.model.Medicine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public final class MedicineFileWriter {

    // Конструктор private, экземпляр класса создавать не нужно
    private MedicineFileWriter() {}

    public static void appendAll(List<Medicine> medicines, String fileName) {

        Objects.requireNonNull(medicines, "medicines");
        Objects.requireNonNull(fileName, "fileName");

        // true в конструкторе FileWriter - это режим добавления:
        // новые строки дописываются в конец, старое содержимое не стирается
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, StandardCharsets.UTF_8, true))) {

            for (Medicine medicine : medicines) {
                writer.write(medicine.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось записать в файл " + fileName, e);
        }
    }
}
