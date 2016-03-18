/*
 * Copyright 2016 ferko.
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

import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

public final class LocationEvent extends Event {

    private final LatLng latLng;

    private final LatLngBounds latLngBounds;

    private final Number accuracy;

    private final Number altitude;

    private final Number altitudeAccuracy;

    private final Number heading;

    private final Number speed;

    private final Number timestamp;

    public LocationEvent(String type, LatLng latLng, LatLngBounds latLngBounds,
            Number accuracy, Number altitude, Number altitudeAccuracy, Number heading, Number speed,
            Number timestamp) {
        super(type);
        this.latLng = latLng;
        this.latLngBounds = latLngBounds;
        this.accuracy = accuracy;
        this.altitude = altitude;
        this.altitudeAccuracy = altitudeAccuracy;
        this.heading = heading;
        this.speed = speed;
        this.timestamp = timestamp;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public LatLngBounds getLatLngBounds() {
        return this.latLngBounds;
    }

    public Number getAccuracy() {
        return this.accuracy;
    }

    public Number getAltitude() {
        return this.altitude;
    }

    public Number getAltitudeAccuracy() {
        return this.altitudeAccuracy;
    }

    public Number getHeading() {
        return this.heading;
    }

    public Number getSpeed() {
        return this.speed;
    }

    public Number getTimestamp() {
        return this.timestamp;
    }

}
