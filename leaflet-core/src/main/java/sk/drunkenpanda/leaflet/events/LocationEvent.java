/*
 * Copyright 2016 Jan Ferko.
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

package sk.drunkenpanda.leaflet.events;

import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

/**
 * Event triggered when geolocation went successfully.
 *
 * @author Jan Ferko
 */
public final class LocationEvent implements Event {

    /**
     * The event type.
     */
    private final MapEventType type;

    /**
     * Detected geographical location of the user.
     */
    private final LatLng latLng;

    /**
     * Geographical boundaries of the area user is located in (with respect to the accuracy of location).
     */
    private final LatLngBounds latLngBounds;

    /**
     * Accuracy of location in meters.
     */
    private final double accuracy;

    /**
     * Height of the position above the WSG84 ellipsoid in meters.
     */
    private final double altitude;

    /**
     * Accuracy of altitude in meters.
     */
    private final double altitudeAccuracy;

    /**
     * The direction of travel in degrees counting clockwise from true North.
     */
    private final double heading;

    /**
     * Current velocity in meters per second.
     */
    private final double speed;

    /**
     * The time when position was acquired in seconds elapsed since January 1, 1970.
     */
    private final double timestamp;

    /**
     * Constructs new location event from given parameters.
     *
     * @param type the event type that triggered event.
     * @param latLng detected geographical location of the user
     * @param latLngBounds geographical boundaries of the area user is located in (with respect to the accuracy of location)
     * @param accuracy accuracy of location in meters
     * @param altitude height of the position above the WSG84 ellipsoid in meters.
     * @param altitudeAccuracy accuracy of altitude in meters
     * @param heading the direction of travel in degrees counting clockwise from true North
     * @param speed current velocity in meters per second
     * @param timestamp the time when position was acquired in seconds elapsed since January 1, 1970
     */
    public LocationEvent(MapEventType type, LatLng latLng, LatLngBounds latLngBounds,
            double accuracy, double altitude, double altitudeAccuracy, double heading, double speed,
            double timestamp) {
        this.type = type;
        this.latLng = latLng;
        this.latLngBounds = latLngBounds;
        this.accuracy = accuracy;
        this.altitude = altitude;
        this.altitudeAccuracy = altitudeAccuracy;
        this.heading = heading;
        this.speed = speed;
        this.timestamp = timestamp;
    }

    /**
     * Returns detected geographical location of the user.
     *
     * @return detected geographical location of the user
     */
    public LatLng getLatLng() {
        return this.latLng;
    }

    /**
     * Returns geographical boundaries of the area user is located in (with respect to the accuracy of location).
     *
     * @return geographical boundaries of the area user is located in (with respect to the accuracy of location)
     */
    public LatLngBounds getLatLngBounds() {
        return this.latLngBounds;
    }

    /**
     * Returns accuracy of location in meters.
     * It returns accuracy, if returns {@link Double#NaN}.
     *
     * @return accuracy of location in meters
     */
    public double getAccuracy() {
        return this.accuracy;
    }

    /**
     * Returns height of the position above the WSG84 ellipsoid in meters.
     * If altitude was not found, it returns {@link Double#NaN}.
     *
     * @return height of the position above the WSG84 ellipsoid in meters
     */
    public double getAltitude() {
        return this.altitude;
    }

    /**
     * Returns accuracy of altitude in meters.
     * If altitude accuracy was not found, it returns {@link Double#NaN}
     *
     * @return accuracy of altitude in meters
     */
    public double getAltitudeAccuracy() {
        return this.altitudeAccuracy;
    }

    /**
     * Returns the direction of travel in degrees counting clockwise from true North.
     * If heading was not found, it returns {@link Double#NaN}.
     *
     * @return the direction of travel in degrees counting clockwise from true North
     */
    public double getHeading() {
        return this.heading;
    }

    /**
     * Returns current velocity in meters per second.
     * If velocity was not found, it returns {@link Double#NaN}.
     *
     * @return current velocity in meters per second
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the time when position was acquired in seconds elapsed since January 1, 1970.
     *
     * @return the time when position was acquired in seconds elapsed since January 1, 1970
     */
    public double getTimestamp() {
        return this.timestamp;
    }

    @Override
    public MapEventType getType() {
        return this.type;
    }
}
