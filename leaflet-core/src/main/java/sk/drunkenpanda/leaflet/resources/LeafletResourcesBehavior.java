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

package sk.drunkenpanda.leaflet.resources;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;
import sk.drunkenpanda.leaflet.Leaflet;
import sk.drunkenpanda.leaflet.LeafletSettings;

/**
 * Behavior adds all resources to component.
 * You don't need to add this to components, if you set {@link LeafletSettings#autoAppendResources() }
 * to {@code true}.
 * 
 * @author Jan Ferko 
 */
public class LeafletResourcesBehavior extends Behavior {
    
    /** Singleton instance of behavior */
    private static final LeafletResourcesBehavior INSTANCE = new LeafletResourcesBehavior();
        
    private LeafletResourcesBehavior() {       
    }
    
    /**     
     * @return singleton instance of behavior
     */
    public static LeafletResourcesBehavior instance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc }     
     */
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        Args.notNull(component, "component");
        Args.notNull(response, "response");
        
        super.renderHead(component, response);        
        
        LeafletSettings settings = Leaflet.getSettings(component.getApplication());
        renderHead(settings, response);
    }
    
    /**
     * Renders resources needed by Leaflet library, including core javascript and css stylesheet.
     * It provides access to settings for its subclasses by overriding this method.
     * 
     * @param settings settings installed in application
     * @param response current {@link org.apache.wicket.markup.head.IHeaderResponse}
     * @throws IllegalArgumentException if any parameter is {@code null}.
     */
    public void renderHead(LeafletSettings settings, IHeaderResponse response) {
        Args.notNull(settings, "settings");
        Args.notNull(response, "response");
        
        response.render(JavaScriptHeaderItem.forReference(settings.getJavascriptReference()));
        response.render(CssHeaderItem.forReference(settings.getCssReference()));
    }
        
}
