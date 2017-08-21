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

import org.apache.wicket.util.lang.Args;
import org.immutables.value.Value;

/**
 * Represents a rectangular area in pixels.
 *
 * @author Jan Ferko
 */
@ModelStyle
@Value.Immutable(builder = false)
public abstract class AbstractBounds implements Serializable {

    @Value.Check
    protected void check() {
        Args.isTrue(getTopLeft().getX() <= getBottomRight().getX(), "X coordinate of topLeft must be less than or equal to X coordinate of bottomRight");
        Args.isTrue(getTopLeft().getY() <= getBottomRight().getY(), "Y coordinate of topLeft must be less than or equal to Y coordinate of bottomRight");
    }

    /**
     * @return the top-left corner of bounds
     */
    @Nonnull
    @Value.Parameter
    public abstract Point getTopLeft();

    /**
     * @return the bottom-right corner of bounds
     */
    @Nonnull
    @Value.Parameter
    public abstract Point getBottomRight();

    /**
     * @return the center point of bounds
     */
    @Nonnull
    public Point getCenter() {
        final double centerX = (getBottomRight().getX() + getTopLeft().getX()) / 2;
        final double centerY = (getBottomRight().getY() + getTopLeft().getY()) / 2;

        return Point.of(centerX, centerY);
    }
}
