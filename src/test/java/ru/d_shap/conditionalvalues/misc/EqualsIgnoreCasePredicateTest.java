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
package ru.d_shap.conditionalvalues.misc;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link EqualsIgnoreCasePredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class EqualsIgnoreCasePredicateTest {

    /**
     * Test class constructor.
     */
    public EqualsIgnoreCasePredicateTest() {
        super();
    }

    /**
     * {@link EqualsIgnoreCasePredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, null, null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", null, null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", null, null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "vaLUe", "ValUE")).isTrue();
    }

}
