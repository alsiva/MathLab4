package org.example.dotStuff;

import java.util.List;

public class DotStorage {

    private static final List<Dot> DOTS = List.of(
        new Dot(-2, -0.5),
        new Dot(-1.8, -0.497),
        new Dot(-1.6, -0.488),
        new Dot(-1.4, -0.47),
        new Dot(-1.2, -0.441),
        new Dot(-1, -0.4),
        new Dot(-0.8, -0.345),
        new Dot(-0.6, -0.275),
        new Dot(-0.4, -0.192),
        new Dot(-0.2, -0.099),
        new Dot(0, 0)


    );

    public Dot getDot(int index) {
        return DOTS.get(index);
    }

    public int size() {
        return DOTS.size();
    }

}
