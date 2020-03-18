package bearmaps;

import java.util.List;

public class KDTree {

    private static class Node {
        Point coord;
        int level;
        Node left;
        Node right;

        Node(Point coord, int level) {
            this.coord = coord;
            this.level = level;
        }
    }

    private Node root;
    private Point nearest;

    public KDTree(List<Point> items) {
        for(Point i:items) add(i);
    }

    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        nearest = root.coord;
        nearHelper(root, target);
        return nearest;
    }

    public void add(Point p) {
        if (root==null) root = new Node(p, 1);
        else addHelper(root, p);
    }

    private boolean closerThanProjectPoint(Node cur, Point target) {
        if (cur.level%2 == 0) {
            return Point.distance(nearest, target) <= Point.distance(target, new Point(target.getX(), cur.coord.getY()));
        } else {
            return Point.distance(nearest, target) <= Point.distance(target, new Point(cur.coord.getX(), target.getY()));
        }
    }

    private void nearHelper(Node curN, Point target) {
        if (curN == null) return;
        Point cur = curN.coord;
        if (Point.distance(cur, target) < Point.distance(nearest, target)) nearest = cur;
        Node choose_side = curN.left, another_side = curN.right;
        if (lessThan(curN, target)) {
            choose_side = curN.right;
            another_side = curN.left;
        }
        nearHelper(choose_side, target);
        if (!closerThanProjectPoint(curN, target)) nearHelper(another_side, target);
    }

    private boolean lessThan(Node cur, Point target) {
        double targetPos, curPos;
        if (cur.level%2 == 0) {
            targetPos = target.getY();
            curPos = cur.coord.getY();
        } else {
            targetPos = target.getX();
            curPos = cur.coord.getX();
        }
        return curPos-targetPos <= 0;
    }

    private void addHelper(Node cur, Point newP) {
        if (cur.coord.equals(newP)) return;
        if (lessThan(cur, newP)) {
            if (cur.right == null) cur.right = new Node(newP, cur.level+1);
            else addHelper(cur.right, newP);
        } else {
            if (cur.left == null) cur.left = new Node(newP, cur.level+1);
            else addHelper(cur.left, newP);
        }
    }
}