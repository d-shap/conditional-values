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

import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link ValueSetBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetBuilderTest {

    /**
     * Test class constructor.
     */
    public ValueSetBuilderTest() {
        super();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val11");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).isEmpty();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1");

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val11");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet2.getAllValues()).containsExactly("val1", "val2");

        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val11", "val12");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildAndClearTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val11");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).isEmpty();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1");

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).isEmpty();
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet2.getAllValues()).containsExactly("val2");

        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val12");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).isEmpty();
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", true);
        valueSetBuilder.addCondition("cond1", true);
        valueSetBuilder.addCondition("cond1", 1);
        valueSetBuilder.addCondition("cond1", 2);
        valueSetBuilder.addCondition("cond1", 1L);
        valueSetBuilder.addCondition("cond1", 3L);
        valueSetBuilder.addCondition("cond1", 3.5f);
        valueSetBuilder.addCondition("cond1", 4.9);
        valueSetBuilder.addCondition("cond2", new StringBuilder().append("val1"));
        valueSetBuilder.addCondition("cond2", new StringBuilder().append("val2"));
        Object value2 = "val2";
        valueSetBuilder.addCondition("cond2", value2);
        Object value3 = "val3";
        valueSetBuilder.addCondition("cond2", value3);
        valueSetBuilder.addCondition("cond2", "val3");
        valueSetBuilder.addCondition("cond2", "val4");
        Object value5 = null;
        valueSetBuilder.addCondition("cond2", value5);
        String value6 = null;
        valueSetBuilder.addCondition("cond2", value6);
        valueSetBuilder.addCondition(null, "val7");
        valueSetBuilder.addCondition(null, (Object) null);

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet.getAllConditionValues("cond1")).containsExactly("true", "1", "2", "3", "3.5", "4.9");
        Assertions.assertThat(valueSet.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder = valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder = valueSetBuilder.addCondition("cond2", true);
        valueSetBuilder = valueSetBuilder.addCondition("cond3", 1);
        valueSetBuilder = valueSetBuilder.addCondition("cond4", 2L);
        valueSetBuilder = valueSetBuilder.addCondition("cond5", 1.1f);
        valueSetBuilder = valueSetBuilder.addCondition("cond6", 1.2);
        valueSetBuilder = valueSetBuilder.addCondition("cond7", new StringBuilder("val2"));

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6", "cond7");
        Assertions.assertThat(valueSet.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet.getAllConditionValues("cond2")).containsExactly("true");
        Assertions.assertThat(valueSet.getAllConditionValues("cond3")).containsExactly("1");
        Assertions.assertThat(valueSet.getAllConditionValues("cond4")).containsExactly("2");
        Assertions.assertThat(valueSet.getAllConditionValues("cond5")).containsExactly("1.1");
        Assertions.assertThat(valueSet.getAllConditionValues("cond6")).containsExactly("1.2");
        Assertions.assertThat(valueSet.getAllConditionValues("cond7")).containsExactly("val2");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", true);
        valueSetBuilder.addCondition("cond1", true);
        valueSetBuilder.addCondition("cond2", 1);
        valueSetBuilder.addCondition("cond2", 2);
        valueSetBuilder.addCondition("cond2", 1L);
        valueSetBuilder.addCondition("cond2", 3L);
        valueSetBuilder.addCondition("cond3", 3.5f);
        valueSetBuilder.addCondition("cond3", 4.9);
        valueSetBuilder.addCondition("cond4", new StringBuilder().append("val1"));
        valueSetBuilder.addCondition("cond4", new StringBuilder().append("val2"));
        valueSetBuilder.addCondition("cond4", (Object) "val2");
        valueSetBuilder.addCondition("cond4", (Object) "val3");
        valueSetBuilder.addCondition("cond4", "val3");
        valueSetBuilder.addCondition("cond4", "val4");

        valueSetBuilder.removeConditions("cond1");
        valueSetBuilder.removeConditions((String) null);
        valueSetBuilder.removeCondition("cond1", false);
        valueSetBuilder.removeCondition("cond1", true);
        valueSetBuilder.removeCondition("cond2", 1);
        valueSetBuilder.removeCondition("cond2", "3");
        valueSetBuilder.removeCondition("cond2", 2L);
        valueSetBuilder.removeCondition("cond3", 3.5f);
        valueSetBuilder.removeCondition("cond3", 3.6f);
        valueSetBuilder.removeCondition("cond3", 3.7f);
        valueSetBuilder.removeCondition("cond3", 4.5);
        valueSetBuilder.removeCondition("cond3", 4.6);
        valueSetBuilder.removeCondition("cond3", 4.7);
        valueSetBuilder.removeCondition("cond4", "val1");
        valueSetBuilder.removeCondition("cond4", new StringBuilder().append("val1"));
        valueSetBuilder.removeCondition("cond4", new StringBuilder().append("val2"));
        Object value3 = "val3";
        valueSetBuilder.removeCondition("cond4", value3);
        Object value4 = null;
        valueSetBuilder.removeCondition("cond4", value4);
        String value5 = null;
        valueSetBuilder.removeCondition("cond4", value5);
        valueSetBuilder.removeCondition(null, "val3");
        valueSetBuilder.removeCondition(null, (Object) null);

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).containsExactly("cond3", "cond4");
        Assertions.assertThat(valueSet.getAllConditionValues("cond3")).containsExactly("4.9");
        Assertions.assertThat(valueSet.getAllConditionValues("cond4")).containsExactly("val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", true);
        valueSetBuilder.addCondition("cond2", false);
        valueSetBuilder.addCondition("cond3", 1);
        valueSetBuilder.addCondition("cond3", 3);
        valueSetBuilder.addCondition("cond4", 2L);
        valueSetBuilder.addCondition("cond4", 4L);
        valueSetBuilder.addCondition("cond5", 1.1f);
        valueSetBuilder.addCondition("cond5", 1.3f);
        valueSetBuilder.addCondition("cond6", 1.2);
        valueSetBuilder.addCondition("cond6", 1.4);
        valueSetBuilder.addCondition("cond7", new StringBuilder("val2"));
        valueSetBuilder.addCondition("cond7", new StringBuilder("val3"));
        valueSetBuilder.addCondition("cond8", "val4");
        valueSetBuilder.addCondition("cond8", "val5");

        valueSetBuilder = valueSetBuilder.removeCondition("cond1", "val1");
        valueSetBuilder = valueSetBuilder.removeCondition("cond2", true);
        valueSetBuilder = valueSetBuilder.removeCondition("cond3", 1);
        valueSetBuilder = valueSetBuilder.removeCondition("cond4", 2L);
        valueSetBuilder = valueSetBuilder.removeCondition("cond5", 1.1f);
        valueSetBuilder = valueSetBuilder.removeCondition("cond6", 1.2);
        valueSetBuilder = valueSetBuilder.removeCondition("cond7", new StringBuilder("val2"));
        valueSetBuilder = valueSetBuilder.removeConditions("cond8");

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6", "cond7");
        Assertions.assertThat(valueSet.getAllConditionValues("cond1")).containsExactly("val2");
        Assertions.assertThat(valueSet.getAllConditionValues("cond2")).containsExactly("false");
        Assertions.assertThat(valueSet.getAllConditionValues("cond3")).containsExactly("3");
        Assertions.assertThat(valueSet.getAllConditionValues("cond4")).containsExactly("4");
        Assertions.assertThat(valueSet.getAllConditionValues("cond5")).containsExactly("1.3");
        Assertions.assertThat(valueSet.getAllConditionValues("cond6")).containsExactly("1.4");
        Assertions.assertThat(valueSet.getAllConditionValues("cond7")).containsExactly("val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", true);
        valueSetBuilder.addCondition("cond3", 1);
        valueSetBuilder.addCondition("cond4", 2L);
        valueSetBuilder.addCondition("cond5", 1.1f);
        valueSetBuilder.addCondition("cond6", 1.2);
        valueSetBuilder.addCondition("cond7", new StringBuilder("val2"));

        valueSetBuilder = valueSetBuilder.clearConditions();

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue((String) null);
        valueSetBuilder.addValue((String[]) null);

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder = valueSetBuilder.addValue("val1", "val2");
        valueSetBuilder = valueSetBuilder.addValue("val3");
        valueSetBuilder = valueSetBuilder.addValue("val4");

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllValues()).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addValue("val1", "val3", "val2");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue("val20", "val17", "val19", "val18");

        valueSetBuilder.removeValue("val17", "val18");
        valueSetBuilder.removeValue("val3");
        valueSetBuilder.removeValue((String) null);
        valueSetBuilder.removeValue((String[]) null);

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllValues()).containsExactly("val1", "val2", "val4", "val5", "val19", "val20");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val4", "val5");
        valueSetBuilder.addValue("val6");

        valueSetBuilder = valueSetBuilder.removeValue("val1", "val3");
        valueSetBuilder = valueSetBuilder.removeValue("val5");

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllValues()).containsExactly("val2", "val4", "val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val4", "val5");
        valueSetBuilder.addValue("val6");

        valueSetBuilder = valueSetBuilder.clearValues();

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllValues()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", true);
        valueSetBuilder.addCondition("cond3", 1);
        valueSetBuilder.addCondition("cond4", 2L);
        valueSetBuilder.addCondition("cond5", 1.1f);
        valueSetBuilder.addCondition("cond6", 1.2);
        valueSetBuilder.addCondition("cond7", new StringBuilder("val2"));
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val4", "val5");
        valueSetBuilder.addValue("val6");

        valueSetBuilder = valueSetBuilder.clear();

        ValueSet<String> valueSet = valueSetBuilder.build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).isEmpty();
        Assertions.assertThat(valueSet.getAllValues()).isEmpty();
    }

}
