package com.example.shortlink.util;

public class Couple<F, S> {
    private F f;
    private S s;

    public Couple(F f, S s) {
        this.f = f;
        this.s = s;
    }

    public F getFirst() {
        return f;
    }

    public S getSecond() {
        return s;
    }

    public boolean isFirstNotNull() {
        return f != null;
    }

    public boolean isSecondNotNull() {
        return s != null;
    }

    public boolean isCoupleNotNull() {
        return f != null && s != null;
    }

    @Override
    public String toString() {
        return "Couple{" +
                "first is = " + f.toString() +
                ", second is = " + s.toString() +
                '}';
    }
}
