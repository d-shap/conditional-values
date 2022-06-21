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
import ru.d_shap.conditionalvalues.data.Tuple;
import ru.d_shap.conditionalvalues.data.TupleValue1Extractor;
import ru.d_shap.conditionalvalues.data.TupleValue2Extractor;

/**
 * Tests for {@link ValueSetFunctionPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetFunctionPredicateTest {

    /**
     * Test class constructor.
     */
    public ValueSetFunctionPredicateTest() {
        super();
    }

    /**
     * {@link ValueSetFunctionPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, 1, null)).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, null, new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, 1, new Tuple(1, 2))).isTrue();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, 2, new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue2Extractor(), new EqualsPredicate()).evaluate(null, 2, new Tuple(1, 2))).isTrue();

        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("", null, null)).isTrue();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("", 1, null)).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("", null, new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("", 1, new Tuple(1, 2))).isTrue();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("", 2, new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue2Extractor(), new EqualsPredicate()).evaluate("", 2, new Tuple(1, 2))).isTrue();

        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("condition", 1, null)).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("condition", null, new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("condition", 1, new Tuple(1, 2))).isTrue();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate("condition", 2, new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue2Extractor(), new EqualsPredicate()).evaluate("condition", 2, new Tuple(1, 2))).isTrue();

        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, "value", new Tuple(1, 2))).isFalse();
        Assertions.assertThat(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate()).evaluate(null, 1, "value")).isFalse();
    }

    /**
     * {@link ValueSetFunctionPredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new ValueSetFunctionPredicate(new TupleValue1Extractor(), new EqualsPredicate());
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", new Tuple(1, 500));
        valueSetBuilder.addCondition("cond", new Tuple(2, 500));
        valueSetBuilder.addCondition("cond", new Tuple(3, 500));
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", new Tuple(4, 500));
        valueSetBuilder.addCondition("cond", new Tuple(5, 500));
        valueSetBuilder.addCondition("cond", new Tuple(6, 500));
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", new Tuple(7, 500));
        valueSetBuilder.addCondition("cond", new Tuple(8, 500));
        valueSetBuilder.addCondition("cond", new Tuple(9, 500));
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
