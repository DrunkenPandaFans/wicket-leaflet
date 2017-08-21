/*
 * Copyright 2016 Jan Ferko.
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

package sk.drunkenpanda.leaflet.events;

import sk.drunkenpanda.leaflet.components.map.MapEventType;

/**
 * Event that is triggered when error occurs.
 *
 * @author Jan Ferko
 */
public final class ErrorEvent implements Event {

    /**
     * The event type.
     */
    private final MapEventType type;

    /**
     * The error message.
     */
    private final String message;

    /**
     * The error code (if applicable).
     */
    private final Integer code;

    /**
     * Constructs new error event with given parameters.
     *
     * @param type event type, that triggered error.
     * @param message the error message
     * @param code the error code
     */
    public ErrorEvent(MapEventType type, String message, Integer code) {
        this.type = type;
        this.message = message;
        this.code = code;
    }

    @Override
    public MapEventType getType() {
        return this.type;
    }

    /**
     * Returns the error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the error code.
     *
     * @return the error code or {@code null} if error does not have error code.
     */
    public Integer getCode() {
        return this.code;
    }

}
