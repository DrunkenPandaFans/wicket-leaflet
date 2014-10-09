package sk.drunkenpanda.leaflet.options;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ján Koščák
 * @sa.date 2014-10-09T15:14:11+0100
 */
public class MarkerOptions {
    
    private static final String CLICKABLE = "clickable";
    private static final String DRAGGABLE = "draggable";
    private static final String KEYBOARD = "keyboard";
    private static final String TITLE = "title";
    private static final String ALT = "alt";
    private static final String Z_INDEX_OFFSET = "zIndexOffset";
    private static final String OPACITY = "opacity";
    private static final String RISE_ON_HOVER = "riseOnHover";
    private static final String RISE_OFFSET = "riseOffset";
    
    private final Map<String,String> options;
    
    public MarkerOptions() {
        options = new HashMap<>();
    }
    
    public void setClickable(final Boolean clickable) {
        options.put(CLICKABLE, clickable.toString());
    }
    
    public void setDraggable(final Boolean draggable) {
        options.put(DRAGGABLE, draggable.toString());
    }
    
    public void setKeyboard(final Boolean keyboard) {
        options.put(KEYBOARD, keyboard.toString());
    }
    
    public void setTitle(final String title) {
        options.put(TITLE, "\'" + title + "\'");
    }
    
    public void setAlt(final String alt) {
        options.put(ALT, "\'" + alt + "\'");
    }
    
    public void setZIndexOffset(final Integer zIndexOffset) {
        options.put(Z_INDEX_OFFSET, zIndexOffset.toString());
    }
    
    public void setOpacity(final Double opacity) {
        options.put(OPACITY,opacity.toString());
    }
    
    public void setRiseOnHover(final Boolean riseOnHover) {
        options.put(RISE_ON_HOVER, riseOnHover.toString());
    }
    
    public void setRiseOffset(final Integer riseOffset) {
        options.put(RISE_OFFSET, riseOffset.toString());
    }
    
    public boolean isEmpty() {
        return options.isEmpty();
    }
    
    @Override
    public String toString() {
        return "{" + TITLE + " : " + options.get(TITLE) + "}";
    }

}
