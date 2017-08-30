package sk.drunkenpanda.leaflet.components.marker;

import javax.annotation.Nonnull;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;
import sk.drunkenpanda.leaflet.models.LatLng;

/**
 * @author Jan Ferko
 */
public final class Marker extends Behavior {

    @Nonnull
    private final IModel<LatLng> position;

    @Nonnull
    private final MarkerOptions markerOptions;

    public Marker(@Nonnull IModel<LatLng> position, @Nonnull MarkerOptions markerOptions) {
        this.position = position;
        this.markerOptions = markerOptions;
    }

    @Override
    public void bind(Component component) {
        if (!(component instanceof Map)) {
            throw new IllegalArgumentException(
                    "You tried to add Marker to ["
                    + component.getClass()
                    + "]. Marker can be added only to Map component"
            );
        }
        super.bind(component);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        final ResourceReference markerScript = new PackageResourceReference(getClass(), "MarkerBehavior.js");
        response.render(JavaScriptHeaderItem.forReference(markerScript));

        response.render(OnLoadHeaderItem.forScript(getMarkerScript(component)));
    }

    private String getMarkerScript(Component component) {
        final Map map = (Map) component;
        final String mapVarName = map.getMapVarName();
        final JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();

        final String encodedOptions = renderer.toJson(this.markerOptions);

        final LatLng latLng = this.position.getObject();
        if (latLng != null) {
            final String encodedPosition = renderer.toJson(latLng);
            return "WicketLeaflet.Marker.init("
                    + mapVarName
                    + ", "
                    + encodedPosition
                    + ", "
                    + encodedOptions + ");";
        }  else {
            throw new IllegalStateException("Position model contains null. To render marker, position must be set.");
        }
    }
}
