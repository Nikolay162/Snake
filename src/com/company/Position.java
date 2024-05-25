package com.company;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this(position.x, position.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(Direction direction) {
        x += direction.getDx();
        y += direction.getDy();
    }

    public void normalize(int width, int height) {
        x = Math.floorMod(x, width);
        y = Math.floorMod(y, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getX() == position.getX() && getY() == position.getY();
    }

}
