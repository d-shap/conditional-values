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

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.misc.ComparableComparator;

/**
 * Tests for {@link Values}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValuesTest {

    /**
     * Test class constructor.
     */
    public ValuesTest() {
        super();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void createSetTest() {
        Assertions.assertThat(Values.createSet(null)).isInstanceOf(HashSet.class);
        Assertions.assertThat(Values.createSet(new ComparableComparator<String>())).isInstanceOf(TreeSet.class);
        Assertions.assertThat(Values.createSet(Collections.reverseOrder())).isInstanceOf(TreeSet.class);
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void isEmptyTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.isEmpty()).isTrue();

        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.isEmpty()).isTrue();

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(null, valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.isEmpty()).isTrue();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets4 = new HashSet<>();
        valueSets4.add(null);
        valueSets4.add(valueSet41);
        valueSets4.add(valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, new HashSet<String>());
        Assertions.assertThat(values4.isEmpty()).isTrue();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets5 = new HashSet<>();
        valueSets5.add(valueSet51);
        valueSets5.add(valueSet52);
        Values<String> values5 = new Values<>(null, valueSets5, new HashSet<String>());
        Assertions.assertThat(values5.isEmpty()).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets6 = new HashSet<>();
        valueSets6.add(null);
        valueSets6.add(valueSet61);
        valueSets6.add(valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, new HashSet<String>());
        Assertions.assertThat(values6.isEmpty()).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets7 = new HashSet<>();
        valueSets7.add(valueSet71);
        valueSets7.add(valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, new HashSet<String>());
        Assertions.assertThat(values7.isEmpty()).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void containsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.contains("val1")).isFalse();
        Assertions.assertThat(values1.contains("val2")).isFalse();
        Assertions.assertThat(values1.contains("val3")).isFalse();
        Assertions.assertThat(values1.contains("val4")).isFalse();
        Assertions.assertThat(values1.contains("val5")).isFalse();

        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.contains("val1")).isFalse();
        Assertions.assertThat(values2.contains("val2")).isFalse();
        Assertions.assertThat(values2.contains("val3")).isFalse();
        Assertions.assertThat(values2.contains("val4")).isFalse();
        Assertions.assertThat(values2.contains("val5")).isFalse();

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(null, valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.contains("val1")).isFalse();
        Assertions.assertThat(values3.contains("val2")).isFalse();
        Assertions.assertThat(values3.contains("val3")).isFalse();
        Assertions.assertThat(values3.contains("val4")).isFalse();
        Assertions.assertThat(values3.contains("val5")).isFalse();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets4 = new HashSet<>();
        valueSets4.add(null);
        valueSets4.add(valueSet41);
        valueSets4.add(valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, new HashSet<String>());
        Assertions.assertThat(values4.contains("val1")).isFalse();
        Assertions.assertThat(values4.contains("val2")).isFalse();
        Assertions.assertThat(values4.contains("val3")).isFalse();
        Assertions.assertThat(values4.contains("val4")).isFalse();
        Assertions.assertThat(values4.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets5 = new HashSet<>();
        valueSets5.add(valueSet51);
        valueSets5.add(valueSet52);
        Values<String> values5 = new Values<>(null, valueSets5, new HashSet<String>());
        Assertions.assertThat(values5.contains("val1")).isTrue();
        Assertions.assertThat(values5.contains("val2")).isTrue();
        Assertions.assertThat(values5.contains("val3")).isFalse();
        Assertions.assertThat(values5.contains("val4")).isFalse();
        Assertions.assertThat(values5.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets6 = new HashSet<>();
        valueSets6.add(null);
        valueSets6.add(valueSet61);
        valueSets6.add(valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, new HashSet<String>());
        Assertions.assertThat(values6.contains("val1")).isTrue();
        Assertions.assertThat(values6.contains("val2")).isTrue();
        Assertions.assertThat(values6.contains("val3")).isFalse();
        Assertions.assertThat(values6.contains("val4")).isFalse();
        Assertions.assertThat(values6.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets7 = new HashSet<>();
        valueSets7.add(valueSet71);
        valueSets7.add(valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, new HashSet<String>());
        Assertions.assertThat(values7.contains("val1")).isTrue();
        Assertions.assertThat(values7.contains("val2")).isTrue();
        Assertions.assertThat(values7.contains("val3")).isTrue();
        Assertions.assertThat(values7.contains("val4")).isTrue();
        Assertions.assertThat(values7.contains("val5")).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void allValuesContainsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<String> allValues1 = null;
        Values<String> values1 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues1);
        Assertions.assertThat(values1.allValuesContains("val1")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val2")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val5")).isFalse();

        Set<String> allValues2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues2);
        Assertions.assertThat(values2.allValuesContains("val1")).isFalse();
        Assertions.assertThat(values2.allValuesContains("val2")).isFalse();
        Assertions.assertThat(values2.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values2.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values2.allValuesContains("val5")).isFalse();

        Set<String> allValues3 = new HashSet<>();
        allValues3.add(null);
        Values<String> values3 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues3);
        Assertions.assertThat(values3.allValuesContains("val1")).isFalse();
        Assertions.assertThat(values3.allValuesContains("val2")).isFalse();
        Assertions.assertThat(values3.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values3.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values3.allValuesContains("val5")).isFalse();

        Set<String> allValues4 = new HashSet<>();
        allValues4.add("val1");
        allValues4.add("val2");
        Values<String> values4 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues4);
        Assertions.assertThat(values4.allValuesContains("val1")).isTrue();
        Assertions.assertThat(values4.allValuesContains("val2")).isTrue();
        Assertions.assertThat(values4.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values4.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values4.allValuesContains("val5")).isFalse();

        Set<String> allValues5 = new HashSet<>();
        allValues5.add(null);
        allValues5.add("val1");
        allValues5.add("val2");
        Values<String> values5 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues5);
        Assertions.assertThat(values5.allValuesContains("val1")).isTrue();
        Assertions.assertThat(values5.allValuesContains("val2")).isTrue();
        Assertions.assertThat(values5.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values5.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values5.allValuesContains("val5")).isFalse();

        Set<String> allValues6 = new HashSet<>();
        allValues6.add("val1");
        allValues6.add("val2");
        allValues6.add("val3");
        allValues6.add("val4");
        Values<String> values6 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues6);
        Assertions.assertThat(values6.allValuesContains("val1")).isTrue();
        Assertions.assertThat(values6.allValuesContains("val2")).isTrue();
        Assertions.assertThat(values6.allValuesContains("val3")).isTrue();
        Assertions.assertThat(values6.allValuesContains("val4")).isTrue();
        Assertions.assertThat(values6.allValuesContains("val5")).isFalse();

        Set<String> allValues7 = new HashSet<>();
        allValues7.add(null);
        allValues7.add("val1");
        allValues7.add("val2");
        allValues7.add("val3");
        allValues7.add("val4");
        Values<String> values7 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues7);
        Assertions.assertThat(values7.allValuesContains("val1")).isTrue();
        Assertions.assertThat(values7.allValuesContains("val2")).isTrue();
        Assertions.assertThat(values7.allValuesContains("val3")).isTrue();
        Assertions.assertThat(values7.allValuesContains("val4")).isTrue();
        Assertions.assertThat(values7.allValuesContains("val5")).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.getValues()).containsExactly();

        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.getValues()).containsExactly();

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(null, valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.getValues()).containsExactly();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets4 = new HashSet<>();
        valueSets4.add(null);
        valueSets4.add(valueSet41);
        valueSets4.add(valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, new HashSet<String>());
        Assertions.assertThat(values4.getValues()).containsExactly();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets5 = new HashSet<>();
        valueSets5.add(valueSet51);
        valueSets5.add(valueSet52);
        Values<String> values5 = new Values<>(null, valueSets5, new HashSet<String>());
        Assertions.assertThat(values5.getValues()).containsExactly("val1", "val2");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets6 = new HashSet<>();
        valueSets6.add(null);
        valueSets6.add(valueSet61);
        valueSets6.add(valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, new HashSet<String>());
        Assertions.assertThat(values6.getValues()).containsExactly("val1", "val2");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets7 = new HashSet<>();
        valueSets7.add(valueSet71);
        valueSets7.add(valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, new HashSet<String>());
        Assertions.assertThat(values7.getValues()).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet81 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet82 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets8 = new HashSet<>();
        valueSets8.add(valueSet81);
        valueSets8.add(valueSet82);
        Values<String> values8 = new Values<>(new ComparableComparator<String>(), valueSets8, new HashSet<String>());
        Assertions.assertThat(values8.getValues()).containsExactlyInOrder("val1", "val2", "val3", "val4");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(null, valueSets, new HashSet<String>());
        Assertions.assertThat(values.getValues()).hasSize(4);
        values.getValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableEmptyFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        ValueSet<String> valueSet1 = valueSetBuilder.build();
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(null, valueSets, new HashSet<String>());
        Assertions.assertThat(values.getValues()).hasSize(0);
        values.getValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<String> allValues1 = null;
        Values<String> values1 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues1);
        Assertions.assertThat(values1.getAllValues()).containsExactly();

        Set<String> allValues2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues2);
        Assertions.assertThat(values2.getAllValues()).containsExactly();

        Set<String> allValues3 = new HashSet<>();
        allValues3.add(null);
        Values<String> values3 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues3);
        Assertions.assertThat(values3.getAllValues()).containsExactly();

        Set<String> allValues4 = new HashSet<>();
        allValues4.add("val1");
        allValues4.add("val2");
        Values<String> values4 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues4);
        Assertions.assertThat(values4.getAllValues()).containsExactly("val1", "val2");

        Set<String> allValues5 = new HashSet<>();
        allValues5.add(null);
        allValues5.add("val1");
        allValues5.add("val2");
        Values<String> values5 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues5);
        Assertions.assertThat(values5.getAllValues()).containsExactly("val1", "val2");

        Set<String> allValues6 = new HashSet<>();
        allValues6.add("val1");
        allValues6.add("val2");
        allValues6.add("val3");
        allValues6.add("val4");
        Values<String> values6 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues6);
        Assertions.assertThat(values6.getAllValues()).containsExactly("val1", "val2", "val3", "val4");

        Set<String> allValues7 = new HashSet<>();
        allValues7.add(null);
        allValues7.add("val1");
        allValues7.add("val2");
        allValues7.add("val3");
        allValues7.add("val4");
        Values<String> values7 = new Values<>(null, new HashSet<ValueSet<String>>(), allValues7);
        Assertions.assertThat(values7.getAllValues()).containsExactly("val1", "val2", "val3", "val4");

        Set<String> allValues8 = new HashSet<>();
        allValues8.add(null);
        allValues8.add("val1");
        allValues8.add("val2");
        allValues8.add("val3");
        allValues8.add("val4");
        Values<String> values8 = new Values<>(new ComparableComparator<String>(), new HashSet<ValueSet<String>>(), allValues8);
        Assertions.assertThat(values8.getAllValues()).containsExactlyInOrder("val1", "val2", "val3", "val4");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<String> allValues = new HashSet<>();
        allValues.add("val1");
        allValues.add("val2");
        Values<String> values = new Values<>(null, new HashSet<ValueSet<String>>(), allValues);
        Assertions.assertThat(values.getAllValues()).hasSize(2);
        values.getAllValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableEmptyFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<String> allValues = new HashSet<>();
        Values<String> values = new Values<>(null, new HashSet<ValueSet<String>>(), allValues);
        Assertions.assertThat(values.getAllValues()).hasSize(0);
        values.getAllValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getIdsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.getIds()).containsExactlyInOrder();

        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.getIds()).containsExactlyInOrder();

        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet3);
        Values<String> values3 = new Values<>(null, valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.getIds()).containsExactlyInOrder();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets4 = new HashSet<>();
        valueSets4.add(valueSet41);
        valueSets4.add(valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, new HashSet<String>());
        Assertions.assertThat(values4.getIds()).containsExactlyInOrder();

        valueSetBuilder.setId("id");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets5 = new HashSet<>();
        valueSets5.add(valueSet5);
        Values<String> values5 = new Values<>(null, valueSets5, new HashSet<String>());
        Assertions.assertThat(values5.getIds()).containsExactlyInOrder("id");

        valueSetBuilder.setId("id1");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.setId("id2");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets6 = new HashSet<>();
        valueSets6.add(valueSet61);
        valueSets6.add(valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, new HashSet<String>());
        Assertions.assertThat(values6.getIds()).containsExactlyInOrder("id1", "id2");

        valueSetBuilder.setId("id");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets7 = new HashSet<>();
        valueSets7.add(valueSet71);
        valueSets7.add(valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, new HashSet<String>());
        Assertions.assertThat(values7.getIds()).containsExactlyInOrder("id");

        valueSetBuilder.setId("id1");
        ValueSet<String> valueSet81 = valueSetBuilder.build();
        ValueSet<String> valueSet82 = valueSetBuilder.build();
        valueSetBuilder.setId("id2");
        ValueSet<String> valueSet83 = valueSetBuilder.build();
        valueSetBuilder.setId("id1");
        ValueSet<String> valueSet84 = valueSetBuilder.build();
        ValueSet<String> valueSet85 = valueSetBuilder.build();
        valueSetBuilder.setId("id3");
        ValueSet<String> valueSet86 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets8 = new HashSet<>();
        valueSets8.add(valueSet81);
        valueSets8.add(valueSet82);
        valueSets8.add(valueSet83);
        valueSets8.add(valueSet84);
        valueSets8.add(valueSet85);
        valueSets8.add(valueSet86);
        Values<String> values8 = new Values<>(null, valueSets8, new HashSet<String>());
        Assertions.assertThat(values8.getIds()).containsExactlyInOrder("id1", "id2", "id3");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getIdsUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.setId("id1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.setId("id2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.setId("id3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        valueSets.add(valueSet3);
        Values<String> values = new Values<>(null, valueSets, new HashSet<String>());
        Assertions.assertThat(values.getIds()).hasSize(3);
        values.getIds().add("id");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getIdsUnmodifiableEmptyFailTest() {
        Set<ValueSet<String>> valueSets = new HashSet<>();
        Values<String> values = new Values<>(null, valueSets, new HashSet<String>());
        Assertions.assertThat(values.getIds()).hasSize(0);
        values.getIds().add("id");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void performActionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet11 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets1 = new HashSet<>();
        valueSets1.add(valueSet11);
        valueSets1.add(valueSet12);
        Values<String> values1 = new Values<>(null, valueSets1, new HashSet<String>());
        ActionImpl action1 = null;
        values1.performAction(action1);

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet22 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        valueSets2.add(valueSet21);
        valueSets2.add(valueSet22);
        Values<String> values2 = new Values<>(null, valueSets2, new HashSet<String>());
        ActionImpl action2 = new ActionImpl();
        values2.performAction(action2);
        Assertions.assertThat(action2._values).containsExactly("proc_val1", "proc_val2", "proc_val3", "proc_val4", "proc_val5", "proc_val6");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(new ComparableComparator<String>(), valueSets3, new HashSet<String>());
        ActionImpl action3 = new ActionImpl();
        values3.performAction(action3);
        Assertions.assertThat(action3._values).containsExactlyInOrder("proc_val1", "proc_val2", "proc_val3", "proc_val4", "proc_val5", "proc_val6");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Set<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, new HashSet<String>());
        Assertions.assertThat(values1).hasToString("[]");

        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        Values<String> values2 = new Values<>(null, valueSets2, new HashSet<String>());
        Assertions.assertThat(values2).hasToString("[]");

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets31 = new HashSet<>();
        valueSets31.add(valueSet31);
        Values<String> values31 = new Values<>(null, valueSets31, new HashSet<String>());
        Assertions.assertThat(values31).hasToString("[{}]");

        valueSetBuilder.setId("");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets32 = new HashSet<>();
        valueSets32.add(valueSet32);
        Values<String> values32 = new Values<>(null, valueSets32, new HashSet<String>());
        Assertions.assertThat(values32).hasToString("[={}]");

        valueSetBuilder.setId("id");
        ValueSet<String> valueSet33 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets33 = new HashSet<>();
        valueSets33.add(valueSet33);
        Values<String> values33 = new Values<>(null, valueSets33, new HashSet<String>());
        Assertions.assertThat(values33).hasToString("[id={}]");

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet41 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets41 = new HashSet<>();
        valueSets41.add(valueSet41);
        Values<String> values41 = new Values<>(null, valueSets41, new HashSet<String>());
        Assertions.assertThat(values41).hasToString("[{cond=[val]}]");

        valueSetBuilder.setId("");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets42 = new HashSet<>();
        valueSets42.add(valueSet42);
        Values<String> values42 = new Values<>(null, valueSets42, new HashSet<String>());
        Assertions.assertThat(values42).hasToString("[={cond=[val]}]");

        valueSetBuilder.setId("id");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet43 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets43 = new HashSet<>();
        valueSets43.add(valueSet43);
        Values<String> values43 = new Values<>(null, valueSets43, new HashSet<String>());
        Assertions.assertThat(values43).hasToString("[id={cond=[val]}]");

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets51 = new HashSet<>();
        valueSets51.add(null);
        valueSets51.add(valueSet51);
        Values<String> values51 = new Values<>(null, valueSets51, new HashSet<String>());
        Assertions.assertThat(values51).hasToString("[{cond=[val]}]");

        valueSetBuilder.setId("");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets52 = new HashSet<>();
        valueSets52.add(null);
        valueSets52.add(valueSet52);
        Values<String> values52 = new Values<>(null, valueSets52, new HashSet<String>());
        Assertions.assertThat(values52).hasToString("[={cond=[val]}]");

        valueSetBuilder.setId("id");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet53 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets53 = new HashSet<>();
        valueSets53.add(null);
        valueSets53.add(valueSet53);
        Values<String> values53 = new Values<>(null, valueSets53, new HashSet<String>());
        Assertions.assertThat(values53).hasToString("[id={cond=[val]}]");

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet611 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        ValueSet<String> valueSet612 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets61 = new HashSet<>();
        valueSets61.add(valueSet611);
        valueSets61.add(valueSet612);
        Values<String> values61 = new Values<>(null, valueSets61, new HashSet<String>());
        Assertions.assertThat(values61).toToString().startsWith("[{");
        Assertions.assertThat(values61).toStringContains("cond1=[val1");
        Assertions.assertThat(values61).toStringContains("cond2=[val2]");
        Assertions.assertThat(values61).toStringContains("cond3=[val3");

        valueSetBuilder.setId("");
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet621 = valueSetBuilder.build();
        valueSetBuilder.setId("");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        ValueSet<String> valueSet622 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets62 = new HashSet<>();
        valueSets62.add(valueSet621);
        valueSets62.add(valueSet622);
        Values<String> values62 = new Values<>(null, valueSets62, new HashSet<String>());
        Assertions.assertThat(values62).toToString().startsWith("[={");
        Assertions.assertThat(values62).toStringContains("cond1=[val1");
        Assertions.assertThat(values62).toStringContains("cond2=[val2]");
        Assertions.assertThat(values62).toStringContains("cond3=[val3");

        valueSetBuilder.setId("id1");
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet631 = valueSetBuilder.build();
        valueSetBuilder.setId("id2");
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        ValueSet<String> valueSet632 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets63 = new HashSet<>();
        valueSets63.add(valueSet631);
        valueSets63.add(valueSet632);
        Values<String> values63 = new Values<>(null, valueSets63, new HashSet<String>());
        Assertions.assertThat(values63).toToString().startsWith("[id");
        Assertions.assertThat(values63).toStringContains("cond1=[val1");
        Assertions.assertThat(values63).toStringContains("cond2=[val2]");
        Assertions.assertThat(values63).toStringContains("cond3=[val3");
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
