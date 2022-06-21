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
import ru.d_shap.conditionalvalues.data.IsGreaterThenPredicate;
import ru.d_shap.conditionalvalues.data.IsLessThenPredicate;
import ru.d_shap.conditionalvalues.data.Tuple;
import ru.d_shap.conditionalvalues.data.TupleValue1Extractor;
import ru.d_shap.conditionalvalues.data.TupleValue2Extractor;

/**
 * Tests for {@link LogicalNotPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class LogicalNotPredicateTest {

    /**
     * Test class constructor.
     */
    public LogicalNotPredicateTest() {
        super();
    }

    /**
     * {@link LogicalNotPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate(null, null, null)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate(null, "value", null)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate(null, null, "value")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate(null, "value1", "value2")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate(null, "value", "value")).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate(null, "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("", null, null)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("", "value", null)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("", null, "value")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("", "value1", "value2")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("", "value", "value")).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("", "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("condition", null, null)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("condition", "value", null)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("condition", null, "value")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("condition", "value1", "value2")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("condition", "value", "value")).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("condition", "vaLUe", "ValUE")).isTrue();

        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", null, null)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", 5, null)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", null, 5)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", 5, 5)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", 5, 6)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", 6, 5)).isTrue();

        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", "value", "value")).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", new StringBuilder("value"), "value")).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", "value", new StringBuilder("value"))).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", new StringBuilder("value"), new StringBuilder("value"))).isTrue();

        Object obj1 = new StringBuilder("value");
        Object obj2 = new Object();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", obj1, obj1)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", obj2, obj2)).isFalse();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", obj1, obj2)).isTrue();
        Assertions.assertThat(new LogicalNotPredicate(new EqualsPredicate()).evaluate("c", obj2, obj1)).isTrue();
    }

    /**
     * {@link LogicalNotPredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new LogicalNotPredicate(new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate())));
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", new Tuple(10, 39));
        valueSetBuilder.addCondition("cond", new Tuple(20, 39));
        valueSetBuilder.addCondition("cond", new Tuple(30, 39));
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", new Tuple(40, 69));
        valueSetBuilder.addCondition("cond", new Tuple(50, 69));
        valueSetBuilder.addCondition("cond", new Tuple(60, 69));
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", new Tuple(70, 99));
        valueSetBuilder.addCondition("cond", new Tuple(80, 99));
        valueSetBuilder.addCondition("cond", new Tuple(90, 99));
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", 37);
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("next 3 values", "last 3 values");

        conditionSetBuilder.addCondition("cond", 45);
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("first 3 values", "next 3 values", "last 3 values");

        conditionSetBuilder.addCondition("cond", 63);
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("first 3 values", "last 3 values");

        conditionSetBuilder.addCondition("cond", 98);
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("first 3 values", "next 3 values");
    }

}
