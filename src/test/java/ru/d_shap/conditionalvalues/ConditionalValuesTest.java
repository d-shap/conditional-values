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

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.misc.ComparableComparator;
import ru.d_shap.conditionalvalues.misc.EqualsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.misc.EqualsPredicate;

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
    public void createConditionalValuesStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("val1", "val2");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesBooleanTest() {
        ValueSetBuilder<Boolean> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<Boolean> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly(true, false);

        ConditionalValues<Boolean> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly(true, false);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesCharTest() {
        ValueSetBuilder<Character> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValue('1');
        ValueSet<Character> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValue('2');
        ValueSet<Character> valueSet2 = valueSetBuilder.build();

        ConditionalValues<Character> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly('1', '2');

        ConditionalValues<Character> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly('1', '2');
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesIntTest() {
        ValueSetBuilder<Integer> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<Integer> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly(1, 2);

        ConditionalValues<Integer> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly(1, 2);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesLongTest() {
        ValueSetBuilder<Long> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<Long> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly(1L, 2L);

        ConditionalValues<Long> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly(1L, 2L);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesFloatTest() {
        ValueSetBuilder<Float> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<Float> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly(1.0f, 2.0f);

        ConditionalValues<Float> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly(1.0f, 2.0f);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesDoubleTest() {
        ValueSetBuilder<Double> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<Double> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly(1.0, 2.0);

        ConditionalValues<Double> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly(1.0, 2.0);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesObjectTest() {
        ValueSetBuilder<StringBuilder> valueSetBuilder = ValueSetBuilder.newInstance();

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

        ConditionalValues<StringBuilder> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");

        ConditionalValues<StringBuilder> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, null, valueSet2, null, null, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createNullConditionalValuesTest() {
        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly();

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets((ValueSet<String>) null));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly();

        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(null, null, createValueSets((ValueSet<String>) null, null));
        Assertions.assertThat(conditionalValues3.getAllConditionNames()).containsExactly();
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void duplicateValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond5", "val51");
        valueSetBuilder.addCondition("cond5", "val52");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond6", "val61");
        valueSetBuilder.addCondition("cond6", "val62");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addCondition("cond7", "val71");
        valueSetBuilder.addCondition("cond7", "val72");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet6 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        valueSetBuilder.addCondition("cond8", "val81");
        valueSetBuilder.addCondition("cond8", "val82");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet7 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2, valueSet3, valueSet4, valueSet5, valueSet6, valueSet7, valueSet1));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6", "cond7", "cond8");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("val1", "val2");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet7, valueSet6, valueSet5, valueSet4, valueSet3, valueSet2, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6", "cond7", "cond8");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void duplicateValueSetEqualsPredicateTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "vAl");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "VaL");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(new EqualsPredicate(), null, createValueSets(valueSet1, valueSet2, valueSet3, valueSet1));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("val1", "val2", "val3");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(new EqualsPredicate(), null, createValueSets(valueSet1, valueSet3, valueSet2, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void duplicateValueSetEqualsIgnoreCasePredicateTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "vAl2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "VaL3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(new EqualsIgnoreCasePredicate(), null, createValueSets(valueSet1, valueSet2, valueSet3, valueSet1));
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly("cond");
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("val1", "val2", "val3");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(new EqualsIgnoreCasePredicate(), null, createValueSets(valueSet1, valueSet3, valueSet2, valueSet1));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond");
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetAllConditionsFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

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

        new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetUniqueConditionFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetFirstValuesContainsSecondValuesFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetSecondValuesContainsFirstValuesFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetEqualsPredicateFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "vAl");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "vAl");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();

        new ConditionalValues<>(new EqualsPredicate(), null, createValueSets(valueSet1, valueSet2, valueSet3, valueSet1));
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = DuplicateValueSetException.class)
    public void duplicateValueSetEqualsIgnoreCasePredicateFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "vAl");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond", "VaL");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();

        new ConditionalValues<>(new EqualsIgnoreCasePredicate(), null, createValueSets(valueSet1, valueSet2, valueSet3, valueSet1));
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesPredicateTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "vAl1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "vAl2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("val1", "val2");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "vAl1").build()).getValues()).containsExactly("val1");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "vAl2").build()).getValues()).containsExactly("val2");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(new EqualsPredicate(), null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("val1", "val2");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "vAl1").build()).getValues()).containsExactly("val1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "vAl2").build()).getValues()).containsExactly("val2");

        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(new EqualsIgnoreCasePredicate(), null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactly("val1", "val2");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("val1");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond1", "vAl1").build()).getValues()).containsExactly("val1");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("val2");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond2", "vAl2").build()).getValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void createConditionalValuesComparatorTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue("val6");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, new ComparableComparator<String>(), createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactlyInOrder("val1", "val2", "val3", "val4", "val5", "val6");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("val1", "val3", "val5");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("val2", "val4", "val6");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, Collections.reverseOrder(new ComparableComparator<String>()), createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactlyInOrder("val6", "val5", "val4", "val3", "val2", "val1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("val5", "val3", "val1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("val6", "val4", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllConditionNamesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues1.getAllConditionNames()).containsExactly();

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet2));
        Assertions.assertThat(conditionalValues2.getAllConditionNames()).containsExactly("cond");

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(null, null, createValueSets(valueSet31, valueSet32));
        Assertions.assertThat(conditionalValues3.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionNamesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet));
        Assertions.assertThat(conditionalValues.getAllConditionNames()).hasSize(1);

        conditionalValues.getAllConditionNames().add("cond");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionNamesUnmodifiableEmptyFailTest() {
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues.getAllConditionNames()).hasSize(0);

        conditionalValues.getAllConditionNames().add("cond");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllConditionValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues1.getAllConditionValues("cond1")).containsExactly();
        Assertions.assertThat(conditionalValues1.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(conditionalValues1.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(conditionalValues1.getAllConditionValues("cond4")).containsExactly();
        Assertions.assertThat(conditionalValues1.getAllConditionValues("cond5")).containsExactly();

        valueSetBuilder.addCondition("cond1", "val1");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet2));
        Assertions.assertThat(conditionalValues2.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(conditionalValues2.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(conditionalValues2.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(conditionalValues2.getAllConditionValues("cond4")).containsExactly();
        Assertions.assertThat(conditionalValues2.getAllConditionValues("cond5")).containsExactly();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(null, null, createValueSets(valueSet31, valueSet32));
        Assertions.assertThat(conditionalValues3.getAllConditionValues("cond1")).containsExactly("val11", "val12");
        Assertions.assertThat(conditionalValues3.getAllConditionValues("cond2")).containsExactly("val21", "val22");
        Assertions.assertThat(conditionalValues3.getAllConditionValues("cond3")).containsExactly("val31", "val32");
        Assertions.assertThat(conditionalValues3.getAllConditionValues("cond4")).containsExactly("val41", "val42");
        Assertions.assertThat(conditionalValues3.getAllConditionValues("cond5")).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet));
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond")).hasSize(1);

        conditionalValues.getAllConditionValues("cond").add("val");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionValuesUnmodifiableEmptyFailTest() {
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues.getAllConditionValues("cond")).hasSize(0);

        conditionalValues.getAllConditionValues("cond").add("val");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllValueSetUniqueConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val21");
        valueSetBuilder.addCondition("cond2", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val41");
        valueSetBuilder.addCondition("cond4", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues1.getAllValueSetUniqueConditions()).hasSize(0);

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1));
        Assertions.assertThat(conditionalValues2.getAllValueSetUniqueConditions()).hasSize(4);

        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(null, null, createValueSets(valueSet2));
        Assertions.assertThat(conditionalValues3.getAllValueSetUniqueConditions()).hasSize(2);

        ConditionalValues<String> conditionalValues4 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues4.getAllValueSetUniqueConditions()).hasSize(6);
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValueSetUniqueConditionsUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet));
        Assertions.assertThat(conditionalValues.getAllValueSetUniqueConditions()).hasSize(1);

        conditionalValues.getAllValueSetUniqueConditions().add(new ValueSetUniqueCondition());
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValueSetUniqueConditionsUnmodifiableEmptyFailTest() {
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues.getAllValueSetUniqueConditions()).hasSize(0);

        conditionalValues.getAllValueSetUniqueConditions().add(new ValueSetUniqueCondition());
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void getAllValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue("val6");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly();

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1));
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("val1", "val3", "val5");

        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(null, null, createValueSets(valueSet2));
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactly("val2", "val4", "val6");

        ConditionalValues<String> conditionalValues4 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));
        Assertions.assertThat(conditionalValues4.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValue("val");
        ValueSet<String> valueSet = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet));
        Assertions.assertThat(conditionalValues.getAllValues()).hasSize(1);

        conditionalValues.getAllValues().add("value");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableEmptyFailTest() {
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues.getAllValues()).hasSize(0);

        conditionalValues.getAllValues().add("value");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetSimpleConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond2", "val2");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsSimpleConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val2");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val1");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsSimpleConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        Values<String> values = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values.getValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetAndConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        Values<String> values11 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values11.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        Values<String> values12 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values12.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond4", "val4");
        Values<String> values13 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values13.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        Values<String> values21 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values21.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond1", "val1");
        Values<String> values22 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values22.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond2", "val2");
        Values<String> values23 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values23.getValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsAndConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond3", "val3");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond4", "val4");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond4", "val4");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsAndConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        Values<String> values = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values.getValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetOrConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val2");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond2", "val3");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond2", "val4");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsOrConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val3");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val4");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val1");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val2");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsOrConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val3");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val4");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val2");
        conditionSetBuilder.addCondition("cond2", "val3");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val2");
        conditionSetBuilder.addCondition("cond2", "val4");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupSingleValueSetAndOrConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

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
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        Values<String> values11 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values11.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values12 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values12.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values13 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values13.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values14 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values14.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values15 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values15.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        Values<String> values21 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values21.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values22 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values22.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values23 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values23.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values24 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values24.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values25 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values25.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        Values<String> values31 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values31.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values32 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values32.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values33 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values33.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values34 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values34.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values35 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values35.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        Values<String> values41 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values41.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values42 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values42.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values43 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values43.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values44 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values44.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values45 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values45.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values51 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values51.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val11");
        Values<String> values52 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values52.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val12");
        Values<String> values53 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values53.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val21");
        Values<String> values54 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values54.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val22");
        Values<String> values55 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values55.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values61 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values61.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val11");
        Values<String> values62 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values62.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val12");
        Values<String> values63 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values63.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val21");
        Values<String> values64 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values64.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val22");
        Values<String> values65 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values65.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values71 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values71.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val11");
        Values<String> values72 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values72.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond1", "val12");
        Values<String> values73 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values73.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val21");
        Values<String> values74 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values74.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        conditionSetBuilder.addCondition("cond2", "val22");
        Values<String> values75 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values75.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values81 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values81.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val11");
        Values<String> values82 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values82.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond1", "val12");
        Values<String> values83 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values83.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val21");
        Values<String> values84 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values84.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        conditionSetBuilder.addCondition("cond2", "val22");
        Values<String> values85 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values85.getValues()).containsExactly("val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupNoValueSetsAndOrConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

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
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values11 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values11.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values12 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values12.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values13 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values13.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values14 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values14.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values21 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values21.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values22 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values22.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values23 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values23.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values24 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values24.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values31 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values31.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values32 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values32.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values33 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values33.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values34 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values34.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        Values<String> values41 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values41.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        Values<String> values42 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values42.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values43 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values43.getValues()).containsExactly();

        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values44 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values44.getValues()).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupMultipleValueSetsAndOrConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

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
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values1111 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1111.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values1112 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1112.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values1121 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1121.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values1122 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1122.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values1211 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1211.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values1212 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1212.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values1221 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1221.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values1222 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1222.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values2111 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2111.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values2112 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2112.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values2121 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2121.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values2122 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2122.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values2211 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2211.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values2212 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2212.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        Values<String> values2221 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2221.getValues()).containsExactly("val1", "val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val22");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val42");
        Values<String> values2222 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2222.getValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupLessSpecificValueSetsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

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
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2, valueSet3));

        conditionSetBuilder.addCondition("cond1", "val1");
        Values<String> values1 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values1.getValues()).containsExactly("val1");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        Values<String> values2 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values2.getValues()).containsExactly("val2");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        Values<String> values3 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values3.getValues()).containsExactly("val3");

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3");
        conditionSetBuilder.addCondition("cond4", "val4");
        Values<String> values4 = conditionalValues.lookup(conditionSetBuilder.build());
        Assertions.assertThat(values4.getValues()).containsExactly("val3");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupValueSetsNullConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

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
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2, valueSet3));

        Values<String> values = conditionalValues.lookup(null);
        Assertions.assertThat(values.getValues()).containsExactly();
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void lookupValueSetsWithActionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

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
        ConditionalValues<String> conditionalValues = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2));

        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        ActionImpl action1 = new ActionImpl();
        conditionalValues.lookup(conditionSetBuilder.build(), action1);
        Assertions.assertThat(action1._values).containsExactly("proc_val1");

        conditionSetBuilder.addCondition("cond3", "val31");
        conditionSetBuilder.addCondition("cond4", "val42");
        ActionImpl action2 = new ActionImpl();
        conditionalValues.lookup(conditionSetBuilder.build(), action2);
        Assertions.assertThat(action2._values).containsExactly("proc_val2");

        conditionSetBuilder.addCondition("cond1", "val12");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val32");
        conditionSetBuilder.addCondition("cond4", "val41");
        ActionImpl action3 = new ActionImpl();
        conditionalValues.lookup(conditionSetBuilder.build(), action3);
        Assertions.assertThat(action3._values).containsExactly("proc_val1", "proc_val2");
    }

    /**
     * {@link ConditionalValues} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.setId("id2");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();

        ConditionalValues<String> conditionalValues1 = new ConditionalValues<>(null, null, null);
        Assertions.assertThat(conditionalValues1).hasToString("[]");

        ConditionalValues<String> conditionalValues2 = new ConditionalValues<>(null, null, createValueSets(valueSet1));
        Assertions.assertThat(conditionalValues2).hasToString("[{cond1=[val1]}]");

        ConditionalValues<String> conditionalValues3 = new ConditionalValues<>(null, null, createValueSets(valueSet2));
        Assertions.assertThat(conditionalValues3).hasToString("[id2={cond2=[val2]}]");

        ConditionalValues<String> conditionalValues4 = new ConditionalValues<>(null, null, createValueSets(valueSet1, valueSet2, valueSet3));
        Assertions.assertThat(conditionalValues4).toStringContains("cond1=[val1]");
        Assertions.assertThat(conditionalValues4).toStringContains("cond2=[val2]");
        Assertions.assertThat(conditionalValues4).toStringContains("cond3=[val3]");
    }

    @SafeVarargs
    private static <T> List<ValueSet<T>> createValueSets(final ValueSet<T>... valueSets) {
        return Arrays.asList(valueSets);
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
            _values = new LinkedHashSet<>();
        }

        @Override
        public void perform(final String value) {
            _values.add("proc_" + value);
        }

    }

}
