/*
 * Copyright 2014 Jan Ferko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sk.drunkenpanda.leaflet.components.map;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;
import sk.drunkenpanda.leaflet.resources.LeafletResourcesBehavior;

import java.util.HashMap;

/**
 *
 * @author Jan Ferko
 */
public class Map extends GenericPanel<LatLng> {

    private final java.util.Map<MapOptions, Object> options = new HashMap<>();

    public Map(String id) {
        this(id, null);
    }

    public Map(String id, IModel<LatLng> model) {
        super(id, model);
        setDefaultOptions();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnLoadHeaderItem.forScript(getScript()));
    }

    private void setDefaultOptions() {
        options.put(MapOptions.DRAGGING, true);
        options.put(MapOptions.TOUCH_ZOOM, true);
        options.put(MapOptions.SCROLL_WHEEL_ZOOM, true);
        options.put(MapOptions.BOX_ZOOM, true);
        options.put(MapOptions.DOUBLE_CLICK_ZOOM, true);
        options.put(MapOptions.TAP, true);
        options.put(MapOptions.TAP_TOLERANCE, 15);
        options.put(MapOptions.TRACK_RESIZE, true);
        options.put(MapOptions.WORLD_COPY_JUMP, false);
        options.put(MapOptions.CLOSE_POPUP_ON_CLICK, true);
        options.put(MapOptions.BOUNCE_AT_ZOOM_LIMIT, true);
        options.put(MapOptions.KEYBOARD, true);
        options.put(MapOptions.KEYBOARD_PAN_OFFSET, 80);
        options.put(MapOptions.KEYBOARD_ZOOM_OFFSET, 1);
        options.put(MapOptions.INERTIA, true);
        options.put(MapOptions.INERTIA_DECELARATION, 3000);
        options.put(MapOptions.INERTIA_MAX_SPEED, 1500);
        options.put(MapOptions.ATTRIBUTION_CONTROL, true);
        options.put(MapOptions.ZOOM_ANIMATION_THRESHOLD, 4);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(LeafletResourcesBehavior.instance());
    }

    protected String getScript() {
        return "var " + getMarkupId() + " = L.map('" + getMarkupId() + "', {\n"
                + "    center: [51.505, -0.09],\n"
                + "    zoom: 13});";
    }

    /**
     * @return initial map zoom
     */
    public Integer getZoom() {
        return (Integer) options.get(MapOptions.ZOOM);
    }

    /**
     * Sets new initial map zoom
     * @param zoom initial map zoom
     * @return instance of this map for chaining
     */
    public Map setZoom(int zoom) {
        options.put(MapOptions.ZOOM, zoom);
        return this;
    }

    /**
     * @return minimum zoom level of map.
     */
    public Integer getMinZoom() {
        return (Integer) options.get(MapOptions.MIN_ZOOM);
    }

    /**
     * Sets minimum zoom level of map. This zoom level overrides minimum zoom of any layer.
     * @param minZoom minimum zoom level
     * @return this instance for chaining
     */
    public Map setMinZoom(int minZoom) {
        options.put(MapOptions.MIN_ZOOM, minZoom);
        return this;
    }

    /**
     * @return maximum zoom level of map
     */
    public Integer getMaxZoom() {
        return (Integer) options.get(MapOptions.MAX_ZOOM);
    }

    /**
     * Sets maximum zoom level of map. This overrides maximum zoom of any layer.
     * @param maxZoom maximum zoom level
     * @return this instance for chaining
     */
    public Map setMaxZoom(int maxZoom) {
        options.put(MapOptions.MAX_ZOOM, maxZoom);
        return this;
    }

    /**
     * When this options is set, map restricts user view to given bounds, bouncing the user back
     * when he tries to pan outside of view.
     * @return maximum geographical bounds
     */
    public LatLngBounds getMaxBounds() {
        return (LatLngBounds) options.get(MapOptions.MAX_BOUND);
    }

    /**
     * Sets new maximum geographical bounds.
     * @param maxBounds maximum geographical bounds.
     * @return this instance for chaining
     */
    public Map setMaxBounds(LatLngBounds maxBounds) {
        options.put(MapOptions.MAX_BOUND, maxBounds);
        return this;
    }

    /**
     * @return whether map can be draggable with touch/mouse or not
     */
    public boolean isDragging() {
        return (boolean) options.get(MapOptions.DRAGGING);
    }

    /**
     * Sets whether the map can be draggable or not
     * @param dragging if {@code true} map is draggable
     * @return this instance for chaining
     */
    public Map setDragging(boolean dragging) {
        options.put(MapOptions.DRAGGING, dragging);
        return this;
    }

    /**
     * @return whether map can be zoomed by touch-dragging with two fingers.
     */
    public boolean isTouchZoom() {
        return (boolean) options.get(MapOptions.TOUCH_ZOOM);
    }

    /**
     * Sets whether the map can be zoomed by touch-dragging with two fingers.
     * @param touchZoom if {@code true} map can be zoomed by touch
     * @return this instance for chaining
     */
    public Map setTouchZoom(boolean touchZoom) {
        options.put(MapOptions.TOUCH_ZOOM, touchZoom);
        return this;
    }

    /**
     * @return whether the map can be zoomed by using mouse wheel or not
     */
    public boolean isScrollWheelZoom() {
        return (boolean) options.get(MapOptions.SCROLL_WHEEL_ZOOM);
    }

    /**
     * Sets whether the map can be zoomed by using mouse wheel or not.
     * @param scrollWheelZoom {@code true} if map can be zoomed by mouse wheel
     * @return this instance for chaining
     */
    public Map setScrollWheelZoom(boolean scrollWheelZoom) {
        options.put(MapOptions.SCROLL_WHEEL_ZOOM, scrollWheelZoom);
        return this;
    }

    /**
     * @return whether the map can be zoomed by double-clicking on it and zoom out by double-clicking while pressing shift button.
     */
    public boolean isDoubleClickZoom() {
        return (boolean) options.get(MapOptions.DOUBLE_CLICK_ZOOM);
    }

    /**
     * Sets whether the map can be zoomed by double-clicking on it.
     * @param doubleClickZoom  {@code true} if map can be zoomed by double-clicking
     * @return this instance for chaining
     */
    public Map setDoubleClickZoom(boolean doubleClickZoom) {
        options.put(MapOptions.DOUBLE_CLICK_ZOOM, doubleClickZoom);
        return this;
    }

    /**
     * @return whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     */
    public boolean isBoxZoom() {
        return (boolean) options.get(MapOptions.BOX_ZOOM);
    }

    /**
     * Sets whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     * @param boxZoom {@code true} if map can be zoomed by boxing
     * @return this instance for chaining
     */
    public Map setBoxZoom(boolean boxZoom) {
        options.put(MapOptions.BOX_ZOOM, boxZoom);
        return this;
    }

    /**
     * @return whether the map enables mobile hacks for supporting instant taps and touch holds (fired as contextmenu)
     */
    public boolean isTap() {
        return (boolean) options.get(MapOptions.TAP);
    }

    /**
     * Sets whether the map enables mobile hacks for supporting instant taps and touch holds.
     * @param tap {@code true} if map enables taps and touch holds
     * @return this instance for chaining
     */
    public Map setTap(boolean tap) {
        options.put(MapOptions.TAP, tap);
        return this;
    }

    /**
     * @return the max number of pixels a user can shift his finger during touch for it to be considered valid tap.
     */
    public int getTapTolerance() {
        return (int) options.get(MapOptions.TAP_TOLERANCE);
    }

    /**
     * Sets max number of pixels a user can shift his finger during touch for it to be considered valid tap
     * @param tapTolerance max number of pixels a user can shift his finger during touch.
     * @return this instance for chaining
     */
    public Map setTapTolerance(int tapTolerance) {
        options.put(MapOptions.TAP_TOLERANCE, tapTolerance);
        return this;
    }

    /**
     * @return whether the map automatically handles browser resize to update itself.
     */
    public boolean isTrackResize() {
        return (boolean) options.get(MapOptions.TRACK_RESIZE);
    }

    /**
     * Sets whether the map should automatically handle browser resize
     * @param trackResize {@code true} if map handles browser resizing
     * @return this instance for chaining
     */
    public Map setTrackResize(boolean trackResize) {
        options.put(MapOptions.TRACK_RESIZE, trackResize);
        return this;
    }

    /**
     * With this options enabled, map tracks when you pan to another copy of the world and seamlessly jumps to the original one,
     * so that all overlays are still visible.
     * @return whether map handles panning to another copy of world seamlessly
     */
    public boolean isWorldCopyJump() {
        return (boolean) options.get(MapOptions.WORLD_COPY_JUMP);
    }

    /**
     * Sets whether map should handle panning to another copy of world seamlessly.
     * @param worldCopyJump {@code true} map handles seamless panning between worlds
     * @return this instance for chaining
     */
    public Map setWorldCopyJump(boolean worldCopyJump) {
        options.put(MapOptions.WORLD_COPY_JUMP, worldCopyJump);
        return this;
    }

    /**
     * @return whether popup windows should close on user click
     */
    public boolean isClosePopupOnClick() {
        return (boolean) options.get(MapOptions.CLOSE_POPUP_ON_CLICK);
    }

    /**
     * Sets whether popup window should close on click.
     * @param closePopupOnClick {@code true} popup window closes on user click.
     * @return this instance for chaining
     */
    public Map setClosePopupOnClick(boolean closePopupOnClick) {
        options.put(MapOptions.CLOSE_POPUP_ON_CLICK, closePopupOnClick);
        return this;
    }

    /**
     * If {@code false}, the map zooms beyond min/max zoom limit and then bounces back with pinch-zooming.
     * @return whether map respects zoom limits
     */
    public boolean isBounceAtZoomLimit() {
        return (boolean) options.get(MapOptions.BOUNCE_AT_ZOOM_LIMIT);
    }

    /**
     * Sets whether map respects zoom limits or not.
     * @param bounceAtZoomLimit if {@code true} map respects zoom limit.
     * @return this instance for chaining
     * @see #isBounceAtZoomLimit()
     */
    public Map setBounceAtZoomLimit(boolean bounceAtZoomLimit) {
        options.put(MapOptions.BOUNCE_AT_ZOOM_LIMIT, bounceAtZoomLimit);
        return this;
    }

    /**
     * @return makes the map focusable and allows users to navigate the map with keyboard arrows and +/- keys.
     */
    public boolean isKeyboard() {
        return (boolean) options.get(MapOptions.KEYBOARD);
    }

    /**
     * Sets whether map can be navigated by keyboard.
     * @param keyboard if {@code true} map is navigated by keyboard
     * @return this instance for chaining
     */
    public Map setKeyboard(boolean keyboard) {
        options.put(MapOptions.KEYBOARD, keyboard);
        return this;
    }

    /**
     * @return amount of pixels to pan when pressing an arrow key
     */
    public int getKeyboardPanOffset() {
        return (int) options.get(MapOptions.KEYBOARD_PAN_OFFSET);
    }

    /**
     * Sets amount of pixels to pan when pressing an arrow key
     * @param keyboardPanOffset amount of pixels to pan when pressing an arrow key
     * @return this instance for chaining
     */
    public Map setKeyboardPanOffset(int keyboardPanOffset) {
        options.put(MapOptions.KEYBOARD_PAN_OFFSET, keyboardPanOffset);
        return this;
    }

    /**
     * @return number of zoom level to change when pressing +/-
     */
    public int getKeyboardZoomOffset() {
        return (int) options.get(MapOptions.KEYBOARD_ZOOM_OFFSET);
    }

    /**
     * Sets number of zoom levels to change when pressing +/-.
     * @param keyboardZoomOffset number of zoom levels to change
     * @return this instance for chaining
     */
    public Map setKeyboardZoomOffset(int keyboardZoomOffset) {
        options.put(MapOptions.KEYBOARD_ZOOM_OFFSET, keyboardZoomOffset);
        return this;
    }

    /**
     * Returns if the map uses inertia effect.
     * This effect builds momentum when dragging and moving in same direction for some time.
     * @return whether map uses inertia effect
     */
    public boolean isInertia() {
        return (boolean) options.get(MapOptions.INERTIA);
    }

    /**
     * Sets whether map uses inertia effect.
     * @param inertia if {@code true} map should use inertia effect
     * @return this instance for chaining
     */
    public Map setInertia(boolean inertia) {
        options.put(MapOptions.INERTIA, inertia);
        return this;
    }

    /**
     * The rate with which inertia movement slows down in pixels/second.
     * @return rate with which inertia movement slows down
     */
    public int getInertiaDeceleration() {
        return (int) options.get(MapOptions.INERTIA_DECELARATION);
    }

    /**
     * Sets the rate with which inertia movement slows down in pixels/second.
     * @param inertiaDeceleration rate with which inertia movement slows down.
     * @return this instance for chaining
     */
    public Map setInertiaDeceleration(int inertiaDeceleration) {
        options.put(MapOptions.INERTIA_DECELARATION, inertiaDeceleration);
        return this;
    }

    /**
     * @return maximum speed of inertia acceleration in pixels/second
     */
    public int getInertiaMaxSpeed() {
        return (int) options.get(MapOptions.INERTIA_MAX_SPEED);
    }

    /**
     * Sets maximum speed of inertia acceleration in pixels/second
     * @param inertiaMaxSpeed new maximum speed
     * @return this instance for chaining
     */
    public Map setInertiaMaxSpeed(int inertiaMaxSpeed) {
        options.put(MapOptions.INERTIA_MAX_SPEED, inertiaMaxSpeed);
        return this;
    }

    /**
     * @return number of milliseconds that should pass between stopping the movement and releasing touch or mouse.
     */
    public Integer getInertiaThreshold() {
        return (Integer) options.get(MapOptions.INERTIA_THRESHOLD);
    }

    /**
     * Sets new inertia threshold.
     * @param inertiaThreshold number of milliseconds that should pass between stopping the movement and releasing touch or mouse
     * @return this instance for chaining
     */
    public Map setInertiaThreshold(Integer inertiaThreshold) {
        options.put(MapOptions.INERTIA_THRESHOLD, inertiaThreshold);
        return this;
    }

    /**
     * @return whether the zoom control is added to the map
     */
    public Boolean isZoomControl() {
        return (Boolean) options.get(MapOptions.ZOOM_CONTROL);
    }

    /**
     * Sets whether the zoom control is added to the map.
     * @param zoomControl if {@code true} zoom control is added
     * @return this instance for chaining
     */
    public Map setZoomControl(boolean zoomControl) {
        options.put(MapOptions.ZOOM_CONTROL, zoomControl);
        return this;
    }

    /**
     * @return whether the attribution control is added to the map
     */
    public boolean isAttributionControl() {
        return (boolean) options.get(MapOptions.ATTRIBUTION_CONTROL);
    }

    /**
     * Sets whether the attribution control is added to the map.
     * @param attributionControl if {@code true} attribution control is added
     * @return this instance for chaining
     */
    public Map setAttributionControl(boolean attributionControl) {
        options.put(MapOptions.ATTRIBUTION_CONTROL, attributionControl);
        return this;
    }

    /**
     * @return whether the fade animation is enabled
     */
    public Boolean getFadeAnimation() {
        return (Boolean) options.get(MapOptions.FADE_ANIMATION);
    }

    /**
     * Sets whether the fade animation should be enabled.
     * @param fadeAnimation if {@code true} fade animation is enabled
     * @return this instance for chaining
     */
    public Map setFadeAnimation(boolean fadeAnimation) {
        options.put(MapOptions.FADE_ANIMATION, fadeAnimation);
        return this;
    }

    /**
     * @return whether the zoom animation is enabled.
     */
    public Boolean getZoomAnimation() {
        return (Boolean) options.get(MapOptions.ZOOM_ANIMATION);
    }

    /**
     * Sets whether the zoom animation should be enabled.
     * @param zoomAnimation if {@code true} zoom animation is enabled
     * @return this instance for chaining
     */
    public Map setZoomAnimation(Boolean zoomAnimation) {
        options.put(MapOptions.ZOOM_ANIMATION, zoomAnimation);
        return this;
    }

    /**
     * The threshold to not use zoom animation. If zoom difference exceeds animation is not used.
     * @return threshold for using zoom animation
     */
    public int getZoomAnimationThreshold() {
        return (int) options.get(MapOptions.ZOOM_ANIMATION_THRESHOLD);
    }

    /**
     * Sets new threshold to not use zoom animation.
     * @param zoomAnimationThreshold new threshold for using zoom animation
     * @return this instance for chaining
     */
    public Map setZoomAnimationThreshold(int zoomAnimationThreshold) {
        options.put(MapOptions.ZOOM_ANIMATION_THRESHOLD, zoomAnimationThreshold);
        return this;
    }

    /**
     * If animation is turn off, marker aren't visible for length of animation.
     * @return whether marker animates its zoom with zoom animation.
     */
    public Boolean getMarkerZoomAnimation() {
        return (Boolean) options.get(MapOptions.MARKER_ZOOM_ANIMATION);
    }

    /**
     * Sets if markers should be animated with zoom animation.
     * @param markerZoomAnimation if {@code true} markers are animated with zoom animation
     * @return this instance for chaining
     */
    public Map setMarkerZoomAnimation(Boolean markerZoomAnimation) {
        options.put(MapOptions.MARKER_ZOOM_ANIMATION, markerZoomAnimation);
        return this;
    }
}
