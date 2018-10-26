package net.bbqroast.roadworks;

import net.bbqroast.roadworks.world.World;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends BasicGame {
    World world;
    Camera camera;
    GUIController controller;
    private int updateCount;

    public Main(String title) {
        super(title);
        world = new World();
        camera = new Camera(world);
        controller = new GUIController(world, camera);
    }

    public static void main(String[] args)    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(1920, 1080, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        updateCount += i;

        // Poll game logic at 20 ticks
        if (updateCount >= 50) {
            camera.poll(gameContainer);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        camera.render(graphics);
        controller.render(graphics, gameContainer);
    }
    @Override
    public void mouseReleased(int button, int x, int y)    {
        controller.mouseReleased(button, x, y);
    }

}
