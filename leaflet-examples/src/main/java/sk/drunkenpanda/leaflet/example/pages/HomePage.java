package sk.drunkenpanda.leaflet.example.pages;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import sk.drunkenpanda.leaflet.behaviors.MouseEventBehavior;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.components.map.MapOptions;
import sk.drunkenpanda.leaflet.events.MouseEvent;
import sk.drunkenpanda.leaflet.example.config.MapboxConfiguration;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;
import sk.drunkenpanda.leaflet.models.TileLayer;
import sk.drunkenpanda.leaflet.models.TileLayerOptions;

public class HomePage extends AbstractLeafletExamplePage {
    private static final long serialVersionUID = 1L;

    @Inject
    private MapboxConfiguration mapboxConfiguration;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        final IModel<LatLng> center = new Model<>(LatLng.of(51.505, -0.09));
        final LatLngBounds bounds = LatLngBounds.of(
                LatLng.of(55.0, 1),
                LatLng.of(50.0, -1)
        );

        final TileLayerOptions tilesOptions = TileLayerOptions.builder()
                .attribution("Map data &copy;...")
                .maxZoom(18)
                .putExtraParameters("id", mapboxConfiguration.getMapId())
                .putExtraParameters("accessToken", mapboxConfiguration.getAccessToken())
                .build();
        final TileLayer tileLayer = TileLayer.of(mapboxConfiguration.getUrlTemplate(), tilesOptions);

        final MapOptions options = MapOptions.builder()
                .zoom(13)
                .isDoubleClickZoom(false)
                .addLayers(tileLayer)
                .maxBounds(bounds)
                .build();
        final Map map = new Map("map", center, options);
        map.add(new MouseEventBehavior(MapEventType.CLICK) {
            @Override
            protected void onEvent(MouseEvent event, AjaxRequestTarget target) {
                System.out.println(event.toString());
                target.appendJavaScript("alert(\"You clicked on map!\");");
            }
        });
        add(map);
    }
}
