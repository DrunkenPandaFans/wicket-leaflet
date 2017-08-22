package sk.drunkenpanda.leaflet.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Meta annotation for serializing classes in proper JSON format instead of
 * Leaflet function calls, e.g. L.tileLayer(url, opts).
 *
 * @author Jan Ferko
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@JsonSerialize
@JsonDeserialize
public @interface ProperJson {
}
