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

import sk.drunkenpanda.leaflet.resources.LeafletJavascriptResourceReference;
import sk.drunkenpanda.leaflet.resources.LeafletStylesheetResourceReference;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * Default implementation of Wicket Leaflet settings.
 * 
 * @author Jan Ferko
 * @see LeafletSettings
 */
public class DefaultLeafletSettings implements LeafletSettings {        
    
    /** Default version of leaflet, used when custom version isn't provided. */
    public static final String DEFAULT_VERSION = "0.7.3";
    
    /** Parametrized CDN url for javascript resource. */
    public static final String JS_CDN_PATTERN = "http://cdn.leafletjs.com/leaflet-%1$s/leaflet.js";
    
    /** Parametrized CDN url for stylesheet resource. */
    public static final String CSS_CDN_PATTERN = "http://cdn.leafletjs.com/leaflet-%1$s/leaflet.css";        
    
    private final String version;
    private final ResourceReference javascriptReference;
    private final ResourceReference cssReference;
    private final boolean useCdn;
    private final boolean autoAppend;
    
    /**
     * Constructor, that creates default Leaflet settings.
     */
    public DefaultLeafletSettings() {
        this(DEFAULT_VERSION, null, null, false, false);
    }

    /**
     * Constructor, that creates customized Leaflet settings.
     * 
     * @param version the used version of Leaflet library
     * @param javascriptReference the core javascript resource reference
     * @param cssReference the core CSS resource reference
     * @param useCdn indicator if CDN resources are used
     * @param autoAppend indicator if resources should be added to every component automatically
     */
    public DefaultLeafletSettings(String version, ResourceReference javascriptReference, ResourceReference cssReference, 
            boolean useCdn, boolean autoAppend) {
        this.version = version;
        this.javascriptReference = javascriptReference;
        this.cssReference = cssReference;
        this.useCdn = useCdn;
        this.autoAppend = autoAppend;
    }        

    @Override
    public ResourceReference getJavascriptReference() {
        ResourceReference jsReference;
        
        if (useCdn) {
            String jsUrl = String.format(JS_CDN_PATTERN, getVersion());
            jsReference = new UrlResourceReference(Url.parse(jsUrl));
        } else {
            jsReference = javascriptReference;
        }
        
        return jsReference != null ? jsReference : Holder.LEAFLET_JAVASCRIPT;
    }

    @Override
    public ResourceReference getCssReference() {
        ResourceReference stylesheetReference;
        
        if (useCdn) {
            String cssUrl = String.format(CSS_CDN_PATTERN, getVersion());
            stylesheetReference = new UrlResourceReference(Url.parse(cssUrl));
        } else {
            stylesheetReference = cssReference;
        }
        
        return stylesheetReference != null ? stylesheetReference : Holder.LEAFLET_STYLESHEET;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public boolean useWebJars() {
        return !useCdn && (javascriptReference == null || cssReference == null);
    }

    @Override
    public boolean useCDN() {
        return this.useCdn;
    }

    @Override
    public boolean autoAppendResources() {
        return this.autoAppend;
    }
    
    /**
     * Holder for on demand initialization of WebJar resources.
     */
    private static class Holder {
        static final ResourceReference LEAFLET_JAVASCRIPT = LeafletJavascriptResourceReference.instance();
        static final ResourceReference LEAFLET_STYLESHEET = LeafletStylesheetResourceReference.instance();
    }

    /**
     * Default settings builder.
     */
    public static class Builder {
        private String version;
        private boolean useCdn;
        private boolean autoAppend;
        
        private ResourceReference jsReference;
        private ResourceReference cssReference;
        
        public Builder() {
            useCdn = false;
            autoAppend = false;
            version = DEFAULT_VERSION;
        }
        
        /**
         * Sets custom core javascript resource reference.
         * @param jsReference core javascript resource reference
         * @return this instance for chaining
         */
        public Builder setJavascriptReference(ResourceReference jsReference) {
            this.jsReference = jsReference;
            return this;
        }
        
        /**
         * Sets custom stylesheet resource reference.
         * @param cssReference stylesheet resource reference
         * @return this instance for chaining
         */
        public Builder setCssReference(ResourceReference cssReference) {
            this.cssReference = cssReference;
            return this;
        }
        
        /**
         * Sets indicator for CDN resources usage
         * @param useCdn {@code true} if CDN resources should be used
         * @return this instance for chaining
         */
        public Builder setUseCdn(boolean useCdn) {
            this.useCdn = useCdn;
            return this;
        }
        
        /**
         * Sets indicator for automatic addition of resources to every component.         
         * @param autoAppendResources {@code true} if resources should be added automatically
         * @return this instance for chaining
         */
        public Builder setAutoAppendResources(boolean autoAppendResources) {
            this.autoAppend = autoAppendResources;
            return this;
        }
        
        /**
         * Sets used version of Leaflet.
         * Version is used by CDN resources to generate urls.
         * @param version version of Leaflets
         * @return this instance for chaining
         */
        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }
        
        /**
         * Builds new instance of default leaflet settings based on builder state.                  
         * @return new instance of default leaflet settings
         */
        public DefaultLeafletSettings build() {
            return new DefaultLeafletSettings(version, jsReference, cssReference, useCdn, autoAppend);
        }
    }
}
