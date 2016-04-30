"use strict";

var WicketLeaflet = WicketLeaflet || {};

WicketLeaflet.MouseEvent = {};

WicketLeaflet.MouseEvent.getMouseEvent = function(event) {
    var mouseEvent = {};

    if (event.type !== undefined) {
        mouseEvent.type = event.type;
    }

    if (event.latLng !== undefined) {
        mouseEvent.latLng = event.latLng;
    }

    if (event.layerPoint !== undefined) {
        mouseEvent.layerPoint = event.layerPoint;
    }

    if (event.containerPoint !== undefined) {
        mouseEvent.containerPoint = event.containerPoint;
    }

    return JSON.stringify(mouseEvent);
};