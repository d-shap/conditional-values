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
 * Tests for {@link ConditionSetBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetBuilderTest {

    /**
     * Test class constructor.
     */
    public ConditionSetBuilderTest() {
        super();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionStringTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("val4");

        conditionSetBuilder.addCondition(null, "val");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();

        conditionSetBuilder.addCondition("cond", null);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).isEmpty();

        conditionSetBuilder.addCondition(null, null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4).isNotNull();
        Assertions.assertThat(conditionSet4.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionBooleanTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", true);
        conditionSetBuilder.addCondition("cond2", false);
        conditionSetBuilder.addCondition("cond3", true).addCondition("cond4", true);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("true");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("false");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("true");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("true");

        conditionSetBuilder.addCondition(null, true);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionIntTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", 1);
        conditionSetBuilder.addCondition("cond2", 2);
        conditionSetBuilder.addCondition("cond3", 3).addCondition("cond4", 4);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("3");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("4");

        conditionSetBuilder.addCondition(null, 0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionLongTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", 1L);
        conditionSetBuilder.addCondition("cond2", 2L);
        conditionSetBuilder.addCondition("cond3", 3L).addCondition("cond4", 4L);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("2");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("3");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("4");

        conditionSetBuilder.addCondition(null, 0L);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionFloatTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", 1.0f);
        conditionSetBuilder.addCondition("cond2", 2.0f);
        conditionSetBuilder.addCondition("cond3", 3.0f).addCondition("cond4", 4.0f);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("1.0");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("3.0");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("4.0");

        conditionSetBuilder.addCondition(null, 0.0f);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionDoubleTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", 1.0);
        conditionSetBuilder.addCondition("cond2", 2.0);
        conditionSetBuilder.addCondition("cond3", 3.0).addCondition("cond4", 4.0);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("1.0");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("2.0");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("3.0");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("4.0");

        conditionSetBuilder.addCondition(null, 0.0);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionObjectTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", new StringBuilder().append("val1"));
        conditionSetBuilder.addCondition("cond2", new StringBuilder().append("val2"));
        conditionSetBuilder.addCondition("cond3", new StringBuilder().append("val3")).addCondition("cond4", new StringBuilder().append("val4"));
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("val4");

        conditionSetBuilder.addCondition(null, new StringBuilder().append("val"));
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();

        conditionSetBuilder.addCondition("cond", (Object) null);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).isEmpty();

        conditionSetBuilder.addCondition(null, (Object) null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4).isNotNull();
        Assertions.assertThat(conditionSet4.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionsTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        ConditionSet template = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(template);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("val4");

        conditionSetBuilder = conditionSetBuilder.addConditions(template);
        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5");
        Assertions.assertThat(conditionSet2.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getCondition("cond2")).isEqualTo("val6");
        Assertions.assertThat(conditionSet2.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getCondition("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet2.getCondition("cond5")).isEqualTo("val5");

        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        conditionSetBuilder = conditionSetBuilder.addConditions(template);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5");
        Assertions.assertThat(conditionSet3.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getCondition("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet3.getCondition("cond5")).isEqualTo("val5");

        conditionSetBuilder = conditionSetBuilder.addConditions(null);
        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4).isNotNull();
        Assertions.assertThat(conditionSet4.nameIterator()).isEmpty();

        conditionSetBuilder = conditionSetBuilder.addConditions(null);
        conditionSetBuilder.addCondition("cond2", "val6");
        conditionSetBuilder.addCondition("cond5", "val5");
        ConditionSet conditionSet5 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet5).isNotNull();
        Assertions.assertThat(conditionSet5.nameIterator()).containsExactly("cond2", "cond5");
        Assertions.assertThat(conditionSet5.getCondition("cond2")).isEqualTo("val6");
        Assertions.assertThat(conditionSet5.getCondition("cond5")).isEqualTo("val5");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5").addCondition("cond6", "val6");
        ConditionSet template = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(template);
        conditionSetBuilder.removeCondition("cond1");
        conditionSetBuilder.removeCondition("cond3");
        conditionSetBuilder.removeCondition("cond5").removeCondition("cond6");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isEqualTo("val4");

        conditionSetBuilder = conditionSetBuilder.addConditions(template);
        conditionSetBuilder.removeCondition(null);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6");
        Assertions.assertThat(conditionSet2.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet2.getCondition("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet2.getCondition("cond5")).isEqualTo("val5");
        Assertions.assertThat(conditionSet2.getCondition("cond6")).isEqualTo("val6");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionsTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5").addCondition("cond6", "val6");
        ConditionSet addTemplate = conditionSetBuilder.build();

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5");
        ConditionSet removeTemplate = conditionSetBuilder.build();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditions(removeTemplate);
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond2", "cond3", "cond6");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet1.getCondition("cond6")).isEqualTo("val6");

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditions(addTemplate);
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).isEmpty();

        conditionSetBuilder = conditionSetBuilder.addConditions(addTemplate);
        conditionSetBuilder = conditionSetBuilder.removeConditions(null);
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6");
        Assertions.assertThat(conditionSet3.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getCondition("cond4")).isEqualTo("val4");
        Assertions.assertThat(conditionSet3.getCondition("cond5")).isEqualTo("val5");
        Assertions.assertThat(conditionSet3.getCondition("cond6")).isEqualTo("val6");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void clearTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2");
        conditionSetBuilder.addCondition("cond3", "val3").addCondition("cond4", "val4");
        conditionSetBuilder.addCondition("cond5", "val5").addCondition("cond6", "val6");
        ConditionSet conditionSet = conditionSetBuilder.clear().build();
        Assertions.assertThat(conditionSet).isNotNull();
        Assertions.assertThat(conditionSet.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void buildWithClearFlagTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond2");
        Assertions.assertThat(conditionSet2.getCondition("cond1")).isNull();
        Assertions.assertThat(conditionSet2.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getCondition("cond3")).isNull();
        Assertions.assertThat(conditionSet2.getCondition("cond4")).isNull();

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond3");
        Assertions.assertThat(conditionSet3.getCondition("cond1")).isNull();
        Assertions.assertThat(conditionSet3.getCondition("cond2")).isNull();
        Assertions.assertThat(conditionSet3.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getCondition("cond4")).isNull();

        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4).isNotNull();
        Assertions.assertThat(conditionSet4.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void buildWithoutClearFlagTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getCondition("cond4")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2");
        Assertions.assertThat(conditionSet2.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getCondition("cond3")).isNull();
        Assertions.assertThat(conditionSet2.getCondition("cond4")).isNull();

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(conditionSet3.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getCondition("cond4")).isNull();

        ConditionSet conditionSet4 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet4).isNotNull();
        Assertions.assertThat(conditionSet4.nameIterator()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(conditionSet4.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet4.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet4.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet4.getCondition("cond4")).isNull();
    }

}
