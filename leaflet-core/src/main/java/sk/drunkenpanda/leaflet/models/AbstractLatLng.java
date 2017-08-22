/*
 * Copyright 2014 Jan Ferko.
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

package sk.drunkenpanda.leaflet.models;

import java.io.Serializable;

import javax.annotation.Nonnull;

import org.apache.wicket.util.lang.Args;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import sk.drunkenpanda.leaflet.json.ProperJson;

/**
 * Models represents point on map with certain latitude and longitude.
 *
 * @author Jan Ferko
 */
@ModelStyle
@Value.Immutable(builder = false)
@ProperJson
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public abstract class AbstractLatLng implements Serializable {

    @Value.Check
    protected void check() {
        Args.withinRange(-90.0, 90.0, getLatitude(), "latitude");
        Args.withinRange(-180.0, 180.0, getLongitude(), "longitude");
    }

    /**
     * @return latitude of point
     */
    @Nonnull
    @Value.Parameter
    @JsonProperty()
    public abstract double getLatitude();

    /**
     * @return longitude of point
     */
    @Nonnull
    @Value.Parameter
    @JsonProperty()
    public abstract double getLongitude();
}
