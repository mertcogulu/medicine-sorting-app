package ru.aston.medicine.strategy;

import ru.aston.medicine.model.Medicine;
import ru.aston.medicine.validation.InputValidation;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileFillStrategy implements FillStrategy {

    private final Path path;

    public FileFillStrategy(Path path) {
        this.path = path;
    }

    @Override
    public List<Medicine> fill() {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(this::parseLine)
                    .flatMap(Optional::stream)
                    .toList();
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }

    private Optional<Medicine> parseLine(String line) {
        try {
            InputValidation.validateFileLineFormat(line);
            String[] parts = line.split(";");

            Medicine medicine = new Medicine.Builder()
                    .name(InputValidation.validateName(parts[0]))
                    .shelfLifeDays(InputValidation.parseShelfLifeDays(parts[1]))
                    .price(InputValidation.parsePrice(parts[2]))
                    .build();

            return Optional.of(medicine);
        } catch (IllegalArgumentException e) {
            System.out.println("Пропущена строка: " + line + ". " + e.getMessage());
            return Optional.empty();
        }
    }
}