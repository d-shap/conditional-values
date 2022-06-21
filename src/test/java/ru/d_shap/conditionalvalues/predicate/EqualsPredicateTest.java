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
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "xxvaluexx", "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "alu", "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "vaLUe", "ValUE")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "xxvaLUexx", "ValUE")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate(null, "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("", null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "xxvaluexx", "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "alu", "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "vaLUe", "ValUE")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "xxvaLUexx", "ValUE")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("", "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "xxvaluexx", "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "alu", "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "vaLUe", "ValUE")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "xxvaLUexx", "ValUE")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("condition", "aLU", "ValUE")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("c", "", "")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", "value", "")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", "", "value")).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("c", null, null)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 5, null)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", null, 5)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 5, 5)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 5, 6)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", 6, 5)).isFalse();

        Assertions.assertThat(new EqualsPredicate().evaluate("c", "value", "value")).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", new StringBuilder("value"), "value")).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", "value", new StringBuilder("value"))).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", new StringBuilder("value"), new StringBuilder("value"))).isFalse();

        Object obj1 = new StringBuilder("value");
        Object obj2 = new Object();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj1, obj1)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj2, obj2)).isTrue();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj1, obj2)).isFalse();
        Assertions.assertThat(new EqualsPredicate().evaluate("c", obj2, obj1)).isFalse();
    }

    /**
     * {@link EqualsPredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new EqualsPredicate();
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", 1);
        valueSetBuilder.addCondition("cond", 2);
        valueSetBuilder.addCondition("cond", 3);
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", 4);
        valueSetBuilder.addCondition("cond", 5);
        valueSetBuilder.addCondition("cond", 6);
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", 7);
        valueSetBuilder.addCondition("cond", 8);
        valueSetBuilder.addCondition("cond", 9);
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", 2);
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", 4);
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", 6);
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", 8);
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("last 3 values");

        conditionSetBuilder.addCondition("cond", 10);
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly();
    }

}
