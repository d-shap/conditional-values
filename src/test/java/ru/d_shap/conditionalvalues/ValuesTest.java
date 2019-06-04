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
    public void isEmptyTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        ValueSet<String> valueSet11 = valueSetBuilder.build();
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets1 = new HashSet<>();
        valueSets1.add(valueSet11);
        valueSets1.add(valueSet12);
        Values<String> values1 = new Values<>(valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.isEmpty()).isTrue();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet22 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        valueSets2.add(valueSet21);
        valueSets2.add(valueSet22);
        Values<String> values2 = new Values<>(valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.isEmpty()).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.isEmpty()).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void containsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        ValueSet<String> valueSet11 = valueSetBuilder.build();
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets1 = new HashSet<>();
        valueSets1.add(valueSet11);
        valueSets1.add(valueSet12);
        Values<String> values1 = new Values<>(valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.contains("val1")).isFalse();
        Assertions.assertThat(values1.contains("val2")).isFalse();
        Assertions.assertThat(values1.contains("val3")).isFalse();
        Assertions.assertThat(values1.contains("val4")).isFalse();
        Assertions.assertThat(values1.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet22 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        valueSets2.add(valueSet21);
        valueSets2.add(valueSet22);
        Values<String> values2 = new Values<>(valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.contains("val1")).isTrue();
        Assertions.assertThat(values2.contains("val2")).isTrue();
        Assertions.assertThat(values2.contains("val3")).isFalse();
        Assertions.assertThat(values2.contains("val4")).isFalse();
        Assertions.assertThat(values2.contains("val5")).isFalse();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.contains("val1")).isTrue();
        Assertions.assertThat(values3.contains("val2")).isTrue();
        Assertions.assertThat(values3.contains("val3")).isTrue();
        Assertions.assertThat(values3.contains("val4")).isTrue();
        Assertions.assertThat(values3.contains("val5")).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void allValuesContainsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        Set<String> allValues1 = new HashSet<>();
        Values<String> values1 = new Values<>(new HashSet<ValueSet<String>>(), allValues1);
        Assertions.assertThat(values1.allValuesContains("val1")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val2")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values1.allValuesContains("val5")).isFalse();

        Set<String> allValues2 = new HashSet<>();
        allValues2.add("val1");
        allValues2.add("val2");
        Values<String> values2 = new Values<>(new HashSet<ValueSet<String>>(), allValues2);
        Assertions.assertThat(values2.allValuesContains("val1")).isTrue();
        Assertions.assertThat(values2.allValuesContains("val2")).isTrue();
        Assertions.assertThat(values2.allValuesContains("val3")).isFalse();
        Assertions.assertThat(values2.allValuesContains("val4")).isFalse();
        Assertions.assertThat(values2.allValuesContains("val5")).isFalse();

        Set<String> allValues3 = new HashSet<>();
        allValues3.add("val1");
        allValues3.add("val2");
        allValues3.add("val3");
        allValues3.add("val4");
        Values<String> values3 = new Values<>(new HashSet<ValueSet<String>>(), allValues3);
        Assertions.assertThat(values3.allValuesContains("val1")).isTrue();
        Assertions.assertThat(values3.allValuesContains("val2")).isTrue();
        Assertions.assertThat(values3.allValuesContains("val3")).isTrue();
        Assertions.assertThat(values3.allValuesContains("val4")).isTrue();
        Assertions.assertThat(values3.allValuesContains("val5")).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        ValueSet<String> valueSet11 = valueSetBuilder.build();
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets1 = new HashSet<>();
        valueSets1.add(valueSet11);
        valueSets1.add(valueSet12);
        Values<String> values1 = new Values<>(valueSets1, new HashSet<String>());
        Assertions.assertThat(values1.getValues()).isNotNull();
        Assertions.assertThat(values1.getValues()).containsExactly();

        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        ValueSet<String> valueSet22 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets2 = new HashSet<>();
        valueSets2.add(valueSet21);
        valueSets2.add(valueSet22);
        Values<String> values2 = new Values<>(valueSets2, new HashSet<String>());
        Assertions.assertThat(values2.getValues()).isNotNull();
        Assertions.assertThat(values2.getValues()).containsExactly("val1", "val2");

        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet31 = valueSetBuilder.build();
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet32 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet31);
        valueSets3.add(valueSet32);
        Values<String> values3 = new Values<>(valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.getValues()).isNotNull();
        Assertions.assertThat(values3.getValues()).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet41 = valueSetBuilder.build();
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet42 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets4 = new HashSet<>();
        valueSets4.add(valueSet41);
        valueSets4.add(valueSet42);
        Values<String> values4 = new Values<>(valueSets4, new HashSet<String>());
        Assertions.assertThat(values4.getValues()).isNotNull();
        Assertions.assertThat(values4.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addValue("val3", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(valueSets, new HashSet<String>());
        Assertions.assertThat(values.getValues()).hasSize(4);
        values.getValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableEmptyFailTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(valueSets, new HashSet<String>());
        Assertions.assertThat(values.getValues()).hasSize(0);
        values.getValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        Set<String> allValues1 = new HashSet<>();
        Values<String> values1 = new Values<>(new HashSet<ValueSet<String>>(), allValues1);
        Assertions.assertThat(values1.getAllValues()).isNotNull();
        Assertions.assertThat(values1.getAllValues()).containsExactly();

        Set<String> allValues2 = new HashSet<>();
        allValues2.add("val1");
        allValues2.add("val2");
        Values<String> values2 = new Values<>(new HashSet<ValueSet<String>>(), allValues2);
        Assertions.assertThat(values2.getAllValues()).isNotNull();
        Assertions.assertThat(values2.getAllValues()).containsExactly("val1", "val2");

        Set<String> allValues3 = new HashSet<>();
        allValues3.add("val1");
        allValues3.add("val2");
        allValues3.add("val3");
        allValues3.add("val4");
        Values<String> values3 = new Values<>(new HashSet<ValueSet<String>>(), allValues3);
        Assertions.assertThat(values3.getAllValues()).isNotNull();
        Assertions.assertThat(values3.getAllValues()).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableFailTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        Set<String> allValues = new HashSet<>();
        allValues.add("val1");
        allValues.add("val2");
        Values<String> values = new Values<>(new HashSet<ValueSet<String>>(), allValues);
        Assertions.assertThat(values.getAllValues()).hasSize(2);
        values.getAllValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllValuesUnmodifiableEmptyFailTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        Set<String> allValues = new HashSet<>();
        Values<String> values = new Values<>(new HashSet<ValueSet<String>>(), allValues);
        Assertions.assertThat(values.getAllValues()).hasSize(0);
        values.getAllValues().add("value");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getIdsTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void performActionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(valueSets, new HashSet<String>());

        ActionImpl action = new ActionImpl();
        values.performAction(action);
        Assertions.assertThat(action._values).isNotNull();
        Assertions.assertThat(action._values).containsExactly("proc_val1", "proc_val2", "proc_val3", "proc_val4", "proc_val5", "proc_val6");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void performNullActionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(valueSets, new HashSet<String>());
        values.performAction(null);
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31");
        valueSetBuilder.addCondition("cond3", "val32");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(valueSets, new HashSet<String>());
        Assertions.assertThat(values).toStringContains("cond1=[val1");
        Assertions.assertThat(values).toStringContains("cond2=[val2]");
        Assertions.assertThat(values).toStringContains("cond3=[val3");
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
