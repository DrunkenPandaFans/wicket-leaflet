package sk.drunkenpanda.leaflet.components.map;

import sk.drunkenpanda.leaflet.components.OptionKey;

/**
 * @author Jan Ferko
 */
enum MapOptions implements OptionKey {

    ZOOM("zoom"),

    MAX_ZOOM("maxZoom"),

    MIN_ZOOM("minZoom"),

    MAX_BOUND("maxBounds"),

    /** Interaction options */

    DRAGGING("dragging"),

    TOUCH_ZOOM("touchZoom"),

    SCROLL_WHEEL_ZOOM("scrollWheelZoom"),

    DOUBLE_CLICK_ZOOM("doubleClickZoom"),

    BOX_ZOOM("boxZoom"),

    TAP("tap"),

    TAP_TOLERANCE("tapTolerance"),

    TRACK_RESIZE("trackResize"),

    WORLD_COPY_JUMP("worldCopyJump"),

    CLOSE_POPUP_ON_CLICK("closePopupOnClick"),

    BOUNCE_AT_ZOOM_LIMIT("bounceAtZoomLimit"),

    /** Keyboard navigation options */

    KEYBOARD("keyboard"),

    KEYBOARD_PAN_OFFSET("keyboardPanOffset"),

    KEYBOARD_ZOOM_OFFSET("keyboardZoomOffset"),

    /** Panning inertia options */

    INERTIA("inertia"),

    INERTIA_DECELARATION("inertiaDeceleration"),

    INERTIA_MAX_SPEED("inertiaMaxSpeed"),

    INERTIA_THRESHOLD("inertiaThreshold"),

    /** Control options */

    ZOOM_CONTROL("zoomControl"),

    ATTRIBUTION_CONTROL("attributionControl"),

    /** Animation options */

    FADE_ANIMATION("fadeAnimation"),

    ZOOM_ANIMATION("zoomAnimation"),

    ZOOM_ANIMATION_THRESHOLD("zoomAnimationThreshold"),

    MARKER_ZOOM_ANIMATION("markerZoomAnimation");

    private final String name;

    private MapOptions(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static MapOptions find(String name) {
        if (name == null) {
            return null;
        }

        MapOptions found = null;
        for (MapOptions option : values()) {
            if (option.getName().equals(name)) {
                found = option;
                break;
            }
        }
        return found;
    }
}
