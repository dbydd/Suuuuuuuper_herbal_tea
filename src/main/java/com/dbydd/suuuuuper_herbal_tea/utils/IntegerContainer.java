package com.dbydd.suuuuuper_herbal_tea.utils;

import net.minecraft.nbt.CompoundNBT;

public class IntegerContainer {
    private int min;
    private int max;
    private int current;

    public IntegerContainer(int min, int max) {
        this.min = min;
        this.max = max;
        this.current = 0;
    }

    public IntegerContainer(int min, int max, int current) {
        this.min = min;
        this.max = max;
        this.current = current;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt("min", min);
        compoundNBT.putInt("max", max);
        compoundNBT.putInt("current", current);
        return compoundNBT;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.current = nbt.getInt("current");
        this.max = nbt.getInt("max");
        this.min = nbt.getInt("min");
    }

    public int add(int value) {
        current += value;
        if (current > max) current = max;
        if (current < min) current = min;
        return current;
    }

    public int self_add() {
        current++;
        if (current > max) current = max;
        if (current < min) current = min;
        return current;
    }

    public int self_substract(){
        current--;
        if (current > max) current = max;
        if (current < min) current = min;
        return current;
    }

    public boolean atMaxValue(){
        return current == max;
    }

    public boolean atMinValue(){
        return current == min;
    }

    public int getCurrent() {
        return current;
    }
}
