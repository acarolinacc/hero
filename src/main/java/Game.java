import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;
    private Hero hero;

    private TextGraphics graphics;
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

    public Game(){
        try {
            hero = new Hero(new Position(10,10));
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            arena = new Arena(80,24, hero);
            TerminalSize terminalSize = new TerminalSize(80, 24);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            graphics = screen.newTextGraphics();
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
        arena.draw(graphics);
        screen.refresh();
    }



    public void run() throws IOException{
        while(true) {
            draw();
            KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter()
                    == 'q') {
                screen.close();
            } else if (key.getKeyType() == KeyType.EOF) {
                break;
                }
            arena.processKey(key);
        }
    }
}


