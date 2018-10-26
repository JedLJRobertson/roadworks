package net.bbqroast.roadworks.world;

import com.sun.javafx.geom.Vec2f;

import java.util.Collection;
import java.util.LinkedList;

public class Intersection {
    private float x;
    private float y;
    private int id;

    private LinkedList<Intersection> connections = new LinkedList<>();

    public Intersection(float x, float y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Intersection(float x, float y)   {
        this.x = x;
        this.y = y;
        this.id = -1;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getID() {
        return id;
    }

    /**
     * Returns connected roads
     * @param polled a boolean array describing what intersections have already been polled
     * @return
     */
    public LinkedList<Road> getConnections(boolean[] polled) {
        LinkedList<Road> roads = new LinkedList<>();

        for (Intersection intersection : connections)   {
            if (!polled[intersection.getID()])   {
                Road r = new Road(this.x, this.y);
                r.extend(intersection.getX(), intersection.getY());
                roads.add(r);
            }
        }

        return roads;
    }

    public void addConnection(Intersection prev) {
        connections.add(prev);
    }

    public Vec2f getLoc() {
        return new Vec2f(x, y);
    }
}
