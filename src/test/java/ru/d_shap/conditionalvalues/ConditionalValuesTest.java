///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Conditional values.
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

import org.junit.Assert;
import org.junit.Test;

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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        valueSets.add(null);

        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<Boolean> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(false);
        ValueSet<Boolean> valueSet2 = valueSetBuilder.build();

        ConditionalValues<Boolean> conditionalValues = ConditionalValues.createBooleanConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<Integer> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2);
        ValueSet<Integer> valueSet2 = valueSetBuilder.build();

        ConditionalValues<Integer> conditionalValues = ConditionalValues.createIntegerConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<Long> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2L);
        ValueSet<Long> valueSet2 = valueSetBuilder.build();

        ConditionalValues<Long> conditionalValues = ConditionalValues.createLongConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<Float> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2.0f);
        ValueSet<Float> valueSet2 = valueSetBuilder.build();

        ConditionalValues<Float> conditionalValues = ConditionalValues.createFloatConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<Double> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(2.0);
        ValueSet<Double> valueSet2 = valueSetBuilder.build();

        ConditionalValues<Double> conditionalValues = ConditionalValues.createDoubleConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
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
        ValueSet<StringBuilder> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue(new StringBuilder());
        ValueSet<StringBuilder> valueSet2 = valueSetBuilder.build();

        ConditionalValues<?> conditionalValues = ConditionalValues.createObjectConditionalValues(valueSet1, valueSet2, null);
        Assert.assertNotNull(conditionalValues);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createNullConditionalValuesTest() {
        List<ValueSet<String>> valueSets1 = null;
        ConditionalValues<String> conditionalValues1 = ConditionalValues.createConditionalValues(valueSets1);
        Assert.assertNotNull(conditionalValues1);
        Assert.assertTrue(conditionalValues1.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets2 = null;
        ConditionalValues<String> conditionalValues2 = ConditionalValues.createStringConditionalValues(valueSets2);
        Assert.assertNotNull(conditionalValues2);
        Assert.assertTrue(conditionalValues2.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets3 = null;
        ConditionalValues<Boolean> conditionalValues3 = ConditionalValues.createBooleanConditionalValues(valueSets3);
        Assert.assertNotNull(conditionalValues3);
        Assert.assertTrue(conditionalValues3.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets4 = null;
        ConditionalValues<Integer> conditionalValues4 = ConditionalValues.createIntegerConditionalValues(valueSets4);
        Assert.assertNotNull(conditionalValues4);
        Assert.assertTrue(conditionalValues4.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets5 = null;
        ConditionalValues<Long> conditionalValues5 = ConditionalValues.createLongConditionalValues(valueSets5);
        Assert.assertNotNull(conditionalValues5);
        Assert.assertTrue(conditionalValues5.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets6 = null;
        ConditionalValues<Float> conditionalValues6 = ConditionalValues.createFloatConditionalValues(valueSets6);
        Assert.assertNotNull(conditionalValues6);
        Assert.assertTrue(conditionalValues6.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets7 = null;
        ConditionalValues<Double> conditionalValues7 = ConditionalValues.createDoubleConditionalValues(valueSets7);
        Assert.assertNotNull(conditionalValues7);
        Assert.assertTrue(conditionalValues7.getAllConditionNames().isEmpty());

        ValueSet<?>[] valueSets8 = null;
        ConditionalValues<?> conditionalValues8 = ConditionalValues.createObjectConditionalValues(valueSets8);
        Assert.assertNotNull(conditionalValues8);
        Assert.assertTrue(conditionalValues8.getAllConditionNames().isEmpty());
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        Set<String> allConditionNames = conditionalValues.getAllConditionNames();
        Assert.assertEquals(4, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));
        Assert.assertTrue(allConditionNames.contains("cond3"));
        Assert.assertTrue(allConditionNames.contains("cond4"));
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        Set<String> allConditionValues1 = conditionalValues.getAllConditionValues("cond1");
        Assert.assertEquals(2, allConditionValues1.size());
        Assert.assertTrue(allConditionValues1.contains("val11"));
        Assert.assertTrue(allConditionValues1.contains("val12"));

        Set<String> allConditionValues2 = conditionalValues.getAllConditionValues("cond2");
        Assert.assertEquals(2, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val21"));
        Assert.assertTrue(allConditionValues2.contains("val22"));

        Set<String> allConditionValues3 = conditionalValues.getAllConditionValues("cond3");
        Assert.assertEquals(2, allConditionValues3.size());
        Assert.assertTrue(allConditionValues3.contains("val31"));
        Assert.assertTrue(allConditionValues3.contains("val32"));

        Set<String> allConditionValues4 = conditionalValues.getAllConditionValues("cond4");
        Assert.assertEquals(2, allConditionValues4.size());
        Assert.assertTrue(allConditionValues4.contains("val41"));
        Assert.assertTrue(allConditionValues4.contains("val42"));

        Set<String> allConditionValues5 = conditionalValues.getAllConditionValues("cond5");
        Assert.assertEquals(0, allConditionValues5.size());
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2);

        Set<ValueSetUniqueCondition> valueSetUniqueConditions = conditionalValues.getAllValueSetUniqueConditions();
        Assert.assertNotNull(valueSetUniqueConditions);
        Assert.assertEquals(6, valueSetUniqueConditions.size());
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val6", "val2", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet = conditionSetBuilder.build();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertFalse(values.isEmpty());
        Set<String> allValues = values.getAllValues();
        Assert.assertEquals(3, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val3"));
        Assert.assertTrue(allValues.contains("val5"));
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val6", "val2", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet = conditionSetBuilder.build();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertTrue(values.isEmpty());
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
        ValueSet<String> valueSet = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assert.assertTrue(values1.isEmpty());

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assert.assertTrue(values2.isEmpty());

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assert.assertFalse(values3.isEmpty());
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
        ValueSet<String> valueSet = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assert.assertFalse(values1.isEmpty());

        conditionSetBuilder.addCondition("cond1", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assert.assertFalse(values2.isEmpty());

        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assert.assertTrue(values3.isEmpty());
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val23");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2, valueSet3, valueSet4);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet = conditionSetBuilder.build();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertFalse(values.isEmpty());
        Assert.assertTrue(values.contains("val1"));
        Assert.assertFalse(values.contains("val2"));
        Assert.assertFalse(values.contains("val3"));
        Assert.assertFalse(values.contains("val4"));
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val23");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2, valueSet3, valueSet4);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet = conditionSetBuilder.build();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertFalse(values.isEmpty());
        Assert.assertFalse(values.contains("val1"));
        Assert.assertTrue(values.contains("val2"));
        Assert.assertFalse(values.contains("val3"));
        Assert.assertTrue(values.contains("val4"));
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2, valueSet3);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assert.assertFalse(values1.isEmpty());
        Assert.assertFalse(values1.contains("val1"));
        Assert.assertFalse(values1.contains("val2"));
        Assert.assertTrue(values1.contains("val3"));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assert.assertFalse(values2.isEmpty());
        Assert.assertTrue(values2.contains("val1"));
        Assert.assertTrue(values2.contains("val2"));
        Assert.assertFalse(values2.contains("val3"));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assert.assertFalse(values3.isEmpty());
        Assert.assertFalse(values3.contains("val1"));
        Assert.assertTrue(values3.contains("val2"));
        Assert.assertTrue(values3.contains("val3"));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.getValues(conditionSet4);
        Assert.assertFalse(values4.isEmpty());
        Assert.assertTrue(values4.contains("val1"));
        Assert.assertFalse(values4.contains("val2"));
        Assert.assertFalse(values4.contains("val3"));

        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet5 = conditionSetBuilder.build();
        Values<String> values5 = conditionalValues.getValues(conditionSet5);
        Assert.assertTrue(values5.isEmpty());
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2, valueSet3);

        Values<String> values = conditionalValues.getValues(null);
        Assert.assertNotNull(values);
        Assert.assertTrue(values.isEmpty());
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
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createStringConditionalValues(valueSet1, valueSet2, valueSet3);
        String str = conditionalValues.toString();
        Assert.assertTrue(str.contains("cond1=[val1]"));
        Assert.assertTrue(str.contains("cond2=[val2]"));
        Assert.assertTrue(str.contains("cond3=[val3]"));
        Assert.assertTrue(str.contains("cond4=[val4]"));
    }

}
