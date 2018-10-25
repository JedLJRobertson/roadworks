package net.bbqroast.roadworks.net.bbqroast.slicktest.guiStates;

import net.bbqroast.roadworks.GUIController;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Standby implements IGUIState {
    GUIController controller;

    public Standby(GUIController controller)    {
        this.controller = controller;
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        if (button == Input.MOUSE_LEFT_BUTTON) {
            controller.setState(new DrawRoad(controller, x, y));
        }
    }

    @Override
    public void render(Graphics graphics, GameContainer gameContainer) {

    }
}
