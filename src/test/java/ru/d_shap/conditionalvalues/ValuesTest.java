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

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet11 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
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

        Set<ValueSet<String>> valueSets3 = new HashSet<>();
        valueSets3.add(valueSet22);
        Values<String> values3 = new Values<>(valueSets3, new HashSet<String>());
        Assertions.assertThat(values3.isEmpty()).isTrue();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void containsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<ValueSet<String>> valueSets = new HashSet<>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<>(valueSets, new HashSet<String>());
        Assertions.assertThat(values.contains("val1")).isTrue();
        Assertions.assertThat(values.contains("val2")).isTrue();
        Assertions.assertThat(values.contains("val3")).isTrue();
        Assertions.assertThat(values.contains("val4")).isTrue();
        Assertions.assertThat(values.contains("val5")).isFalse();
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void allValuesContainsTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getValuesTest() {
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
        Assertions.assertThat(values.getValues()).isNotNull();
        Assertions.assertThat(values.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllValuesTest() {
        // TODO
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getValueSetIdsTest() {
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
