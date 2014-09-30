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

package sk.drunkenpanda.leaflet.components;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;
import sk.drunkenpanda.leaflet.resources.LeafletResourcesBehavior;

/**
 *
 * @author Jan Ferko
 */
public class Map extends GenericPanel<LatLng> {

    private int zoom;
    private int minZoom;
    private int maxZoom;
    private LatLngBounds maxBounds;

    /** Interaction options */
    private boolean dragging = true;
    private boolean touchZoom = true;
    private boolean scrollWheelZoom = true;
    private boolean doubleClickZoom = true;
    private boolean boxZoom = true;
    private boolean tap = true;
    private int tapTolerance = 15;
    private boolean trackResize = true;
    private boolean worldCopyJump = false;
    private boolean closePopupOnClick = true;
    private boolean bounceAtZoomLimit = true;

    /** Keyboard navigation options */
    private boolean keyboard = true;
    private int keyboardPanOffset = 80;
    private int keyboardZoomOffset = 1;

    /** Panning inertia options */
    private boolean inertia = true;
    private int inertiaDeceleration = 3000;
    private int inertiaMaxSpeed = 1500;
    private Integer inertiaThreshold;

    /** Control options */
    private boolean zoomControl;
    private boolean attributionControl = true;

    /** Animation options */
    private Boolean fadeAnimation;
    private Boolean zoomAnimation;
    private int zoomAnimationThreshold = 4;
    private Boolean markerZoomAnimation;

    public Map(String id) {
        super(id);
    }

    public Map(String id, IModel<LatLng> model) {
        super(id, model);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnLoadHeaderItem.forScript(getScript()));
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
    public int getZoom() {
        return zoom;
    }

    /**
     * Sets new initial map zoom
     * @param zoom initial map zoom
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * @return minimum zoom level of map.
     */
    public int getMinZoom() {
        return minZoom;
    }

    /**
     * Sets minimum zoom level of map. This zoom level overrides minimum zoom of any layer.
     * @param minZoom minimum zoom level
     */
    public void setMinZoom(int minZoom) {
        this.minZoom = minZoom;
    }

    /**
     * @return maximum zoom level of map
     */
    public int getMaxZoom() {
        return maxZoom;
    }

    /**
     * Sets maximum zoom level of map. This overrides maximum zoom of any layer.
     * @param maxZoom maximum zoom level
     */
    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }

    /**
     * When this options is set, map restricts user view to given bounds, bouncing the user back
     * when he tries to pan outside of view.
     * @return maximum geographical bounds
     */
    public LatLngBounds getMaxBounds() {
        return maxBounds;
    }

    /**
     * Sets new maximum geographical bounds.
     * @param maxBounds maximum geographical bounds.
     */
    public void setMaxBounds(LatLngBounds maxBounds) {
        this.maxBounds = maxBounds;
    }

    /**
     * @return whether map can be draggable with touch/mouse or not
     */
    public boolean isDragging() {
        return dragging;
    }

    /**
     * Sets whether the map can be draggable or not
     * @param dragging if {@code true} map is draggable
     */
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    /**
     * @return whether map can be zoomed by touch-dragging with two fingers.
     */
    public boolean isTouchZoom() {
        return touchZoom;
    }

    /**
     * Sets whether the map can be zoomed by touch-dragging with two fingers.
     * @param touchZoom if {@code true} map can be zoomed by touch
     */
    public void setTouchZoom(boolean touchZoom) {
        this.touchZoom = touchZoom;
    }

    /**
     * @return whether the map can be zoomed by using mouse wheel or not
     */
    public boolean isScrollWheelZoom() {
        return scrollWheelZoom;
    }

    /**
     * Sets whether the map can be zoomed by using mouse wheel or not.
     * @param scrollWheelZoom {@code true} if map can be zoomed by mouse wheel
     */
    public void setScrollWheelZoom(boolean scrollWheelZoom) {
        this.scrollWheelZoom = scrollWheelZoom;
    }

    /**
     * @return whether the map can be zoomed by double-clicking on it and zoom out by double-clicking while pressing shift button.
     */
    public boolean isDoubleClickZoom() {
        return doubleClickZoom;
    }

    /**
     * Sets whether the map can be zoomed by double-clicking on it.
     * @param doubleClickZoom  {@code true} if map can be zoomed by double-clicking
     */
    public void setDoubleClickZoom(boolean doubleClickZoom) {
        this.doubleClickZoom = doubleClickZoom;
    }

    /**
     * @return whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     */
    public boolean isBoxZoom() {
        return boxZoom;
    }

    /**
     * Sets whether the map can be zoomed to rectangular area specified by dragging the mouse while pressing shift key.
     * @param boxZoom {@code true} if map can be zoomed by boxing
     */
    public void setBoxZoom(boolean boxZoom) {
        this.boxZoom = boxZoom;
    }

    /**
     * @return whether the map enables mobile hacks for supporting instant taps and touch holds (fired as contextmenu)
     */
    public boolean isTap() {
        return tap;
    }

    /**
     * Sets whether the map enables mobile hacks for supporting instant taps and touch holds.
     * @param tap {@code true} if map enables taps and touch holds
     */
    public void setTap(boolean tap) {
        this.tap = tap;
    }

    /**
     * @return the max number of pixels a user can shift his finger during touch for it to be considered valid tap.
     */
    public int getTapTolerance() {
        return tapTolerance;
    }

    /**
     * Sets max number of pixels a user can shift his finger during touch for it to be considered valid tap
     * @param tapTolerance max number of pixels a user can shift his finger during touch.
     */
    public void setTapTolerance(int tapTolerance) {
        this.tapTolerance = tapTolerance;
    }

    /**
     * @return whether the map automatically handles browser resize to update itself.
     */
    public boolean isTrackResize() {
        return trackResize;
    }

    /**
     * Sets whether the map should automatically handle browser resize
     * @param trackResize {@code true} if map handles browser resizing
     */
    public void setTrackResize(boolean trackResize) {
        this.trackResize = trackResize;
    }

    /**
     * With this options enabled, map tracks when you pan to another copy of the world and seamlessly jumps to the original one,
     * so that all overlays are still visible.
     * @return whether map handles panning to another copy of world seamlessly
     */
    public boolean isWorldCopyJump() {
        return worldCopyJump;
    }

    /**
     * Sets whether map should handle panning to another copy of world seamlessly.
     * @param worldCopyJump {@code true} map handles seamless panning between worlds
     */
    public void setWorldCopyJump(boolean worldCopyJump) {
        this.worldCopyJump = worldCopyJump;
    }

    /**
     * @return whether popup windows should close on user click
     */
    public boolean isClosePopupOnClick() {
        return closePopupOnClick;
    }

    /**
     * Sets whether popup window should close on click.
     * @param closePopupOnClick {@code true} popup window closes on user click.
     */
    public void setClosePopupOnClick(boolean closePopupOnClick) {
        this.closePopupOnClick = closePopupOnClick;
    }

    /**
     * If {@code false}, the map zooms beyond min/max zoom limit and then bounces back with pinch-zooming.
     * @return whether map respects zoom limits
     */
    public boolean isBounceAtZoomLimit() {
        return bounceAtZoomLimit;
    }

    /**
     * Sets whether map respects zoom limits or not.
     * @param bounceAtZoomLimit if {@code true} map respects zoom limit.
     * @see #isBounceAtZoomLimit()
     */
    public void setBounceAtZoomLimit(boolean bounceAtZoomLimit) {
        this.bounceAtZoomLimit = bounceAtZoomLimit;
    }

    /**
     * @return makes the map focusable and allows users to navigate the map with keyboard arrows and +/- keys.
     */
    public boolean isKeyboard() {
        return keyboard;
    }

    /**
     * Sets whether map can be navigated by keyboard.
     * @param keyboard if {@code true} map is navigated by keyboard
     */
    public void setKeyboard(boolean keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * @return amount of pixels to pan when pressing an arrow key
     */
    public int getKeyboardPanOffset() {
        return keyboardPanOffset;
    }

    /**
     * Sets amount of pixels to pan when pressing an arrow key
     * @param keyboardPanOffset amount of pixels to pan when pressing an arrow key
     */
    public void setKeyboardPanOffset(int keyboardPanOffset) {
        this.keyboardPanOffset = keyboardPanOffset;
    }

    /**
     * @return number of zoom level to change when pressing +/-
     */
    public int getKeyboardZoomOffset() {
        return keyboardZoomOffset;
    }

    /**
     * Sets number of zoom levels to change when pressing +/-.
     * @param keyboardZoomOffset number of zoom levels to change
     */
    public void setKeyboardZoomOffset(int keyboardZoomOffset) {
        this.keyboardZoomOffset = keyboardZoomOffset;
    }

    /**
     * Returns if the map uses inertia effect.
     * This effect builds momentum when dragging and moving in same direction for some time.
     * @return whether map uses inertia effect
     */
    public boolean isInertia() {
        return inertia;
    }

    /**
     * Sets whether map uses inertia effect.
     * @param inertia if {@code true} map should use inertia effect
     */
    public void setInertia(boolean inertia) {
        this.inertia = inertia;
    }

    /**
     * The rate with which inertia movement slows down in pixels/second.
     * @return rate with which inertia movement slows down
     */
    public int getInertiaDeceleration() {
        return inertiaDeceleration;
    }

    /**
     * Sets the rate with which inertia movement slows down in pixels/second.
     * @param inertiaDeceleration rate with which inertia movement slows down.
     */
    public void setInertiaDeceleration(int inertiaDeceleration) {
        this.inertiaDeceleration = inertiaDeceleration;
    }

    /**
     * @return maximum speed of inertia acceleration in pixels/second
     */
    public int getInertiaMaxSpeed() {
        return inertiaMaxSpeed;
    }

    /**
     * Sets maximum speed of inertia acceleration in pixels/second
     * @param inertiaMaxSpeed new maximum speed
     */
    public void setInertiaMaxSpeed(int inertiaMaxSpeed) {
        this.inertiaMaxSpeed = inertiaMaxSpeed;
    }

    /**
     * @return number of milliseconds that should pass between stopping the movement and releasing touch or mouse.
     */
    public Integer getInertiaThreshold() {
        return inertiaThreshold;
    }

    /**
     * Sets new inertia threshold.
     * @param inertiaThreshold number of milliseconds that should pass between stopping the movement and releasing touch or mouse
     */
    public void setInertiaThreshold(Integer inertiaThreshold) {
        this.inertiaThreshold = inertiaThreshold;
    }

    /**
     * @return whether the zoom control is added to the map
     */
    public boolean isZoomControl() {
        return zoomControl;
    }

    /**
     * Sets whether the zoom control is added to the map.
     * @param zoomControl if {@code true} zoom control is added
     */
    public void setZoomControl(boolean zoomControl) {
        this.zoomControl = zoomControl;
    }

    /**
     * @return whether the attribution control is added to the map
     */
    public boolean isAttributionControl() {
        return attributionControl;
    }

    /**
     * Sets whether the attribution control is added to the map.
     * @param attributionControl if {@code true} attribution control is added
     */
    public void setAttributionControl(boolean attributionControl) {
        this.attributionControl = attributionControl;
    }

    /**
     * @return whether the fade animation is enabled
     */
    public Boolean getFadeAnimation() {
        return fadeAnimation;
    }

    /**
     * Sets whether the fade animation should be enabled.
     * @param fadeAnimation if {@code true} fade animation is enabled
     */
    public void setFadeAnimation(Boolean fadeAnimation) {
        this.fadeAnimation = fadeAnimation;
    }

    /**
     * @return whether the zoom animation is enabled.
     */
    public Boolean getZoomAnimation() {
        return zoomAnimation;
    }

    /**
     * Sets whether the zoom animation should be enabled.
     * @param zoomAnimation if {@code true} zoom animation is enabled
     */
    public void setZoomAnimation(Boolean zoomAnimation) {
        this.zoomAnimation = zoomAnimation;
    }

    /**
     * The threshold to not use zoom animation. If zoom difference exceeds animation is not used.
     * @return threshold for using zoom animation
     */
    public int getZoomAnimationThreshold() {
        return zoomAnimationThreshold;
    }

    /**
     * Sets new threshold to not use zoom animation.
     * @param zoomAnimationThreshold new threshold for using zoom animation
     */
    public void setZoomAnimationThreshold(int zoomAnimationThreshold) {
        this.zoomAnimationThreshold = zoomAnimationThreshold;
    }

    /**
     * If animation is turn off, marker aren't visible for length of animation.
     * @return whether marker animates its zoom with zoom animation.
     */
    public Boolean getMarkerZoomAnimation() {
        return markerZoomAnimation;
    }

    /**
     * Sets if markers should be animated with zoom animation.
     * @param markerZoomAnimation if {@code true} markers are animated with zoom animation
     */
    public void setMarkerZoomAnimation(Boolean markerZoomAnimation) {
        this.markerZoomAnimation = markerZoomAnimation;
    }
}
