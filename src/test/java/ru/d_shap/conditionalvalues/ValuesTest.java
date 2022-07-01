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

import java.util.List;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.util.DataHelper;
import ru.d_shap.conditionalvalues.data.ConcatStringAction;
import ru.d_shap.conditionalvalues.misc.NaturalOrderComparator;

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
    public void getIdsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, DataHelper.<String>createArrayList());
        Assertions.assertThat(values1.getIds()).containsExactlyInOrder();

        List<ValueSet<String>> valueSets2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, valueSets2, DataHelper.<String>createArrayList());
        Assertions.assertThat(values2.getIds()).containsExactlyInOrder();

        ValueSet<String> valueSet3 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets3 = DataHelper.createArrayList(valueSet3);
        Values<String> values3 = new Values<>(null, valueSets3, DataHelper.<String>createArrayList());
        Assertions.assertThat(values3.getIds()).containsExactlyInOrder();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets4 = DataHelper.createArrayList(valueSet41, valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, DataHelper.<String>createArrayList());
        Assertions.assertThat(values4.getIds()).containsExactlyInOrder();

        valueSetBuilder.setId("id");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets5 = DataHelper.createArrayList(valueSet5);
        Values<String> values5 = new Values<>(null, valueSets5, DataHelper.<String>createArrayList());
        Assertions.assertThat(values5.getIds()).containsExactlyInOrder("id");

        valueSetBuilder.setId("id1");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.setId("id2");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets6 = DataHelper.createArrayList(valueSet61, valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, DataHelper.<String>createArrayList());
        Assertions.assertThat(values6.getIds()).containsExactlyInOrder("id1", "id2");

        valueSetBuilder.setId("id");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets7 = DataHelper.createArrayList(valueSet71, valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, DataHelper.<String>createArrayList());
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
        List<ValueSet<String>> valueSets8 = DataHelper.createArrayList(valueSet81, valueSet82, valueSet83, valueSet84, valueSet85, valueSet86);
        Values<String> values8 = new Values<>(null, valueSets8, DataHelper.<String>createArrayList());
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
        List<ValueSet<String>> valueSets = DataHelper.createArrayList(valueSet1, valueSet2, valueSet3);
        Values<String> values = new Values<>(null, valueSets, DataHelper.<String>createArrayList());
        Assertions.assertThat(values.getIds()).hasSize(3);

        values.getIds().add("id");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getIdsUnmodifiableEmptyFailTest() {
        List<ValueSet<String>> valueSets = DataHelper.createArrayList();
        Values<String> values = new Values<>(null, valueSets, DataHelper.<String>createArrayList());
        Assertions.assertThat(values.getIds()).hasSize(0);

        values.getIds().add("id");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void isEmptyTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, DataHelper.<String>createArrayList());
        Assertions.assertThat(values1.isEmpty()).isTrue();

        List<ValueSet<String>> valueSets2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, valueSets2, DataHelper.<String>createArrayList());
        Assertions.assertThat(values2.isEmpty()).isTrue();

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets3 = DataHelper.createArrayList(valueSet31, valueSet32);
        Values<String> values3 = new Values<>(null, valueSets3, DataHelper.<String>createArrayList());
        Assertions.assertThat(values3.isEmpty()).isTrue();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets4 = DataHelper.createArrayList(null, valueSet41, valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, DataHelper.<String>createArrayList());
        Assertions.assertThat(values4.isEmpty()).isTrue();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets5 = DataHelper.createArrayList(valueSet51, valueSet52);
        Values<String> values5 = new Values<>(null, valueSets5, DataHelper.<String>createArrayList());
        Assertions.assertThat(values5.isEmpty()).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets6 = DataHelper.createArrayList(null, valueSet61, valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, DataHelper.<String>createArrayList());
        Assertions.assertThat(values6.isEmpty()).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValues("val3", "val4");
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets7 = DataHelper.createArrayList(valueSet71, valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, DataHelper.<String>createArrayList());
        Assertions.assertThat(values7.isEmpty()).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void containsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, DataHelper.<String>createArrayList());
        Assertions.assertThat(values1.contains("val1")).isFalse();
        Assertions.assertThat(values1.contains("val2")).isFalse();
        Assertions.assertThat(values1.contains("val3")).isFalse();
        Assertions.assertThat(values1.contains("val4")).isFalse();
        Assertions.assertThat(values1.contains("val5")).isFalse();

        List<ValueSet<String>> valueSets2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, valueSets2, DataHelper.<String>createArrayList());
        Assertions.assertThat(values2.contains("val1")).isFalse();
        Assertions.assertThat(values2.contains("val2")).isFalse();
        Assertions.assertThat(values2.contains("val3")).isFalse();
        Assertions.assertThat(values2.contains("val4")).isFalse();
        Assertions.assertThat(values2.contains("val5")).isFalse();

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets3 = DataHelper.createArrayList(valueSet31, valueSet32);
        Values<String> values3 = new Values<>(null, valueSets3, DataHelper.<String>createArrayList());
        Assertions.assertThat(values3.contains("val1")).isFalse();
        Assertions.assertThat(values3.contains("val2")).isFalse();
        Assertions.assertThat(values3.contains("val3")).isFalse();
        Assertions.assertThat(values3.contains("val4")).isFalse();
        Assertions.assertThat(values3.contains("val5")).isFalse();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets4 = DataHelper.createArrayList(null, valueSet41, valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, DataHelper.<String>createArrayList());
        Assertions.assertThat(values4.contains("val1")).isFalse();
        Assertions.assertThat(values4.contains("val2")).isFalse();
        Assertions.assertThat(values4.contains("val3")).isFalse();
        Assertions.assertThat(values4.contains("val4")).isFalse();
        Assertions.assertThat(values4.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets5 = DataHelper.createArrayList(valueSet51, valueSet52);
        Values<String> values5 = new Values<>(null, valueSets5, DataHelper.<String>createArrayList());
        Assertions.assertThat(values5.contains("val1")).isTrue();
        Assertions.assertThat(values5.contains("val2")).isTrue();
        Assertions.assertThat(values5.contains("val3")).isFalse();
        Assertions.assertThat(values5.contains("val4")).isFalse();
        Assertions.assertThat(values5.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets6 = DataHelper.createArrayList(null, valueSet61, valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, DataHelper.<String>createArrayList());
        Assertions.assertThat(values6.contains("val1")).isTrue();
        Assertions.assertThat(values6.contains("val2")).isTrue();
        Assertions.assertThat(values6.contains("val3")).isFalse();
        Assertions.assertThat(values6.contains("val4")).isFalse();
        Assertions.assertThat(values6.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValues("val3", "val4");
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets7 = DataHelper.createArrayList(valueSet71, valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, DataHelper.<String>createArrayList());
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
    public void doesNotContainTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void allValuesContainsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<String> allValues1 = null;
        Values<String> values1 = new Values<>(null, null, allValues1);
        Assertions.assertThat(values1.allValuesContain("val1")).isFalse();
        Assertions.assertThat(values1.allValuesContain("val2")).isFalse();
        Assertions.assertThat(values1.allValuesContain("val3")).isFalse();
        Assertions.assertThat(values1.allValuesContain("val4")).isFalse();
        Assertions.assertThat(values1.allValuesContain("val5")).isFalse();

        List<String> allValues2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, null, allValues2);
        Assertions.assertThat(values2.allValuesContain("val1")).isFalse();
        Assertions.assertThat(values2.allValuesContain("val2")).isFalse();
        Assertions.assertThat(values2.allValuesContain("val3")).isFalse();
        Assertions.assertThat(values2.allValuesContain("val4")).isFalse();
        Assertions.assertThat(values2.allValuesContain("val5")).isFalse();

        List<String> allValues3 = DataHelper.createArrayList((String) null);
        Values<String> values3 = new Values<>(null, null, allValues3);
        Assertions.assertThat(values3.allValuesContain("val1")).isFalse();
        Assertions.assertThat(values3.allValuesContain("val2")).isFalse();
        Assertions.assertThat(values3.allValuesContain("val3")).isFalse();
        Assertions.assertThat(values3.allValuesContain("val4")).isFalse();
        Assertions.assertThat(values3.allValuesContain("val5")).isFalse();

        List<String> allValues4 = DataHelper.createArrayList("val1", "val2");
        Values<String> values4 = new Values<>(null, null, allValues4);
        Assertions.assertThat(values4.allValuesContain("val1")).isTrue();
        Assertions.assertThat(values4.allValuesContain("val2")).isTrue();
        Assertions.assertThat(values4.allValuesContain("val3")).isFalse();
        Assertions.assertThat(values4.allValuesContain("val4")).isFalse();
        Assertions.assertThat(values4.allValuesContain("val5")).isFalse();

        List<String> allValues5 = DataHelper.createArrayList(null, "val1", "val2");
        Values<String> values5 = new Values<>(null, null, allValues5);
        Assertions.assertThat(values5.allValuesContain("val1")).isTrue();
        Assertions.assertThat(values5.allValuesContain("val2")).isTrue();
        Assertions.assertThat(values5.allValuesContain("val3")).isFalse();
        Assertions.assertThat(values5.allValuesContain("val4")).isFalse();
        Assertions.assertThat(values5.allValuesContain("val5")).isFalse();

        List<String> allValues6 = DataHelper.createArrayList("val1", "val2", "val3", "val4");
        Values<String> values6 = new Values<>(null, null, allValues6);
        Assertions.assertThat(values6.allValuesContain("val1")).isTrue();
        Assertions.assertThat(values6.allValuesContain("val2")).isTrue();
        Assertions.assertThat(values6.allValuesContain("val3")).isTrue();
        Assertions.assertThat(values6.allValuesContain("val4")).isTrue();
        Assertions.assertThat(values6.allValuesContain("val5")).isFalse();

        List<String> allValues7 = DataHelper.createArrayList(null, "val1", "val2", "val3", "val4");
        Values<String> values7 = new Values<>(null, null, allValues7);
        Assertions.assertThat(values7.allValuesContain("val1")).isTrue();
        Assertions.assertThat(values7.allValuesContain("val2")).isTrue();
        Assertions.assertThat(values7.allValuesContain("val3")).isTrue();
        Assertions.assertThat(values7.allValuesContain("val4")).isTrue();
        Assertions.assertThat(values7.allValuesContain("val5")).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void allValuesDoesNotContainTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, DataHelper.<String>createArrayList());
        Assertions.assertThat(values1.getValues()).containsExactly();

        List<ValueSet<String>> valueSets2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, valueSets2, DataHelper.<String>createArrayList());
        Assertions.assertThat(values2.getValues()).containsExactly();

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets3 = DataHelper.createArrayList(valueSet31, valueSet32);
        Values<String> values3 = new Values<>(null, valueSets3, DataHelper.<String>createArrayList());
        Assertions.assertThat(values3.getValues()).containsExactly();

        ValueSet<String> valueSet41 = valueSetBuilder.build();
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets4 = DataHelper.createArrayList(null, valueSet41, valueSet42);
        Values<String> values4 = new Values<>(null, valueSets4, DataHelper.<String>createArrayList());
        Assertions.assertThat(values4.getValues()).containsExactly();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets5 = DataHelper.createArrayList(valueSet51, valueSet52);
        Values<String> values5 = new Values<>(null, valueSets5, DataHelper.<String>createArrayList());
        Assertions.assertThat(values5.getValues()).containsExactly("val1", "val2");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet61 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet62 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets6 = DataHelper.createArrayList(null, valueSet61, valueSet62);
        Values<String> values6 = new Values<>(null, valueSets6, DataHelper.<String>createArrayList());
        Assertions.assertThat(values6.getValues()).containsExactly("val1", "val2");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet71 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValues("val3", "val4");
        ValueSet<String> valueSet72 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets7 = DataHelper.createArrayList(valueSet71, valueSet72);
        Values<String> values7 = new Values<>(null, valueSets7, DataHelper.<String>createArrayList());
        Assertions.assertThat(values7.getValues()).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet81 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValues("val3", "val4");
        ValueSet<String> valueSet82 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets8 = DataHelper.createArrayList(valueSet81, valueSet82);
        Values<String> values8 = new Values<>(new NaturalOrderComparator<String>(), valueSets8, DataHelper.<String>createArrayList());
        Assertions.assertThat(values8.getValues()).containsExactlyInOrder("val1", "val2", "val3", "val4");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValues("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addValues("val3", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = DataHelper.createArrayList(valueSet1, valueSet2);
        Values<String> values = new Values<>(null, valueSets, DataHelper.<String>createArrayList());
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
        List<ValueSet<String>> valueSets = DataHelper.createArrayList(valueSet1, valueSet2);
        Values<String> values = new Values<>(null, valueSets, DataHelper.<String>createArrayList());
        Assertions.assertThat(values.getValues()).hasSize(0);

        values.getValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getUniqueValuesTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getUniqueValuesUnmodifiableFailTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getUniqueValuesUnmodifiableEmptyFailTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<String> allValues1 = null;
        Values<String> values1 = new Values<>(null, null, allValues1);
        Assertions.assertThat(values1.getAllValues()).containsExactly();

        List<String> allValues2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, null, allValues2);
        Assertions.assertThat(values2.getAllValues()).containsExactly();

        List<String> allValues3 = DataHelper.createArrayList((String) null);
        Values<String> values3 = new Values<>(null, null, allValues3);
        Assertions.assertThat(values3.getAllValues()).containsExactly();

        List<String> allValues4 = DataHelper.createArrayList("val1", "val2");
        Values<String> values4 = new Values<>(null, null, allValues4);
        Assertions.assertThat(values4.getAllValues()).containsExactly("val1", "val2");

        List<String> allValues5 = DataHelper.createArrayList(null, "val1", "val2");
        Values<String> values5 = new Values<>(null, null, allValues5);
        Assertions.assertThat(values5.getAllValues()).containsExactly("val1", "val2");

        List<String> allValues6 = DataHelper.createArrayList("val1", "val2", "val3", "val4");
        Values<String> values6 = new Values<>(null, null, allValues6);
        Assertions.assertThat(values6.getAllValues()).containsExactly("val1", "val2", "val3", "val4");

        List<String> allValues7 = DataHelper.createArrayList(null, "val1", "val2", "val3", "val4");
        Values<String> values7 = new Values<>(null, null, allValues7);
        Assertions.assertThat(values7.getAllValues()).containsExactly("val1", "val2", "val3", "val4");

        List<String> allValues8 = DataHelper.createArrayList(null, "val1", "val2", "val3", "val4");
        Values<String> values8 = new Values<>(new NaturalOrderComparator<String>(), null, allValues8);
        Assertions.assertThat(values8.getAllValues()).containsExactlyInOrder("val1", "val2", "val3", "val4");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<String> allValues = DataHelper.createArrayList("val1", "val2");
        Values<String> values = new Values<>(null, null, allValues);
        Assertions.assertThat(values.getAllValues()).hasSize(2);

        values.getAllValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableEmptyFailTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<String> allValues = DataHelper.createArrayList();
        Values<String> values = new Values<>(null, null, allValues);
        Assertions.assertThat(values.getAllValues()).hasSize(0);

        values.getAllValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllUniqueValuesTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllUniqueValuesUnmodifiableFailTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllUniqueValuesUnmodifiableEmptyFailTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void performActionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val4", "val2");
        ValueSet<String> valueSet11 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets1 = DataHelper.createArrayList(valueSet11, valueSet12);
        Values<String> values1 = new Values<>(null, valueSets1, DataHelper.<String>createArrayList());
        ConcatStringAction action1 = null;
        values1.performAction(action1);

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val4", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet22 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets2 = DataHelper.createArrayList(valueSet21, valueSet22);
        Values<String> values2 = new Values<>(null, valueSets2, DataHelper.<String>createArrayList());
        ConcatStringAction action2 = new ConcatStringAction("proc_", 0);
        values2.performAction(action2);
        Assertions.assertThat(action2.getValues()).containsExactly("proc_val1", "proc_val2", "proc_val3", "proc_val4", "proc_val5", "proc_val6");

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValues("val1", "val4", "val2");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets3 = DataHelper.createArrayList(valueSet31, valueSet32);
        Values<String> values3 = new Values<>(new NaturalOrderComparator<String>(), valueSets3, DataHelper.<String>createArrayList());
        ConcatStringAction action3 = new ConcatStringAction("proc_", 0);
        values3.performAction(action3);
        Assertions.assertThat(action3.getValues()).containsExactlyInOrder("proc_val1", "proc_val2", "proc_val3", "proc_val4", "proc_val5", "proc_val6");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void performActionMultipleTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        List<ValueSet<String>> valueSets1 = null;
        Values<String> values1 = new Values<>(null, valueSets1, DataHelper.<String>createArrayList());
        Assertions.assertThat(values1).hasToString("[]");

        List<ValueSet<String>> valueSets2 = DataHelper.createArrayList();
        Values<String> values2 = new Values<>(null, valueSets2, DataHelper.<String>createArrayList());
        Assertions.assertThat(values2).hasToString("[]");

        ValueSet<String> valueSet31 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets31 = DataHelper.createArrayList(valueSet31);
        Values<String> values31 = new Values<>(null, valueSets31, DataHelper.<String>createArrayList());
        Assertions.assertThat(values31).hasToString("[{}]");

        valueSetBuilder.setId("");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets32 = DataHelper.createArrayList(valueSet32);
        Values<String> values32 = new Values<>(null, valueSets32, DataHelper.<String>createArrayList());
        Assertions.assertThat(values32).hasToString("[={}]");

        valueSetBuilder.setId("id");
        ValueSet<String> valueSet33 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets33 = DataHelper.createArrayList(valueSet33);
        Values<String> values33 = new Values<>(null, valueSets33, DataHelper.<String>createArrayList());
        Assertions.assertThat(values33).hasToString("[id={}]");

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet41 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets41 = DataHelper.createArrayList(valueSet41);
        Values<String> values41 = new Values<>(null, valueSets41, DataHelper.<String>createArrayList());
        Assertions.assertThat(values41).hasToString("[{cond=[val]}]");

        valueSetBuilder.setId("");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets42 = DataHelper.createArrayList(valueSet42);
        Values<String> values42 = new Values<>(null, valueSets42, DataHelper.<String>createArrayList());
        Assertions.assertThat(values42).hasToString("[={cond=[val]}]");

        valueSetBuilder.setId("id");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet43 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets43 = DataHelper.createArrayList(valueSet43);
        Values<String> values43 = new Values<>(null, valueSets43, DataHelper.<String>createArrayList());
        Assertions.assertThat(values43).hasToString("[id={cond=[val]}]");

        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet51 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets51 = DataHelper.createArrayList(null, valueSet51);
        Values<String> values51 = new Values<>(null, valueSets51, DataHelper.<String>createArrayList());
        Assertions.assertThat(values51).hasToString("[{cond=[val]}]");

        valueSetBuilder.setId("");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet52 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets52 = DataHelper.createArrayList(null, valueSet52);
        Values<String> values52 = new Values<>(null, valueSets52, DataHelper.<String>createArrayList());
        Assertions.assertThat(values52).hasToString("[={cond=[val]}]");

        valueSetBuilder.setId("id");
        valueSetBuilder.addCondition("cond", "val");
        ValueSet<String> valueSet53 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets53 = DataHelper.createArrayList(null, valueSet53);
        Values<String> values53 = new Values<>(null, valueSets53, DataHelper.<String>createArrayList());
        Assertions.assertThat(values53).hasToString("[id={cond=[val]}]");

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet611 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        ValueSet<String> valueSet612 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets61 = DataHelper.createArrayList(valueSet611, valueSet612);
        Values<String> values61 = new Values<>(null, valueSets61, DataHelper.<String>createArrayList());
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
        List<ValueSet<String>> valueSets62 = DataHelper.createArrayList(valueSet621, valueSet622);
        Values<String> values62 = new Values<>(null, valueSets62, DataHelper.<String>createArrayList());
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
        List<ValueSet<String>> valueSets63 = DataHelper.createArrayList(valueSet631, valueSet632);
        Values<String> values63 = new Values<>(null, valueSets63, DataHelper.<String>createArrayList());
        Assertions.assertThat(values63).toToString().startsWith("[id");
        Assertions.assertThat(values63).toStringContains("cond1=[val1");
        Assertions.assertThat(values63).toStringContains("cond2=[val2]");
        Assertions.assertThat(values63).toStringContains("cond3=[val3");
    }

}
