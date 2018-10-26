package net.bbqroast.roadworks.guiStates;

import com.sun.javafx.geom.Vec2f;
import net.bbqroast.roadworks.Camera;
import net.bbqroast.roadworks.GUIController;
import net.bbqroast.roadworks.world.Road;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class DrawRoad implements  IGUIState {
    private GUIController controller;
    private Road road;

    public DrawRoad(GUIController controller, int x, int y, int xoff, int yoff)    {
        this.controller = controller;
        road = new Road(x - xoff, y - yoff);
    }

    @Override
    public void mouseReleased(int button, int x, int y, int xoff, int yoff) {
        if (button == Input.MOUSE_RIGHT_BUTTON) {
            if (road.getNoVertexes() > 1)  {
                controller.getWorld().getRoadNetwork().addRoad(road);
            }

            controller.setState(new Standby(controller));
        } else if (button == Input.MOUSE_LEFT_BUTTON)   {
            road.extend(x - xoff, y - yoff);
        }
    }

    @Override
    public void render(Graphics graphics, GameContainer gameContainer, Camera camera) {
        road.render(graphics);

        graphics.setColor(Color.gray);
        graphics.drawLine(road.getVertex(road.getNoVertexes() - 1).x, road.getVertex(road.getNoVertexes() - 1).y, gameContainer.getInput().getMouseX() - camera.getX(), gameContainer.getInput().getMouseY() - camera.getY());

        controller.getWorld().getRoadNetwork().renderPotentialIntersections(road.getVertex(road.getNoVertexes() - 1), new Vec2f(gameContainer.getInput().getMouseX() - camera.getX(), gameContainer.getInput().getMouseY() - camera.getY()), graphics);
    }
}
