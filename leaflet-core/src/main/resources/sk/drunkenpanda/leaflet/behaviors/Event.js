var WicketLeaflet = WicketLeaflet || {};

WicketLeaflet.Event = {};

WicketLeaflet.Event.getEvent = function(event) {
    var e = {};

    if (event.type !== undefined) {
        e.type = event.type;
    }

    return JSON.stringify(e);
};