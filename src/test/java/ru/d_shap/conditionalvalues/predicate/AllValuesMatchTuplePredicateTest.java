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
import ru.d_shap.assertions.util.DataHelper;
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.TuplePredicate;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.Values;

/**
 * Tests for {@link AllValuesMatchTuplePredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class AllValuesMatchTuplePredicateTest {

    /**
     * Test class constructor.
     */
    public AllValuesMatchTuplePredicateTest() {
        super();
    }

    /**
     * {@link AllValuesMatchTuplePredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, null, null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, null, "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate(null, new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", null, null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", null, "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", null, null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", null, "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", null, null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", null, "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", null, "value", DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", new StringContainsPredicate(), null, null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", new StringContainsPredicate(), "value", null)).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", new StringContainsPredicate(), null, DataHelper.createHashSet((Object) "value"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", new StringContainsPredicate(), "value1", DataHelper.createHashSet((Object) "value2"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("condition", new StringContainsPredicate(), "value", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xxxvaluexxx", DataHelper.createHashSet((Object) "value"))).isTrue();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "valuexxx", DataHelper.createHashSet((Object) "value"))).isTrue();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xxxvalue", DataHelper.createHashSet((Object) "value"))).isTrue();

        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "val", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "yval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "zval", DataHelper.createHashSet((Object) "xval", "yval", "zval"))).isFalse();

        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xx valx valy valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isTrue();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xx valx xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xx valy xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
        Assertions.assertThat(new AllValuesMatchTuplePredicate().evaluate("", new StringContainsPredicate(), "xx valz xxx", DataHelper.createHashSet((Object) "valx", "valy", "valz"))).isFalse();
    }

    /**
     * {@link AllValuesMatchTuplePredicate} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        TuplePredicate tuplePredicate = new AllValuesMatchTuplePredicate();
        conditionalValuesBuilder.setTuplePredicate(tuplePredicate);
        Predicate predicate = new StringContainsPredicate();
        conditionalValuesBuilder.setPredicate(predicate);
        valueSetBuilder.addCondition("cond", "val1");
        valueSetBuilder.addCondition("cond", "val2");
        valueSetBuilder.addCondition("cond", "val3");
        valueSetBuilder.addValue("first 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "val4");
        valueSetBuilder.addCondition("cond", "val5");
        valueSetBuilder.addCondition("cond", "val6");
        valueSetBuilder.addValue("next 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "val7");
        valueSetBuilder.addCondition("cond", "val8");
        valueSetBuilder.addCondition("cond", "val9");
        valueSetBuilder.addValue("last 3 values");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("cond", "sadval2czxcval1jklffdsval3333");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("first 3 values");

        conditionSetBuilder.addCondition("cond", "112val4cxzcval5valvalval666");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("next 3 values");

        conditionSetBuilder.addCondition("cond", "___val6");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond", "val8123123val99999vvvvval7fsdfsdf");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("last 3 values");

        conditionSetBuilder.addCondition("cond", "val9");
        Values<String> values5 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values5.getValues()).containsExactly();
    }

}
