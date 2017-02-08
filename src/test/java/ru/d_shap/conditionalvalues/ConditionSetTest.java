///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Conditional values.
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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link ConditionSet}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetTest {

    /**
     * Test class constructor.
     */
    public ConditionSetTest() {
        super();
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void nameIteratorTest() {
        Map<String, String> conditions = new HashMap<>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");

        ConditionSet conditionSet = new ConditionSet(conditions);
        Assertions.assertThat(conditionSet.nameIterator()).containsExactly("cond1", "cond2", "cond3");
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void getConditionTest() {
        Map<String, String> conditions = new HashMap<>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");

        ConditionSet conditionSet = new ConditionSet(conditions);
        Assertions.assertThat(conditionSet.getCondition("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet.getCondition("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet.getCondition("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet.getCondition("cond4")).isNull();
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void toStringTest() {
        Map<String, String> conditions = new HashMap<>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");

        ConditionSet conditionSet = new ConditionSet(conditions);
        Assertions.assertThat(conditionSet).toStringContains("cond1=val1");
        Assertions.assertThat(conditionSet).toStringContains("cond2=val2");
        Assertions.assertThat(conditionSet).toStringContains("cond3=val3");
    }

}
