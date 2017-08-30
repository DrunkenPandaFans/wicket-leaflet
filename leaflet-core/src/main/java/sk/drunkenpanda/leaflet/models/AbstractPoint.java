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

import javax.annotation.Nonnull;

import org.immutables.value.Value;

import sk.drunkenpanda.leaflet.json.ProperJson;

/**
 * Represents a point with x and y coordinates in pixels.
 *
 * @author Jan Ferko
 */
@ModelStyle
@Value.Immutable(builder = false)
@ProperJson
public abstract class AbstractPoint implements Serializable {

    /**
     * @return the x coordinate of point
     */
    @Nonnull
    @Value.Parameter
    public abstract double getX();

    /**
     * @return the y coordinate of point
     */
    @Nonnull
    @Value.Parameter
    public abstract double getY();

    /**
     * Adds current and given point.
     *
     * @param other the point, that should be added.
     * @return the result of addition of the current and the given points.
     */
    @Nonnull
    public Point add(AbstractPoint other) {
        final double newX = getX() + other.getX();
        final double newY = getY() + other.getY();
        return Point.of(newX, newY);
    }

    /**
     * Subtracts given point from current point.
     *
     * @param other the point, that is subtracted from the current point.
     * @return the result of subtraction of the given point from current.
     */
    @Nonnull
    public Point subtract(AbstractPoint other) {
        final double newX = getX() - other.getX();
        final double newY = getY() - other.getY();
        return Point.of(newX, newY);
    }

    /**
     * Multiplies current point by multiplier.
     *
     * @param multiplier the value which point is multiplied by.
     * @return the result of multiplication of the current point by multiplier.
     */
    @Nonnull
    public Point multipleBy(double multiplier) {
        final double newX = getX() * multiplier;
        final double newY = getY() * multiplier;
        return Point.of(newX, newY);
    }

    /**
     * Divides the current point by the given number.
     * If round is {@code true}, result of division is rounded.
     *
     * @param divider the value which point is divided by.
     * @return the result of division of the current point by multiplier,
     *  optionally rounded.
     */
    @Nonnull
    public Point dividedBy(double divider) {
        final double newX = getX() / divider;
        final double newY = getY() / divider;
        return Point.of(newX, newY);
    }

    /**
     * Computes distance between the current and the given points.
     *
     * @param other the point for which distance from the current point is computed.
     * @return the distance between the current and the given points.
     */
    @Nonnull
    public double distanceTo(AbstractPoint other) {
        final double xDistance = other.getX() - getX();
        final double yDistance = other.getY() - getY();
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    /**
     * @return copy of the current point with rounded coordinates.
     */
    @Nonnull
    public Point round() {
        return AbstractPoint.rounded(getX(), getY());
    }

    /**
     * @return copy of the current point with rounded down coordinates.
     */
    @Nonnull
    public Point floor() {
        final double newX = Math.floor(getX());
        final double newY = Math.floor(getY());
        return Point.of(newX, newY);
    }

    public static Point rounded(double x, double y) {
        final double roundedX = Math.round(x);
        final double roundedY = Math.round(y);
        return Point.of(roundedX, roundedY);
    }
}
