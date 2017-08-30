package sk.drunkenpanda.leaflet.components.marker;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.immutables.value.Value;

/**
 * Configuration of map marker.
 *
 * @author Jan Ferko
 */
@Value.Style(
        typeAbstract = "Abstract*",
        typeImmutable = "*"
)
@Value.Immutable
public abstract class AbstractMarkerOptions implements Serializable {

    // TODO Add customizable ICON

    /**
     * If {@code false}, the marker will not emit mouse events and will act as a part of the underlying map.
     */
    @Value.Default
    public boolean isClickable() {
        return true;
    }

    /**
     * Whether marker is draggable by mouse/touch or not.
     *
     * @return {@code true} if marker is draggable by mouse/touch, otherwise {@code false}.
     */
    @Value.Default
    public boolean isDraggable() {
        return false;
    }

    /**
     * Whether the marker can be tapped to with a keyboard or clicked by pressing enter.
     *
     * @return {@code true} if the marker can be tapped to with a keyboard or clicked by pressing enter,
     * otherwise {@code false}.
     */
    @Value.Default
    public boolean allowsKeyboard() {
        return true;
    }

    /**
     * Text for the browser tooltip, that appears on marker hover (no tooltip by default).
     *
     * @return text for the browser tooltip
     */
    @Nonnull
    @Value.Default
    public String getTitle() {
        return "";
    }

    /**
     * Text for the alt attribute of marker image (useful for accessibility).
     *
     * @return text for the alt attribute of marker image.
     */
    @Nonnull
    @Value.Default
    public String getAlt() {
        return "";
    }

    /**
     * By default, zIndex for the marker image is set based on its latitude.
     * Use this option if you want to put the marker on top of all others (or below),
     * by specifying a high value like 1000 (or high negative value).
     *
     * @return zindex for the marker.
     */
    @Value.Default
    public int getZIndexOffset() {
        return 0;
    }

    /**
     * The opacity of the marker.
     *
     * @return the opacity of the marker.
     */
    @Value.Default
    public double getOpacity() {
        return 1.0;
    }

    /**
     * If {@code true}, the marker will get on top of the others when you hover the mouse over it.
     *
     * @return {@code true} the marker will get on top of the others when you hover the mouse over it
     */
    @Value.Default
    public boolean shouldRiseOnHover() {
        return false;
    }

    /**
     * The z-index offset used for {@code riseOnHover} feature.
     *
     * @return the z-index offset used for {@code riseOnHover} feature
     * @see #shouldRiseOnHover()
     */
    @Value.Default
    public int getRiseOffset() {
        return 250;
    }
}
