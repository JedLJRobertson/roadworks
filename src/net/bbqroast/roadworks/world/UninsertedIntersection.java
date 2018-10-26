package net.bbqroast.roadworks.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

/**
 * An intersection not ready for the prime time.
 *
 * It knows who it intersects but has not notified them of that yet.
 */
public class UninsertedIntersection extends Intersection {
    private LinkedList<Intersection> futureConnections = new LinkedList<>();
    private RemovalRequest toRemove;

    public UninsertedIntersection(float x, float y, RemovalRequest toRemove) {
        super(x, y,-1);
        this.toRemove = toRemove;
    }

    public UninsertedIntersection(float x, float y) {
        super(x, y,-1);
        this.toRemove = null;
    }

    public Intersection implement(int id) {
        Intersection out = new Intersection(this.getX(), this.getY(), id);

        for (Intersection connection : futureConnections)   {
            out.addConnection(connection);
            connection.addConnection(out);
        }

        if (toRemove != null) {
            toRemove.implement();
        }

        return out;
    }

    @Override
    public void addConnection(Intersection intersection)    {
        futureConnections.add(intersection);
    }

    @Override
    public void render(Graphics g)  {
        if (futureConnections.size() < 3) {
            g.setColor(Color.orange);
            g.drawOval(getX() - 3, getY() - 3, 6, 6);
        }
    }
}
