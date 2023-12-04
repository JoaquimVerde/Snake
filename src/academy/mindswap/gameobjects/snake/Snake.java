package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Position;

import java.util.Iterator;
import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;

    private Position head;


    LinkedList<Position> snakeBody;


    public Snake(){
        alive = true;
        head = new Position(50,12);
        getFullSnake();
    }

    public void increaseSize() {

        snakeBody.add(new Position(getTail().getCol(), getTail().getRow()));

        /*if(direction == Direction.UP){
            snakeBody.add(new Position(getTail().getCol(), getTail().getRow()+1));
        }
        if(direction == Direction.DOWN){
            snakeBody.add(new Position(getTail().getCol(), getTail().getRow()-1));
        }
        if(direction == Direction.LEFT){
            snakeBody.add(new Position(getTail().getCol()+1, getTail().getRow()));
        }
        if(direction == Direction.RIGHT){
            snakeBody.add(new Position(getTail().getCol()-1, getTail().getRow()));
        }*/
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
        if(direction == Direction.RIGHT){
            head = new Position(head.getCol()+1, head.getRow());

        }
        if(direction == Direction.LEFT){
            head = new Position(head.getCol()-1, head.getRow());

        }
        if(direction == Direction.UP){
            head = new Position(head.getCol(), head.getRow()-1);

        }
        if(direction == Direction.DOWN){
            head = new Position(head.getCol(), head.getRow()+1);
        }
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return head;
    }

    public Position getTail() {
        if(direction == Direction.UP){
            return new Position(head.getCol(),head.getRow()+getSnakeSize());
        }
        if(direction == Direction.DOWN){
            return new Position(head.getCol(),head.getRow()-getSnakeSize());
        }
        if(direction == Direction.LEFT){
            return new Position(head.getCol()+getSnakeSize(),head.getRow());
        }
        if(direction == Direction.RIGHT){
            return new Position(head.getCol()-getSnakeSize(),head.getRow());
        }
        return new Position(head.getCol()-getSnakeSize(),head.getRow());
    }

    public LinkedList<Position> getFullSnake(){
        snakeBody = new LinkedList<>();
        snakeBody.add(head);
        snakeBody.add(getTail());

        return snakeBody;
    }



    public int getSnakeSize() {
        return snakeBody.size();
    }


}

