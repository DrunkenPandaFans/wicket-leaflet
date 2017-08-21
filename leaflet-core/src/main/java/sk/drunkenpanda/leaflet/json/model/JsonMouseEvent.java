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
import sk.drunkenpanda.leaflet.events.MouseEvent;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.Point;

public final class JsonMouseEvent implements JsonEntity<MouseEvent> {

    private String type;
    private JsonLatLng latLng;
    private JsonPoint layerPoint;
    private JsonPoint containerPoint;

    public JsonLatLng getLatLng() {
        return this.latLng;
    }

    public void setLatLng(JsonLatLng latLng) {
        this.latLng = latLng;
    }

    public JsonPoint getLayerPoint() {
        return this.layerPoint;
    }

    public void setLayerPoint(JsonPoint layerPoint) {
        this.layerPoint = layerPoint;
    }

    public JsonPoint getContainerPoint() {
        return this.containerPoint;
    }

    public void setContainerPoint(JsonPoint containerPoint) {
        this.containerPoint = containerPoint;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public MouseEvent toModel() {
        LatLng latLngModel = null;
        if (latLng != null) {
            latLngModel = latLng.toModel();
        }

        Point layerPointModel = null;
        if (layerPoint != null) {
            layerPointModel = layerPoint.toModel();
        }

        Point containerPointModel = null;
        if (containerPoint != null) {
            containerPointModel = containerPoint.toModel();
        }

        final MapEventType eventType = MapEventType.find(type);
        return MouseEvent.builder()
                .type(eventType)
                .containerPoint(containerPointModel)
                .layerPoint(layerPointModel)
                .latLng(latLngModel)
                .build();
    }

}
