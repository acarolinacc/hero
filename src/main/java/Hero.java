import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero extends Element{
    public Hero(Position pos) {
        super(pos);
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("##EC008C"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getPosition().getX(),this.getPosition().getY()), "X");
    }
    public Position moveUp() {
        return new Position(this.getPosition().getX(), this.getPosition().getY()-1);
    }

    public Position moveDown() {
        return new Position(this.getPosition().getX(), this.getPosition().getY()+1);
    }

    public Position moveLeft() {
        return new Position(this.getPosition().getX()-1, this.getPosition().getY());
    }

    public Position moveRight() {
        return new Position(this.getPosition().getX()+1, this.getPosition().getY());
    }

}