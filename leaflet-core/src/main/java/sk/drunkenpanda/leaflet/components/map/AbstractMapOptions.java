package sk.drunkenpanda.leaflet.components.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.immutables.value.Value;

import sk.drunkenpanda.leaflet.models.ILayer;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

@Value.Style(
        typeAbstract = "Abstract*",
        typeImmutable = "*"
)
@Value.Immutable
public abstract class AbstractMapOptions implements Serializable {

    /**
     * @return initial map zoom
     */
    @Nullable
    public abstract Integer getZoom();

    /**
     * @return minimum zoom level of map.
     */
    @Nullable
    public abstract Integer getMinZoom();

    /**
     * @return maximum zoom level of map
     */
    @Nullable
    public abstract Integer getMaxZoom();

    @Nonnull
    @Value.Default
    public List<ILayer> getLayers() {
        return new ArrayList<>();
    }

    /**
     * When this options is set, map restricts user view to given bounds, bouncing the user back
     * when he tries to pan outside of view.
     * @return maximum geographical bounds
     */
    @Nullable
    public abstract LatLngBounds getMaxBounds();

    /**
     * @return whether map can be draggable with touch/mouse or not
     */
    @Value.Default
    public boolean isDragging() {
        return true;
    }

    /**
     * @return whether map can be zoomed by touch-dragging with two fingers.
     */
    @Value.Default
    public boolean isTouchZoom() {
        return true;
    }

    /**
     * @return whether the map can be zoomed by using mouse wheel or not
     */
    @Value.Default
    public boolean isScrollWheelZoom() {
        return true;
    }

    /**
     * @return whether the map can be zoomed by double-clicking on it and zoom out by double-clicking while pressing shift button.
     */
    @Value.Default
    public boolean isDoubleClickZoom() {
        return true;
    }

    /**
     * @return whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     */
    @Value.Default
    public boolean isBoxZoom() {
        return true;
    }

    /**
     * @return whether the map enables mobile hacks for supporting instant taps and touch holds (fired as contextmenu)
     */
    @Value.Default
    public boolean isTap() {
        return true;
    }

    /**
     * @return the max number of pixels a user can shift his finger during touch for it to be considered valid tap.
     */
    @Value.Default
    public int getTapTolerance() {
        return 15;
    }

    /**
     * @return whether the map automatically handles browser resize to update itself.
     */
    @Value.Default
    public boolean isTrackResize() {
        return true;
    }

    /**
     * With this options enabled, map tracks when you pan to another copy of the world and seamlessly jumps to the original one,
     * so that all overlays are still visible.
     * @return whether map handles panning to another copy of world seamlessly
     */
    @Value.Default
    public boolean isWorldCopyJump() {
        return false;
    }

    /**
     * @return whether popup windows should close on user click
     */
    @Value.Default
    public boolean isClosePopupOnClick() {
        return true;
    }

    /**
     * If {@code false}, the map zooms beyond min/max zoom limit and then bounces back with pinch-zooming.
     * @return whether map respects zoom limits
     */
    @Value.Default
    public boolean isBounceAtZoomLimit() {
        return true;
    }

    /**
     * @return makes the map focusable and allows users to navigate the map with keyboard arrows and +/- keys.
     */
    @Value.Default
    public boolean isKeyboard() {
        return true;
    }

    /**
     * @return amount of pixels to pan when pressing an arrow key
     */
    @Value.Default
    public int getKeyboardPanOffset() {
        return 80;
    }

    /**
     * @return number of zoom level to change when pressing +/-
     */
    @Value.Default
    public int getKeyboardZoomOffset() {
        return 1;
    }

    /**
     * Returns if the map uses inertia effect.
     * This effect builds momentum when dragging and moving in same direction for some time.
     * @return whether map uses inertia effect
     */
    @Value.Default
    public boolean isInertia() {
        return true;
    }

    /**
     * The rate with which inertia movement slows down in pixels/second.
     * @return rate with which inertia movement slows down
     */
    @Value.Default
    public int getInertiaDeceleration() {
        return 3000;
    }

    /**
     * @return maximum speed of inertia acceleration in pixels/second
     */
    @Value.Default
    public int getInertiaMaxSpeed() {
        return 1500;
    }

    /**
     * @return number of milliseconds that should pass between stopping the movement and releasing touch or mouse.
     */
    @Nullable
    public abstract Integer getInertiaThreshold();

    /**
     * @return whether the zoom control is added to the map
     */
    public boolean isZoomControl() {
        return true;
    }

    /**
     * @return whether the attribution control is added to the map
     */
    public boolean isAttributionControl() {
        return true;
    }

    /**
     * @return whether the fade animation is enabled
     */
    @Nullable
    public abstract Boolean getFadeAnimation();

    /**
     * @return whether the zoom animation is enabled.
     */
    @Nullable
    public abstract Boolean getZoomAnimation();

    /**
     * The threshold to not use zoom animation. If zoom difference exceeds animation is not used.
     * @return threshold for using zoom animation
     */
    @Value.Default
    public int getZoomAnimationThreshold() {
        return 4;
    }

    /**
     * If animation is turn off, marker aren't visible for length of animation.
     * @return whether marker animates its zoom with zoom animation.
     */
    @Nullable
    public abstract Boolean getMarkerZoomAnimation();

}
