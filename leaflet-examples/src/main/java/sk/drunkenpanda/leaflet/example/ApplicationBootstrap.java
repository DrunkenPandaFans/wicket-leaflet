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
package sk.drunkenpanda.leaflet.example;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.*;
import java.util.EnumSet;

@SpringBootApplication
public class ApplicationBootstrap implements ServletContextInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // register default servlet to serve static resources
//        final ServletRegistration.Dynamic defaultServletReg = servletContext.addServlet("default", new DefaultServlet());
//        defaultServletReg.setLoadOnStartup(1);
//        defaultServletReg.addMapping("/static/*");

        final WicketFilter wicketFilter = new WicketFilter(new WicketApplication());
        final FilterRegistration.Dynamic wicketFilterReg = servletContext
                .addFilter("wicket.wicket-leaflet-examples", wicketFilter);
        wicketFilterReg.setInitParameter("applicationClassName", WicketApplication.class.getCanonicalName());
        wicketFilterReg.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");

        final EnumSet<DispatcherType> dispatchers = EnumSet.noneOf(DispatcherType.class);
        wicketFilterReg.addMappingForUrlPatterns(dispatchers, true, "/*");
    }
}
