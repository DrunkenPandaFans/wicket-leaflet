var WicketLeaflet = WicketLeaflet || {};

WicketLeaflet.DragEndEvent = {};

WicketLeaflet.DragEndEvent.getDragEndEvent = function(event) {
    var dragEndEvent = {};

    if (event.type !== undefined) {
        dragEndEvent.type = event.type;
    }
    if (event.distance !== undefined) {
        dragEndEvent.distance = event.distance;
    }

    return JSON.stringify(dragEndEvent);
};