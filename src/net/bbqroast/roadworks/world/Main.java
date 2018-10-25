package net.bbqroast.roadworks.world;

import net.bbqroast.roadworks.Camera;
import net.bbqroast.roadworks.GUIController;
import net.bbqroast.roadworks.World;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends BasicGame {
    World world;
    Camera camera;
    GUIController controller;

    public Main(String title) {
        super(title);
        world = new World();
        camera = new Camera(world);
        controller = new GUIController(world);
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
