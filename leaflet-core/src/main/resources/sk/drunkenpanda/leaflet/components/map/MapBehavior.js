var WicketLeaflet = WicketLeaflet || {};

WicketLeaflet.Map = {};

WicketLeaflet.Map.prepareOptions = function(options, center) {
    var newOptions = options;
    newOptions.center = center;
    return newOptions;
};