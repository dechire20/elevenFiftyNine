package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 300;
    public static final int scale = 3;
    public static final int DEFAULT_CREATURE_WIDTH = 16 * scale,
            DEFAULT_CREATURE_HEIGHT = 16 * scale;

    protected int health;
    protected boolean onFloor = false;
    protected float movementVelocity;
    protected double xMove, yMove;
    protected float xVelocity, yVelocity;
    private long lastTime;
    protected double deltaTime;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width ,height);
        health = DEFAULT_HEALTH;
        movementVelocity = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        lastTime = System.nanoTime();
    }

    public void move() {
        long currentTime = System.nanoTime();
        deltaTime = (double)(currentTime - lastTime) / 1000000000;
        if (deltaTime > 0.017){
            deltaTime = 0.017;
        }
        else if (deltaTime < 0.015){
            deltaTime = 0.015;
        }
        lastTime = currentTime;
        moveX(deltaTime);
        moveY(deltaTime);
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public void moveX(double timeElapsed){
        xMove = xVelocity * deltaTime;
        if (xMove > 0){
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx,(int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx,(int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) && tx < handler.getWorld().getWidth()){
                x += xMove;
            }
            else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }
        else if (xMove < 0){
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx,(int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx,(int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
            else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY(double timeElapsed){
        yMove = yVelocity * deltaTime;
        if (yMove < 0){
           int ty = (int) (y + yMove + bounds.y) / Tile.TILEWIDTH;

           if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
               y += yMove;
           }
           else{
               y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
               yVelocity = 0;
           }
        }
        else if (yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEWIDTH;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
            else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                onFloor = true;
                yVelocity = 0;
            }

        }

    }

    public boolean isDead(){
        if ((int)y >= 529){
            return true;
        }
        return false;
    }

    public double getxMove() {
        return xMove;
    }

    public void setxMove(double xMove) {
        this.xMove = xMove;
    }

    public double getyMove() {
        return yMove;
    }

    public void setyMove(double yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getMovementVelocity() {
        return movementVelocity;
    }

    public void setMovementVelocity(float movementVelocity) {
        this.movementVelocity = movementVelocity;
    }

}