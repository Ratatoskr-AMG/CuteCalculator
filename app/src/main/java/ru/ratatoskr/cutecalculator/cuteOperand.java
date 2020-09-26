package ru.ratatoskr.cutecalculator;

public class cuteOperand {

    public String name;
    public double value;
    public String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public cuteOperand(String name) {
        this.name = name;
        this.value = 0;
        this.type = "operation";
    }

    public cuteOperand(String name, double value) {
        this.name = name;
        this.value = value;
        this.type = "operand";
    }
}
