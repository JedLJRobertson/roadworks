package net.bbqroast.roadworks.world;

import com.sun.javafx.geom.Vec2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.LinkedList;

public class RoadNetwork {
    private LinkedList<Intersection> intersections = new LinkedList<>();

    public void render(Graphics g)  {
        LinkedList<Road> roads = getRoads();

        for (Road road : roads) {
            road.render(g);
        }
    }

    public void renderPotentialIntersections(Vec2f start, Vec2f end, Graphics g)  {
        // Find bounding box
        ArrayList<Intersection> intersections = findIntersectionsWith(start, end);

        for (Intersection intersection : intersections) {
            g.setColor(Color.orange);
            g.drawOval(intersection.getX() - 3, intersection.getY() - 3, 6, 6);
        }
    }

    /**
     * Returns the list of intersections that would be formed by the road denoted by start and end.
     * @param start
     * @param end
     * @return
     */
    private ArrayList<Intersection> findIntersectionsWith(Vec2f start, Vec2f end)   {
        ArrayList<Intersection> list = new ArrayList<>();

        // Find bounding box
        int sx = Math.min((int)start.x, (int)end.x);
        int ex = Math.max((int)start.x, (int)end.x);

        int sy = Math.min((int)start.y, (int)end.y);
        int ey = Math.max((int)start.y, (int)end.y);

        LinkedList<Road> roads = getRoads();

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

                list.add(new Intersection(x, y));
            }
        }

        return list;
    }

    private LinkedList<Road> getRoads()    {
        LinkedList<Road> roads = new LinkedList<>();

        if (intersections.size() == 0)  {
            return roads;
        }
        boolean[] polled = new boolean[intersections.get(intersections.size() - 1).getID() + 1];

        for (Intersection intersection : intersections) {
            roads.addAll(intersection.getConnections(polled));
            polled[intersection.getID()] = true;
        }

        return roads;
    }

    public void addRoad(Road road)  {
        Vec2f loc = road.getVertex(0);
        Intersection cross = new Intersection(loc.x, loc.y, intersections.size());
        Intersection prev;
        intersections.add(cross);

        for (int i = 1; i < road.getNoVertexes(); i++)  {
            prev = cross;
            loc = road.getVertex(i);

            // Find intersections


            cross = new Intersection(loc.x, loc.y, intersections.size());
            cross.addConnection(prev);
            prev.addConnection(cross);
            intersections.add(cross);
        }
    }
}
