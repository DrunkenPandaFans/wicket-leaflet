"use strict";

var WicketLeaflet = WicketLeaflet || {};

WicketLeaflet.ResizeEvent = {};

WicketLeaflet.ResizeEvent.getResizeEvent = function(event) {
    var resizeEvent = {};

    if (event.type !== undefined) {
        resizeEvent.type = event.type;
    }
    if (event.oldSize !== undefined) {
        resizeEvent.oldSize = event.oldSize;
    }
    if (event.newSize !== undefined) {
        resizeEvent.newSize = event.newSize;
    }

    return JSON.stringify(resizeEvent);
};