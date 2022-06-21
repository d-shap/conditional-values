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
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.Values;

/**
 * Tests for {@link StringContainsIgnoreCasePredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class StringContainsIgnoreCasePredicateTest {

    /**
     * Test class constructor.
     */
    public StringContainsIgnoreCasePredicateTest() {
        super();
    }

    /**
     * {@link StringContainsIgnoreCasePredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "xxvaluexx", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "valuexx", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "xxvalue", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "alu", "value")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "vaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "xxvaLUexx", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "vaLUexx", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "xxvaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate(null, "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "xxvaluexx", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "valuexx", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "xxvalue", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "alu", "value")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "vaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "xxvaLUexx", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "vaLUexx", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "xxvaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("", "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "xxvaluexx", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "valuexx", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "xxvalue", "value")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "alu", "value")).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "vaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "xxvaLUexx", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "vaLUexx", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "xxvaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("condition", "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", "", "")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", "value", "")).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", "", "value")).isFalse();

        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", null, 5)).isFalse();
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", 5, 5);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", 5, 6);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", 6, 5);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }

        Assertions.assertThat(new StringContainsIgnoreCasePredicate().evaluate("c", "value", "value")).isTrue();
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", new StringBuilder("value"), "value");
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongConditionSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", "value", new StringBuilder("value"));
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", new StringBuilder("value"), new StringBuilder("value"));
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }

        Object obj1 = new StringBuilder("value");
        Object obj2 = new Object();
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", obj1, obj1);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", obj2, obj2);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Object");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", obj1, obj2);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Object");
        }
        try {
            new StringContainsIgnoreCasePredicate().evaluate("c", obj2, obj1);
            Assertions.fail("StringContainsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
    }

    /**
     * {@link StringContainsIgnoreCasePredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new StringContainsIgnoreCasePredicate();
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", "Value1");
        valueSetBuilder.addCondition("cond", "vAlue2");
        valueSetBuilder.addCondition("cond", "vaLue3");
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "valUe4");
        valueSetBuilder.addCondition("cond", "valuE5");
        valueSetBuilder.addCondition("cond", "VAlue6");
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "vALue7");
        valueSetBuilder.addCondition("cond", "vaLUe8");
        valueSetBuilder.addCondition("cond", "valUE9");
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", "some ValuE2");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", "vAlUe4 is the best");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", "VAlue6 vaLUe7");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("next 3 values", "last 3 values");

        conditionSetBuilder.addCondition("cond", "Am I a valUE8, right?");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("last 3 values");

        conditionSetBuilder.addCondition("cond", "xxx");
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly();
    }

}
