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
import ru.d_shap.conditionalvalues.data.TupleValue3Extractor;
import ru.d_shap.conditionalvalues.data.TupleValue4Extractor;

/**
 * Tests for {@link LogicalAndPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class LogicalAndPredicateTest {

    /**
     * Test class constructor.
     */
    public LogicalAndPredicateTest() {
        super();
    }

    /**
     * {@link LogicalAndPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate(null, "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("", null, null)).isTrue();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("", "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new LogicalAndPredicate(new EqualsPredicate()).evaluate("condition", "vaLUe", "ValUE")).isFalse();

        Predicate predicate1 = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()));
        Assertions.assertThat(predicate1.evaluate("c", 1, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 9, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 10, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 11, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate1.evaluate("c", 15, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate1.evaluate("c", 19, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate1.evaluate("c", 20, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 21, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 29, new Tuple(10, 20))).isFalse();

        Predicate predicate2 = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new LogicalNotPredicate(new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsGreaterThenPredicate())));
        Assertions.assertThat(predicate2.evaluate("c", 1, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 9, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 10, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 11, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 15, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 19, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 20, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 21, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 29, new Tuple(10, 20))).isFalse();

        Predicate predicate31 = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()), new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()));
        Assertions.assertThat(predicate31.evaluate("c", 1, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 9, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 10, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 11, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 15, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate31.evaluate("c", 19, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 20, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 21, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 29, new Tuple(10, 20, 15))).isFalse();

        Predicate predicate32 = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()));
        Assertions.assertThat(predicate32.evaluate("c", 1, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 9, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 10, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 11, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 15, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate32.evaluate("c", 19, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 20, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 21, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 29, new Tuple(10, 20, 15))).isFalse();

        Predicate predicate33 = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()), new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()));
        Assertions.assertThat(predicate33.evaluate("c", 1, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 9, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 10, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 11, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 15, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate33.evaluate("c", 19, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 20, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 21, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 29, new Tuple(10, 20, 15))).isFalse();

        Predicate predicate4 = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()), new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()), new ValueSetFunctionPredicate(new TupleValue4Extractor(), new LogicalNotPredicate(new EqualsPredicate())));
        Assertions.assertThat(predicate4.evaluate("c", 1, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 9, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 10, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 11, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 15, new Tuple(10, 20, 15, 9))).isTrue();
        Assertions.assertThat(predicate4.evaluate("c", 19, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 20, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 21, new Tuple(10, 20, 15, 9))).isFalse();
        Assertions.assertThat(predicate4.evaluate("c", 29, new Tuple(10, 20, 15, 9))).isFalse();
    }

    /**
     * {@link LogicalAndPredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate predicate = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()));
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", new Tuple(10, 19));
        valueSetBuilder.addCondition("cond", new Tuple(20, 29));
        valueSetBuilder.addCondition("cond", new Tuple(30, 39));
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", new Tuple(40, 49));
        valueSetBuilder.addCondition("cond", new Tuple(50, 59));
        valueSetBuilder.addCondition("cond", new Tuple(60, 69));
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", new Tuple(70, 79));
        valueSetBuilder.addCondition("cond", new Tuple(80, 89));
        valueSetBuilder.addCondition("cond", new Tuple(90, 99));
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", 27);
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", 45);
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", 63);
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", 88);
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("last 3 values");

        conditionSetBuilder.addCondition("cond", 5);
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly();
    }

}
