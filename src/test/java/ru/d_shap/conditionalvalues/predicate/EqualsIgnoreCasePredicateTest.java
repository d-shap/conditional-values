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
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate(null, "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("", "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("condition", "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", null, 5)).isFalse();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", 5, 5)).isTrue();
        try {
            new EqualsIgnoreCasePredicate().evaluate("c", 5, 6);
            Assertions.fail("EqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }
        try {
            new EqualsIgnoreCasePredicate().evaluate("c", 6, 5);
            Assertions.fail("EqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }

        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", "value", "value")).isTrue();
        try {
            new EqualsIgnoreCasePredicate().evaluate("c", new StringBuilder("value"), "value");
            Assertions.fail("EqualsIgnoreCasePredicate test fail");
        } catch (WrongConditionSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new EqualsIgnoreCasePredicate().evaluate("c", "value", new StringBuilder("value"));
            Assertions.fail("EqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }

        Object obj1 = new StringBuilder("value");
        Object obj2 = new Object();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", obj1, obj1)).isTrue();
        Assertions.assertThat(new EqualsIgnoreCasePredicate().evaluate("c", obj2, obj2)).isTrue();
        try {
            new EqualsIgnoreCasePredicate().evaluate("c", obj1, obj2);
            Assertions.fail("EqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Object");
        }
        try {
            new EqualsIgnoreCasePredicate().evaluate("c", obj2, obj1);
            Assertions.fail("EqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
    }

}
