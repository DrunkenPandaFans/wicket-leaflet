package sk.drunkenpanda.leaflet.example.pages;

import javax.inject.Inject;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapOptions;
import sk.drunkenpanda.leaflet.example.config.MapboxConfiguration;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.TileLayer;
import sk.drunkenpanda.leaflet.models.TileLayerOptions;

public class HomePage extends AbstractLeafletExamplePage {
    private static final long serialVersionUID = 1L;

    @Inject
    private MapboxConfiguration mapboxConfiguration;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        final IModel<LatLng> center = new Model<LatLng>(new LatLng(51.505, -0.09));

        final TileLayerOptions tilesOptions = new TileLayerOptions()
                .setAttribution("Map data &copy;...")
                .setMaxZoom(18)
                .addExtraParameter("id", mapboxConfiguration.getMapId())
                .addExtraParameter("accessToken", mapboxConfiguration.getAccessToken());
        final TileLayer tileLayer = new TileLayer(mapboxConfiguration.getUrlTemplate(), tilesOptions);

        final MapOptions options = new MapOptions()
                .setZoom(13)
                .setDoubleClickZoom(false)
                .addLayer(tileLayer);
        add(new Map("map", center, options));
    }
}
