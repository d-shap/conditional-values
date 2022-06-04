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
 * Tests for {@link PatternMatchesPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class PatternMatchesPredicateTest {

    /**
     * Test class constructor.
     */
    public PatternMatchesPredicateTest() {
        super();
    }

    /**
     * {@link PatternMatchesPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new PatternMatchesPredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate(null, null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate(null, "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate(null, "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate(null, "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();

        Assertions.assertThat(new PatternMatchesPredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("", null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("", "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("", "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("", "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();

        Assertions.assertThat(new PatternMatchesPredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("condition", null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("condition", "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("condition", "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("condition", "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();

        Assertions.assertThat(new PatternMatchesPredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new PatternMatchesPredicate().evaluate("c", null, 5)).isFalse();
        try {
            new PatternMatchesPredicate().evaluate("c", 5, 5);
            Assertions.fail("PatternMatchesPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
        try {
            new PatternMatchesPredicate().evaluate("c", 5, 6);
            Assertions.fail("PatternMatchesPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
        try {
            new PatternMatchesPredicate().evaluate("c", 6, 5);
            Assertions.fail("PatternMatchesPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }

        try {
            new PatternMatchesPredicate().evaluate("c", new StringBuilder("value"), Pattern.compile("value"));
            Assertions.fail("PatternMatchesPredicate test fail");
        } catch (WrongConditionSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
    }

    /**
     * {@link PatternMatchesPredicate} class test.
     */
    @Test
    public void lookupTest() {
        // TODO
    }

}
