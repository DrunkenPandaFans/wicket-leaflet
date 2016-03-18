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

package sk.drunkenpanda.leaflet.json.model;

import sk.drunkenpanda.leaflet.events.LocationEvent;

public final class JsonLocationEvent implements JsonEntity<LocationEvent> {

    private String type;

    private JsonLatLng latLng;

    private JsonLatLngBounds latLngBounds;

    private double accuracy;

    private double altitude;

    private double altitudeAccuracy;

    private double heading;

    private double speed;

    private double timestamp;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonLatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(JsonLatLng latLng) {
        this.latLng = latLng;
    }

    public JsonLatLngBounds getLatLngBounds() {
        return latLngBounds;
    }

    public void setLatLngBounds(JsonLatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    public void setAltitudeAccuracy(double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public LocationEvent toModel() {
        return new LocationEvent(type, latLng.toModel(), latLngBounds.toModel(), accuracy, altitude,
                altitudeAccuracy, heading, speed, timestamp);
    }

}
