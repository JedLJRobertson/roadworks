package net.bbqroast.roadworks.world;

import com.sun.javafx.geom.Vec2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

public class Road {
    private LinkedList<Vec2f> spline = new LinkedList<>();

    public Road(int sx, int sy) {
        spline.add(new Vec2f(sx, sy));
    }

    public void extend(int x, int y)    {
        spline.add(new Vec2f(x, y));
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
}
