package sk.drunkenpanda.leaflet.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapOptions;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.TileLayer;
import sk.drunkenpanda.leaflet.models.TileLayerOptions;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		final IModel<LatLng> center = new Model<>(new LatLng(37.7833, -122.4167));

		final TileLayerOptions tilesOptions = new TileLayerOptions()
				.setAttribution("Map data &copy;...")
				.setMaxZoom(18)
				.addExtraParameter("id", "<map_id>")
				.addExtraParameter("accessToken", "<access_token>");
		final TileLayer tileLayer = new TileLayer("https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}", tilesOptions);

		final MapOptions options = new MapOptions()
				.setZoom(5)
                .setDoubleClickZoom(false)
				.addLayer(tileLayer);
		add(new Map("map", center, options));
    }
}
