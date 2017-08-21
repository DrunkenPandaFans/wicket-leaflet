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
import sk.drunkenpanda.leaflet.models.Point;

/**
 * Model that describes mouse event created by interacting with Leaflet components.
 *
 * @author Jan Ferko
 */
public final class MouseEvent implements Event {

    /**
     * The event type.
     */
    private final MapEventType type;

    /**
     * The geographical point where the mouse event occurred.
     */
    private final LatLng latLng;

    /**
     * Pixel coordinates of the point where mouse event occurred relative to map layer.
     */
    private final Point layerPoint;

    /**
     * Pixel coordinates of the point where mouse event occurred relative to map container.
     */
    private final Point containerPoint;

    /**
     * Constructs new mouse event.
     *
     * @param type the event type (e.g. 'click')
     * @param latLng the geographical point where the mouse event occurred
     * @param layerPoint pixel coordinates of the point where mouse event occurred relative to map layer
     * @param containerPoint pixel coordinates of the point where mouse event occurred relative to map container
     */
    public MouseEvent(MapEventType type, LatLng latLng, Point layerPoint, Point containerPoint) {
        this.type = type;
        this.latLng = latLng;
        this.layerPoint = layerPoint;
        this.containerPoint = containerPoint;
    }

    /**
     * Returns the geographical point where the mouse event occurred.
     *
     * @return the geographical point where the mouse event occurred or {@code null}
     *  if mouse event does not provide data about geographical point where it occurred
     */
    public LatLng getLatLng() {
        return this.latLng;
    }

    /**
     * Returns pixel coordinates of the point where mouse event occurred relative to map layer.
     *
     * @return pixel coordinates of the point where mouse event occurred relative to map layer or {@code null}
     *  if mouse event does not provide data about layer point.
     */
    public Point getLayerPoint() {
        return this.layerPoint;
    }

    /**
     * Returns pixel coordinates of the point where mouse event occurred relative to map container.
     *
     * @return pixel coordinates of the point where mouse event occurred relative to map container or {@code null}
     *  if mouse event does not provide data about container point.
     */
    public Point getContainerPoint() {
        return this.containerPoint;
    }

    @Override
    public MapEventType getType() {
        return this.type;
    }
}
