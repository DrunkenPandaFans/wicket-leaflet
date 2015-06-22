package sk.drunkenpanda.leaflet.components.map;

import sk.drunkenpanda.leaflet.models.ILayer;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapOptions implements Serializable {

    private Integer zoom;
    private Integer minZoom;
    private Integer maxZoom;
    private LatLngBounds maxBounds;

    private List<ILayer> layers;
    // todo add CRS

    private boolean dragging;
    private boolean touchZoom ;
    private boolean scrollWheelZoom;
    private boolean doubleClickZoom;
    private boolean boxZoom;
    private boolean tap;
    private int tapTolerance;
    private boolean trackResize;
    private boolean worldCopyJump;
    private boolean closePopupOnClick;
    private boolean bounceAtZoomLimit;

    private boolean keyboard;
    private int keyboardPanOffset;
    private int keyboardZoomOffset;

    private boolean inertia;
    private int inertiaDeceleration;
    private int inertiaMaxSpeed;
    private Integer inertiaThreshold;

    private boolean zoomControl;
    private boolean attributionControl;

    private Boolean fadeAnimation;
    private Boolean zoomAnimation;
    private int zoomAnimationThreshold;
    private Boolean markerZoomAnimation;


    public MapOptions() {
        this.dragging = true;
        this.touchZoom = true;
        this.scrollWheelZoom = true;
        this.boxZoom = true;
        this.doubleClickZoom = true;
        this.tap = true;
        this.tapTolerance = 15;
        this.trackResize = true;
        this.worldCopyJump = false;
        this.closePopupOnClick = true;
        this.bounceAtZoomLimit = true;
        this.keyboard = true;
        this.keyboardPanOffset = 80;
        this.keyboardZoomOffset = 1;
        this.inertia = true;
        this.inertiaDeceleration = 3000;
        this.inertiaMaxSpeed = 1500;
        this.attributionControl = true;
        this.zoomAnimationThreshold = 4;
        this.layers = new ArrayList<>();
    }

    /**
     * @return initial map zoom
     */
    public Integer getZoom() {
        return this.zoom;
    }

    /**
     * Sets new initial map zoom
     * @param zoom initial map zoom
     * @return instance of this map for chaining
     */
    public MapOptions setZoom(Integer zoom) {
        this.zoom = zoom;
        return this;
    }

    /**
     * @return minimum zoom level of map.
     */
    public Integer getMinZoom() {
        return this.minZoom;
    }

    /**
     * Sets minimum zoom level of map. This zoom level overrides minimum zoom of any layer.
     * @param minZoom minimum zoom level
     * @return this instance for chaining
     */
    public MapOptions setMinZoom(Integer minZoom) {
        this.minZoom = minZoom;
        return this;
    }

    /**
     * @return maximum zoom level of map
     */
    public Integer getMaxZoom() {
        return this.maxZoom;
    }

    /**
     * Sets maximum zoom level of map. This overrides maximum zoom of any layer.
     * @param maxZoom maximum zoom level
     * @return this instance for chaining
     */
    public MapOptions setMaxZoom(Integer maxZoom) {
        this.maxZoom = maxZoom;
        return this;
    }

    public List<ILayer> getLayers() {
        return this.layers;
    }

    public MapOptions addLayer(ILayer layer) {
        this.layers.add(layer);
        return this;
    }

    public MapOptions setLayers(List<ILayer> layers) {
        this.layers.clear();
        this.layers.addAll(layers);
        return this;
    }

    /**
     * When this options is set, map restricts user view to given bounds, bouncing the user back
     * when he tries to pan outside of view.
     * @return maximum geographical bounds
     */
    public LatLngBounds getMaxBounds() {
        return this.maxBounds;
    }

    /**
     * Sets new maximum geographical bounds.
     * @param maxBounds maximum geographical bounds.
     * @return this instance for chaining
     */
    public MapOptions setMaxBounds(LatLngBounds maxBounds) {
        this.maxBounds = maxBounds;
        return this;
    }

    /**
     * @return whether map can be draggable with touch/mouse or not
     */
    public boolean isDragging() {
        return this.dragging;
    }

    /**
     * Sets whether the map can be draggable or not
     * @param dragging if {@code true} map is draggable
     * @return this instance for chaining
     */
    public MapOptions setDragging(boolean dragging) {
        this.dragging = dragging;
        return this;
    }

    /**
     * @return whether map can be zoomed by touch-dragging with two fingers.
     */
    public boolean isTouchZoom() {
        return this.touchZoom;
    }

    /**
     * Sets whether the map can be zoomed by touch-dragging with two fingers.
     * @param touchZoom if {@code true} map can be zoomed by touch
     * @return this instance for chaining
     */
    public MapOptions setTouchZoom(boolean touchZoom) {
        this.touchZoom = touchZoom;
        return this;
    }

    /**
     * @return whether the map can be zoomed by using mouse wheel or not
     */
    public boolean isScrollWheelZoom() {
        return this.scrollWheelZoom;
    }

    /**
     * Sets whether the map can be zoomed by using mouse wheel or not.
     * @param scrollWheelZoom {@code true} if map can be zoomed by mouse wheel
     * @return this instance for chaining
     */
    public MapOptions setScrollWheelZoom(boolean scrollWheelZoom) {
        this.scrollWheelZoom = scrollWheelZoom;
        return this;
    }

    /**
     * @return whether the map can be zoomed by double-clicking on it and zoom out by double-clicking while pressing shift button.
     */
    public boolean isDoubleClickZoom() {
        return this.doubleClickZoom;
    }

    /**
     * Sets whether the map can be zoomed by double-clicking on it.
     * @param doubleClickZoom  {@code true} if map can be zoomed by double-clicking
     * @return this instance for chaining
     */
    public MapOptions setDoubleClickZoom(boolean doubleClickZoom) {
        this.doubleClickZoom = doubleClickZoom;
        return this;
    }

    /**
     * @return whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     */
    public boolean isBoxZoom() {
        return this.boxZoom;
    }

    /**
     * Sets whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     * @param boxZoom {@code true} if map can be zoomed by boxing
     * @return this instance for chaining
     */
    public MapOptions setBoxZoom(boolean boxZoom) {
        this.boxZoom = boxZoom;
        return this;
    }

    /**
     * @return whether the map enables mobile hacks for supporting instant taps and touch holds (fired as contextmenu)
     */
    public boolean isTap() {
        return this.tap;
    }

    /**
     * Sets whether the map enables mobile hacks for supporting instant taps and touch holds.
     * @param tap {@code true} if map enables taps and touch holds
     * @return this instance for chaining
     */
    public MapOptions setTap(boolean tap) {
        this.tap = tap;
        return this;
    }

    /**
     * @return the max number of pixels a user can shift his finger during touch for it to be considered valid tap.
     */
    public int getTapTolerance() {
        return this.tapTolerance;
    }

    /**
     * Sets max number of pixels a user can shift his finger during touch for it to be considered valid tap
     * @param tapTolerance max number of pixels a user can shift his finger during touch.
     * @return this instance for chaining
     */
    public MapOptions setTapTolerance(int tapTolerance) {
        this.tapTolerance = tapTolerance;
        return this;
    }

    /**
     * @return whether the map automatically handles browser resize to update itself.
     */
    public boolean isTrackResize() {
        return this.trackResize;
    }

    /**
     * Sets whether the map should automatically handle browser resize
     * @param trackResize {@code true} if map handles browser resizing
     * @return this instance for chaining
     */
    public MapOptions setTrackResize(boolean trackResize) {
        this.trackResize = trackResize;
        return this;
    }

    /**
     * With this options enabled, map tracks when you pan to another copy of the world and seamlessly jumps to the original one,
     * so that all overlays are still visible.
     * @return whether map handles panning to another copy of world seamlessly
     */
    public boolean isWorldCopyJump() {
        return this.worldCopyJump;
    }

    /**
     * Sets whether map should handle panning to another copy of world seamlessly.
     * @param worldCopyJump {@code true} map handles seamless panning between worlds
     * @return this instance for chaining
     */
    public MapOptions setWorldCopyJump(boolean worldCopyJump) {
        this.worldCopyJump = worldCopyJump;
        return this;
    }

    /**
     * @return whether popup windows should close on user click
     */
    public boolean isClosePopupOnClick() {
        return this.closePopupOnClick;
    }

    /**
     * Sets whether popup window should close on click.
     * @param closePopupOnClick {@code true} popup window closes on user click.
     * @return this instance for chaining
     */
    public MapOptions setClosePopupOnClick(boolean closePopupOnClick) {
        this.closePopupOnClick = closePopupOnClick;
        return this;
    }

    /**
     * If {@code false}, the map zooms beyond min/max zoom limit and then bounces back with pinch-zooming.
     * @return whether map respects zoom limits
     */
    public boolean isBounceAtZoomLimit() {
        return this.bounceAtZoomLimit;
    }

    /**
     * Sets whether map respects zoom limits or not.
     * @param bounceAtZoomLimit if {@code true} map respects zoom limit.
     * @return this instance for chaining
     * @see #isBounceAtZoomLimit()
     */
    public MapOptions setBounceAtZoomLimit(boolean bounceAtZoomLimit) {
        this.bounceAtZoomLimit = bounceAtZoomLimit;
        return this;
    }

    /**
     * @return makes the map focusable and allows users to navigate the map with keyboard arrows and +/- keys.
     */
    public boolean isKeyboard() {
        return this.keyboard;
    }

    /**
     * Sets whether map can be navigated by keyboard.
     * @param keyboard if {@code true} map is navigated by keyboard
     * @return this instance for chaining
     */
    public MapOptions setKeyboard(boolean keyboard) {
        this.keyboard = keyboard;
        return this;
    }

    /**
     * @return amount of pixels to pan when pressing an arrow key
     */
    public int getKeyboardPanOffset() {
        return this.keyboardPanOffset;
    }

    /**
     * Sets amount of pixels to pan when pressing an arrow key
     * @param keyboardPanOffset amount of pixels to pan when pressing an arrow key
     * @return this instance for chaining
     */
    public MapOptions setKeyboardPanOffset(int keyboardPanOffset) {
        this.keyboardPanOffset = keyboardPanOffset;
        return this;
    }

    /**
     * @return number of zoom level to change when pressing +/-
     */
    public int getKeyboardZoomOffset() {
        return this.keyboardZoomOffset;
    }

    /**
     * Sets number of zoom levels to change when pressing +/-.
     * @param keyboardZoomOffset number of zoom levels to change
     * @return this instance for chaining
     */
    public MapOptions setKeyboardZoomOffset(int keyboardZoomOffset) {
        this.keyboardZoomOffset = keyboardZoomOffset;
        return this;
    }

    /**
     * Returns if the map uses inertia effect.
     * This effect builds momentum when dragging and moving in same direction for some time.
     * @return whether map uses inertia effect
     */
    public boolean isInertia() {
        return this.inertia;
    }

    /**
     * Sets whether map uses inertia effect.
     * @param inertia if {@code true} map should use inertia effect
     * @return this instance for chaining
     */
    public MapOptions setInertia(boolean inertia) {
        this.inertia = inertia;
        return this;
    }

    /**
     * The rate with which inertia movement slows down in pixels/second.
     * @return rate with which inertia movement slows down
     */
    public int getInertiaDeceleration() {
        return this.inertiaDeceleration;
    }

    /**
     * Sets the rate with which inertia movement slows down in pixels/second.
     * @param inertiaDeceleration rate with which inertia movement slows down.
     * @return this instance for chaining
     */
    public MapOptions setInertiaDeceleration(int inertiaDeceleration) {
        this.inertiaDeceleration = inertiaDeceleration;
        return this;
    }

    /**
     * @return maximum speed of inertia acceleration in pixels/second
     */
    public int getInertiaMaxSpeed() {
        return this.inertiaMaxSpeed;
    }

    /**
     * Sets maximum speed of inertia acceleration in pixels/second
     * @param inertiaMaxSpeed new maximum speed
     * @return this instance for chaining
     */
    public MapOptions setInertiaMaxSpeed(int inertiaMaxSpeed) {
        this.inertiaMaxSpeed = inertiaMaxSpeed;
        return this;
    }

    /**
     * @return number of milliseconds that should pass between stopping the movement and releasing touch or mouse.
     */
    public Integer getInertiaThreshold() {
        return this.inertiaThreshold;
    }

    /**
     * Sets new inertia threshold.
     * @param inertiaThreshold number of milliseconds that should pass between stopping the movement and releasing touch or mouse
     * @return this instance for chaining
     */
    public MapOptions setInertiaThreshold(Integer inertiaThreshold) {
        this.inertiaThreshold = inertiaThreshold;
        return this;
    }

    /**
     * @return whether the zoom control is added to the map
     */
    public boolean isZoomControl() {
        return this.zoomControl;
    }

    /**
     * Sets whether the zoom control is added to the map.
     * @param zoomControl if {@code true} zoom control is added
     * @return this instance for chaining
     */
    public MapOptions setZoomControl(boolean zoomControl) {
        this.zoomControl = zoomControl;
        return this;
    }

    /**
     * @return whether the attribution control is added to the map
     */
    public boolean isAttributionControl() {
        return this.attributionControl;
    }

    /**
     * Sets whether the attribution control is added to the map.
     * @param attributionControl if {@code true} attribution control is added
     * @return this instance for chaining
     */
    public MapOptions setAttributionControl(boolean attributionControl) {
        this.attributionControl = attributionControl;
        return this;
    }

    /**
     * @return whether the fade animation is enabled
     */
    public Boolean getFadeAnimation() {
        return this.fadeAnimation;
    }

    /**
     * Sets whether the fade animation should be enabled.
     * @param fadeAnimation if {@code true} fade animation is enabled
     * @return this instance for chaining
     */
    public MapOptions setFadeAnimation(Boolean fadeAnimation) {
        this.fadeAnimation = fadeAnimation;
        return this;
    }

    /**
     * @return whether the zoom animation is enabled.
     */
    public Boolean getZoomAnimation() {
        return this.zoomAnimation;
    }

    /**
     * Sets whether the zoom animation should be enabled.
     * @param zoomAnimation if {@code true} zoom animation is enabled
     * @return this instance for chaining
     */
    public MapOptions setZoomAnimation(Boolean zoomAnimation) {
        this.zoomAnimation = zoomAnimation;
        return this;
    }

    /**
     * The threshold to not use zoom animation. If zoom difference exceeds animation is not used.
     * @return threshold for using zoom animation
     */
    public int getZoomAnimationThreshold() {
        return this.zoomAnimationThreshold;
    }

    /**
     * Sets new threshold to not use zoom animation.
     * @param zoomAnimationThreshold new threshold for using zoom animation
     * @return this instance for chaining
     */
    public MapOptions setZoomAnimationThreshold(int zoomAnimationThreshold) {
        this.zoomAnimationThreshold = zoomAnimationThreshold;
        return this;
    }

    /**
     * If animation is turn off, marker aren't visible for length of animation.
     * @return whether marker animates its zoom with zoom animation.
     */
    public Boolean getMarkerZoomAnimation() {
        return this.markerZoomAnimation;
    }

    /**
     * Sets if markers should be animated with zoom animation.
     * @param markerZoomAnimation if {@code true} markers are animated with zoom animation
     * @return this instance for chaining
     */
    public MapOptions setMarkerZoomAnimation(Boolean markerZoomAnimation) {
        this.markerZoomAnimation = markerZoomAnimation;
        return this;
    }

}
