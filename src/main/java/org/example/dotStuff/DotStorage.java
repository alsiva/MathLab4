package org.example.dotStuff;

import java.util.List;

public class DotStorage {

    private final List<Dot> DOTS;

    public DotStorage(List<Dot> DOTS) {
        this.DOTS = DOTS;
    }

    public List<Dot> getDOTS() {
        return this.DOTS;
    }

    public DotStorage copy() {
        return new DotStorage(this.DOTS);
    }

    public Dot getDot(int index) {
        return DOTS.get(index);
    }

    public int size() {
        return DOTS.size();
    }

}
