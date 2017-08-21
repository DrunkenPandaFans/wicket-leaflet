package sk.drunkenpanda.leaflet.example.config;

public final class DefaultMapboxConfiguration implements MapboxConfiguration {
    private static final String URL_TEMPLATE = "https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}";

    private final String accessToken;

    private final String mapId;

    public DefaultMapboxConfiguration(String accessToken, String mapId) {
        if (accessToken == null) {
            throw new IllegalArgumentException("AccessToken must not be null.");
        }
        if (mapId == null) {
            throw new IllegalArgumentException("MapId must not be null.");
        }
        this.accessToken = accessToken;
        this.mapId = mapId;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getMapId() {
        return mapId;
    }

    @Override
    public String getUrlTemplate() {
        return URL_TEMPLATE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DefaultMapboxConfiguration that = (DefaultMapboxConfiguration) o;

        if (!accessToken.equals(that.accessToken)) return false;
        if (!getUrlTemplate().equals(that.getUrlTemplate())) return false;
        return mapId.equals(that.mapId);
    }

    @Override
    public int hashCode() {
        int result = accessToken.hashCode();
        result = 31 * result + mapId.hashCode();
        result = 42 * result + getUrlTemplate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MapboxConfiguration{" + "accessToken='" + accessToken + '\'' +
                ", mapId='" + mapId + '\'' +
                ", urlTemplate='" + getUrlTemplate() + '\'' +
                '}';
    }
}
