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

/**
 * The simplest kind of event. It does not provide any additional payload.
 *
 * @author Jan Ferko
 */
public final class PlainEvent implements Event {

    private final MapEventType type;

    public PlainEvent(MapEventType type) {
        this.type = type;
    }

    @Override
    public MapEventType getType() {
        return type;
    }
}
