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

import java.util.HashSet;
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
    public void createValueSetBuilderTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        Assertions.assertThat(valueSetBuilder).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionSetBuilderTest() {
        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        Assertions.assertThat(conditionSetBuilder).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesStringTest() {
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

        ConditionalValues<String> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<String> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<String> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<String> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesBooleanTest() {
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

        ConditionalValues<Boolean> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<Boolean> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<Boolean> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<Boolean> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesIntegerTest() {
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

        ConditionalValues<Integer> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<Integer> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<Integer> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<Integer> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesLongTest() {
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

        ConditionalValues<Long> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<Long> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<Long> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<Long> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesFloatTest() {
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

        ConditionalValues<Float> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<Float> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<Float> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<Float> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesDoubleTest() {
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

        ConditionalValues<Double> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<Double> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<Double> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<Double> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesObjectTest() {
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

        ConditionalValues<?> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<?> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, null);
        Assertions.assertThat(conditionalValues2).isNotNull();

        ConditionalValues<?> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, null, valueSet2);
        Assertions.assertThat(conditionalValues3).isNotNull();

        ConditionalValues<?> conditionalValues4 = ConditionalValues.createConditionalValues(null, valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues4).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createNullConditionalValuesTest() {
        ConditionalValues<String> conditionalValues1 = ConditionalValues.createConditionalValues((ValueSet<String>[]) null);
        Assertions.assertThat(conditionalValues1).isNotNull();
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).isEmpty();

        ConditionalValues<String> conditionalValues2 = ConditionalValues.createConditionalValues((ValueSet<String>) null);
        Assertions.assertThat(conditionalValues2).isNotNull();
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).isEmpty();

        ConditionalValues<String> conditionalValues3 = ConditionalValues.createConditionalValues(null, null);
        Assertions.assertThat(conditionalValues3).isNotNull();
        Assertions.assertThat(conditionalValues3.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesDuplicateTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond5", "val51");
        valueSetBuilder.addCondition("cond5", "val52");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond6", "val61");
        valueSetBuilder.addCondition("cond6", "val62");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addCondition("cond7", "val71");
        valueSetBuilder.addCondition("cond7", "val72");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet6 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addCondition("cond8", "val81");
        valueSetBuilder.addCondition("cond8", "val82");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet7 = valueSetBuilder.build();

        ConditionalValues<?> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3, valueSet4, valueSet5, valueSet6, valueSet7, valueSet1);
        Assertions.assertThat(conditionalValues1).isNotNull();

        ConditionalValues<?> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet1, valueSet7, valueSet6, valueSet5, valueSet4, valueSet3, valueSet2, valueSet1);
        Assertions.assertThat(conditionalValues2).isNotNull();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetAllConditionsFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues.createConditionalValues(valueSet1, valueSet2);
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
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues.createConditionalValues(valueSet1, valueSet2);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetFirstValuesContainsSecondValuesFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues.createConditionalValues(valueSet1, valueSet2);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetSecondValuesContainsFirstValuesFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues.createConditionalValues(valueSet1, valueSet2);
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

        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues.getAllConditionNames()).isNotNull();
        Assertions.assertThat(conditionalValues.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
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

        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond1")).isNotNull();
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond1")).containsExactly("val11", "val12");
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond2")).isNotNull();
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond2")).containsExactly("val21", "val22");
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond3")).isNotNull();
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond3")).containsExactly("val31", "val32");
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond4")).isNotNull();
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond4")).containsExactly("val41", "val42");
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond5")).isNotNull();
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond5")).isEmpty();
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

        ConditionalValues<String> conditionalValues1 = ConditionalValues.createConditionalValues(valueSet1);
        Assertions.assertThat(conditionalValues1.getAllValueSetUniqueConditions()).isNotNull();
        Assertions.assertThat(conditionalValues1.getAllValueSetUniqueConditions()).hasSize(4);

        ConditionalValues<String> conditionalValues2 = ConditionalValues.createConditionalValues(valueSet2);
        Assertions.assertThat(conditionalValues2.getAllValueSetUniqueConditions()).isNotNull();
        Assertions.assertThat(conditionalValues2.getAllValueSetUniqueConditions()).hasSize(2);

        ConditionalValues<String> conditionalValues3 = ConditionalValues.createConditionalValues(valueSet1, valueSet2);
        Assertions.assertThat(conditionalValues3.getAllValueSetUniqueConditions()).isNotNull();
        Assertions.assertThat(conditionalValues3.getAllValueSetUniqueConditions()).hasSize(6);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetSimpleConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();
        Assertions.assertThat(values1.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();
        Assertions.assertThat(values2.getAllValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsSimpleConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val2");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val1");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsSimpleConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet = conditionSetBuilder.build();
        Values<String> values = conditionalValues.lookup(conditionSet);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isFalse();
        Assertions.assertThat(values.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetAndConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet11 = conditionSetBuilder.build();
        Values<String> values11 = conditionalValues.lookup(conditionSet11);
        Assertions.assertThat(values11).isNotNull();
        Assertions.assertThat(values11.isEmpty()).isFalse();
        Assertions.assertThat(values11.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet12 = conditionSetBuilder.build();
        Values<String> values12 = conditionalValues.lookup(conditionSet12);
        Assertions.assertThat(values12).isNotNull();
        Assertions.assertThat(values12.isEmpty()).isFalse();
        Assertions.assertThat(values12.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet13 = conditionSetBuilder.build();
        Values<String> values13 = conditionalValues.lookup(conditionSet13);
        Assertions.assertThat(values13).isNotNull();
        Assertions.assertThat(values13.isEmpty()).isFalse();
        Assertions.assertThat(values13.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet21 = conditionSetBuilder.build();
        Values<String> values21 = conditionalValues.lookup(conditionSet21);
        Assertions.assertThat(values21).isNotNull();
        Assertions.assertThat(values21.isEmpty()).isFalse();
        Assertions.assertThat(values21.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet22 = conditionSetBuilder.build();
        Values<String> values22 = conditionalValues.lookup(conditionSet22);
        Assertions.assertThat(values22).isNotNull();
        Assertions.assertThat(values22.isEmpty()).isFalse();
        Assertions.assertThat(values22.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet23 = conditionSetBuilder.build();
        Values<String> values23 = conditionalValues.lookup(conditionSet23);
        Assertions.assertThat(values23).isNotNull();
        Assertions.assertThat(values23.isEmpty()).isFalse();
        Assertions.assertThat(values23.getAllValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsAndConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.lookup(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.lookup(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsAndConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet = conditionSetBuilder.build();
        Values<String> values = conditionalValues.lookup(conditionSet);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isFalse();
        Assertions.assertThat(values.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetOrConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();
        Assertions.assertThat(values1.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();
        Assertions.assertThat(values2.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond2", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.lookup(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isFalse();
        Assertions.assertThat(values3.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond2", "val4");
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.lookup(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isFalse();
        Assertions.assertThat(values4.getAllValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsOrConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val3");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val4");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val1");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.lookup(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.lookup(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsOrConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val3");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();
        Assertions.assertThat(values1.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val4");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();
        Assertions.assertThat(values2.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val2");
        conditionSetBuilder.addCondition("cond2", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.lookup(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isFalse();
        Assertions.assertThat(values3.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val2");
        conditionSetBuilder.addCondition("cond2", "val4");
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.lookup(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isFalse();
        Assertions.assertThat(values4.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetAndOrConditionsTest() {
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
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        ConditionSet conditionSet11 = conditionSetBuilder.build();
        Values<String> values11 = conditionalValues.lookup(conditionSet11);
        Assertions.assertThat(values11).isNotNull();
        Assertions.assertThat(values11.isEmpty()).isFalse();
        Assertions.assertThat(values11.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet12 = conditionSetBuilder.build();
        Values<String> values12 = conditionalValues.lookup(conditionSet12);
        Assertions.assertThat(values12).isNotNull();
        Assertions.assertThat(values12.isEmpty()).isFalse();
        Assertions.assertThat(values12.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet13 = conditionSetBuilder.build();
        Values<String> values13 = conditionalValues.lookup(conditionSet13);
        Assertions.assertThat(values13).isNotNull();
        Assertions.assertThat(values13.isEmpty()).isFalse();
        Assertions.assertThat(values13.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet14 = conditionSetBuilder.build();
        Values<String> values14 = conditionalValues.lookup(conditionSet14);
        Assertions.assertThat(values14).isNotNull();
        Assertions.assertThat(values14.isEmpty()).isFalse();
        Assertions.assertThat(values14.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet15 = conditionSetBuilder.build();
        Values<String> values15 = conditionalValues.lookup(conditionSet15);
        Assertions.assertThat(values15).isNotNull();
        Assertions.assertThat(values15.isEmpty()).isFalse();
        Assertions.assertThat(values15.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet21 = conditionSetBuilder.build();
        Values<String> values21 = conditionalValues.lookup(conditionSet21);
        Assertions.assertThat(values21).isNotNull();
        Assertions.assertThat(values21.isEmpty()).isFalse();
        Assertions.assertThat(values21.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet22 = conditionSetBuilder.build();
        Values<String> values22 = conditionalValues.lookup(conditionSet22);
        Assertions.assertThat(values22).isNotNull();
        Assertions.assertThat(values22.isEmpty()).isFalse();
        Assertions.assertThat(values22.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet23 = conditionSetBuilder.build();
        Values<String> values23 = conditionalValues.lookup(conditionSet23);
        Assertions.assertThat(values23).isNotNull();
        Assertions.assertThat(values23.isEmpty()).isFalse();
        Assertions.assertThat(values23.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet24 = conditionSetBuilder.build();
        Values<String> values24 = conditionalValues.lookup(conditionSet24);
        Assertions.assertThat(values24).isNotNull();
        Assertions.assertThat(values24.isEmpty()).isFalse();
        Assertions.assertThat(values24.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet25 = conditionSetBuilder.build();
        Values<String> values25 = conditionalValues.lookup(conditionSet25);
        Assertions.assertThat(values25).isNotNull();
        Assertions.assertThat(values25.isEmpty()).isFalse();
        Assertions.assertThat(values25.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        ConditionSet conditionSet31 = conditionSetBuilder.build();
        Values<String> values31 = conditionalValues.lookup(conditionSet31);
        Assertions.assertThat(values31).isNotNull();
        Assertions.assertThat(values31.isEmpty()).isFalse();
        Assertions.assertThat(values31.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet32 = conditionSetBuilder.build();
        Values<String> values32 = conditionalValues.lookup(conditionSet32);
        Assertions.assertThat(values32).isNotNull();
        Assertions.assertThat(values32.isEmpty()).isFalse();
        Assertions.assertThat(values32.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet33 = conditionSetBuilder.build();
        Values<String> values33 = conditionalValues.lookup(conditionSet33);
        Assertions.assertThat(values33).isNotNull();
        Assertions.assertThat(values33.isEmpty()).isFalse();
        Assertions.assertThat(values33.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet34 = conditionSetBuilder.build();
        Values<String> values34 = conditionalValues.lookup(conditionSet34);
        Assertions.assertThat(values34).isNotNull();
        Assertions.assertThat(values34.isEmpty()).isFalse();
        Assertions.assertThat(values34.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet35 = conditionSetBuilder.build();
        Values<String> values35 = conditionalValues.lookup(conditionSet35);
        Assertions.assertThat(values35).isNotNull();
        Assertions.assertThat(values35.isEmpty()).isFalse();
        Assertions.assertThat(values35.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet41 = conditionSetBuilder.build();
        Values<String> values41 = conditionalValues.lookup(conditionSet41);
        Assertions.assertThat(values41).isNotNull();
        Assertions.assertThat(values41.isEmpty()).isFalse();
        Assertions.assertThat(values41.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet42 = conditionSetBuilder.build();
        Values<String> values42 = conditionalValues.lookup(conditionSet42);
        Assertions.assertThat(values42).isNotNull();
        Assertions.assertThat(values42.isEmpty()).isFalse();
        Assertions.assertThat(values42.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet43 = conditionSetBuilder.build();
        Values<String> values43 = conditionalValues.lookup(conditionSet43);
        Assertions.assertThat(values43).isNotNull();
        Assertions.assertThat(values43.isEmpty()).isFalse();
        Assertions.assertThat(values43.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet44 = conditionSetBuilder.build();
        Values<String> values44 = conditionalValues.lookup(conditionSet44);
        Assertions.assertThat(values44).isNotNull();
        Assertions.assertThat(values44.isEmpty()).isFalse();
        Assertions.assertThat(values44.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet45 = conditionSetBuilder.build();
        Values<String> values45 = conditionalValues.lookup(conditionSet45);
        Assertions.assertThat(values45).isNotNull();
        Assertions.assertThat(values45.isEmpty()).isFalse();
        Assertions.assertThat(values45.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet51 = conditionSetBuilder.build();
        Values<String> values51 = conditionalValues.lookup(conditionSet51);
        Assertions.assertThat(values51).isNotNull();
        Assertions.assertThat(values51.isEmpty()).isFalse();
        Assertions.assertThat(values51.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val11");
        ConditionSet conditionSet52 = conditionSetBuilder.build();
        Values<String> values52 = conditionalValues.lookup(conditionSet52);
        Assertions.assertThat(values52).isNotNull();
        Assertions.assertThat(values52.isEmpty()).isFalse();
        Assertions.assertThat(values52.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val12");
        ConditionSet conditionSet53 = conditionSetBuilder.build();
        Values<String> values53 = conditionalValues.lookup(conditionSet53);
        Assertions.assertThat(values53).isNotNull();
        Assertions.assertThat(values53.isEmpty()).isFalse();
        Assertions.assertThat(values53.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val21");
        ConditionSet conditionSet54 = conditionSetBuilder.build();
        Values<String> values54 = conditionalValues.lookup(conditionSet54);
        Assertions.assertThat(values54).isNotNull();
        Assertions.assertThat(values54.isEmpty()).isFalse();
        Assertions.assertThat(values54.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet55 = conditionSetBuilder.build();
        Values<String> values55 = conditionalValues.lookup(conditionSet55);
        Assertions.assertThat(values55).isNotNull();
        Assertions.assertThat(values55.isEmpty()).isFalse();
        Assertions.assertThat(values55.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet61 = conditionSetBuilder.build();
        Values<String> values61 = conditionalValues.lookup(conditionSet61);
        Assertions.assertThat(values61).isNotNull();
        Assertions.assertThat(values61.isEmpty()).isFalse();
        Assertions.assertThat(values61.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val11");
        ConditionSet conditionSet62 = conditionSetBuilder.build();
        Values<String> values62 = conditionalValues.lookup(conditionSet62);
        Assertions.assertThat(values62).isNotNull();
        Assertions.assertThat(values62.isEmpty()).isFalse();
        Assertions.assertThat(values62.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val12");
        ConditionSet conditionSet63 = conditionSetBuilder.build();
        Values<String> values63 = conditionalValues.lookup(conditionSet63);
        Assertions.assertThat(values63).isNotNull();
        Assertions.assertThat(values63.isEmpty()).isFalse();
        Assertions.assertThat(values63.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val21");
        ConditionSet conditionSet64 = conditionSetBuilder.build();
        Values<String> values64 = conditionalValues.lookup(conditionSet64);
        Assertions.assertThat(values64).isNotNull();
        Assertions.assertThat(values64.isEmpty()).isFalse();
        Assertions.assertThat(values64.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet65 = conditionSetBuilder.build();
        Values<String> values65 = conditionalValues.lookup(conditionSet65);
        Assertions.assertThat(values65).isNotNull();
        Assertions.assertThat(values65.isEmpty()).isFalse();
        Assertions.assertThat(values65.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet71 = conditionSetBuilder.build();
        Values<String> values71 = conditionalValues.lookup(conditionSet71);
        Assertions.assertThat(values71).isNotNull();
        Assertions.assertThat(values71.isEmpty()).isFalse();
        Assertions.assertThat(values71.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val11");
        ConditionSet conditionSet72 = conditionSetBuilder.build();
        Values<String> values72 = conditionalValues.lookup(conditionSet72);
        Assertions.assertThat(values72).isNotNull();
        Assertions.assertThat(values72.isEmpty()).isFalse();
        Assertions.assertThat(values72.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val12");
        ConditionSet conditionSet73 = conditionSetBuilder.build();
        Values<String> values73 = conditionalValues.lookup(conditionSet73);
        Assertions.assertThat(values73).isNotNull();
        Assertions.assertThat(values73.isEmpty()).isFalse();
        Assertions.assertThat(values73.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val21");
        ConditionSet conditionSet74 = conditionSetBuilder.build();
        Values<String> values74 = conditionalValues.lookup(conditionSet74);
        Assertions.assertThat(values74).isNotNull();
        Assertions.assertThat(values74.isEmpty()).isFalse();
        Assertions.assertThat(values74.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet75 = conditionSetBuilder.build();
        Values<String> values75 = conditionalValues.lookup(conditionSet75);
        Assertions.assertThat(values75).isNotNull();
        Assertions.assertThat(values75.isEmpty()).isFalse();
        Assertions.assertThat(values75.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet81 = conditionSetBuilder.build();
        Values<String> values81 = conditionalValues.lookup(conditionSet81);
        Assertions.assertThat(values81).isNotNull();
        Assertions.assertThat(values81.isEmpty()).isFalse();
        Assertions.assertThat(values81.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val11");
        ConditionSet conditionSet82 = conditionSetBuilder.build();
        Values<String> values82 = conditionalValues.lookup(conditionSet82);
        Assertions.assertThat(values82).isNotNull();
        Assertions.assertThat(values82.isEmpty()).isFalse();
        Assertions.assertThat(values82.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val12");
        ConditionSet conditionSet83 = conditionSetBuilder.build();
        Values<String> values83 = conditionalValues.lookup(conditionSet83);
        Assertions.assertThat(values83).isNotNull();
        Assertions.assertThat(values83.isEmpty()).isFalse();
        Assertions.assertThat(values83.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val21");
        ConditionSet conditionSet84 = conditionSetBuilder.build();
        Values<String> values84 = conditionalValues.lookup(conditionSet84);
        Assertions.assertThat(values84).isNotNull();
        Assertions.assertThat(values84.isEmpty()).isFalse();
        Assertions.assertThat(values84.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet85 = conditionSetBuilder.build();
        Values<String> values85 = conditionalValues.lookup(conditionSet85);
        Assertions.assertThat(values85).isNotNull();
        Assertions.assertThat(values85.isEmpty()).isFalse();
        Assertions.assertThat(values85.getAllValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsAndOrConditionsTest() {
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
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet11 = conditionSetBuilder.build();
        Values<String> values11 = conditionalValues.lookup(conditionSet11);
        Assertions.assertThat(values11).isNotNull();
        Assertions.assertThat(values11.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet12 = conditionSetBuilder.build();
        Values<String> values12 = conditionalValues.lookup(conditionSet12);
        Assertions.assertThat(values12).isNotNull();
        Assertions.assertThat(values12.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet13 = conditionSetBuilder.build();
        Values<String> values13 = conditionalValues.lookup(conditionSet13);
        Assertions.assertThat(values13).isNotNull();
        Assertions.assertThat(values13.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet14 = conditionSetBuilder.build();
        Values<String> values14 = conditionalValues.lookup(conditionSet14);
        Assertions.assertThat(values14).isNotNull();
        Assertions.assertThat(values14.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet21 = conditionSetBuilder.build();
        Values<String> values21 = conditionalValues.lookup(conditionSet21);
        Assertions.assertThat(values21).isNotNull();
        Assertions.assertThat(values21.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet22 = conditionSetBuilder.build();
        Values<String> values22 = conditionalValues.lookup(conditionSet22);
        Assertions.assertThat(values22).isNotNull();
        Assertions.assertThat(values22.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet23 = conditionSetBuilder.build();
        Values<String> values23 = conditionalValues.lookup(conditionSet23);
        Assertions.assertThat(values23).isNotNull();
        Assertions.assertThat(values23.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet24 = conditionSetBuilder.build();
        Values<String> values24 = conditionalValues.lookup(conditionSet24);
        Assertions.assertThat(values24).isNotNull();
        Assertions.assertThat(values24.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet31 = conditionSetBuilder.build();
        Values<String> values31 = conditionalValues.lookup(conditionSet31);
        Assertions.assertThat(values31).isNotNull();
        Assertions.assertThat(values31.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet32 = conditionSetBuilder.build();
        Values<String> values32 = conditionalValues.lookup(conditionSet32);
        Assertions.assertThat(values32).isNotNull();
        Assertions.assertThat(values32.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet33 = conditionSetBuilder.build();
        Values<String> values33 = conditionalValues.lookup(conditionSet33);
        Assertions.assertThat(values33).isNotNull();
        Assertions.assertThat(values33.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet34 = conditionSetBuilder.build();
        Values<String> values34 = conditionalValues.lookup(conditionSet34);
        Assertions.assertThat(values34).isNotNull();
        Assertions.assertThat(values34.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet41 = conditionSetBuilder.build();
        Values<String> values41 = conditionalValues.lookup(conditionSet41);
        Assertions.assertThat(values41).isNotNull();
        Assertions.assertThat(values41.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        ConditionSet conditionSet42 = conditionSetBuilder.build();
        Values<String> values42 = conditionalValues.lookup(conditionSet42);
        Assertions.assertThat(values42).isNotNull();
        Assertions.assertThat(values42.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet43 = conditionSetBuilder.build();
        Values<String> values43 = conditionalValues.lookup(conditionSet43);
        Assertions.assertThat(values43).isNotNull();
        Assertions.assertThat(values43.isEmpty()).isTrue();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet44 = conditionSetBuilder.build();
        Values<String> values44 = conditionalValues.lookup(conditionSet44);
        Assertions.assertThat(values44).isNotNull();
        Assertions.assertThat(values44.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsAndOrConditionsTest() {
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
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet1111 = conditionSetBuilder.build();
        Values<String> values1111 = conditionalValues.lookup(conditionSet1111);
        Assertions.assertThat(values1111).isNotNull();
        Assertions.assertThat(values1111.isEmpty()).isFalse();
        Assertions.assertThat(values1111.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet1112 = conditionSetBuilder.build();
        Values<String> values1112 = conditionalValues.lookup(conditionSet1112);
        Assertions.assertThat(values1112).isNotNull();
        Assertions.assertThat(values1112.isEmpty()).isFalse();
        Assertions.assertThat(values1112.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet1121 = conditionSetBuilder.build();
        Values<String> values1121 = conditionalValues.lookup(conditionSet1121);
        Assertions.assertThat(values1121).isNotNull();
        Assertions.assertThat(values1121.isEmpty()).isFalse();
        Assertions.assertThat(values1121.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet1122 = conditionSetBuilder.build();
        Values<String> values1122 = conditionalValues.lookup(conditionSet1122);
        Assertions.assertThat(values1122).isNotNull();
        Assertions.assertThat(values1122.isEmpty()).isFalse();
        Assertions.assertThat(values1122.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet1211 = conditionSetBuilder.build();
        Values<String> values1211 = conditionalValues.lookup(conditionSet1211);
        Assertions.assertThat(values1211).isNotNull();
        Assertions.assertThat(values1211.isEmpty()).isFalse();
        Assertions.assertThat(values1211.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet1212 = conditionSetBuilder.build();
        Values<String> values1212 = conditionalValues.lookup(conditionSet1212);
        Assertions.assertThat(values1212).isNotNull();
        Assertions.assertThat(values1212.isEmpty()).isFalse();
        Assertions.assertThat(values1212.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet1221 = conditionSetBuilder.build();
        Values<String> values1221 = conditionalValues.lookup(conditionSet1221);
        Assertions.assertThat(values1221).isNotNull();
        Assertions.assertThat(values1221.isEmpty()).isFalse();
        Assertions.assertThat(values1221.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet1222 = conditionSetBuilder.build();
        Values<String> values1222 = conditionalValues.lookup(conditionSet1222);
        Assertions.assertThat(values1222).isNotNull();
        Assertions.assertThat(values1222.isEmpty()).isFalse();
        Assertions.assertThat(values1222.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet2111 = conditionSetBuilder.build();
        Values<String> values2111 = conditionalValues.lookup(conditionSet2111);
        Assertions.assertThat(values2111).isNotNull();
        Assertions.assertThat(values2111.isEmpty()).isFalse();
        Assertions.assertThat(values2111.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet2112 = conditionSetBuilder.build();
        Values<String> values2112 = conditionalValues.lookup(conditionSet2112);
        Assertions.assertThat(values2112).isNotNull();
        Assertions.assertThat(values2112.isEmpty()).isFalse();
        Assertions.assertThat(values2112.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet2121 = conditionSetBuilder.build();
        Values<String> values2121 = conditionalValues.lookup(conditionSet2121);
        Assertions.assertThat(values2121).isNotNull();
        Assertions.assertThat(values2121.isEmpty()).isFalse();
        Assertions.assertThat(values2121.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet2122 = conditionSetBuilder.build();
        Values<String> values2122 = conditionalValues.lookup(conditionSet2122);
        Assertions.assertThat(values2122).isNotNull();
        Assertions.assertThat(values2122.isEmpty()).isFalse();
        Assertions.assertThat(values2122.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet2211 = conditionSetBuilder.build();
        Values<String> values2211 = conditionalValues.lookup(conditionSet2211);
        Assertions.assertThat(values2211).isNotNull();
        Assertions.assertThat(values2211.isEmpty()).isFalse();
        Assertions.assertThat(values2211.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet2212 = conditionSetBuilder.build();
        Values<String> values2212 = conditionalValues.lookup(conditionSet2212);
        Assertions.assertThat(values2212).isNotNull();
        Assertions.assertThat(values2212.isEmpty()).isFalse();
        Assertions.assertThat(values2212.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet2221 = conditionSetBuilder.build();
        Values<String> values2221 = conditionalValues.lookup(conditionSet2221);
        Assertions.assertThat(values2221).isNotNull();
        Assertions.assertThat(values2221.isEmpty()).isFalse();
        Assertions.assertThat(values2221.getAllValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet2222 = conditionSetBuilder.build();
        Values<String> values2222 = conditionalValues.lookup(conditionSet2222);
        Assertions.assertThat(values2222).isNotNull();
        Assertions.assertThat(values2222.isEmpty()).isFalse();
        Assertions.assertThat(values2222.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupLessSpecificValueSetsTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();
        Assertions.assertThat(values1.getAllValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();
        Assertions.assertThat(values2.getAllValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.lookup(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isFalse();
        Assertions.assertThat(values3.getAllValues()).containsExactly("val3");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.lookup(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isFalse();
        Assertions.assertThat(values4.getAllValues()).containsExactly("val3");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupExampleValueSetsTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("type", "type1");
        valueSetBuilder.addCondition("isViewer", true);
        valueSetBuilder.addValue("field1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("type", "type1");
        valueSetBuilder.addCondition("isEditor", true);
        valueSetBuilder.addValue("field2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("type", "type1");
        valueSetBuilder.addCondition("state", 1);
        valueSetBuilder.addCondition("isViewer", true);
        valueSetBuilder.addValue("field3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("type", "type1");
        conditionSetBuilder.addCondition("state", 1);
        conditionSetBuilder.addCondition("isViewer", true);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.lookup(conditionSet1);
        Assertions.assertThat(values1).isNotNull();
        Assertions.assertThat(values1.isEmpty()).isFalse();
        Assertions.assertThat(values1.getAllValues()).containsExactly("field3");

        conditionSetBuilder.addCondition("type", "type1");
        conditionSetBuilder.addCondition("state", 2);
        conditionSetBuilder.addCondition("isViewer", true);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.lookup(conditionSet2);
        Assertions.assertThat(values2).isNotNull();
        Assertions.assertThat(values2.isEmpty()).isFalse();
        Assertions.assertThat(values2.getAllValues()).containsExactly("field1");

        conditionSetBuilder.addCondition("type", "type1");
        conditionSetBuilder.addCondition("isViewer", true);
        conditionSetBuilder.addCondition("isEditor", true);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.lookup(conditionSet3);
        Assertions.assertThat(values3).isNotNull();
        Assertions.assertThat(values3.isEmpty()).isFalse();
        Assertions.assertThat(values3.getAllValues()).containsExactly("field1", "field2");

        conditionSetBuilder.addCondition("type", "type1");
        conditionSetBuilder.addCondition("state", 1);
        conditionSetBuilder.addCondition("isViewer", true);
        conditionSetBuilder.addCondition("isEditor", true);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Values<String> values4 = conditionalValues.lookup(conditionSet4);
        Assertions.assertThat(values4).isNotNull();
        Assertions.assertThat(values4.isEmpty()).isFalse();
        Assertions.assertThat(values4.getAllValues()).containsExactly("field2", "field3");

        conditionSetBuilder.addCondition("type", "type1");
        conditionSetBuilder.addCondition("isViewer", true);
        ConditionSet conditionSet5 = conditionSetBuilder.build();
        Values<String> values5 = conditionalValues.lookup(conditionSet5);
        Assertions.assertThat(values5).isNotNull();
        Assertions.assertThat(values5.isEmpty()).isFalse();
        Assertions.assertThat(values5.getAllValues()).containsExactly("field1");

        conditionSetBuilder.addCondition("isViewer", true);
        conditionSetBuilder.addCondition("isEditor", true);
        ConditionSet conditionSet6 = conditionSetBuilder.build();
        Values<String> values6 = conditionalValues.lookup(conditionSet6);
        Assertions.assertThat(values6).isNotNull();
        Assertions.assertThat(values6.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupValueSetsNullConditionsTest() {
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
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);

        Values<String> values = conditionalValues.lookup(null);
        Assertions.assertThat(values).isNotNull();
        Assertions.assertThat(values.isEmpty()).isTrue();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupValueSetsWithActionTest() {
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
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        ActionImpl action1 = new ActionImpl();
        conditionalValues.lookup(conditionSet1, action1);
        Assertions.assertThat(action1._values).isNotNull();
        Assertions.assertThat(action1._values).containsExactly("proc_val1");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        ActionImpl action2 = new ActionImpl();
        conditionalValues.lookup(conditionSet2, action2);
        Assertions.assertThat(action2._values).isNotNull();
        Assertions.assertThat(action2._values).containsExactly("proc_val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        ActionImpl action3 = new ActionImpl();
        conditionalValues.lookup(conditionSet3, action3);
        Assertions.assertThat(action3._values).isNotNull();
        Assertions.assertThat(action3._values).containsExactly("proc_val1", "proc_val2");
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
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSet1, valueSet2, valueSet3);
        Assertions.assertThat(conditionalValues).toStringContains("cond1=[val1]");
        Assertions.assertThat(conditionalValues).toStringContains("cond2=[val2]");
        Assertions.assertThat(conditionalValues).toStringContains("cond3=[val3]");
        Assertions.assertThat(conditionalValues).toStringContains("cond4=[val4]");
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class ActionImpl implements Action<String> {

        private final Set<String> _values;

        ActionImpl() {
            super();
            _values = new HashSet<>();
        }

        @Override
        public void perform(final String value) {
            _values.add("proc_" + value);
        }

    }

}
