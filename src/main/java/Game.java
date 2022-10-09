import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    public static void main(String[] args){
        Game game = new Game();
        game.run();
    }
    Screen screen;
    private Arena arena ;
    private Hero hero;
    private TextGraphics graphics;

    public Game(){
        try {
            hero = new Hero(new Position(10,10));
            arena = new Arena(80, 24, hero);

            TerminalSize terminalSize = new TerminalSize(80, 24);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = new DefaultTerminalFactory().createTerminal();

            screen = new TerminalScreen(terminal);
            graphics = screen.newTextGraphics();
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() {
        try {
            screen.clear();
            arena.draw(graphics);
            screen.refresh();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                arena.processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                }
                else if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



