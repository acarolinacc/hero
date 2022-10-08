import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }
    private Hero hero;
    public Game(){
        try {
            hero = new Hero(new Position(10,10));
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    private void moveHero(Position position) {
        hero.setPosition(position);
    }
    private void processKey(KeyStroke key) {
        KeyType pressed = key.getKeyType();
        switch (pressed)
        {
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;

        }
    }
    public void run() throws IOException{
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter()
                    == 'q') {
                screen.close();
            } else if (key.getKeyType() == KeyType.EOF) {
                break;
                }

        }



    }

}


