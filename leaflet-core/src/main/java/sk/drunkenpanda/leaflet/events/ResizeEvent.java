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
import sk.drunkenpanda.leaflet.models.Point;

/**
 * Event triggered when map changes its size.
 *
 * @author Jan Ferko
 */
public final class ResizeEvent implements Event {

    /**
     * The event type.
     */
    private final MapEventType type;

    /**
     * The old size before resize event.
     */
    private final Point oldSize;

    /**
     * The new size after resize event.
     */
    private final Point newSize;

    /**
     * Constructs new resize events with given parameters.
     *
     * @param type the event type that triggered event
     * @param oldSize the old size before resize event
     * @param newSize the new size after resize event
     */
    public ResizeEvent(MapEventType type, Point oldSize, Point newSize) {
        this.type = type;
        this.oldSize = oldSize;
        this.newSize = newSize;
    }

    /**
     * Returns the old size before resize event.
     *
     * @return the old size before resize event
     */
    public Point getOldSize() {
        return this.oldSize;
    }

    /**
     * Returns the new size after resize event.
     *
     * @return the new size after resize event
     */
    public Point getNewSize() {
        return this.newSize;
    }

    @Override
    public MapEventType getType() {
        return this.type;
    }
}
