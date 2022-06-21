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
 * Tests for {@link StringEqualsIgnoreCasePredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class StringEqualsIgnoreCasePredicateTest {

    /**
     * Test class constructor.
     */
    public StringEqualsIgnoreCasePredicateTest() {
        super();
    }

    /**
     * {@link StringEqualsIgnoreCasePredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "xxvaluexx", "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "alu", "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "vaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "xxvaLUexx", "ValUE")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate(null, "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "xxvaluexx", "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "alu", "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "vaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "xxvaLUexx", "ValUE")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("", "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "xxvaluexx", "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "alu", "value")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "vaLUe", "ValUE")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "xxvaLUexx", "ValUE")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("condition", "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", "", "")).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", "value", "")).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", "", "value")).isFalse();

        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", null, 5)).isFalse();
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", 5, 5);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", 5, 6);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", 6, 5);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Integer");
        }

        Assertions.assertThat(new StringEqualsIgnoreCasePredicate().evaluate("c", "value", "value")).isTrue();
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", new StringBuilder("value"), "value");
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongConditionSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", "value", new StringBuilder("value"));
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", new StringBuilder("value"), new StringBuilder("value"));
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }

        Object obj1 = new StringBuilder("value");
        Object obj2 = new Object();
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", obj1, obj1);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", obj2, obj2);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Object");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", obj1, obj2);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.Object");
        }
        try {
            new StringEqualsIgnoreCasePredicate().evaluate("c", obj2, obj1);
            Assertions.fail("StringEqualsIgnoreCasePredicate test fail");
        } catch (WrongValueSetValueException ex) {
            Assertions.assertThat(ex).hasMessage("Condition with name c has a wrong class, expected java.lang.String, but was java.lang.StringBuilder");
        }
    }

    /**
     * {@link StringEqualsIgnoreCasePredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new StringEqualsIgnoreCasePredicate();
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

        conditionSetBuilder.addCondition("cond", "ValuE2");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", "vAlUe4");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", "VAlue6");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", "valUE8");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("last 3 values");

        conditionSetBuilder.addCondition("cond", "xxx");
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly();
    }

}
