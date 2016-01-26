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
 * Represents rectangular geographical area on map.
 * @author Jan Ferko
 */
public class LatLngBounds implements Serializable {

    private final LatLng northEast;

    private final LatLng southWest;

    /**
     * Constructor creates new rectangular area for given bounds.
     * Bounds can't be {@null}.
     * Latitude/longitude of northEast corner must be greater than or equal latitude/longitude of southWest bound.
     *
     * @param southWest south-west corner of rectangle
     * @param northEast north-east corner of rectangle
     * @throws IllegalArgumentException if one of corner is {@code null} or
     *  if latitude/longitude of southWest corner is greater than latitude/longitude of northEast corner
     */
    public LatLngBounds(LatLng southWest, LatLng northEast) {
        Args.notNull(southWest, "southWest");
        Args.notNull(northEast, "northEast");
        Args.isTrue(northEast.getLatitude() >= southWest.getLatitude(), "North latitude must be greater than south latitude");
        Args.isTrue(northEast.getLongitude() >= southWest.getLongitude(), "East longitude must be greater than west longitude");

        this.northEast = northEast;
        this.southWest = southWest;
    }

    /**
     * @return the north-east point of the bound
     */
    public LatLng getNorthEast() {
        return this.northEast;
    }

    /**
     * @return the south-west point of the bound
     */
    public LatLng getSouthWest() {
        return this.southWest;
    }

    /**
     * @return the north latitude of the bounds
     */
    public double getNorth() {
        return this.northEast.getLatitude();
    }

    /**
     * @return the east longitude of the bounds
     */
    public double getEast() {
        return this.northEast.getLongitude();
    }

    /**
     * @return the south latitude of the bounds
     */
    public double getSouth() {
        return this.southWest.getLatitude();
    }

    /**
     * @return the west longitude of the bounds
     */
    public double getWest() {
        return this.southWest.getLongitude();
    }

    /**
     * @return the center point of the bounds
     */
    public LatLng getCenter() {
        double centerLat = (getNorth() + getSouth()) / 2;
        double centerLong = (getEast() + getWest()) / 2;

        return new LatLng(centerLat, centerLong);
    }

    /**
     * {@inheritDoc }
     *
     * Method uses {@link #getNorthEast() } and {@link #getSouthWest() } to compare instance with object.
     *
     * @param obj the object to be compared with the instance
     * @return {@code true} if object is equal to instance, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }

        LatLngBounds other = (LatLngBounds) obj;
        return northEast.equals(other.northEast) && southWest.equals(other.southWest);
    }

    /**
     *{@inheritDoc }
     *
     * @return hash code of instance based on its northEast and southWest corners
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.northEast.hashCode();
        hash = 71 * hash + this.southWest.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc }
     * @return text representation of bounds with its northEast and southWest corners
     */
    @Override
    public String toString() {
        return String.format("LatLngBounds {northEast=[%1$s], southWest=[%2$s]}",
                northEast, southWest);
    }
}
