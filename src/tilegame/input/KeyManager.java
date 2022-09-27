package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

    private final boolean[] keys;
    public boolean left, right, jump, esc, enter, ctrl;

    public KeyManager() {
        keys = new boolean[1000];
    }

    public void update() {
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        jump = keys[KeyEvent.VK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];
        enter = keys[KeyEvent.VK_ENTER];
        ctrl = keys[KeyEvent.VK_CONTROL];

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getExtendedKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getExtendedKeyCode()] = false;
    }

}
