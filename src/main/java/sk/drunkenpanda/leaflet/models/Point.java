/*
 * Copyright 2014 Jan Ferko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;

/**
 * Represents a point with x and y coordinates in pixels.
 * 
 * @author Jan Ferko
 */
public class Point implements Serializable {
    
    private final double x;
    
    private final double y;
    
    /**
     * Creates new point for given coordinates x and y.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this(x, y, false);
    }
    
    /**
     * Creates new point for given coordinates x and y.
     * If round is {@code true}, it rounds x and y values.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param round indicator if coordinate values should be rounded.
     */
    public Point(double x, double y, boolean round) {
        this.x = round ? Math.round(x) : x;
        this.y = round ? Math.round(y) : y;
    }
    
    /**     
     * @return the x coordinate of point
     */
    public double getX() {
        return x;
    }
    
    /**     
     * @return the y coordinate of point
     */
    public double getY() {
        return y;
    }
    
    /**
     * Adds current and given point.
     * 
     * @param other the point, that should be added.
     * @return the result of addition of the current and the given points.
     */
    public Point add(Point other) {
        double newX = x + other.getX();
        double newY = y + other.getY();
        return new Point(newX, newY);
    }
    
    /**
     * Subtracts given point from current point.
     * 
     * @param other the point, that is subtracted from the current point.
     * @return the result of subtraction of the given point from current.
     */
    public Point subtract(Point other) {
        double newX = x - other.getX();
        double newY = y - other.getY();
        return new Point(newX, newY);
    }
    
    /**
     * Multiplies current point by multiplier.
     * 
     * @param multiplier the value which point is multiplied by.
     * @return the result of multiplication of the current point by multiplier.
     */
    public Point multipleBy(double multiplier) {
        double newX = x * multiplier;
        double newY = y * multiplier;
        return new Point(newX, newY);
    }
    
    /**
     * Divides the current point by the given number.
     * If round is {@code true}, result of division is rounded.
     * 
     * @param divider the value which point is divided by.
     * @param round indicator if result of division should be rounded
     * @return the result of division of the current point by multiplier,
     *  optionally rounded.
     */
    public Point dividedBy(double divider, boolean round) {
        double newX = x / divider;
        double newY = y / divider;        
        return new Point(newX, newY, round);
    }
    
    /**
     * Computes distance between the current and the given points.
     * 
     * @param other the point for which distance from the current point is computed.
     * @return the distance between the current and the given points.
     */
    public double distanceTo(Point other) {
        double xDistance = other.getX() - x;
        double yDistance = other.getY() - y;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
    
    /**     
     * @return copy of the current point with rounded coordinates.
     */
    public Point round() {
        return new Point(x, y, true);
    }
    
    /**
     * @return copy of the current point with rounded down coordinates.     
     */
    public Point floor() {
        double newX = Math.floor(x);
        double newY = Math.floor(y);
        return new Point(newX, newY);
    }
    
    /**
     * {@inheritDoc }
     * 
     * Compares object with the current point based on its x and y coordinates.
     * 
     * @param obj the object, that is compared with the current point
     * @return {@code true} if object is equal to the current point based on its
     * coordinates.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * {@inheritDoc }
     * @return hash code of the current point based on x and y coordinates
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }    
    
    /**
     * {@inheritDoc }
     * @return text representation of point, that contains its x and y coordinates
     */
    @Override
    public String toString() {
        return String.format("Point {x=[%1$.2f], y=[%2$.2f]}", x, y);
    }
    
}
