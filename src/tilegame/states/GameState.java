package tilegame.states;

import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private final Player player;
    private final World world;
    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 48, 10);
    }

    @Override
    public void update() {
        world.update();
        player.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);


    }


}
