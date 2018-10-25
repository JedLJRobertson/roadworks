package net.bbqroast.roadworks.world;

import net.bbqroast.roadworks.world.Road;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

public class World {
    private RoadNetwork roadNetwork;

    public World()  {
        roadNetwork = new RoadNetwork();
    }

    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    public void render(Graphics g)    {
        roadNetwork.render(g);
    }
}
