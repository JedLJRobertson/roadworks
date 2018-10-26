package net.bbqroast.roadworks.guiStates;

import com.sun.javafx.geom.Vec2f;
import net.bbqroast.roadworks.Camera;
import net.bbqroast.roadworks.GUIController;
import net.bbqroast.roadworks.world.Intersection;
import net.bbqroast.roadworks.world.Road;
import net.bbqroast.roadworks.world.UninsertedIntersection;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.LinkedList;

public class DrawRoad implements  IGUIState {
    private GUIController controller;
    private LinkedList<UninsertedIntersection> intersections = new LinkedList<>();

    public DrawRoad(GUIController controller, int x, int y, int xoff, int yoff)    {
        this.controller = controller;
        intersections.add(new UninsertedIntersection(x - xoff, y - yoff));
    }

    @Override
    public void mouseReleased(int button, int x, int y, int xoff, int yoff) {
        if (button == Input.MOUSE_RIGHT_BUTTON) {
            if (intersections.size() > 1)  {
                controller.getWorld().getRoadNetwork().addRoad(intersections);
            }

            controller.setState(new Standby(controller));
        } else if (button == Input.MOUSE_LEFT_BUTTON)   {
            UninsertedIntersection cross = new UninsertedIntersection(x - xoff, y - yoff);
            UninsertedIntersection prev = intersections.get(intersections.size() - 1);

            ArrayList<UninsertedIntersection> meets = controller.getWorld().getRoadNetwork().findIntersectionsWith(prev.getLoc(), cross.getLoc());
            for (UninsertedIntersection junction : meets)   {
                intersections.add(junction);
            }

            intersections.add(cross);
        }
    }

    @Override
    public void render(Graphics graphics, GameContainer gameContainer, Camera camera) {
        // Draw road
        Intersection last = null;
        for (Intersection intersection : intersections) {
            if (last != null)   {
                graphics.setColor(Color.white);
                graphics.drawLine(last.getX(), last.getY(), intersection.getX(), intersection.getY());
            }

            intersection.render(graphics);
            last = intersection;
        }

        // Draw drafted segment
        graphics.setColor(Color.gray);
        graphics.drawLine(last.getX(), last.getY(), gameContainer.getInput().getMouseX() - camera.getX(), gameContainer.getInput().getMouseY() - camera.getY());

    }
}
