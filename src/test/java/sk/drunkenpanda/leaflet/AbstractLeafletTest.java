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
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

/**
 *
 * @author Jan Ferko
 */
public abstract class AbstractLeafletTest {
    private WicketTester tester;
    
    @Before
    public void before() {
        WebApplication application = createApplication();
        
        this.tester = new WicketTester(application);
    }
    
    private WebApplication createApplication() {
        final LeafletSettings settings = getSettings();
        return new WebApplication() {

            @Override
            protected void init() {
                super.init();
                // create based on settings
                Leaflet.install(this, settings);
                
                // execute children init setup
                AbstractLeafletTest.this.init(this);
            }
                        
            @Override
            public Class<? extends Page> getHomePage() {
                return AbstractLeafletTest.this.getHomePage();
            }
        };
    }
    
    protected WicketTester getTester() {
        return this.tester;
    }
    
    protected WebApplication getApplication() {
        return this.tester.getApplication();
    }
    
    protected void init(WebApplication application) {        
    }
    
    protected Class<? extends Page> getHomePage() {
        return Page.class;
    }
    
    protected LeafletSettings getSettings() {
        return new DefaultLeafletSettings();
    }
}
