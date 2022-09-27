package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    float jumpingVelocity = -430;
    int gravity = 910;

    long deathCounter = 0;
    private int lastDirection;

    private final Animation animLeft, animRight;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 13;
        bounds.y = 3;
        bounds. width = 19;
        bounds.height = 43;

        // Animation
        animLeft = new Animation(200, Assets.playerLeft);
        animRight = new Animation(200, Assets.playerRight);
    }

    @Override
    public void update() {
        getInput();
        animLeft.update();
        animRight.update();
        deadFunctionality();
        won();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xVelocity = 0;

        // player movement
        if (handler.getKeyManager().left) xVelocity -= movementVelocity;
        if (handler.getKeyManager().right) xVelocity += movementVelocity;
        // restart game
        if (handler.getKeyManager().esc){
            x = 48;
            y = 10;
        }


        // player jump
        if (handler.getKeyManager().jump && onFloor) {
            yVelocity += jumpingVelocity;
            onFloor = false;
        }
        // gravity
        yVelocity += gravity * deltaTime;
    }

    public void restart(){
        x = 48;
        y = 10;
    }

    public void deadFunctionality(){
        if (isDead()){
            restart();
            deathCounter++;
        }
    }

    public void won(){
        if (x >= 2943){
            restart();
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        g.drawImage(getCurrentAnimationFrame(), (int) ( x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //deadFunctionality(g);
        g.setFont(new Font("serif", Font.PLAIN, 30));
        g.drawString("deaths: " + deathCounter, 30, 30);

    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            lastDirection = -1;
            if (onFloor){
                return animLeft.getCurrentFrame();
            }
            else{
                return Assets.playerJumpLeft;
            }
        }
        if (xMove > 0){
            lastDirection = 1;
            if (onFloor){
                return animRight.getCurrentFrame();
            }
            else{
                return Assets.playerJumpRight;
            }
        }
        if (lastDirection < 0){
            return Assets.playerLeftDirection;
        }
        else if (lastDirection > 0){
            return Assets.playerRightDirection;
        }

        return Assets.playerRightDirection;
    }
}