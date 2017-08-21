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

import org.immutables.value.Value;

import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

/**
 * Event triggered when geolocation went successfully.
 *
 * @author Jan Ferko
 */
@EventStyle
@Value.Immutable
public abstract class AbstractLocationEvent implements Event {

    /**
     * Returns detected geographical location of the user.
     *
     * @return detected geographical location of the user
     */
    public abstract LatLng getLatLng();

    /**
     * Returns geographical boundaries of the area user is located in (with respect to the accuracy of location).
     *
     * @return geographical boundaries of the area user is located in (with respect to the accuracy of location)
     */
    public abstract LatLngBounds getLatLngBounds();

    /**
     * Returns accuracy of location in meters.
     * It returns accuracy, if returns {@link Double#NaN}.
     *
     * @return accuracy of location in meters
     */
    public abstract double getAccuracy();

    /**
     * Returns height of the position above the WSG84 ellipsoid in meters.
     * If altitude was not found, it returns {@link Double#NaN}.
     *
     * @return height of the position above the WSG84 ellipsoid in meters
     */
    public abstract double getAltitude();

    /**
     * Returns accuracy of altitude in meters.
     * If altitude accuracy was not found, it returns {@link Double#NaN}
     *
     * @return accuracy of altitude in meters
     */
    public abstract double getAltitudeAccuracy();

    /**
     * Returns the direction of travel in degrees counting clockwise from true North.
     * If heading was not found, it returns {@link Double#NaN}.
     *
     * @return the direction of travel in degrees counting clockwise from true North
     */
    public abstract double getHeading();

    /**
     * Returns current velocity in meters per second.
     * If velocity was not found, it returns {@link Double#NaN}.
     *
     * @return current velocity in meters per second
     */
    public abstract double getSpeed();

    /**
     * Returns the time when position was acquired in seconds elapsed since January 1, 1970.
     *
     * @return the time when position was acquired in seconds elapsed since January 1, 1970
     */
    public abstract double getTimestamp();

    @Override
    public abstract MapEventType getType();
}
