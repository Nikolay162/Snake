package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int SQUARE_SIZE = 10;
    private static final int WIDTH_FIELD = SQUARE_SIZE * WIDTH;
    private static final int HEIGHT_FIELD = SQUARE_SIZE * HEIGHT;

    private View view;
    private Graphics graphics;
    private Position applePosition;
    private Direction direction = Direction.RIGHT;
    private Direction previousDirection = direction;
    private List<Position> snake = new ArrayList<>();

    public void start() {
        view.create(WIDTH_FIELD, HEIGHT_FIELD);
        snake.add(new Position(WIDTH / 2, HEIGHT / 2));
        createApple();
        run();
    }

    public void setView(View view) {
        this.view = view;
    }

    private int randomNumber(int max) {
        return (int) (Math.random() * max);
    }

    public void createApple() {
        do {
            applePosition = new Position(randomNumber(WIDTH), randomNumber(HEIGHT));
        } while (snake.contains(applePosition));
    }

    private void renderFrame() {
        BufferedImage image = new BufferedImage(WIDTH_FIELD, HEIGHT_FIELD, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        drawSquare(applePosition, Color.RED);
        drawSnake();
        graphics.drawString(String.valueOf(snake.size() - 1) , WIDTH_FIELD - SQUARE_SIZE, SQUARE_SIZE);
        view.setImage(image);
    }

    private void drawSquare(Position position, Color color) {
        graphics.setColor(color);
        graphics.fillRect(position.getX() * SQUARE_SIZE, position.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void handleKeyPress(int keyCode) {
        Direction nextDirection = null;

        switch (keyCode) {
            case KeyEvent.VK_UP -> nextDirection = Direction.UP;
            case KeyEvent.VK_RIGHT -> nextDirection = Direction.RIGHT;
            case KeyEvent.VK_DOWN -> nextDirection = Direction.DOWN;
            case KeyEvent.VK_LEFT -> nextDirection = Direction.LEFT;
        }
        if (nextDirection != null && !nextDirection.isOpposite(previousDirection)) {
            direction = nextDirection;
        }
    }


    private void run() {
        while (true) {
            renderFrame();
            delay();
            Position newHead = new Position(snake.get(0));
            newHead.move(direction);
            previousDirection = direction;

            if (snake.contains(newHead)) {
                return;
            }
            snake.add(0, newHead);
            newHead.normalize(WIDTH, HEIGHT);
            if (newHead.equals(applePosition)) {
                createApple();
            } else {
                snake.remove(snake.size() - 1);
            }
        }
    }

    private void delay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void drawSnake() {
        for (Position position : snake) {
            drawSquare(position, Color.GREEN);
        }
    }
}
