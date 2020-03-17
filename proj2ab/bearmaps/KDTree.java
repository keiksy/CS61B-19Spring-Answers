package bearmaps;

import java.util.List;

public class KDTree {

    class Node {
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

    private void nearHelper(Node curN, Point target) {
        if (curN == null) return;
        Point cur = curN.coord;
        if (Point.distance(cur, target) < Point.distance(nearest, target)) nearest = cur;
        if (curN.level%2 == 0) {
            if (target.getY() <= cur.getY()) {
                nearHelper(curN.left, target);
                if (Point.distance(target, nearest) > Point.distance(target, new Point(cur.getX(), target.getY())))
                    nearHelper(curN.right, target);
            } else {
                nearHelper(curN.right, target);
                if (Point.distance(target, nearest) > Point.distance(target, new Point(cur.getX(), target.getY())))
                    nearHelper(curN.left, target);
            }
        } else {
            if (target.getX() <= cur.getX()) {
                nearHelper(curN.left, target);
                if (Point.distance(target, nearest) > Point.distance(target, new Point(cur.getX(), target.getY())))
                    nearHelper(curN.right, target);
            } else {
                nearHelper(curN.right, target);
                if (Point.distance(target, nearest) > Point.distance(target, new Point(cur.getX(), target.getY())))
                    nearHelper(curN.left, target);
            }
        }
    }

    public void add(Point p) {
        if (root==null) root = new Node(p, 1);
        else addHelper(root, p);
    }

    private void addHelper(Node cur, Point newP) {
        Point curP = cur.coord;
        if (curP.equals(newP)) return;
        if (cur.level%2 == 0) {
            if (curP.getY() >= newP.getY()) {
                if (cur.right == null) cur.right = new Node(newP, cur.level+1);
                else addHelper(cur.right, newP);
            } else {
                if (cur.left == null) cur.left = new Node(newP, cur.level+1);
                else addHelper(cur.left, newP);
            }
        } else {
            if (curP.getX() <= newP.getX()) {
                if (cur.right == null) cur.right = new Node(newP, cur.level+1);
                else addHelper(cur.right, newP);
            } else {
                if (cur.left == null) cur.left = new Node(newP, cur.level+1);
                else addHelper(cur.left, newP);
            }
        }
    }
}
