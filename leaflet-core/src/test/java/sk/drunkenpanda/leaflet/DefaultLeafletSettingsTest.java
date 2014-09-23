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
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jan Ferko
 * @sa.date 2014-08-01T16:23:21+0100
 */
public class DefaultLeafletSettingsTest extends AbstractLeafletTest {

    @Test
    public void testGetWebJarJavascriptReferenceByDefault() {
        ResourceReference reference = new DefaultLeafletSettings().getJavascriptReference();
        Assert.assertTrue(reference.getClass().isAssignableFrom(LeafletJavascriptResourceReference.class));
    }
    
    @Test
    public void testGetCDNJavascriptReferenceIfCDNRequested() {
        ResourceReference reference = new DefaultLeafletSettings.Builder()
                .setUseCdn(true).build().getJavascriptReference();
        Assert.assertTrue(reference.getClass().isAssignableFrom(UrlResourceReference.class));
    }
    
    @Test
    public void testGetLocalJavascriptResourceReferenceIfSet() {
        JavaScriptResourceReference expected = new JavaScriptResourceReference(getClass(), "leaflet.js");
        ResourceReference reference = new DefaultLeafletSettings.Builder()
                .setJavascriptReference(expected)
                .build().getJavascriptReference();
        Assert.assertEquals(expected, reference);
    }
    
    @Test
    public void testGetWebJarCSSReferenceByDefault() {
        ResourceReference reference = new DefaultLeafletSettings().getCssReference();
        Assert.assertTrue(reference.getClass().isAssignableFrom(LeafletStylesheetResourceReference.class));        
    }
    
    @Test
    public void testGetCDNStylesheetReferenceIfCDNRequested() {
        ResourceReference reference = new DefaultLeafletSettings.Builder()
                .setUseCdn(true).build().getCssReference();
        Assert.assertTrue(reference.getClass().isAssignableFrom(UrlResourceReference.class));
    }
    
    @Test
    public void testGetLocalCSSResourceReferenceIfSet() {
        CssResourceReference expected = new CssResourceReference(getClass(), "leaflet.css");
        ResourceReference reference = new DefaultLeafletSettings.Builder().setCssReference(expected)
                .build().getCssReference();
        Assert.assertEquals(expected, reference);
    }
    
    @Test
    public void testDoesntUseWebJarsIfCDNIsSet() {
        DefaultLeafletSettings settings = new DefaultLeafletSettings.Builder()
                .setUseCdn(true).build();
        Assert.assertFalse(settings.useWebJars());
    }
    
    @Test
    public void testDoesntUseWebJarsIfReferencesAreSet() {
        CssResourceReference cssReference = new CssResourceReference(getClass(), "leaflet.css");
        JavaScriptResourceReference jsReference = new JavaScriptResourceReference(getClass(), "leaflet.js");
        DefaultLeafletSettings settings = new DefaultLeafletSettings.Builder()
                .setCssReference(cssReference)
                .setJavascriptReference(jsReference).build();
        Assert.assertFalse(settings.useWebJars());
    }
        
    @Test
    public void testUsesWebJarsByDefault() {
        DefaultLeafletSettings settings = new DefaultLeafletSettings();
        Assert.assertTrue(settings.useWebJars());
    }
}
