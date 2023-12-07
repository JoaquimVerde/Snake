package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.Iterator;
import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;

    LinkedList<Position> snakeBody;


    public Snake(){
        snakeBody = new LinkedList<>();
        buildInitialSnake();
        alive = true;
    }

    public void buildInitialSnake(){
        int startingCol = Field.getWidth() / 2;
        int startingRow = Field.getHeight() / 2;

        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            snakeBody.add(new Position(startingCol, startingRow));
            startingCol -= 1;
        }
    }

    public void increaseSize() {
        snakeBody.addLast(getTail());
    }

    public void move(Direction direction) {
        this.direction = direction;
    }

    public boolean isOppositeDirection(Direction newDirection){
        if (direction == Direction.UP && newDirection == Direction.DOWN){
            return true;
        }
        if (direction == Direction.DOWN && newDirection == Direction.UP){
            return true;
        }
        if (direction == Direction.LEFT && newDirection == Direction.RIGHT){
            return true;
        }
        if (direction == Direction.RIGHT && newDirection == Direction.LEFT){
            return true;
        }
        return false;
    }

    public void move(){
        move(direction);
        if(direction == Direction.UP) {
            snakeBody.addFirst(new Position(getHead().getCol(), getHead().getRow() - 1));
            snakeBody.remove(getTail());
            return;
        }
        if(direction == Direction.DOWN) {
            snakeBody.addFirst(new Position(getHead().getCol(), getHead().getRow() + 1));
            snakeBody.remove(getTail());
            return;
        }
        if(direction == Direction.LEFT) {
            snakeBody.addFirst(new Position(getHead().getCol() - 1, getHead().getRow()));
            snakeBody.remove(getTail());
            return;
        }
        if(direction == Direction.RIGHT){
            snakeBody.addFirst(new Position(getHead().getCol()+1,getHead().getRow()));
            snakeBody.remove(getTail());
        }
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return snakeBody.getFirst();
    }

    public Position getTail() {
        return snakeBody.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return snakeBody;
    }

    public int getSnakeSize() {
        return snakeBody.size();
    }


}

