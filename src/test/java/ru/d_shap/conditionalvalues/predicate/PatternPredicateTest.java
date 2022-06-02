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

import java.util.regex.Pattern;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link PatternPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class PatternPredicateTest {

    /**
     * Test class constructor.
     */
    public PatternPredicateTest() {
        super();
    }

    /**
     * {@link PatternPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new PatternPredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate(null, null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate(null, "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate(null, "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate(null, "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();

        Assertions.assertThat(new PatternPredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("", null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("", "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("", "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate("", "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();

        Assertions.assertThat(new PatternPredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("condition", null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("condition", "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("condition", "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate("condition", "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();

        Assertions.assertThat(new PatternPredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new PatternPredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new PatternPredicate().evaluate("c", null, 5)).isFalse();
        try {
            new PatternPredicate().evaluate("c", 5, 5);
            Assertions.fail("PatternPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
        try {
            new PatternPredicate().evaluate("c", 5, 6);
            Assertions.fail("PatternPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
        try {
            new PatternPredicate().evaluate("c", 6, 5);
            Assertions.fail("PatternPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
    }

}
