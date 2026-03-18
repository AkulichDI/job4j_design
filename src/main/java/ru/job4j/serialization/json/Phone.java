package ru.job4j.serialization.json;

import java.util.ArrayList;
import java.util.Objects;

public class Phone {

    private final String model;
    private boolean broken;
    private int price;
    private ArrayList<String> components ;
    private CPU cpu;

    public Phone(String model, boolean broken, int price, ArrayList<String> components,CPU cpu) {

        this.model = model;
        this.broken = broken;
        this.price = price;
        this.components = components;
        this.cpu = cpu;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Phone phone = (Phone) object;
        return broken == phone.broken && price == phone.price && Objects.equals(model, phone.model) && Objects.equals(components, phone.components) && Objects.equals(cpu, phone.cpu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, broken, price, components, cpu);
    }

    public String getModel() {
        return model;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }
}
