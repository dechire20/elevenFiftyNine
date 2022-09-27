package tilegame;

import display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.states.GameState;
import tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable{

    private Display display;
    private int width, height;

    public String title;

    private boolean running = false;
    public boolean won = false;
    private Thread thread;

    private double timeElapsed;
    public int time = 60;
    public float fps;

    private BufferStrategy bs;
    private Graphics g;
    private State gameState;

    private final KeyManager keyManager;

    private Handler handler;

    private GameCamera gameCamera;


    public Game(String title, int width, int height) {

        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();

    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        gameState = new GameState(handler);
        State.setState(gameState);

    }

    private void update() {
        keyManager.update();
        if (State.getState() != null) {
            State.getState().update();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(4);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double deltaTime = 0;
        double currentTime;
        double lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {

            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / timePerTick;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (deltaTime >= 1) {
                update();
                deltaTime--;
                ticks++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + ticks);
                if (time >= 1 && !won){
                    time--;
                }
                timer = 0;
                ticks = 0;
            }
            render();
        }

        stop();
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
