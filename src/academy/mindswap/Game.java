package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.ScreenWriter;

import java.util.Iterator;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        fruit = new Fruit();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        fruit.generateNewPosition();
        generateFruit();

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions(); //bater nas margens, em si própria, ou na fruta.
            Field.drawSnake(snake);
        }
        System.out.println("Game Over");
    }

    private void generateFruit() {
        Field.drawFruit(fruit);
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    if(snake.isOppositeDirection(Direction.UP)){
                        return;
                    }
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    if(snake.isOppositeDirection(Direction.DOWN)){
                        return;
                    }
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    if(snake.isOppositeDirection(Direction.LEFT)){
                        return;
                    }
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    if(snake.isOppositeDirection(Direction.RIGHT)){
                        return;
                    }
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();

    }

    private void checkCollisions() {

        if(snake.getHead().equals(fruit.getPosition())){
            snake.increaseSize();
            fruit.generateNewPosition();
            generateFruit();
        }

        if(checkBodyCollision()){
            snake.die();
        }

        if(snake.getHead().getRow()==Field.getHeight()-1){
            snake.die();
        }
        if(snake.getHead().getRow() == 0){
            snake.die();
        }
        if(snake.getHead().getCol()==Field.getWidth()-1){
            snake.die();
        }
        if(snake.getHead().getCol() == 0){
            snake.die();
        }

    }


    private boolean checkBodyCollision(){
        Iterator<Position> iterator = snake.getFullSnake().iterator();
        iterator.next();
        while(iterator.hasNext()){
            if(snake.getHead().equals(iterator.next()))
                return true;
        }
        return false;
    }
}
