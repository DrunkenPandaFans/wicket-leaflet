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

package sk.drunkenpanda.leaflet;

import org.apache.wicket.request.resource.ResourceReference;

/**
 * Settings interface for Leaflet.js configuration.
 * 
 * @author Jan Ferko 
 */
public interface LeafletSettings {
    
    /**     
     * @return the base leaflet.js javascript resource reference
     */
    ResourceReference getJavascriptReference();
    
    /**     
     * @return the base leaflet.js css resource reference
     */
    ResourceReference getCssReference();
    
    /**     
     * @return the version of Leaflet.js. CDN resources use this to construct resource urls.
     */
    String getVersion();        
    
    /**
     * Indicator if Wicket Leaflet uses web jars library.
     * If you don't want to use WebJars, set Javascript/CSS references or use CDN.
     * @return {@code true} if library uses web jars library.
     */
    boolean useWebJars();
    
    /**     
     * @return {@code true} if library uses CDN resources.
     */
    boolean useCDN();
    
    /**     
     * @return {@code true} if Leaflet should be added to every component
     */
    boolean autoAppendResources();

}
