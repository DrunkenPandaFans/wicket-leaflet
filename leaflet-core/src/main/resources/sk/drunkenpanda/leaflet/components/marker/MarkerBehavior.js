var WicketLeaflet = WicketLeaflet || {};

WicketLeaflet.Marker = {};

WicketLeaflet.Marker.init = function(map, latLng, markerOptions) {
    var marker = L.marker(latLng, markerOptions);
    marker.addTo(map);
    return marker;
}