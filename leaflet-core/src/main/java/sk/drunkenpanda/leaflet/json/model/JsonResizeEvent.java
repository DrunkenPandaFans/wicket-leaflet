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

package sk.drunkenpanda.leaflet.json.model;

import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.ResizeEvent;

public final class JsonResizeEvent implements JsonEntity<ResizeEvent> {

    private String type;

    private JsonPoint oldSize;

    private JsonPoint newSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonPoint getOldSize() {
        return oldSize;
    }

    public void setOldSize(JsonPoint oldSize) {
        this.oldSize = oldSize;
    }

    public JsonPoint getNewSize() {
        return newSize;
    }

    public void setNewSize(JsonPoint newSize) {
        this.newSize = newSize;
    }

    @Override
    public ResizeEvent toModel() {
        MapEventType eventType = MapEventType.find(type);
        return new ResizeEvent(eventType, oldSize.toModel(), newSize.toModel());
    }

}
