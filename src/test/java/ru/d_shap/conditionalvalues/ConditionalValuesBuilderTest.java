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
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.util.ReflectionHelper;
import ru.d_shap.conditionalvalues.data.ConditionNamePredicate;
import ru.d_shap.conditionalvalues.data.StringLengthComparator;
import ru.d_shap.conditionalvalues.misc.NaturalOrderComparator;
import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringEqualsIgnoreCasePredicate;

/**
 * Tests for {@link ConditionalValuesBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValuesBuilderTest {

    /**
     * Test class constructor.
     */
    public ConditionalValuesBuilderTest() {
        super();
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void newInstanceTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder1 = ConditionalValuesBuilder.newInstance();
        Assertions.assertThat(conditionalValuesBuilder1).isNotNull();

        ConditionalValuesBuilder<String> conditionalValuesBuilder2 = ConditionalValuesBuilder.newInstance();
        Assertions.assertThat(conditionalValuesBuilder2).isNotNull();

        Assertions.assertThat(conditionalValuesBuilder1).isNotSameAs(conditionalValuesBuilder2);
        Assertions.assertThat(conditionalValuesBuilder2).isNotSameAs(conditionalValuesBuilder1);
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setPredicateTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder = conditionalValuesBuilder.setPredicate(new EqualsPredicate());
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");

        conditionalValuesBuilder = conditionalValuesBuilder.setPredicate(new StringEqualsIgnoreCasePredicate());
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly("value2");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");

        conditionalValuesBuilder = conditionalValuesBuilder.setPredicate(new ConditionNamePredicate());
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("a", "a");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("b", "c");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("a", "a").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("b", "c").build()).getValues()).containsExactly();

        conditionalValuesBuilder = conditionalValuesBuilder.setPredicate(null);
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues4 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setEqualsPredicateTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder = conditionalValuesBuilder.setEqualsPredicate();
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");

        conditionalValuesBuilder = conditionalValuesBuilder.setPredicate(new ConditionNamePredicate());
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        conditionalValuesBuilder = conditionalValuesBuilder.setEqualsPredicate();
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setEqualsIgnoreCasePredicateTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder = conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly("value2");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");

        conditionalValuesBuilder = conditionalValuesBuilder.setPredicate(new ConditionNamePredicate());
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        conditionalValuesBuilder = conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        Assertions.assertThat(conditionalValuesBuilder, "_predicate").isNotNull();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly("value2");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setComparatorTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder = conditionalValuesBuilder.setComparator(new NaturalOrderComparator<String>());
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactlyInOrder("a", "bbb", "cc", "dddd");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("a", "bbb");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("cc", "dddd");

        conditionalValuesBuilder = conditionalValuesBuilder.setComparator(Collections.reverseOrder(new NaturalOrderComparator<String>()));
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactlyInOrder("dddd", "cc", "bbb", "a");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("bbb", "a");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("dddd", "cc");

        conditionalValuesBuilder = conditionalValuesBuilder.setComparator(new StringLengthComparator());
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactlyInOrder("a", "cc", "bbb", "dddd");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("a", "bbb");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("cc", "dddd");

        conditionalValuesBuilder = conditionalValuesBuilder.setComparator(null);
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues4 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues4.getAllValues()).containsExactly("a", "bbb", "cc", "dddd");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("a", "bbb");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("cc", "dddd");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setNaturalOrderComparatorTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder = conditionalValuesBuilder.setNaturalOrderComparator();
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactlyInOrder("a", "bbb", "cc", "dddd");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("a", "bbb");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("cc", "dddd");

        conditionalValuesBuilder = conditionalValuesBuilder.setComparator(new StringLengthComparator());
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        conditionalValuesBuilder = conditionalValuesBuilder.setNaturalOrderComparator();
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactlyInOrder("a", "bbb", "cc", "dddd");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("a", "bbb");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("cc", "dddd");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void setNaturalOrderComparatorWithNullsTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        conditionalValuesBuilder.setNaturalOrderComparator();
        Comparator<String> comparator = (Comparator<String>) ReflectionHelper.getFieldValue(conditionalValuesBuilder, "_comparator");
        List<String> values = Arrays.asList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values, comparator);
        Assertions.assertThat(values).containsExactlyInOrder("a", "a", "b", "c", "c", "d", "d", null, null, null);
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setReverseOrderComparatorTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder = conditionalValuesBuilder.setReverseOrderComparator();
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactlyInOrder("dddd", "cc", "bbb", "a");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("bbb", "a");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("dddd", "cc");

        conditionalValuesBuilder = conditionalValuesBuilder.setComparator(new StringLengthComparator());
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        conditionalValuesBuilder = conditionalValuesBuilder.setReverseOrderComparator();
        Assertions.assertThat(conditionalValuesBuilder, "_comparator").isNotNull();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactlyInOrder("dddd", "cc", "bbb", "a");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("bbb", "a");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("dddd", "cc");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void setReverseOrderComparatorWithNullsTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        conditionalValuesBuilder.setReverseOrderComparator();
        Comparator<String> comparator = (Comparator<String>) ReflectionHelper.getFieldValue(conditionalValuesBuilder, "_comparator");
        List<String> values = Arrays.asList(null, "c", "b", "a", "a", null, "d", "d", "c", null);
        Collections.sort(values, comparator);
        Assertions.assertThat(values).containsExactlyInOrder("d", "d", "c", "c", "b", "a", "a", null, null, null);
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void addValueSetTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("value1", "value2");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond", "val").build()).getValues()).containsExactly("value1", "value2");

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(null);
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("value1", "value2");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond", "val").build()).getValues()).containsExactly("value1", "value2");

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactly("value1", "value1", "value2", "value2");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond", "val").build()).getValues()).containsExactly("value1", "value1", "value2", "value2");

        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("value3");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues4 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues4.getAllValues()).containsExactly("value1", "value2", "value3");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond", "val").build()).getValues()).containsExactly("value1", "value2", "value3");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void clearTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond", "vAl");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        conditionalValuesBuilder = conditionalValuesBuilder.clear();
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond", "vAl").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues1, "_predicate").isInstanceOf(EqualsPredicate.class);
        Assertions.assertThat(conditionalValues1, "_comparator").isNull();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        conditionalValuesBuilder = conditionalValuesBuilder.clear();
        valueSetBuilder.addCondition("cond", "vAl");
        valueSetBuilder.addValue("value1");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("value1", "value2");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond", "vAl").build()).getValues()).containsExactly("value1", "value2");
        Assertions.assertThat(conditionalValues2, "_predicate").isInstanceOf(EqualsPredicate.class);
        Assertions.assertThat(conditionalValues2, "_comparator").isNull();
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void buildTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond1", "vAl1");
        valueSetBuilder.addValue("value11");
        valueSetBuilder.addValue("value12");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactlyInOrder("value11", "value12");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("value11", "value12");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond2", "vAl2");
        valueSetBuilder.addValue("value21");
        valueSetBuilder.addValue("value22");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond3", "vAl3");
        valueSetBuilder.addValue("value31");
        valueSetBuilder.addValue("value32");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactlyInOrder("value31", "value32");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder("value31", "value32");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();

        ConditionalValues<String> conditionalValues4 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues4.getAllValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void buildClearTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond1", "vAl1");
        valueSetBuilder.addValue("value11");
        valueSetBuilder.addValue("value12");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build(true);
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactlyInOrder("value11", "value12");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder("value11", "value12");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond2", "vAl2");
        valueSetBuilder.addValue("value21");
        valueSetBuilder.addValue("value22");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build(false);
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();

        valueSetBuilder.addCondition("cond3", "vAl3");
        valueSetBuilder.addValue("value31");
        valueSetBuilder.addValue("value32");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build(false);
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactlyInOrder("value21", "value22", "value31", "value32");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder("value31", "value32");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();

        valueSetBuilder.addCondition("cond4", "vAl4");
        valueSetBuilder.addValue("value41");
        valueSetBuilder.addValue("value42");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues4 = conditionalValuesBuilder.build(true);
        Assertions.assertThat(conditionalValues4.getAllValues()).containsExactlyInOrder("value21", "value22", "value31", "value32", "value41", "value42");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder("value31", "value32");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder("value41", "value42");
        Assertions.assertThat(conditionalValues4.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();

        conditionalValuesBuilder.setStringEqualsIgnoreCasePredicate();
        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond2", "vAl2");
        valueSetBuilder.addValue("value21");
        valueSetBuilder.addValue("value22");
        conditionalValuesBuilder = conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues5 = conditionalValuesBuilder.build(false);
        Assertions.assertThat(conditionalValues5.getAllValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues5.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues5.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues5.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues5.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues5.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();

        ConditionalValues<String> conditionalValues6 = conditionalValuesBuilder.build(true);
        Assertions.assertThat(conditionalValues6.getAllValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues6.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues6.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder("value21", "value22");
        Assertions.assertThat(conditionalValues6.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues6.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues6.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();

        ConditionalValues<String> conditionalValues7 = conditionalValuesBuilder.build(false);
        Assertions.assertThat(conditionalValues7.getAllValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues7.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues7.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues7.lookup(conditionSetBuilder.addCondition("cond3", "val3").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues7.lookup(conditionSetBuilder.addCondition("cond4", "val4").build()).getValues()).containsExactlyInOrder();
        Assertions.assertThat(conditionalValues7.lookup(conditionSetBuilder.addCondition("cond5", "val5").build()).getValues()).containsExactlyInOrder();
    }

}
