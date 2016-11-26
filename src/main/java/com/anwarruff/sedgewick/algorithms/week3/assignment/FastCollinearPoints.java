package com.anwarruff.sedgewick.algorithms.week3.assignment;

import java.util.*;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        CollinearPoint[] copy = new CollinearPoint[points.length];
        CollinearPoint[] original = new CollinearPoint[points.length];
        double DEGENERATE = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < points.length; ++i) {
            if (points[i] == null) {
                throw new NullPointerException();
            }
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].slopeTo(points[j]) == DEGENERATE) {
                    throw new IllegalArgumentException();
                }
            }
            copy[i] = new CollinearPoint(points[i]);
            original[i] = copy[i];
        }

        ArrayList<LineSegment> lines = new ArrayList<>();
        for (int i = 0; i < original.length; ++i) {
            CollinearPoint p = original[i];

            Arrays.sort(copy, p.slopeOrder());

            ArrayList<CollinearPoint> linePoints = new ArrayList<>();
            for (int j = 2; j < copy.length; ++j) {
                boolean matchingSlopesFound = p.slopesEqual(copy[j-1], copy[j]);
                if (matchingSlopesFound) {
                    if (linePoints.isEmpty()) {
                        linePoints.add(copy[j-1]);
                    }
                   linePoints.add(copy[j]);
                }

                if (linePoints.size() >= 3  && (! matchingSlopesFound || j == copy.length-1)){
                    linePoints.add(p);
                    CollinearPoint minPoint = Collections.min(linePoints);
                    CollinearPoint maxPoint = Collections.max(linePoints);
                    Double lineSlope = minPoint.slopeTo(maxPoint);
                    if ( ! minPoint.hasSlope(lineSlope) || ! maxPoint.hasSlope(lineSlope)) {
                        minPoint.addSlope(lineSlope);
                        maxPoint.addSlope(lineSlope);

                        lines.add(new LineSegment(minPoint.getPoint(), maxPoint.getPoint()));
                    }

                    linePoints = new ArrayList<>();
                }

                if (! matchingSlopesFound) {
                    linePoints = new ArrayList<>();
                }
            }
        }

        lineSegments = lines.toArray(new LineSegment[lines.size()]);
    }

    /**
     * The number of line segments.
     * @return
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[lineSegments.length];
        System.arraycopy(lineSegments, 0, copy, 0, lineSegments.length);
        return copy;
    }

    private class CollinearPoint implements Comparable<CollinearPoint>{
        private Point p;
        private HashSet<Double> slopes;

        public CollinearPoint(Point p) {
            this.p = p;
            this.slopes = new HashSet<>();
        }

        public Point getPoint() {
            return p;
        }

        public boolean slopesEqual(CollinearPoint p1, CollinearPoint p2) {
            return slopeOrder().compare(p1, p2) == 0;
        }

        public boolean hasSlope(Double slope) {
            return slopes.contains(slope);
        }

        public void addSlope(Double slope) {
            slopes.add(slope);
        }

        public double slopeTo(CollinearPoint collinearPoint) {
            return p.slopeTo(collinearPoint.getPoint());
        }

        @Override
        public int compareTo(CollinearPoint other) {
            return p.compareTo(other.getPoint());
        }

        public Comparator<CollinearPoint> slopeOrder() {
            int LESS = -1;
            int GREATER = 1;
            int EQUAL = 0;
            return (p1, p2) -> {
                if (p.slopeTo(p1.getPoint()) < p.slopeTo(p2.getPoint())) {
                    return LESS;
                }
                else if (p.slopeTo(p1.getPoint()) > p.slopeTo(p2.getPoint())) {
                    return GREATER;
                }
                else {
                    return EQUAL;
                }
            };
        }
    }

}
