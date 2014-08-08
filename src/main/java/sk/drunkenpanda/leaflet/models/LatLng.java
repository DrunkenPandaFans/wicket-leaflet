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
 * Models represents point on map with certain latitude and longitude.
 * 
 * @author Jan Ferko 
 */
public class LatLng implements Serializable {
    
    private final double latitude;
    
    private final double longitude;
    
    /**
     * Constructor creates new point with given latitude and longitude.
     * 
     * @param latitude latitude of point in degrees
     * @param longitude longitude of point in degrees
     * @throws IllegalArgumentException if {@code latitude} is not in range [-90, 90]
     *  or longitude is not in range [-180, 180].
     */
    public LatLng(double latitude, double longitude) {
        Args.withinRange(-90.0, 90.0, latitude, "latitude");
        Args.withinRange(-180.0, 180.0, longitude, "longitude");
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**     
     * @return latitude of point
     */
    public double getLatitude() {
        return this.latitude;
    }
    
    /**     
     * @return longitude of point
     */
    public double getLongitude() {
        return this.longitude;
    }
    
    /**
     * {@inheritDoc }
     * 
     * Method uses {@code latitude} and {@code longitude} of point for equality comparison.
     * 
     * @param obj object to be compared with instance
     * @return {@code true} if object is equal to instance based on its latitude and longitude,
     *  {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LatLng)) {
            return false;
        }
        
        LatLng other = (LatLng) obj;
        return latitude == other.latitude && longitude == other.longitude;
    }
    
    /**
     * {@inheritDoc }
     * @return hash code of instance based on its latitude and longitude
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }
    
    /**
     * {@inheritDoc }
     * @return string representation of LatLng, that contains its latitude and longitude
     */
    @Override
    public String toString() {
        return String.format("LatLng{lat=%1$.2f, long=%2$.2f}", latitude, longitude);
    }
}
