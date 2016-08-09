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

package sk.drunkenpanda.leaflet.components.map;

/**
 * Enumeration of all events produced by map component.
 *
 * @author Jan Ferko
 */
public enum MapEventType {

    /**
     * Fired when user clicks (or taps) on the map.
     */
    CLICK("click"),

    /**
     * Fired when user double-clicks (or double-taps) on the map.
     */
    DOUBLE_CLICK("dblclick"),

    /**
     * Fired when user pushes the mouse button on the map.
     */
    MOUSEDOWN("mousedown"),

    /**
     * Fired when user releases the mouse button on the map.
     */
    MOUSEUP("mouseup"),

    /**
     * Fired when mouse cursor enters the map.
     */
    MOUSEOVER("mouseover"),

    /**
     * Fired when mouse cursor leaves the map.
     */
    MOUSEOUT("mouseout"),

    /**
     * Fired when mouse cursor moves over the map.
     */
    MOUSEMOVE("mousemove"),

    /**
     * Fired when user pushes right mouse button on the map.
     * It prevents default browser context menu to show, if there are listeners registered to this event.
     */
    CONTEXT_MENU("contextmenu"),

    /**
     * Fired when user focuses the map, either by tabbing or clicking/panning.
     */
    FOCUS("focus"),

    /**
     * Fired when user the map loses focus.
     */
    BLUR("blur"),

    /**
     * Fired before the mouse click on the map.
     */
    PRECLICK("preclick"),

    /**
     * Fired when the map is initialized.
     */
    LOAD("load"),

    /**
     * Fired when the map is destroyed.
     */
    UNLOAD("unload"),

    /**
     * Fired when the map needs to redraw its content.
     */
    VIEW_RESET("viewreset"),

    /**
     * Fired when the view of the map starts changing.
     */
    MOVE_START("movestart"),

    /**
     * Fired on any movement of the map view.
     */
    MOVE("move"),

    /**
     * Fired on when the map stops changing.
     */
    MOVE_END("moveend"),

    /**
     * Fired when the user starts dragging the map.
     */
    DRAG_START("dragstart"),

    /**
     * Fired while the user is dragging the map.
     */
    DRAG("drag"),

    /**
     * Fired when the user stops dragging the map.
     */
    DRAG_END("dragend"),

    /**
     * Fired when the map zoom is about to change.
     */
    ZOOM_START("zoomstart"),

    /**
     * Fired when the map zoom changes.
     */
    ZOOM_END("zoomend"),

    /**
     * Fired when the zoom levels on the map is changed due to adding or removing the layer.
     */
    ZOOM_LEVELS_CHANGE("zoomlevelschange"),

    /**
     * Fired when the map is resized.
     */
    RESIZE("resize"),

    /**
     * Fired when the map starts autopanning when opening the popup.
     */
    AUTOPAN_START("autopanstart"),

    /**
     * Fired when layer is added.
     */
    LAYER_ADD("layeradd"),

    /**
     * Fired when layer is removed.
     */
    LAYER_REMOVE("layerremove"),

    /**
     * Fired when base layer is changed through the layer control.
     */
    BASE_LAYER_CHANGE("baselayerchange"),

    /**
     * Fired when overlay is selected through the layer control.
     */
    OVERLAY_ADD("overlayadd"),

    /**
     * Fired when overlay is deselected through the layer control.
     */
    OVERLAY_REMOVE("overlayremove"),

    /**
     * Fired when geolocation was successful.
     */
    LOCATION_FOUND("locationfound"),

    /**
     * Fired when geolocation failed.
     */
    LOCATION_ERROR("locationerror"),

    /**
     * Fired when the popup is opened.
     */
    POPUP_OPEN("popupopen"),

    /**
     * Fired when the popup is closed.
     */
    POPUP_CLOSE("popupclose");

    /**
     * The javascript event name.
     */
    private final String javascriptName;

    /**
     * Creates new map event type for given name.
     *
     * @param javascriptName the javascript event name
     */
    private MapEventType(String javascriptName) {
        this.javascriptName = javascriptName;
    }

    /**
     * @return the javascript event name
     */
    public String getJavascriptName() {
        return this.javascriptName;
    }

    /**
     * Finds event with given javascript name.
     *
     * @param javascriptName the map event name in javascript
     * @return event with given javascript name or {@code null} if event does not exist.
     */
    public static MapEventType find(String javascriptName) {
        for (MapEventType eventType : values()) {
            if (eventType.getJavascriptName().equals(javascriptName)) {
                return eventType;
            }
        }

        return null;
    }

}
