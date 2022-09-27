package tilegame;


public class Main {

    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;

    private final int screenWidth = maxScreenCol * tileSize;
    private final int screenHeight = maxScreenRow * tileSize;

    public static void main(String[] args) {
        Main launcher = new Main();

        Game game = new Game("Ttile", launcher.screenWidth, launcher.screenHeight);
        game.start();
    }
}
