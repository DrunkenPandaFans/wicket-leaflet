package sk.drunkenpanda.leaflet.components.map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;
import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;

public final class MapBehavior extends Behavior {

    private final Map map;

    public MapBehavior(Map map) {
        this.map = map;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(getClass(), "MapBehavior.js")));

        String optionsScript = getMapOptionsScript();
        String mapScript = String.format("window.%1$s = L.map('%2$s', %3$s);", map.getMapVarName(),
                map.getMarkupId(), getMapOptionsVarName());
        response.render(OnLoadHeaderItem.forScript(optionsScript + mapScript));
    }

    @Override
    public void bind(Component component) {
        if (!(component instanceof Map)) {
            throw new IllegalArgumentException("MapBehavior accepts only Map component.");
        }
        super.bind(component);
    }

    private String getMapOptionsVarName() {
        return map.getMapVarName() + "Options";
    }

    private String getMapOptionsScript() {
        JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();
        String optionsVarName = getMapOptionsVarName();
        String options = renderer.toJson(map.getOptions());
        String center = renderer.toJson(map.getModelObject());

        return String.format("var %1$s = WicketLeaflet.Map.prepareOptions(%2$s, %3$s);",
                optionsVarName, options, center);
    }
}
