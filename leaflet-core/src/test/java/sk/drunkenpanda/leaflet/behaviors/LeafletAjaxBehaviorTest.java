/*
 * Copyright 2016 Jan Ferko.
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

package sk.drunkenpanda.leaflet.behaviors;

import java.util.HashMap;
import com.google.common.collect.ImmutableMap;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.tester.WicketTester;
import static org.junit.Assert.*;
import org.junit.Test;
import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.components.map.Map;

/**
 *
 * @author Jan Ferko
 */
public final class LeafletAjaxBehaviorTest extends AbstractLeafletTest {

    @Test
    public void testStoresAttributesInAjaxRequest() {
        ImmutableMap<String, String> attributes = ImmutableMap.of(
                "test1", "A", "test2", "B", "test3", "C"
        );
        TestAjaxBehavior behavior = new TestAjaxBehavior(attributes);
        final AjaxRequestAttributes requestAttributes = new AjaxRequestAttributes();

        behavior.updateAjaxAttributes(requestAttributes);

        for (java.util.Map.Entry<String, String> entry : attributes.entrySet()) {
            Object actual = requestAttributes.getExtraParameters().get(entry.getKey());
            assertNotNull(actual);
            assertEquals(entry.getValue(), actual);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullAttributeValue() {
        TestAjaxBehavior behavior = new TestAjaxBehavior("test", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullAttributeName() {
        TestAjaxBehavior behavior = new TestAjaxBehavior(null, "A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullParameters() {
        TestAjaxBehavior behavior = new TestAjaxBehavior(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddParametersContainsNull() {
        java.util.Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("A", null);
        TestAjaxBehavior behavior = new TestAjaxBehavior(parameters);
    }

    @Test
    public void testGetVariable() {
        final WicketTester tester = getTester();

        TestAjaxBehavior behavior = new TestAjaxBehavior("testA", "A");
        Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = this.prepareRequest(tester, behavior, "testA", "testValue");
        tester.setRequest(request);
        tester.applyRequest();

        StringValue actual = behavior.tryGetVariable("testA");
        assertFalse(actual.isEmpty());
        assertEquals(actual.toString(), "testValue");
    }

    @Test
    public void testGetMissingVariable() {
        final WicketTester tester = getTester();

        TestAjaxBehavior behavior = new TestAjaxBehavior("testA", "A");
        Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = prepareRequest(tester, behavior, "testA", "testA");
        tester.setRequest(request);
        tester.applyRequest();

        StringValue missingValue = behavior.getVariableValue("missing");
        assertTrue(missingValue.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetVariableForNullName() {
        final WicketTester tester = getTester();

        TestAjaxBehavior behavior = new TestAjaxBehavior("testA", "A");
        Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        behavior.tryGetVariable(null);
    }

    private static class TestAjaxBehavior extends LeafletAjaxBehavior {

        public StringValue tryGetVariable(String parameterName) {
            return this.getVariableValue(parameterName);
        }

        public TestAjaxBehavior(String parameterName, String parameterValue) {
            this.addJavascriptValue(parameterName, parameterValue);
        }

        public TestAjaxBehavior(java.util.Map<String, String> testAttributes) {
            this.addJavascriptValues(testAttributes);
        }

        @Override
        protected void respond(AjaxRequestTarget target) {
        }

    }

}
