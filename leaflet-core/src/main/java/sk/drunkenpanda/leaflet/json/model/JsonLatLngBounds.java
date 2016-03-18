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

import sk.drunkenpanda.leaflet.models.LatLngBounds;

public final class JsonLatLngBounds implements JsonEntity<LatLngBounds> {

    private JsonLatLng northEast;

    private JsonLatLng southWest;

    public JsonLatLng getNorthEast() {
        return this.northEast;
    }

    public void setNorthEast(JsonLatLng northEast) {
        this.northEast = northEast;
    }

    public JsonLatLng getSouthWest() {
        return this.southWest;
    }

    public void setSouthWest(JsonLatLng southWest) {
        this.southWest = southWest;
    }

    @Override
    public LatLngBounds toModel() {
        return new LatLngBounds(northEast.toModel(), southWest.toModel());
    }
}
