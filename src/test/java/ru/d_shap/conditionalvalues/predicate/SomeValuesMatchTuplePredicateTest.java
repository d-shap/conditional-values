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
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.TuplePredicate;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.Values;

/**
 * Tests for {@link SomeValuesMatchTuplePredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class SomeValuesMatchTuplePredicateTest {

    /**
     * Test class constructor.
     */
    public SomeValuesMatchTuplePredicateTest() {
        super();
    }

    /**
     * {@link SomeValuesMatchTuplePredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, null, null, null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, null, "value", null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate(null, new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", null, null, null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", null, "value", null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", null, null, null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", null, "value", null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("condition", new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xxxvaluexxx", DataHelper.createHashSet((Object) "value"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "valuexxx", DataHelper.createHashSet((Object) "value"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xxxvalue", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, -1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 0).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 2).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 3).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 4).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(-1, 5).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, -1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 0).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 2).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 3).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(0, 4).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, -1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 0).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 2).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 3).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(1, 4).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, -1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 0).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 2).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 3).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(2, 4).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, -1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 0).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 2).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 3).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(3, 4).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, -1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 0).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 1).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 2).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 3).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();

        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new SomeValuesMatchTuplePredicate(4, 4).evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
    }

    /**
     * {@link SomeValuesMatchTuplePredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        TuplePredicate tuplePredicate = new SomeValuesMatchTuplePredicate(1, 2);
        conditionalValuesBuilder.setTuplePredicate(tuplePredicate);
        Predicate predicate = new StringContainsPredicate();
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", "val1");
        valueSetBuilder.addCondition("cond", "val2");
        valueSetBuilder.addCondition("cond", "val3");
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "val4");
        valueSetBuilder.addCondition("cond", "val5");
        valueSetBuilder.addCondition("cond", "val6");
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "val7");
        valueSetBuilder.addCondition("cond", "val8");
        valueSetBuilder.addCondition("cond", "val9");
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", "sadval2czxcval3sdfsdfa");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", "112val4cxzc");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", "___val6____val5___val4");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond", "val8123123val1111");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("first 3 values", "last 3 values");

        conditionSetBuilder.addCondition("cond", "val1val2val3val7val8val9");
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly();
    }

}
