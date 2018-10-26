package net.bbqroast.roadworks;

import net.bbqroast.roadworks.guiStates.IGUIState;
import net.bbqroast.roadworks.guiStates.Standby;
import net.bbqroast.roadworks.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIController {
    World world;
    IGUIState state;
    Camera camera;


    public GUIController (World world, Camera camera)  {
        this.world = world;
        this.camera = camera;

        state = new Standby(this);
    }

    public void setState(IGUIState state)   {
        this.state = state;
    }

    public void mouseReleased(int button, int x, int y)    {
        state.mouseReleased(button, x, y, camera.getX(), camera.getY());
    }

    public World getWorld() {
        return world;
    }

    public void render(Graphics graphics, GameContainer gameContainer) {
        state.render(graphics, gameContainer, camera);
    }
}
