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
import static org.assertj.core.api.Assertions.assertThat;
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
            assertThat(actual).isNotNull();
            assertThat(actual).isEqualTo(entry.getValue());
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
        TestAjaxBehavior behavior = new TestAjaxBehavior((java.util.Map<String, String>)null);
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

        TestAjaxBehavior behavior = new TestAjaxBehavior("testA");
        Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = this.prepareRequest(tester, behavior, "testA", "testValue");
        tester.processRequest(request);

        StringValue actual = behavior.actualVariableValue;
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.toString()).isEqualTo("testValue");
    }

    @Test
    public void testGetMissingVariable() {
        final WicketTester tester = getTester();

        TestAjaxBehavior behavior = new TestAjaxBehavior("testB");
        Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = prepareRequest(tester, behavior, "testA", "testA");
        tester.processRequest(request);

        StringValue missingValue = behavior.actualVariableValue;
        assertThat(missingValue.isEmpty()).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetVariableForNullName() {
        final WicketTester tester = getTester();

        TestAjaxBehavior behavior = new TestAjaxBehavior("testA", "A");
        Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        behavior.getVariableValue(null);
    }

    private static class TestAjaxBehavior extends LeafletAjaxBehavior {

        StringValue actualVariableValue;

        private String expectedVariableName;

        public TestAjaxBehavior(String expectedVariableName) {
            this.expectedVariableName = expectedVariableName;
        }

        public TestAjaxBehavior(String parameterName, String parameterValue) {
            this.addJavascriptValue(parameterName, parameterValue);
        }

        public TestAjaxBehavior(java.util.Map<String, String> testAttributes) {
            this.addJavascriptValues(testAttributes);
        }

        @Override
        protected void respond(AjaxRequestTarget target) {
            if (this.expectedVariableName != null) {
                this.actualVariableValue = this.getVariableValue(expectedVariableName);
            }
        }

    }

}
