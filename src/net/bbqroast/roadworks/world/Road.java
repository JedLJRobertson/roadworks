package net.bbqroast.roadworks.world;

import com.sun.javafx.geom.Vec2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

public class Road {
    private LinkedList<Vec2f> spline = new LinkedList<>();
    private LinkedList<Intersection> intersections = new LinkedList<>();

    public Road(float sx, float sy, Intersection cross) {
        spline.add(new Vec2f(sx, sy));
        intersections.add(cross);
    }

    public void extend(float x, float y, Intersection cross)    {
        spline.add(new Vec2f(x, y));
        intersections.add(cross);
    }

    public void render (Graphics g) {
        for (int i = 0; i < spline.size() - 1; i++) {
            g.setColor(Color.white);
            g.drawLine(spline.get(i).x, spline.get(i).y, spline.get(i + 1).x, spline.get(i + 1).y);
        }
    }

    public int getNoVertexes() {
        return spline.size();
    }

    public Vec2f getVertex(int i) {
        return spline.get(i);
    }

    public Intersection getIntersection(int i) {
        return intersections.get(i);
    }
}
