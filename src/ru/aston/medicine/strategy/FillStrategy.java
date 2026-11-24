package ru.aston.medicine.strategy;

import ru.aston.medicine.model.Medicine;

import java.util.List;

public interface FillStrategy {

    List<Medicine> fill();
}
