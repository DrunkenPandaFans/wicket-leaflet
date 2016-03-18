"use strict";

var WicketLeaflet = window.WicketLeaflet || {};

WicketLeaflet.LocationEvent = {};

WicketLeaflet.LocationEvent.getLocationEvent = function(event) {
    var locationEvent = {};

    if (event.latLng !== undefined) {
        locationEvent.latLng = event.latLng;
    }

    if (event.bounds !== undefined) {
        locationEvent.bounds = event.bounds;
    }

    if (event.accuracy !== undefined) {
        locationEvent.accuracy = event.accuracy;
    }

    if (event.altitude !== undefined) {
        locationEvent.altitude = event.altitude;
    }

    if (event.altitudeAccuracy !== undefined) {
        locationEvent.altitudeAccuracy = event.altitudeAccuracy;
    }

    if (event.heading !== undefined) {
        locationEvent.heading = event.heading;
    }

    if (event.speed !== undefined) {
        locationEvent.speed = event.speed;
    }

    if (event.timestamp !== undefined) {
        locationEvent.timestamp = event.timestamp;
    }

    return JSON.stringify(locationEvent);
};

WicketLeaflet.LocationEvent.getLocationError = function(event) {
    var locationError = {};

    if (event.getMessage !== undefined) {
        locationError.message = event.message;
    }
    if (event.getCode !== undefined) {
        locationError.code = event.code;
    }

    return JSON.stringify(locationError);
};




