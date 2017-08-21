package sk.drunkenpanda.leaflet.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Common style for model classes used by Immutables generator.
 *
 * @author Jan Ferko
 */
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
        typeAbstract = "Abstract*",
        typeImmutable = "*"
)
@JsonSerialize
@interface ModelStyle {
}
