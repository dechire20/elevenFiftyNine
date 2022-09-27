package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 16, height = 16;

    public static BufferedImage playerLeftDirection, playerRightDirection, playerJumpLeft, playerJumpRight, stoneBrick, grass, sky, rocks;
    public static BufferedImage[] playerLeft, playerRight;

    public static void init() {
        SpriteSheet spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteSheet.png"));

        playerLeft = new BufferedImage[4];
        playerRight = new BufferedImage[4];
        for (int i = 0; i < 4; i++){
            playerLeft[i] = spritesheet.crop(width * (i + 1), 0, width, height);
        }
        playerRight[0] = spritesheet.crop(width * 6, 0, width, height);
        playerRight[1] = spritesheet.crop(width * 7, 0, width, height);
        playerRight[2] = spritesheet.crop(0, height, width, height);
        playerRight[3] = spritesheet.crop(width, height, width, height);

        playerLeftDirection = spritesheet.crop(0, 0, width, height);
        playerRightDirection = spritesheet.crop(width * 5, 0, width, height);
        playerJumpLeft = spritesheet.crop(width * 3, height, width, height);
        playerJumpRight = spritesheet.crop(width * 2, height, width, height);

        stoneBrick = spritesheet.crop(0, height * 2, width , height);
        grass = spritesheet.crop(width, height * 2, width, height);
        sky = spritesheet.crop(width * 2, height * 2, width, height);
        rocks = spritesheet.crop(width * 3, height * 2, width, height);
    }


}
