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
    public void buildTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1", "cond2");
        Assertions.assertThat(conditionSet2.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getCondition("cond3")).isNull();

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build(false);
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(conditionSet3.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getCondition("cond3")).isEqualTo("val3");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void buildAndClearTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet1).isNotNull();
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly("cond1");
        Assertions.assertThat(conditionSet1.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet1.getCondition("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getCondition("cond3")).isNull();

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet2).isNotNull();
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond2");
        Assertions.assertThat(conditionSet2.getCondition("cond1")).isNull();
        Assertions.assertThat(conditionSet2.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet2.getCondition("cond3")).isNull();

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet3).isNotNull();
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond3");
        Assertions.assertThat(conditionSet3.getCondition("cond1")).isNull();
        Assertions.assertThat(conditionSet3.getCondition("cond2")).isNull();
        Assertions.assertThat(conditionSet3.getCondition("cond3")).isEqualTo("val3");

        ConditionSet conditionSet4 = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet4).isNotNull();
        Assertions.assertThat(conditionSet4.nameIterator()).isEmpty();
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", true);
        conditionSetBuilder.addCondition("cond3", false);
        conditionSetBuilder.addCondition("cond4", 1);
        conditionSetBuilder.addCondition("cond5", 2);
        conditionSetBuilder.addCondition("cond6", 7L);
        conditionSetBuilder.addCondition("cond7", 3.5f);
        conditionSetBuilder.addCondition("cond8", 4.9);
        StringBuilder value9 = new StringBuilder().append("val2");
        conditionSetBuilder.addCondition("cond9", value9);
        Object value10 = "val3";
        conditionSetBuilder.addCondition("cond10", value10);
        String value11 = null;
        conditionSetBuilder.addCondition("cond11", value11);
        Object value12 = null;
        conditionSetBuilder.addCondition("cond12", value12);
        conditionSetBuilder.addCondition(null, "val3");
        conditionSetBuilder.addCondition(null, null);

        ConditionSet conditionSet = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet).isNotNull();
        Assertions.assertThat(conditionSet.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6", "cond7", "cond8", "cond9", "cond10");
        Assertions.assertThat(conditionSet.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet.getCondition("cond2")).isEqualTo("true");
        Assertions.assertThat(conditionSet.getCondition("cond3")).isEqualTo("false");
        Assertions.assertThat(conditionSet.getCondition("cond4")).isEqualTo("1");
        Assertions.assertThat(conditionSet.getCondition("cond5")).isEqualTo("2");
        Assertions.assertThat(conditionSet.getCondition("cond6")).isEqualTo("7");
        Assertions.assertThat(conditionSet.getCondition("cond7")).isEqualTo("3.5");
        Assertions.assertThat(conditionSet.getCondition("cond8")).isEqualTo("4.9");
        Assertions.assertThat(conditionSet.getCondition("cond9")).isEqualTo("val2");
        Assertions.assertThat(conditionSet.getCondition("cond10")).isEqualTo("val3");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void addConditionChainTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();
        conditionSetBuilder = conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder = conditionSetBuilder.addCondition("cond2", true);
        conditionSetBuilder = conditionSetBuilder.addCondition("cond3", 1);
        conditionSetBuilder = conditionSetBuilder.addCondition("cond4", 2L);
        conditionSetBuilder = conditionSetBuilder.addCondition("cond5", 1.1f);
        conditionSetBuilder = conditionSetBuilder.addCondition("cond6", 1.2);
        conditionSetBuilder = conditionSetBuilder.addCondition("cond7", new StringBuilder("val2"));

        ConditionSet conditionSet = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet).isNotNull();
        Assertions.assertThat(conditionSet.nameIterator()).containsExactly("cond1", "cond2", "cond3", "cond4", "cond5", "cond6", "cond7");
        Assertions.assertThat(conditionSet.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet.getCondition("cond2")).isEqualTo("true");
        Assertions.assertThat(conditionSet.getCondition("cond3")).isEqualTo("1");
        Assertions.assertThat(conditionSet.getCondition("cond4")).isEqualTo("2");
        Assertions.assertThat(conditionSet.getCondition("cond5")).isEqualTo("1.1");
        Assertions.assertThat(conditionSet.getCondition("cond6")).isEqualTo("1.2");
        Assertions.assertThat(conditionSet.getCondition("cond7")).isEqualTo("val2");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", true);
        conditionSetBuilder.addCondition("cond3", false);
        conditionSetBuilder.addCondition("cond4", 1);
        conditionSetBuilder.addCondition("cond5", 2);
        conditionSetBuilder.addCondition("cond6", 7L);
        conditionSetBuilder.addCondition("cond7", 3.5f);
        conditionSetBuilder.addCondition("cond8", 4.9);
        StringBuilder value9 = new StringBuilder().append("val2");
        conditionSetBuilder.addCondition("cond9", value9);
        Object value10 = "val3";
        conditionSetBuilder.addCondition("cond10", value10);

        conditionSetBuilder.removeCondition("cond3");
        conditionSetBuilder.removeCondition("cond5");
        conditionSetBuilder.removeCondition("cond9");
        conditionSetBuilder.removeCondition(null);

        ConditionSet conditionSet = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet).isNotNull();
        Assertions.assertThat(conditionSet.nameIterator()).containsExactly("cond1", "cond2", "cond4", "cond6", "cond7", "cond8", "cond10");
        Assertions.assertThat(conditionSet.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet.getCondition("cond2")).isEqualTo("true");
        Assertions.assertThat(conditionSet.getCondition("cond3")).isNull();
        Assertions.assertThat(conditionSet.getCondition("cond4")).isEqualTo("1");
        Assertions.assertThat(conditionSet.getCondition("cond5")).isNull();
        Assertions.assertThat(conditionSet.getCondition("cond6")).isEqualTo("7");
        Assertions.assertThat(conditionSet.getCondition("cond7")).isEqualTo("3.5");
        Assertions.assertThat(conditionSet.getCondition("cond8")).isEqualTo("4.9");
        Assertions.assertThat(conditionSet.getCondition("cond9")).isNull();
        Assertions.assertThat(conditionSet.getCondition("cond10")).isEqualTo("val3");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void removeConditionChainTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", true);
        conditionSetBuilder.addCondition("cond3", 1);
        conditionSetBuilder.addCondition("cond4", 2L);

        conditionSetBuilder = conditionSetBuilder.removeCondition("cond1");
        conditionSetBuilder = conditionSetBuilder.removeCondition("cond3");

        ConditionSet conditionSet = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet).isNotNull();
        Assertions.assertThat(conditionSet.nameIterator()).containsExactly("cond2", "cond4");
        Assertions.assertThat(conditionSet.getCondition("cond2")).isEqualTo("true");
        Assertions.assertThat(conditionSet.getCondition("cond4")).isEqualTo("2");
    }

    /**
     * {@link ConditionSetBuilder} class test.
     */
    @Test
    public void clearTest() {
        ConditionSetBuilder conditionSetBuilder = new ConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", true);
        conditionSetBuilder.addCondition("cond3", 1);
        conditionSetBuilder.addCondition("cond4", 2L);

        conditionSetBuilder = conditionSetBuilder.clear();

        ConditionSet conditionSet = conditionSetBuilder.build();
        Assertions.assertThat(conditionSet.nameIterator()).isEmpty();
    }

}
