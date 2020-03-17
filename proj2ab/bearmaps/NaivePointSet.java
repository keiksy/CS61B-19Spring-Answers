package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{

    List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point dummy = new Point(x, y);
        Point ans = points.get(0);
        for(Point p:points)
            if(Point.distance(p, dummy) < Point.distance(ans, dummy)) ans = p;
        return ans;
    }
}
