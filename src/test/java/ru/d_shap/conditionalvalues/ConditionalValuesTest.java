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
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link ConditionalValues}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValuesTest {

    /**
     * Test class constructor.
     */
    public ConditionalValuesTest() {
        super();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();

        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        valueSets.add(null);

        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createStringConditionalValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createBooleanConditionalValuesTest() {
        ValueSetBuilder<Boolean> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue(true);
        ValueSet<Boolean> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(false);
        ValueSet<Boolean> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<Boolean> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createIntegerConditionalValuesTest() {
        ValueSetBuilder<Integer> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue(1);
        ValueSet<Integer> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2);
        ValueSet<Integer> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<Integer> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createLongConditionalValuesTest() {
        ValueSetBuilder<Long> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue(1L);
        ValueSet<Long> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2L);
        ValueSet<Long> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<Long> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createFloatConditionalValuesTest() {
        ValueSetBuilder<Float> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue(1.0f);
        ValueSet<Float> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2.0f);
        ValueSet<Float> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<Float> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createDoubleConditionalValuesTest() {
        ValueSetBuilder<Double> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue(1.0);
        ValueSet<Double> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2.0);
        ValueSet<Double> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<Double> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createObjectConditionalValuesTest() {
        ValueSetBuilder<StringBuilder> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue(new StringBuilder());
        ValueSet<StringBuilder> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(new StringBuilder());
        ValueSet<StringBuilder> valueSet2 = valueSetBuilder.buildAndClear();

        ConditionalValues<?> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createNullConditionalValuesTest() {
        List<ValueSet<String>> valueSets1 = null;
        ConditionalValues<String> conditionalValues1 = ConditionalValues.createConditionalValues(valueSets1);
        Assertions.assertThat(conditionalValues1).isNotNull();
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).isEmpty();

        ValueSet<String>[] valueSets2 = null;
        ConditionalValues<String> conditionalValues2 = ConditionalValues.createConditionalValues(valueSets2);
        Assertions.assertThat(conditionalValues2).isNotNull();
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).isEmpty();

        ValueSet<Boolean>[] valueSets3 = null;
        ConditionalValues<Boolean> conditionalValues3 = ConditionalValues.createConditionalValues(valueSets3);
        Assertions.assertThat(conditionalValues3).isNotNull();
        Assertions.assertThat(conditionalValues3.getAllConditionNames()).isEmpty();

        ValueSet<Integer>[] valueSets4 = null;
        ConditionalValues<Integer> conditionalValues4 = ConditionalValues.createConditionalValues(valueSets4);
        Assertions.assertThat(conditionalValues4).isNotNull();
        Assertions.assertThat(conditionalValues4.getAllConditionNames()).isEmpty();

        ValueSet<Long>[] valueSets5 = null;
        ConditionalValues<Long> conditionalValues5 = ConditionalValues.createConditionalValues(valueSets5);
        Assertions.assertThat(conditionalValues5).isNotNull();
        Assertions.assertThat(conditionalValues5.getAllConditionNames()).isEmpty();

        ValueSet<Float>[] valueSets6 = null;
        ConditionalValues<Float> conditionalValues6 = ConditionalValues.createConditionalValues(valueSets6);
        Assertions.assertThat(conditionalValues6).isNotNull();
        Assertions.assertThat(conditionalValues6.getAllConditionNames()).isEmpty();

        ValueSet<Double>[] valueSets7 = null;
        ConditionalValues<Double> conditionalValues7 = ConditionalValues.createConditionalValues(valueSets7);
        Assertions.assertThat(conditionalValues7).isNotNull();
        Assertions.assertThat(conditionalValues7.getAllConditionNames()).isEmpty();

        ValueSet<Object>[] valueSets8 = null;
        ConditionalValues<Object> conditionalValues8 = ConditionalValues.createConditionalValues(valueSets8);
        Assertions.assertThat(conditionalValues8).isNotNull();
        Assertions.assertThat(conditionalValues8.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllConditionNamesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        Set<String> allConditionNames = conditionalValues.getAllConditionNames();
        Assertions.assertThat(allConditionNames).isNotNull();
        Assertions.assertThat(allConditionNames).containsExactly("cond1", "cond2", "cond3", "cond4");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllConditionValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        Set<String> allConditionValues1 = conditionalValues.getAllConditionValues("cond1");
        Assertions.assertThat(allConditionValues1).isNotNull();
        Assertions.assertThat(allConditionValues1).containsExactly("val11", "val12");

        Set<String> allConditionValues2 = conditionalValues.getAllConditionValues("cond2");
        Assertions.assertThat(allConditionValues2).isNotNull();
        Assertions.assertThat(allConditionValues2).containsExactly("val21", "val22");

        Set<String> allConditionValues3 = conditionalValues.getAllConditionValues("cond3");
        Assertions.assertThat(allConditionValues3).isNotNull();
        Assertions.assertThat(allConditionValues3).containsExactly("val31", "val32");

        Set<String> allConditionValues4 = conditionalValues.getAllConditionValues("cond4");
        Assertions.assertThat(allConditionValues4).isNotNull();
        Assertions.assertThat(allConditionValues4).containsExactly("val41", "val42");

        Set<String> allConditionValues5 = conditionalValues.getAllConditionValues("cond5");
        Assertions.assertThat(allConditionValues5).isNotNull();
        Assertions.assertThat(allConditionValues5).isEmpty();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllValueSetUniqueConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        Set<ValueSetUniqueCondition> valueSetUniqueConditions = conditionalValues.getAllValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions).hasSize(6);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateFullValueSetFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues.createConditionalValues(valueSets);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetUniqueConditionFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues.createConditionalValues(valueSets);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val6", "val2", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet = conditionSetBuilder.buildAndClear();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isFalse();
        Assertions.assertThat(values.getAllValues()).containsExactly("val1", "val3", "val5");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getEmptyValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val6", "val2", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet = conditionSetBuilder.buildAndClear();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAndValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.buildAndClear();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.buildAndClear();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet3 = conditionSetBuilder.buildAndClear();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isFalse();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getOrValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet = valueSetBuilder.buildAndClear();
        List<ValueSet<String>> valueSets = new ArrayList<>();
        valueSets.add(valueSet);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.buildAndClear();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();

        conditionSetBuilder.addCondition("cond1", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.buildAndClear();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();

        ConditionSet conditionSet3 = conditionSetBuilder.buildAndClear();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getSingleMatchingValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val23");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.buildAndClear();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3, valueSet4);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet = conditionSetBuilder.buildAndClear();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isFalse();
        Assertions.assertThat(values.getAllValues()).containsExactly("val1");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getSeveralMatchingValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val23");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.buildAndClear();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3, valueSet4);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet = conditionSetBuilder.buildAndClear();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isFalse();
        Assertions.assertThat(values.getAllValues()).containsExactly("val2", "val4");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getDifferentCardinalityMatchingValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.buildAndClear();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet1 = conditionSetBuilder.buildAndClear();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();
        Assertions.assertThat(values1.getAllValues()).containsExactly("val3");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet2 = conditionSetBuilder.buildAndClear();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();
        Assertions.assertThat(values2.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet3 = conditionSetBuilder.buildAndClear();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isFalse();
        Assertions.assertThat(values3.getAllValues()).containsExactly("val2", "val3");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet4 = conditionSetBuilder.buildAndClear();
        Values<String> values4 = conditionalValues.getValues(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isFalse();
        Assertions.assertThat(values4.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet5 = conditionSetBuilder.buildAndClear();
        Values<String> values5 = conditionalValues.getValues(conditionSet5);
        Assertions.assertThat(values5).isNotNull();
        Assertions.assertThat(values5.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getNullMatchingValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.buildAndClear();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);

        Values<String> values = conditionalValues.getValues(null);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.buildAndClear();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);
        Assertions.assertThat(conditionalValues).toStringContains("cond1=[val1]");
        Assertions.assertThat(conditionalValues).toStringContains("cond2=[val2]");
        Assertions.assertThat(conditionalValues).toStringContains("cond3=[val3]");
        Assertions.assertThat(conditionalValues).toStringContains("cond4=[val4]");
    }

}
