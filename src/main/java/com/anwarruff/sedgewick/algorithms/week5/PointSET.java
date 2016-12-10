import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

/**
 * Created by aruff on 12/6/16.
 */
public class PointSET {
    private SET<Point2D> set;

    public PointSET() {
        set = new SET<>();
    }

    public boolean isEmpty() {
        return set.size() == 0;
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        return set.contains(p);
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        set.add(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new NullPointerException();

        Point2D topRight = new Point2D(rect.xmax(), rect.ymax());
        Point2D bottomLeft = new Point2D(rect.xmin(), rect.ymin());
        ArrayList<Point2D> points = new ArrayList<>();
        for (Point2D p : set) {
            if (p.x() >= bottomLeft.x() && p.x() <= topRight.x() && p.y() >= bottomLeft.y() && p.y() <= topRight.y()) {
                points.add(p);
            }
        }

        return points;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        double shortestDistance = -1;
        Point2D closest = null;
        for (Point2D neighbor : set) {
            double calculatedDistance  = Math.sqrt(Math.pow(p.x()-neighbor.x(), 2) + Math.pow(p.y()-neighbor.y(), 2));
            if (shortestDistance == -1 || calculatedDistance < shortestDistance) {
                shortestDistance = calculatedDistance;
                closest = neighbor;
            }
        }

        return closest;
    }

}
