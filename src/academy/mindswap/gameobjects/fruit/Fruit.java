package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {

    private Position position;

    public void generateNewPosition() {

        int randomCol = (int)(Math.random()*(98-1+1)+1);
        int randomRow = (int)(Math.random()*(23-1+1)+1);

        position = new Position(randomCol,randomRow);
    }

    public Position getPosition(){
        return position;
    }
}
