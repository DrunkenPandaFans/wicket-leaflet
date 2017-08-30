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

package sk.drunkenpanda.leaflet.components.map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MapEventType {

    CLICK("click"),

    DOUBLE_CLICK("dblclick"),

    MOUSEDOWN("mousedown"),

    MOUSEUP("mouseup"),

    MOUSEOVER("mouseover"),

    MOUSEOUT("mouseout"),

    MOUSEMOVE("mousemove"),

    CONTEXT_MOVE("contextmenu"),

    FOCUS("focus"),

    BLUR("blur"),

    PRECLICK("preclick"),

    LOAD("load"),

    UNLOAD("unload"),

    VIEW_RESET("viewreset"),

    MOVE_START("movestart"),

    MOVE("move"),

    MOVE_END("moveend"),

    DRAG_START("dragstart"),

    DRAG("drag"),

    DRAG_END("dragend"),

    ZOOM_START("zoomstart"),

    ZOOM_END("zoomend"),

    ZOOM_LEVELS_CHANGE("zoomlevelschange"),

    RESIZE("resize"),

    AUTOPAN_START("autopanstart"),

    LAYER_ADD("layeradd"),

    LAYER_REMOVE("layerremove"),

    BASE_LAYER_CHANGE("baselayerchange"),

    OVERLAY_ADD("overlayadd"),

    OVERLAY_REMOVE("overlayremove"),

    LOCATION_FOUND("locationfound"),

    LOCATION_ERROR("locationerror"),

    POPUP_OPEN("popupopen"),

    POPUP_CLOSE("popupclose");

    private final String javascriptName;

    private MapEventType(String javascriptName) {
        this.javascriptName = javascriptName;
    }

    @JsonValue
    public String getJavascriptName() {
        return this.javascriptName;
    }

    @JsonCreator
    public static MapEventType find(String javascriptName) {
        for (MapEventType eventType : values()) {
            if (eventType.getJavascriptName().equals(javascriptName)) {
                return eventType;
            }
        }

        return null;
    }

}
