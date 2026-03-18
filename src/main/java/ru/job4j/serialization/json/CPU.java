package ru.job4j.serialization.json;

import java.util.Objects;

public class CPU {

    private final String model;
    private double price;

    public CPU(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        CPU cpu = (CPU) object;
        return Double.compare(price, cpu.price) == 0 && Objects.equals(model, cpu.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price);
    }
}
