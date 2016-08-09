package sk.drunkenpanda.leaflet.json;

import static org.junit.Assert.*;
import org.junit.Test;

import sk.drunkenpanda.leaflet.json.model.*;

/**
 * @author Jan Ferko
 */
public final class JsonRendererTest {

    private final JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();

    @Test
    public void testProcessDragEvent() {
        final JsonDragEndEvent event = new JsonDragEndEvent();
        event.setDistance(10.0);
        event.setType("dragevent");

        JsonDragEndEvent actual = renderer.fromJson(renderer.toJson(event), JsonDragEndEvent.class);

        assertEquals(event.getDistance(), actual.getDistance(), 0.01);
        assertEquals(event.getType(), actual.getType());
    }

    @Test
    public void testProcessJsonErrorEvent() {
        final JsonErrorEvent event = new JsonErrorEvent();
        event.setType("error");
        event.setCode(100);
        event.setMessage("Serialization test of error event failed.");

        JsonErrorEvent actual = renderer.fromJson(renderer.toJson(event), JsonErrorEvent.class);

        assertEquals(event.getType(), actual.getType());
        assertEquals(event.getMessage(), actual.getMessage());
        assertEquals(event.getCode(), actual.getCode());
    }

    @Test
    public void testProcessJsonEvent() {
        final JsonEvent event = new JsonEvent();
        event.setType("event");

        JsonEvent actual = renderer.fromJson(renderer.toJson(event), JsonEvent.class);

        assertEquals(event.getType(), actual.getType());
    }

    @Test
    public void testProcessJsonLatLng() {
        final JsonLatLng latLng = new JsonLatLng();
        latLng.setLatitude(10.123);
        latLng.setLongitude(-10.124);

        JsonLatLng actual = renderer.fromJson(renderer.toJson(latLng), JsonLatLng.class);

        assertEquals(latLng.getLatitude(), actual.getLatitude(), 0.01);
        assertEquals(latLng.getLongitude(), actual.getLongitude(), 0.01);
    }

    @Test
    public void testProcessJsonLatLngBounds() {
        final JsonLatLngBounds latLngBounds = new JsonLatLngBounds();
        final JsonLatLng northEast = new JsonLatLng();
        northEast.setLatitude(43.13);
        northEast.setLongitude(56.23);
        latLngBounds.setNorthEast(northEast);

        final JsonLatLng southWest = new JsonLatLng();
        southWest.setLatitude(-70.23);
        southWest.setLongitude(-12.34);
        latLngBounds.setSouthWest(southWest);

        JsonLatLngBounds actual = renderer.fromJson(renderer.toJson(latLngBounds), JsonLatLngBounds.class);

        assertEquals(northEast.getLatitude(), actual.getNorthEast().getLatitude(), 0.01);
        assertEquals(northEast.getLongitude(), actual.getNorthEast().getLongitude(), 0.01);
        assertEquals(southWest.getLatitude(), actual.getSouthWest().getLatitude(), 0.01);
        assertEquals(southWest.getLongitude(), actual.getSouthWest().getLongitude(), 0.01);
    }

    @Test
    public void testProcessLocationEvent() {
        final JsonLocationEvent event = new JsonLocationEvent();
        event.setType("location");
        event.setAccuracy(0.98);
        event.setAltitude(1.00);
        event.setAltitudeAccuracy(1.00);
        event.setHeading(34.0);
        event.setSpeed(100.0);
        event.setTimestamp(System.currentTimeMillis());

        final JsonLatLngBounds latLngBounds = new JsonLatLngBounds();
        final JsonLatLng northEast = new JsonLatLng();
        northEast.setLatitude(43.13);
        northEast.setLongitude(56.23);
        latLngBounds.setNorthEast(northEast);
        final JsonLatLng southWest = new JsonLatLng();
        southWest.setLatitude(-70.23);
        southWest.setLongitude(-12.34);
        latLngBounds.setSouthWest(southWest);
        event.setLatLngBounds(latLngBounds);

        final JsonLatLng latLng = new JsonLatLng();
        latLng.setLatitude(10.123);
        latLng.setLongitude(-10.124);

        event.setLatLng(latLng);

        JsonLocationEvent actual = renderer.fromJson(renderer.toJson(event), JsonLocationEvent.class);

        assertEquals(event.getType(), actual.getType());
        assertEquals(event.getAccuracy(), actual.getAccuracy(), 0.01);
        assertEquals(event.getAltitude(), actual.getAltitude(), 0.01);
        assertEquals(event.getAltitudeAccuracy(), actual.getAltitudeAccuracy(), 0.01);
        assertEquals(event.getHeading(), actual.getHeading(), 0.01);
        assertEquals(event.getSpeed(), actual.getSpeed(), 0.01);
        assertEquals(event.getTimestamp(), actual.getTimestamp());

        assertEquals(latLng.getLatitude(), actual.getLatLng().getLatitude(), 0.01);
        assertEquals(latLng.getLongitude(), actual.getLatLng().getLongitude(), 0.01);

        assertEquals(latLngBounds.getNorthEast().getLatitude(), actual.getLatLngBounds().getNorthEast().getLatitude(), 0.01);
        assertEquals(latLngBounds.getNorthEast().getLongitude(), actual.getLatLngBounds().getNorthEast().getLongitude(), 0.01);
        assertEquals(latLngBounds.getSouthWest().getLatitude(), actual.getLatLngBounds().getSouthWest().getLatitude(), 0.01);
        assertEquals(latLngBounds.getSouthWest().getLongitude(), actual.getLatLngBounds().getSouthWest().getLongitude(), 0.01);
    }

    @Test
    public void testProcessMouseEvent() {
        final JsonMouseEvent event = new JsonMouseEvent();
        event.setType("mouseevent");

        final JsonLatLng latLng = new JsonLatLng();
        latLng.setLatitude(10.123);
        latLng.setLongitude(-10.124);
        event.setLatLng(latLng);

        final JsonPoint containerPoint = new JsonPoint();
        containerPoint.setX(10.0);
        containerPoint.setY(12.0);
        event.setContainerPoint(containerPoint);

        final JsonPoint layerPoint = new JsonPoint();
        layerPoint.setX(20.0);
        layerPoint.setY(-10.0);
        event.setLayerPoint(layerPoint);

        JsonMouseEvent actual = renderer.fromJson(renderer.toJson(event), JsonMouseEvent.class);

        assertEquals(event.getType(), actual.getType());
        assertEquals(latLng.getLatitude(), actual.getLatLng().getLatitude(), 0.01);
        assertEquals(latLng.getLongitude(), actual.getLatLng().getLongitude(), 0.01);
        assertEquals(containerPoint.getX(), actual.getContainerPoint().getX(), 0.01);
        assertEquals(containerPoint.getY(), actual.getContainerPoint().getY(), 0.01);
        assertEquals(layerPoint.getX(), actual.getLayerPoint().getX(), 0.01);
        assertEquals(layerPoint.getY(), actual.getLayerPoint().getY(), 0.01);
    }

    @Test
    public void testProcessPoint() {
        JsonPoint point = new JsonPoint();
        point.setX(10.123);
        point.setY(45.12);

        JsonPoint actual = renderer.fromJson(renderer.toJson(point), JsonPoint.class);

        assertEquals(point.getX(), actual.getX(), 0.01);
        assertEquals(point.getY(), actual.getY(), 0.01);
    }

    @Test
    public void testProcessResizeEvent() {
        final JsonResizeEvent event = new JsonResizeEvent();
        event.setType("resize");

        JsonPoint newSize = new JsonPoint();
        newSize.setX(10.123);
        newSize.setY(45.12);
        event.setNewSize(newSize);

        JsonPoint oldSize = new JsonPoint();
        oldSize.setX(12.43);
        oldSize.setY(-55.04);
        event.setOldSize(oldSize);

        JsonResizeEvent actual = renderer.fromJson(renderer.toJson(event), JsonResizeEvent.class);

        assertEquals(event.getType(), actual.getType());
        assertEquals(newSize.getX(), actual.getNewSize().getX(), 0.01);
        assertEquals(newSize.getY(), actual.getNewSize().getY(), 0.01);
        assertEquals(oldSize.getX(), actual.getOldSize().getX(), 0.01);
        assertEquals(oldSize.getY(), actual.getOldSize().getY(), 0.01);
    }
}
