package net.bbqroast.roadworks;

import net.bbqroast.roadworks.net.bbqroast.slicktest.guiStates.IGUIState;
import net.bbqroast.roadworks.net.bbqroast.slicktest.guiStates.Standby;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIController {
    World world;
    IGUIState state;


    public GUIController (World world)  {
        this.world = world;
        state = new Standby(this);
    }

    public void setState(IGUIState state)   {
        this.state = state;
    }

    public void mouseReleased(int button, int x, int y)    {
        state.mouseReleased(button, x, y);
    }

    public World getWorld() {
        return world;
    }

    public void render(Graphics graphics, GameContainer gameContainer) {
        state.render(graphics, gameContainer);
    }
}
