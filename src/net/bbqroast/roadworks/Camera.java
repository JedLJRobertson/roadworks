package net.bbqroast.roadworks;

import net.bbqroast.roadworks.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Camera {
    private int x;
    private int y;
    private World world;

    private static float maxVel = 4;
    private static float accl = 0.05f;
    private static float drag = 0.1f;
    private float xVel = 0;
    private float yVel = 0;

    public Camera(World world)  {
        this.world = world;
    }

    public void render(Graphics g)   {
        g.translate(x, y);
        world.render(g);
    }

    public void poll(GameContainer container)   {
        if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
            if (xVel < maxVel)  {
                xVel += accl;
            }
        } else if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
            if (-xVel < maxVel)  {
                xVel -= accl;
            }
        } else {
            if (xVel > drag)    {
                xVel -= drag;
            } else if (-xVel > drag) {
                xVel += drag;
            } else {
                xVel = 0;
            }
        }
        x += xVel;

        if (container.getInput().isKeyDown(Input.KEY_UP)) {
            if (yVel < maxVel)  {
                yVel += accl;
            }
        } else if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
            if (-yVel < maxVel)  {
                yVel -= accl;
            }
        } else {
            if (yVel > drag)    {
                yVel -= drag;
            } else if (-yVel > drag) {
                yVel += drag;
            } else {
                yVel = 0;
            }
        }
        y += yVel;
    }

    public int getX()  {
        return x;
    }

    public int getY()  {
        return y;
    }
}
