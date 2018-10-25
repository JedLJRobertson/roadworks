package net.bbqroast.roadworks.net.bbqroast.slicktest.guiStates;

import net.bbqroast.roadworks.GUIController;
import net.bbqroast.roadworks.Road;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class DrawRoad implements  IGUIState {
    private GUIController controller;
    private Road road;

    public DrawRoad(GUIController controller, int x, int y)    {
        this.controller = controller;
        road = new Road(x, y);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        if (button == Input.MOUSE_RIGHT_BUTTON) {
            controller.setState(new Standby(controller));
        } else if (button == Input.MOUSE_LEFT_BUTTON)   {
            road.extend(x, y);

            // If road just reached valid (>1) length, add to world
            if (road.getNoVertexes() == 2)  {
                controller.getWorld().addRoad(road);
            }
        }
    }

    @Override
    public void render(Graphics graphics, GameContainer gameContainer) {
        graphics.setColor(Color.gray);
        graphics.drawLine(road.getVertex(road.getNoVertexes() - 1).x, road.getVertex(road.getNoVertexes() - 1).y, gameContainer.getInput().getMouseX(), gameContainer.getInput().getMouseY());
    }
}
