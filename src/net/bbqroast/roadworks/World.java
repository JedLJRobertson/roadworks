package net.bbqroast.roadworks;

import net.bbqroast.roadworks.world.Road;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

public class World {
    private LinkedList<Road>  roads;

    public World()  {
        roads = new LinkedList<>();
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public void render(Graphics g)    {
        for (Road road : roads) {
            road.render(g);
        }
    }
}
