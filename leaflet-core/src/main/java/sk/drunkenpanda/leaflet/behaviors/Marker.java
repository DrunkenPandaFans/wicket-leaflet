package sk.drunkenpanda.leaflet.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import sk.drunkenpanda.leaflet.components.Map;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.options.MarkerOptions;


/**
 *
 * @author Ján Koščák
 * @sa.date 2014-09-30T14:04:17+0100
 */
public class Marker extends Behavior {
    
    private LatLng latLng;
    
    private MarkerOptions options;
    
    public Marker(final LatLng latLng) {
        this(latLng,null);
    }
    
    public Marker(final LatLng latLng, final MarkerOptions options) {
        this.latLng = latLng;
        this.options = options;
    }
    
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        if (component instanceof Map) {
            response.render(OnLoadHeaderItem.forScript(createScript(component.getMarkupId())));
        }
    }
    
    private String createScript(String mapId) {
        final StringBuilder builder = new StringBuilder();
        builder.append("L.marker([")//
                .append(this.getLatLng().getLatitude())//
                .append(",")//
                .append(this.latLng.getLongitude())//
                .append("]")//
                .append((options == null || options.isEmpty()) ? "" : "," + options.toString())//
                .append(").addTo(")//
                .append(mapId)//
                .append(");");
        return builder.toString();
    }
    
    public LatLng getLatLng() {
        return this.latLng;
    }
    
    public void setLatLng(final LatLng latLng) {
        this.latLng = latLng;
    }
    /*
    public void setIcon(final Icon icon) {
        this.icon = icon;
    }
    */
    
    public void setZIndexOffset(final Integer zIndexOffset) {
        this.options.setZIndexOffset(zIndexOffset);
    }
    
    public void setOpacity(final Double opacity) {
        this.options.setOpacity(opacity);
    }  
}
