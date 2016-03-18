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
import sk.drunkenpanda.leaflet.models.Point;

public final class MouseEvent extends Event {

    private final LatLng latLng;

    private final Point layerPoint;

    private final Point containerPoint;

    public MouseEvent(String type, LatLng latLng, Point layerPoint, Point containerPoint) {
        super(type);
        this.latLng = latLng;
        this.layerPoint = layerPoint;
        this.containerPoint = containerPoint;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public Point getLayerPoint() {
        return this.layerPoint;
    }

    public Point getContainerPoint() {
        return this.containerPoint;
    }

}
