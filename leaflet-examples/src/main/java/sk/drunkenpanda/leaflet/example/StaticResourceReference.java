package sk.drunkenpanda.leaflet.example;

import org.apache.wicket.request.resource.PackageResourceReference;

public class StaticResourceReference extends PackageResourceReference {
    public static final StaticResourceReference LOGO = new StaticResourceReference("static/logo.png");
    public static final StaticResourceReference STYLE = new StaticResourceReference("static/style.css");

    private StaticResourceReference(String name) {
        super(StaticResourceReference.class, name);
    }
}
