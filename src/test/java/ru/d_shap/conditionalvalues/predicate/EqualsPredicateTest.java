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

/**
 * Tests for {@link EqualsPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class EqualsPredicateTest {

    /**
     * Test class constructor.
     */
    public EqualsPredicateTest() {
        super();
    }

    /**
     * {@link EqualsPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new EqualsPredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", null, 5)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 5, 5)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 5, 6)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 6, 5)).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("c", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", new StringBuffer("value"), "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", "value", new StringBuffer("value"))).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", new StringBuffer("value"), new StringBuffer("value"))).isFalse();

        Object obj1 = new StringBuffer("value");
        Object obj2 = new Object();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj1, obj1)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj2, obj2)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj1, obj2)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj2, obj1)).isFalse();
    }

}
