package com.snakegame;

import java.util.Random;

public class Poison extends Cell{
    private Random random;
    private Snake snake;

    public Poison(Snake snake) {
        super(-1, -1, GameSnake.CELL_SIZE, GameSnake.POISON_COLOR);
        random = new Random();
        this.snake = snake;
    }

    public void appear(){
        int x, y;
        do{
            x = random.nextInt(GameSnake.CANVAS_WIDTH);
            y = random.nextInt(GameSnake.CANVAS_HEIGHT);
        } while(snake.isInSnake(x, y));
        set(x, y);
    }

    public boolean isEaten(){
        return getX() == -1;
    }

    public void eat() {
        set(-1, -1);
    }

    public boolean isPoison(int x, int y) {
        return (getX() == x && getY() == y);
    }
}
