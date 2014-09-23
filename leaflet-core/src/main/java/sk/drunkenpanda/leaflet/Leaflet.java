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

import de.agilecoders.wicket.webjars.WebJarAssetLocator;
import de.agilecoders.wicket.webjars.WicketWebjars;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;

/**
 * Bootstrapping class of Wicket Leaflet.
 * It properly adds Leaflet resources to application based on {@link LeafletSettings}. 
 * If settings aren't provided default settings are used.
 * Wicket Leaflet uses WebJars for managing Leaflet.js resources.
 * If you don't want to use WebJars, you can use local or CDN resources by setting
 * all {@link org.apache.wicket.request.resource.ResourceReference}s int settings or by setting {@link LeafletSettings#useCDN() }
 * to {@code true}.
 * 
 * Library also appends resources automatically to all pages if {@link LeafletSettings#autoAppendResources() }
 * is {@code true}, otherwise client has to add resources to every component.
 * 
 * @author Jan Ferko 
 */
public final class Leaflet {
    
    /** Meta key, that is used to identify stored settings in application. */
    static final MetaDataKey<LeafletSettings> LEAFLET_SETTINGS_KEY = 
            new MetaDataKey<LeafletSettings>() {};
    
    private Leaflet() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Installs Leaflet with default configuration to given application.
     * @param application application, which Leaflet should be bounded to.
     * @throws IllegalArgumentException if application is {@code null}.
     * @see #install(org.apache.wicket.Application, sk.drunkenpanda.leaflet.LeafletSettings) 
     */
    public static void install(Application application) {        
        install(application, null);
    }
    
    /**
     * Install Leaflet with given settings to application.
     * If application already has Leaflet installed, it ignores new settings.
     * 
     * @param application application, which Leaflet should be bounded to.
     * @param settings custom settings, which are used to initialized library
     * @throws IllegalArgumentException if application is {@code null}.
     */
    public static void install(Application application, LeafletSettings settings) {
        // install Leaflet.js with given configuration
        Args.notNull(application, "application");
        
        if (application.getMetaData(LEAFLET_SETTINGS_KEY) == null) {
            LeafletSettings settingsOrDefault = settings != null ? settings : new DefaultLeafletSettings();
            application.setMetaData(LEAFLET_SETTINGS_KEY, settingsOrDefault);
            
            if (settingsOrDefault.autoAppendResources()) {
                application.getComponentInstantiationListeners().add(new LeafletResourceAppender());
            }
            
            if (settingsOrDefault.useWebJars()) {
                WicketWebjars.install(application);
            }
        }
    }
    
    /**
     * Returns Leaflet settings of given application.     
     * 
     * @param application application, which setting are retrieved
     * @return leaflet settings of application
     * @throws IllegalArgumentException if application is {@code null}
     * @throws IllegalStateException if Leaflet is not installed in application
     */
    public static LeafletSettings getSettings(Application application) {
        // get settings by MetaKey
        Args.notNull(application, "application");
        LeafletSettings settings = application.getMetaData(LEAFLET_SETTINGS_KEY);
        
        if (settings == null) {
            throw new IllegalStateException("Leaflets aren't installed in application [" + application.getName() + "].");
        }
        
        return settings;
    }
    
    /**
     * Returns settings of application, that belongs to current thread.
     * 
     * @return settings of application in current thread
     * @throws IllegalStateException if there isn't any application in current thread
     */
    public static LeafletSettings getSettings() {
        // get settings for application in current thread
        if (Application.exists()) {
            Application app = Application.get();
            return getSettings(app);
        } 
        throw new IllegalStateException("Application was not found in current thread");
    }        

}
