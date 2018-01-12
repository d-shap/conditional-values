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
    public void addConditionStringTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder.addCondition(null, "val");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();

        valueSetBuilder.addCondition("cond", null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).isEmpty();

        valueSetBuilder.addCondition(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionBooleanTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", true);
        valueSetBuilder.addCondition("cond1", false);
        valueSetBuilder.addCondition("cond2", false).addCondition("cond2", true);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("true", "false");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("true", "false");

        valueSetBuilder.addCondition(null, true);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionIntegerTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1);
        valueSetBuilder.addCondition("cond1", 2);
        valueSetBuilder.addCondition("cond2", 3).addCondition("cond2", 4);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1", "2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3", "4");

        valueSetBuilder.addCondition(null, 0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionLongTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1L);
        valueSetBuilder.addCondition("cond1", 2L);
        valueSetBuilder.addCondition("cond2", 3L).addCondition("cond2", 4L);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1", "2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3", "4");

        valueSetBuilder.addCondition(null, 0L);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionFloatTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1.0f);
        valueSetBuilder.addCondition("cond1", 2.0f);
        valueSetBuilder.addCondition("cond2", 3.0f).addCondition("cond2", 4.0f);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1.0", "2.0");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3.0", "4.0");

        valueSetBuilder.addCondition(null, 0.0f);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionDoubleTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1.0);
        valueSetBuilder.addCondition("cond1", 2.0);
        valueSetBuilder.addCondition("cond2", 3.0).addCondition("cond2", 4.0);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1.0", "2.0");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3.0", "4.0");

        valueSetBuilder.addCondition(null, 0.0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionObjectTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", new StringBuilder().append("val1"));
        valueSetBuilder.addCondition("cond1", new StringBuilder().append("val2"));
        valueSetBuilder.addCondition("cond2", new StringBuilder().append("val3")).addCondition("cond2", new StringBuilder().append("val4"));
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder.addCondition(null, new StringBuilder().append("val"));
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();

        valueSetBuilder.addCondition("cond", (Object) null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).isEmpty();

        valueSetBuilder.addCondition(null, (Object) null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionsValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addCondition("cond2", "val5");
        valueSetBuilder.addCondition("cond3", "val6");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val3", "val4", "val5");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly("val6");

        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addCondition("cond2", "val5");
        valueSetBuilder.addCondition("cond3", "val6");
        valueSetBuilder = valueSetBuilder.addConditions(template);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val3", "val4", "val5");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).containsExactly("val6");

        valueSetBuilder = valueSetBuilder.addConditions(null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllConditionNames()).isEmpty();

        valueSetBuilder = valueSetBuilder.addConditions(null);
        valueSetBuilder.addCondition("cond2", "val4");
        valueSetBuilder.addCondition("cond2", "val5");
        valueSetBuilder.addCondition("cond3", "val6");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        Assertions.assertThat(valueSet5).isNotNull();
        Assertions.assertThat(valueSet5.getAllConditionNames()).containsExactly("cond2", "cond3");
        Assertions.assertThat(valueSet5.getAllConditionValues("cond2")).containsExactly("val4", "val5");
        Assertions.assertThat(valueSet5.getAllConditionValues("cond3")).containsExactly("val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionStringTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", "val1").addCondition("cond3", "val2");
        valueSetBuilder.removeCondition("cond1", "val3");
        valueSetBuilder.removeCondition("cond1", "val4");
        valueSetBuilder.removeCondition("cond2", "val1").removeCondition("cond2", "val2");
        valueSetBuilder.removeCondition("cond3", "val1").removeCondition("cond3", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, "val");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition("cond", null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionBooleanTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", true).addCondition("cond1", true).addCondition("cond1", false).addCondition("cond1", false);
        valueSetBuilder.addCondition("cond2", true).addCondition("cond2", true).addCondition("cond2", false).addCondition("cond2", false);
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", true).addCondition("cond3", false);
        valueSetBuilder.removeCondition("cond1", true);
        valueSetBuilder.removeCondition("cond1", true);
        valueSetBuilder.removeCondition("cond2", false).removeCondition("cond2", false);
        valueSetBuilder.removeCondition("cond3", true).removeCondition("cond3", false);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("false");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("true");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, true);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("true", "false");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("true", "false");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionIntegerTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1).addCondition("cond1", 2).addCondition("cond1", 3).addCondition("cond1", 4);
        valueSetBuilder.addCondition("cond2", 1).addCondition("cond2", 2).addCondition("cond2", 3).addCondition("cond2", 4);
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", 1).addCondition("cond3", 2);
        valueSetBuilder.removeCondition("cond1", 3);
        valueSetBuilder.removeCondition("cond1", 4);
        valueSetBuilder.removeCondition("cond2", 1).removeCondition("cond2", 2);
        valueSetBuilder.removeCondition("cond3", 1).removeCondition("cond3", 2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1", "2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3", "4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, 0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("1", "2", "3", "4");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("1", "2", "3", "4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionLongTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1L).addCondition("cond1", 2L).addCondition("cond1", 3L).addCondition("cond1", 4L);
        valueSetBuilder.addCondition("cond2", 1L).addCondition("cond2", 2L).addCondition("cond2", 3L).addCondition("cond2", 4L);
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", 1L).addCondition("cond3", 2L);
        valueSetBuilder.removeCondition("cond1", 3L);
        valueSetBuilder.removeCondition("cond1", 4L);
        valueSetBuilder.removeCondition("cond2", 1L).removeCondition("cond2", 2L);
        valueSetBuilder.removeCondition("cond3", 1L).removeCondition("cond3", 2L);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1", "2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3", "4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, 0L);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("1", "2", "3", "4");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("1", "2", "3", "4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionFloatTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1.0f).addCondition("cond1", 2.0f).addCondition("cond1", 3.0f).addCondition("cond1", 4.0f);
        valueSetBuilder.addCondition("cond2", 1.0f).addCondition("cond2", 2.0f).addCondition("cond2", 3.0f).addCondition("cond2", 4.0f);
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", 1.0f).addCondition("cond3", 2.0f);
        valueSetBuilder.removeCondition("cond1", 3.0f);
        valueSetBuilder.removeCondition("cond1", 4.0f);
        valueSetBuilder.removeCondition("cond2", 1.0f).removeCondition("cond2", 2.0f);
        valueSetBuilder.removeCondition("cond3", 1.0f).removeCondition("cond3", 2.0f);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1.0", "2.0");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3.0", "4.0");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, 0.0f);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("1.0", "2.0", "3.0", "4.0");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("1.0", "2.0", "3.0", "4.0");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionDoubleTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", 1.0).addCondition("cond1", 2.0).addCondition("cond1", 3.0).addCondition("cond1", 4.0);
        valueSetBuilder.addCondition("cond2", 1.0).addCondition("cond2", 2.0).addCondition("cond2", 3.0).addCondition("cond2", 4.0);
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", 1.0).addCondition("cond3", 2.0);
        valueSetBuilder.removeCondition("cond1", 3.0);
        valueSetBuilder.removeCondition("cond1", 4.0);
        valueSetBuilder.removeCondition("cond2", 1.0).removeCondition("cond2", 2.0);
        valueSetBuilder.removeCondition("cond3", 1.0).removeCondition("cond3", 2.0);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("1.0", "2.0");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("3.0", "4.0");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, 0.0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("1.0", "2.0", "3.0", "4.0");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("1.0", "2.0", "3.0", "4.0");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionObjectTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", new StringBuilder().append("val1")).addCondition("cond1", new StringBuilder().append("val2")).addCondition("cond1", new StringBuilder().append("val3")).addCondition("cond1", new StringBuilder().append("val4"));
        valueSetBuilder.addCondition("cond2", new StringBuilder().append("val1")).addCondition("cond2", new StringBuilder().append("val2")).addCondition("cond2", new StringBuilder().append("val3")).addCondition("cond2", new StringBuilder().append("val4"));
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.addCondition("cond3", new StringBuilder().append("val1")).addCondition("cond3", new StringBuilder().append("val2"));
        valueSetBuilder.removeCondition("cond1", new StringBuilder().append("val3"));
        valueSetBuilder.removeCondition("cond1", new StringBuilder().append("val4"));
        valueSetBuilder.removeCondition("cond2", new StringBuilder().append("val1")).removeCondition("cond2", new StringBuilder().append("val2"));
        valueSetBuilder.removeCondition("cond3", new StringBuilder().append("val1")).removeCondition("cond3", new StringBuilder().append("val2"));
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, new StringBuilder().append("val"));
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition("cond", (Object) null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder.removeCondition(null, (Object) null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionsStringTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder = valueSetBuilder.removeConditions("cond1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder = valueSetBuilder.removeConditions("cond1");
        valueSetBuilder = valueSetBuilder.removeConditions("cond2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).isEmpty();

        valueSetBuilder = valueSetBuilder.addConditions(template);
        valueSetBuilder = valueSetBuilder.removeConditions((String) null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionsValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder.addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2");
        ValueSet<String> removeTemplate1 = valueSetBuilder.build();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2");
        ValueSet<String> removeTemplate2 = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions(removeTemplate1);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions(removeTemplate2);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions(addTemplate);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).isEmpty();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions((ValueSet<String>) null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> valueSet = valueSetBuilder.clearConditions().build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueSingleTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1", "val2", "val3");

        valueSetBuilder.addValue((String) null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllValues()).isEmpty();

        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue((String) null);
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueArrayTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1", "val2");
        valueSetBuilder.addValue("val3", "val4").addValue("val5", "val6");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder.addValue((String[]) null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllValues()).isEmpty();

        valueSetBuilder.addValue("val1", "val2");
        valueSetBuilder.addValue((String[]) null);
        valueSetBuilder.addValue("val3", "val4").addValue("val5", "val6");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder.addValue("val1", null);
        valueSetBuilder.addValue(null, "val2").addValue(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValuesValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1", "val2");
        valueSetBuilder.addValue("val3", "val4").addValue("val5", "val6");
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(template);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.addValue("val3", "val5", "val7");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6", "val7");

        valueSetBuilder.addValue("val3", "val5", "val7");
        valueSetBuilder = valueSetBuilder.addValues(template);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6", "val7");

        valueSetBuilder = valueSetBuilder.addValues(null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllValues()).isEmpty();

        valueSetBuilder = valueSetBuilder.addValues(null);
        valueSetBuilder.addValue("val3", "val5", "val7");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        Assertions.assertThat(valueSet5).isNotNull();
        Assertions.assertThat(valueSet5.getAllValues()).containsExactly("val3", "val5", "val7");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueSingleTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1", "val2", "val3", "val4", "val5", "val6");
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue("val2");
        valueSetBuilder.removeValue("val4").removeValue("val6");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1", "val3", "val5");

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue((String) null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue("val2");
        valueSetBuilder.removeValue((String) null);
        valueSetBuilder.removeValue("val4").removeValue("val6");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val3", "val5");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueArrayTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1", "val2", "val3", "val4", "val5", "val6");
        ValueSet<String> template = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue("val1", "val2");
        valueSetBuilder.removeValue("val2", "val3").removeValue("val3", "val4");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue((String[]) null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue("val1", "val2");
        valueSetBuilder.removeValue((String[]) null);
        valueSetBuilder.removeValue("val2", "val3").removeValue("val3", "val4");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(template);
        valueSetBuilder.removeValue("val1", null);
        valueSetBuilder.removeValue(null, "val2").removeValue(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4).isNotNull();
        Assertions.assertThat(valueSet4.getAllValues()).containsExactly("val3", "val4", "val5", "val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValuesValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1", "val2", "val3", "val4", "val5", "val6");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder.addValue("val4", "val5", "val6");
        ValueSet<String> removeTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder = valueSetBuilder.removeValues(removeTemplate);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1", "val2", "val3");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder = valueSetBuilder.removeValues(addTemplate);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllValues()).isEmpty();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder = valueSetBuilder.removeValues(null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet = valueSetBuilder.clearValues().build();
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
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet = valueSetBuilder.clear().build();
        Assertions.assertThat(valueSet).isNotNull();
        Assertions.assertThat(valueSet.getAllConditionNames()).isEmpty();
        Assertions.assertThat(valueSet.getAllValues()).isEmpty();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildWithClearFlagTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1");
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

        valueSetBuilder.addCondition("cond1", "val3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val3");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).isEmpty();
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildWithoutClearFlagTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet1).isNotNull();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).isEmpty();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet1.getAllValues()).containsExactly("val1");

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet2).isNotNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet2.getAllValues()).containsExactly("val1", "val2");

        valueSetBuilder.addCondition("cond1", "val3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet3).isNotNull();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val3");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).isEmpty();
        Assertions.assertThat(valueSet3.getAllValues()).containsExactly("val1", "val2", "val3");
    }

}
