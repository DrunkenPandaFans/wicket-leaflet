package sk.drunkenpanda.leaflet.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Common style for generated event classes.
 * @author Jan Ferko
 */
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
        typeImmutable = "*",
        typeAbstract = "Abstract*"
)
@JsonSerialize
@JsonDeserialize
@interface EventStyle {
}
