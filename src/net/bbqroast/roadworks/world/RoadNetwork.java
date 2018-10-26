package net.bbqroast.roadworks.world;

import com.sun.javafx.geom.Vec2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.LinkedList;

public class RoadNetwork {
    private LinkedList<Road> roads = new LinkedList<>();

    public void render(Graphics g)  {
        for (Road road : roads) {
            road.render(g);
        }
    }

    public void renderPotentialIntersections(Vec2f start, Vec2f end, Graphics g)  {
        // Find bounding box
        int sx = Math.min((int)start.x, (int)end.x);
        int ex = Math.max((int)start.x, (int)end.x);

        int sy = Math.min((int)start.y, (int)end.y);
        int ey = Math.max((int)start.y, (int)end.y);

        for (Road road : roads)  {
            for (int i = 0; i < road.getNoVertexes() - 1; i++)  {
                Vec2f lstart = road.getVertex(i);
                Vec2f lend = road.getVertex(i+1);

                // Find bounding box
                int lsx = Math.min((int)lstart.x, (int)lend.x);
                int lex = Math.max((int)lstart.x, (int)lend.x);

                int lsy = Math.min((int)lstart.y, (int)lend.y);
                int ley = Math.max((int)lstart.y, (int)lend.y);

                // Disqualify easy non collisions
                if (lsx > ex)   {
                    continue;
                }
                if (lsy > ey)   {
                    continue;
                }
                if (lex < sx) {
                    continue;
                }
                if (ley < sy)   {
                    continue;
                }

                g.drawString("hello", 30, 10);
                // Find intersection
                float gradient = ((float)(end.y - start.y))/((float)(end.x - start.x));
                float gradient2 = ((float)(lend.y - lstart.y))/((float)(lend.x - lstart.x));
                float c = start.y - start.x*gradient;
                float c2 = lstart.y - lstart.x*gradient2;

                float x = (c2 - c)/(gradient - gradient2);
                float y = gradient*x + c;


                // Check collision actually on line.
                if (x < lsx || x < sx || x > lex || x > ex || y < lsy || y < sy || y > ley || y > ey)   {
                    continue;
                }

                g.setColor(Color.orange);
                g.drawOval(x - 4, y - 4, 8, 8);
            }
        }
    }


    public void addRoad(Road road)  {
        roads.add(road);
    }
}
