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
    public Game(){
        try {
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
    private int x = 10;
    private int y = 10;
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    private void processKey(KeyStroke key) {
        KeyType pressed = key.getKeyType();
        switch (pressed)
        {
            case ArrowDown:
                y--;
                break;
            case ArrowUp:
                y++;
                break;
            case ArrowLeft:
                x--;
                break;
            case ArrowRight:
                x++;
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


