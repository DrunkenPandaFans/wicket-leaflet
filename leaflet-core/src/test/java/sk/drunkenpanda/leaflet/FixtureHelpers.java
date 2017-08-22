package sk.drunkenpanda.leaflet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.common.io.Resources;

/**
 * A set of helper methods for working with fixture files.
 *
 * @author Jan Ferko
 */
public final class FixtureHelpers {
    private FixtureHelpers() {}

    /**
     * Reads fixture file from classpath, e.g {@code src/test/resources}
     * and returns its content as UTF-8 string.
     *
     * @param filename the filename of the fixture file
     * @return content of file as UTF-8 string
     * @throws IllegalArgumentException if an I/O error occurs.
     */
    public static String fixture(String filename) {
        return fixture(filename, StandardCharsets.UTF_8);
    }

    /**
     * Reads fixture file from classpath, e.g {@code src/test/resources}
     * and returns its content as string.
     *
     * @param filename the filename of the fixture file
     * @param charset the character set of {@code filename}
     * @return content of file as string
     * @throws IllegalArgumentException if an I/O error occurs.
     */
    private static String fixture(String filename, Charset charset) {
        try {
            return Resources.toString(Resources.getResource(filename), charset);
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
