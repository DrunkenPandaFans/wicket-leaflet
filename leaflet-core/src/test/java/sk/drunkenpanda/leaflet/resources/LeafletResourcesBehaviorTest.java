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
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.junit.Test;
import org.mockito.Mockito;
import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.LeafletSettings;

/**
 *
 * @author Jan Ferko 
 */
public class LeafletResourcesBehaviorTest extends AbstractLeafletTest {            
    
    @Test(expected = IllegalArgumentException.class)
    public void testRenderResourcesToNullComponent() {        
        IHeaderResponse response = Mockito.mock(IHeaderResponse.class);
        LeafletResourcesBehavior.instance().renderHead((Component) null, response);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRenderResourcesToComponentWithoutHeaderResponse() {        
        LeafletResourcesBehavior.instance().renderHead(new Label("testLabel"), null);
    }
    
    @Test
    public void testRenderResourcesToComponent() {
        Label testLabel = getTester().startComponentInPage(new Label("testLabel"));
        IHeaderResponse response = Mockito.mock(IHeaderResponse.class);
        
        LeafletResourcesBehavior.instance().renderHead(testLabel, response);
        
        Mockito.verify(response).render(JavaScriptHeaderItem.forReference(getSettings().getJavascriptReference()));
        Mockito.verify(response).render(CssHeaderItem.forReference(getSettings().getCssReference()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRenderResourcesForNullSettings() {
        IHeaderResponse response = Mockito.mock(IHeaderResponse.class);
        LeafletResourcesBehavior.instance().renderHead((LeafletSettings) null, response);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRenderResourcesForNullResponse() {
        LeafletResourcesBehavior.instance().renderHead(getSettings(), null);
    }
    
    @Test
    public void testRenderResourcesForSettings() {
        IHeaderResponse response = Mockito.mock(IHeaderResponse.class);
        LeafletResourcesBehavior.instance().renderHead(getSettings(), response);
        Mockito.verify(response).render(JavaScriptHeaderItem.forReference(getSettings().getJavascriptReference()));
        Mockito.verify(response).render(CssHeaderItem.forReference(getSettings().getCssReference()));        
    }
}
