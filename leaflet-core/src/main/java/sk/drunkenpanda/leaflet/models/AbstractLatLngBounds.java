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
 * Represents rectangular geographical area on map.
 * @author Jan Ferko
 */
@ModelStyle
@Value.Immutable(builder = false)
public abstract class AbstractLatLngBounds implements Serializable {

    @Value.Check
    public void check() {
        Args.isTrue(getNorthEast().getLatitude() >= getSouthWest().getLatitude(), "North latitude must be greater than south latitude");
        Args.isTrue(getNorthEast().getLongitude() >= getSouthWest().getLongitude(), "East longitude must be greater than west longitude");
    }

    /**
     * @return the north-east point of the bound
     */
    @Nonnull
    @Value.Parameter
    public abstract LatLng getNorthEast();

    /**
     * @return the south-west point of the bound
     */
    @Nonnull
    @Value.Parameter
    public abstract LatLng getSouthWest();

    /**
     * @return the north latitude of the bounds
     */
    public double getNorth() {
        return getNorthEast().getLatitude();
    }

    /**
     * @return the east longitude of the bounds
     */
    public double getEast() {
        return getNorthEast().getLongitude();
    }

    /**
     * @return the south latitude of the bounds
     */
    public double getSouth() {
        return getSouthWest().getLatitude();
    }

    /**
     * @return the west longitude of the bounds
     */
    public double getWest() {
        return getSouthWest().getLongitude();
    }

    /**
     * @return the center point of the bounds
     */
    @Nonnull
    public LatLng getCenter() {
        final double centerLat = (getNorth() + getSouth()) / 2;
        final double centerLong = (getEast() + getWest()) / 2;

        return LatLng.of(centerLat, centerLong);
    }
}
