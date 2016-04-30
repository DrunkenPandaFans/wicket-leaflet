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


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class ApplicationBootstrap {

    public static void main(String[] args) throws Exception {
        final String baseDir = "src/main/webapp";

        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "9000";
        }

        final Server server = new Server(Integer.valueOf(port));

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase(baseDir);
        context.setDescriptor(baseDir + "/WEB-INF/web.xml");
        server.setHandler(context);

        server.start();
        server.join();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    server.stop();
                } catch (Exception ex) {
                    // exception during shutdown
                    ex.printStackTrace(System.err);
                }
            }
        }));
    }
}
