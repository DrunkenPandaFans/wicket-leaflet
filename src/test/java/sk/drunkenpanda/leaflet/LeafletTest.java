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

import org.apache.wicket.Page;
import org.apache.wicket.mock.MockHomePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.drunkenpanda.leaflet.resources.LeafletJavascriptResourceReference;
import sk.drunkenpanda.leaflet.resources.LeafletStylesheetResourceReference;

/**
 *
 * @author Jan Ferko 
 */
public class LeafletTest {
    
    private LeafletSettings customSettings;
    
    @Before
    public void before() {
        customSettings = new DefaultLeafletSettings.Builder()
            .setAutoAppendResources(true)
            .setUseCdn(true)
            .setVersion("0.1")
            .build();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInstallWithNullApp() {
        Leaflet.install(null);
    }
    
    @Test
    public void testDefaultConfigurationWasInstalled() {        
        WicketTester tester = new WicketTester(createWebApp(null, true));
        LeafletSettings settings = tester.getApplication().getMetaData(Leaflet.LEAFLET_SETTINGS_KEY);
        
        compareSettings(new DefaultLeafletSettings(), settings);
    }        

    @Test
    public void testCustomConfigurationWasInstalled() {        
        WicketTester tester = new WicketTester(createWebApp(customSettings, true));
        
        LeafletSettings actual = tester.getApplication().getMetaData(Leaflet.LEAFLET_SETTINGS_KEY);
        compareSettings(customSettings, actual);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testInstallCustomConfigurationToNullApp() {
        Leaflet.install(null, new DefaultLeafletSettings());
    }
    
    @Test
    public void testDoesntInstallIfLeafletsAreAlreadyInstalled() {
        WicketTester tester = new WicketTester(createWebApp(customSettings, true));        
        Leaflet.install(tester.getApplication());
        LeafletSettings actual = tester.getApplication().getMetaData(Leaflet.LEAFLET_SETTINGS_KEY);
        compareSettings(customSettings, actual);
    }        
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSettingsForNullApplication() {
        Leaflet.getSettings(null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetSettingWithoutInstallation() {
        // application.init wasn't called yet
        WebApplication application = createWebApp(null, false);
        Leaflet.getSettings(application);
    }
    
    @Test
    public void testGetSettingsForApplication() {
        WicketTester tester = new WicketTester(createWebApp(customSettings, true));
        LeafletSettings settings = Leaflet.getSettings(tester.getApplication());
        compareSettings(customSettings, settings);
    }
    
    @Test
    public void testGetSettingsForThreadLocalApplication() {
        WicketTester tester = new WicketTester(createWebApp(customSettings, true));
        LeafletSettings settings = Leaflet.getSettings();
        compareSettings(customSettings, settings);        
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetSettingsForThreadLocalApplicationWithoutInstallation() {
        WicketTester tester = new WicketTester(createWebApp(null, false));
        LeafletSettings settings = Leaflet.getSettings();
    }
    
    // webjar resources init            
    @Test
    public void testUseWebJars() {
        WicketTester tester = new WicketTester(createWebApp(null, true));
        tester.startResourceReference(LeafletJavascriptResourceReference.instance());
        LeafletSettings settings = Leaflet.getSettings(tester.getApplication());
        Assert.assertNotNull(tester.getLastResponseAsString());

        tester.startResourceReference(LeafletStylesheetResourceReference.instance());
        Assert.assertNotNull(tester.getLastResponseAsString());
    }
    
    @Test
    public void testAddResourcesIfAutoAppendIsUsed() {
        WicketTester tester = new WicketTester(createWebApp(customSettings, true));
        tester.startPage(MockHomePage.class);
        
        TagTester jsResource = TagTester.createTagByAttribute(tester.getLastResponseAsString(), "src", 
                String.format(DefaultLeafletSettings.JS_CDN_PATTERN, customSettings.getVersion()));
        Assert.assertNotNull(jsResource);
        Assert.assertEquals("script", jsResource.getName());
        
        TagTester cssResource = TagTester.createTagByAttribute(tester.getLastResponseAsString(), "href",
                String.format(DefaultLeafletSettings.CSS_CDN_PATTERN, customSettings.getVersion()));
        Assert.assertNotNull(cssResource);
        Assert.assertEquals("link", cssResource.getName());
    }
    
    @Test
    public void testDontAddResourceIfAutoAppendIsntUsed() {
        DefaultLeafletSettings settings = new DefaultLeafletSettings();
        WicketTester tester = new WicketTester(createWebApp(settings, true));
        tester.startPage(MockHomePage.class);
        
        tester.assertContainsNot("<script>");
        tester.assertContainsNot("<link>");
    }
    
    private void compareSettings(LeafletSettings expected, LeafletSettings actual) {
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getCssReference(), actual.getCssReference());
        Assert.assertEquals(expected.getJavascriptReference(), actual.getJavascriptReference());
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
        Assert.assertEquals(expected.useCDN(), actual.useCDN());
        Assert.assertEquals(expected.useWebJars(), actual.useWebJars());
    }
    
    private WebApplication createWebApp(final LeafletSettings settings, final boolean installLeaflets) {
        return new WebApplication() {

            @Override
            protected void init() {
                super.init();                                
                if (installLeaflets) {
                    if (settings == null) {
                        Leaflet.install(this);
                    } else {
                        Leaflet.install(this, settings);
                    }                    
                }
            }

            @Override
            public Class<? extends Page> getHomePage() {
                return Page.class;
            }            
        };
    }        
        
}
