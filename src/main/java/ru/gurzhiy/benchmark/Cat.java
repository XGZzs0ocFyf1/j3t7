package ru.gurzhiy.benchmark;

import java.util.Objects;

public class Cat {

    private String name;
    private String voice = "Мяу";


    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, String voice) {
        this.name = name;
        this.voice = voice;
    }

    public String getName() {
        return name;
    }

    public String getVoice() {
        return voice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) && Objects.equals(voice, cat.voice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, voice);
    }
}
