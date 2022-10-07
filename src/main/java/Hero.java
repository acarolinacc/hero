import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    private int x;
    private int y;

    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Screen screen) {
        screen.setCharacter(this.getX(), this.getY(), TextCharacter.fromCharacter('X')[0]);
    }

    public void moveUp() {
        this.setY(this.getY()-1);
    }

    public void moveDown() {
        this.setY(this.getY()+1);
    }

    public void moveLeft() {
        this.setX(this.getX()-1);
    }

    public void moveRight() {
        this.setX(this.getX()+1);
    }
}