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
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.Values;

/**
 * Tests for {@link PatternFindPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class PatternFindPredicateTest {

    /**
     * Test class constructor.
     */
    public PatternFindPredicateTest() {
        super();
    }

    /**
     * {@link PatternFindPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "xxvaluexx", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "alu", Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "xxvaLUexx", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate(null, "aLU", Pattern.compile("[vV]a[lL]U[eE]"))).isFalse();

        Assertions.assertThat(new PatternFindPredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "xxvaluexx", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "alu", Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "xxvaLUexx", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("", "aLU", Pattern.compile("[vV]a[lL]U[eE]"))).isFalse();

        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", null, Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "value1", Pattern.compile("value2"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "value", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "xxvaluexx", Pattern.compile("value"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "alu", Pattern.compile("value"))).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "vaLUe", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "xxvaLUexx", Pattern.compile("[vV]a[lL]U[eE]"))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("condition", "aLU", Pattern.compile("[vV]a[lL]U[eE]"))).isFalse();

        Assertions.assertThat(new PatternFindPredicate().evaluate("c", "", Pattern.compile(""))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("c", "value", Pattern.compile(""))).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("c", "", Pattern.compile("value"))).isFalse();

        Assertions.assertThat(new PatternFindPredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new PatternFindPredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new PatternFindPredicate().evaluate("c", null, 5)).isFalse();
        try {
            new PatternFindPredicate().evaluate("c", 5, 5);
            Assertions.fail("PatternFindPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
        try {
            new PatternFindPredicate().evaluate("c", 5, 6);
            Assertions.fail("PatternFindPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }
        try {
            new PatternFindPredicate().evaluate("c", 6, 5);
            Assertions.fail("PatternFindPredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.util.regex.Pattern, but was java.lang.Integer");
        }

        try {
            new PatternFindPredicate().evaluate("c", new StringBuilder("value"), Pattern.compile("value"));
            Assertions.fail("PatternFindPredicate test fail");
        } catch (WrongConditionSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
    }

    /**
     * {@link PatternFindPredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new PatternFindPredicate();
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", Pattern.compile("a.*b"));
        valueSetBuilder.addCondition("cond", Pattern.compile("c.*d"));
        valueSetBuilder.addCondition("cond", Pattern.compile("e.*f"));
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", Pattern.compile("aa.*b"));
        valueSetBuilder.addCondition("cond", Pattern.compile("cc.*d"));
        valueSetBuilder.addCondition("cond", Pattern.compile("ee.*f"));
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", Pattern.compile("a.*bb"));
        valueSetBuilder.addCondition("cond", Pattern.compile("c.*dd"));
        valueSetBuilder.addCondition("cond", Pattern.compile("e.*ff"));
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", "cc ab sxfxsf");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", "12312cc123dasfasf");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("first 3 values", "next 3 values");

        conditionSetBuilder.addCondition("cond", "dddddc123ddvvvvvv eexfaafadf");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("first 3 values", "next 3 values", "last 3 values");

        conditionSetBuilder.addCondition("cond", "eeefdfsfff");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("first 3 values", "next 3 values", "last 3 values");
    }

}
