package net.bbqroast.roadworks;

import org.newdawn.slick.Graphics;

public class Camera {
    private int x;
    private int y;
    private World world;

    public Camera(World world)  {
        this.world = world;
    }

    public void render(Graphics g)   {
        world.render(g);
    }
}
