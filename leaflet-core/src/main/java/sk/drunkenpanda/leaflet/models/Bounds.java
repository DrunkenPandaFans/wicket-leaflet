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
import org.apache.wicket.util.lang.Args;

/**
 * Represents a rectangular area in pixels.
 *
 * @author Jan Ferko
 */
public class Bounds implements Serializable {

    private final Point topLeft;

    private final Point bottomRight;

    public Bounds(Point topLeft, Point bottomRight) {
        Args.notNull(topLeft, "topLeft");
        Args.notNull(bottomRight, "bottomRight");
        Args.isTrue(topLeft.getX() <= bottomRight.getX(), "X coordinate of topLeft must be less than or equal to X coordinate of bottomRight");
        Args.isTrue(topLeft.getY() <= bottomRight.getY(), "Y coordinate of topLeft must be less than or equal to Y coordinate of bottomRight");

        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * @return the top-left corner of bounds
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * @return the bottom-right corner of bounds
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    /**
     * @return the center point of bounds
     */
    public Point getCenter() {
        double centerX = (bottomRight.getX() + topLeft.getX()) / 2;
        double centerY = (bottomRight.getY() + topLeft.getY()) / 2;

        return new Point(centerX, centerY);
    }

    /**
     * {@inheritDoc }
     *
     * Compares the current bounds and object based on top-left and bottom-right corners of bounds.
     *
     * @param obj the object, that is compared with the current point
     * @return {@code true} if object is equal to the current point, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bounds)) {
            return false;
        }

        Bounds other = (Bounds) obj;
        return topLeft.equals(other.topLeft) && bottomRight.equals(other.bottomRight);
    }

    /**
     * {@inheritDoc }
     * @return hash code of the current bounds based on top-left and bottom-right corners
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.topLeft.hashCode();
        hash = 53 * hash + this.bottomRight.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc }
     * @return text representation of the current bounds, that contains top-left and bottom-right corners
     */
    @Override
    public String toString() {
        return String.format("Bounds [topLeft=[%1$s], bottomRight=[%2$s]}", topLeft, bottomRight);
    }
}
