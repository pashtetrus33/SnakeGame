package com.snakegame;

import com.snakegame.models.Food;
import com.snakegame.models.Poison;
import com.snakegame.models.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class GameSnake extends JFrame {
    private static final int WIN_LEVEL = 19;
    final String TITLE_OF_PROGRAM = "Classic Game Snake";
    public final static int CELL_SIZE = 20;           // size of cell in pix
    public final static int CANVAS_WIDTH = 30;        // width in cells
    public final static int CANVAS_HEIGHT = 25;       // height in cells
    public final static Color SNAKE_COLOR = Color.darkGray;
    public final static Color FOOD_COLOR = Color.green;
    public final static Color POISON_COLOR = Color.red;
    public final static int KEY_LEFT = 37;            // codes
    public final static int KEY_UP = 38;              //   of
    public final static int KEY_RIGHT = 39;           //   cursor
    public final static int KEY_DOWN = 40;            //   keys
    final int START_SNAKE_SIZE = 5;            // initialization data
    final int START_SNAKE_X = CANVAS_WIDTH / 2;  //   for
    final int START_SNAKE_Y = CANVAS_HEIGHT / 2; //   snake
    final int CHANGE_LEVEL_SNAKE_SIZE = 8; // size for next level (delay degrease)
    public static int snake_delay = 200; // snake delay in milliseconds
    public static int game_level = 1; // game level
    public static int snakeSize = 0;                         // current snake size
    public static boolean gameOver = false;           // a sign game is over or not

    Canvas canvas;                   // canvas object for rendering (drawing)
    Snake snake;                     // declare a snake object
    Food food;                       // declare a food object
    Poison poison;                   // declare a poison object

    public static void main(String[] args) {
        new GameSnake().game();
    }

    public GameSnake() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(CELL_SIZE * CANVAS_WIDTH,
                CELL_SIZE * CANVAS_HEIGHT));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                snake.setDirection(e.getKeyCode());
            }
        });

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void game() {
        snake = new Snake(
                START_SNAKE_X,
                START_SNAKE_Y,
                START_SNAKE_SIZE,
                KEY_RIGHT);

        food = new Food(snake);
        snake.setFood(food);

        poison = new Poison(snake);
        snake.setPoison(poison);

        while (!gameOver) {
            snake.move();
            if (snake.size() != snakeSize) {
                snakeSize = snake.size();
                setTitle(TITLE_OF_PROGRAM + ": Level " + game_level + " Count " + snakeSize);
            }

            if (food.isEaten()) {
                food.appear();
            }

            if (poison.isEaten()) {
                poison.appear();
            }
            if (snake.size() == CHANGE_LEVEL_SNAKE_SIZE) {
                if (game_level == WIN_LEVEL){
                    break;
                }
                snakeSize = 0;
                game_level++;
                snake = new Snake(
                        START_SNAKE_X,
                        START_SNAKE_Y,
                        START_SNAKE_SIZE,
                        KEY_RIGHT);
                if (snake_delay > 10) {
                    snake_delay -= 10;
                }
                food = new Food(snake);
                snake.setFood(food);

                poison = new Poison(snake);
                snake.setPoison(poison);
            }
            canvas.repaint();
            sleep(snake_delay);

        }
        if (game_level == WIN_LEVEL){
            canvas.setBackground(Color.YELLOW);
            showMessageDialog(null, "CONGRATULATIONS. YOU ARE THE WINNER! \n YOUR RESULT IS: " +
                    "Level " + game_level + ". Count " + snakeSize);
        } else {
            canvas.setBackground(Color.RED);
            showMessageDialog(null, "THE LESSON IS OVER. GOODBYE \n YOUR RESULT IS \n" +
                    "Level: " + game_level + ". Count: " + snakeSize);
        }


    }

    private void sleep(long ms) {    // method for suspending
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Canvas extends JPanel {    // class for rendering (drawing)
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            snake.paint(g2D);
            food.paint(g2D);
            poison.paint(g2D);
        }
    }

}
