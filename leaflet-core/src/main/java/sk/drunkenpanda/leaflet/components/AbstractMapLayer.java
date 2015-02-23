package sk.drunkenpanda.leaflet.components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.util.lang.Args;

import java.util.*;

/**
 * Abstract class
 * @author Jan Ferko
 */
public abstract class AbstractMapLayer<T extends OptionKey> extends Behavior {

    private final java.util.Map<T, Object> options;

    private final String id;

    private final String jsObjectName;

    public AbstractMapLayer(String id, String jsObjectName) {
        Args.notNull(id, "id");
        Args.notNull(jsObjectName, "jsObjectName");
        this.id = id;
        this.jsObjectName = jsObjectName;
        this.options = new HashMap<>();
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        String javaScript = toJSON();
        // add layer to map
        String mapId = component.getMarkupId();
        response.render(OnLoadHeaderItem.forScript(javaScript));
    }

    protected String getJsObjectName() {
        return jsObjectName;
    }

    public String getId() {
        return id;
    }

    protected boolean setOption(T key, Object value) {
        boolean wasSet = false;
        if (key != null) {
            options.put(key, value);
            wasSet = true;
        }
        return wasSet;
    }

    private String toJSON() {
        StringBuilder jsonBuilder = new StringBuilder("var ");
        jsonBuilder.append(getId())
                .append(" = ")
                .append(getJsObjectName())
                .append("(")
                .append(getId())
                .append(", {");

        for (Map.Entry<T, Object> entry  : options.entrySet()) {
            jsonBuilder.append(entry.getKey().getName())
                    .append(": ")
                    .append(entry.getValue())
                    .append(",\n");
        }

        jsonBuilder.append("});");

        return jsonBuilder.toString();
    }
}
