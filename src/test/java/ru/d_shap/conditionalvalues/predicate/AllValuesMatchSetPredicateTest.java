///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of conditional values.
//
// Conditional values is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Conditional values is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.conditionalvalues.predicate;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.util.DataHelper;

/**
 * Tests for {@link AllValuesMatchSetPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class AllValuesMatchSetPredicateTest {

    /**
     * Test class constructor.
     */
    public AllValuesMatchSetPredicateTest() {
        super();
    }

    /**
     * {@link AllValuesMatchSetPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, null, null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, null, "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate(null, new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", null, null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", null, "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", null, null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", null, "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("condition", new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xxxvaluexxx", DataHelper.createHashSet((Object) "value"))).isTrue();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "valuexxx", DataHelper.createHashSet((Object) "value"))).isTrue();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xxxvalue", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new AllValuesMatchSetPredicate().evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
    }

    /**
     * {@link AllValuesMatchSetPredicate} class test.
     */
    @Test
    public void lookupTest() {
        // TODO
    }

}
