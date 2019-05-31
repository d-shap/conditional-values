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
        Map<String, String> conditions1 = new HashMap<>();
        ConditionSet conditionSet1 = new ConditionSet(conditions1);
        Assertions.assertThat(conditionSet1.nameIterator()).containsExactly();

        Map<String, String> conditions2 = new HashMap<>();
        conditions2.put("cond1", "val1");
        ConditionSet conditionSet2 = new ConditionSet(conditions2);
        Assertions.assertThat(conditionSet2.nameIterator()).containsExactly("cond1");

        Map<String, String> conditions3 = new HashMap<>();
        conditions3.put("cond1", "val1");
        conditions3.put("cond2", "val2");
        conditions3.put("cond3", "val3");
        ConditionSet conditionSet3 = new ConditionSet(conditions3);
        Assertions.assertThat(conditionSet3.nameIterator()).containsExactly("cond1", "cond2", "cond3");
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void getValueTest() {
        Map<String, String> conditions1 = new HashMap<>();
        ConditionSet conditionSet1 = new ConditionSet(conditions1);
        Assertions.assertThat(conditionSet1.getValue("cond1")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond2")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet1.getValue("cond4")).isNull();

        Map<String, String> conditions2 = new HashMap<>();
        conditions2.put("cond1", "val1");
        ConditionSet conditionSet2 = new ConditionSet(conditions2);
        Assertions.assertThat(conditionSet2.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet2.getValue("cond2")).isNull();
        Assertions.assertThat(conditionSet2.getValue("cond3")).isNull();
        Assertions.assertThat(conditionSet2.getValue("cond4")).isNull();

        Map<String, String> conditions3 = new HashMap<>();
        conditions3.put("cond1", "val1");
        conditions3.put("cond2", "val2");
        conditions3.put("cond3", "val3");
        ConditionSet conditionSet3 = new ConditionSet(conditions3);
        Assertions.assertThat(conditionSet3.getValue("cond1")).isEqualTo("val1");
        Assertions.assertThat(conditionSet3.getValue("cond2")).isEqualTo("val2");
        Assertions.assertThat(conditionSet3.getValue("cond3")).isEqualTo("val3");
        Assertions.assertThat(conditionSet3.getValue("cond4")).isNull();
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void toStringTest() {
        Map<String, String> conditions1 = new HashMap<>();
        ConditionSet conditionSet1 = new ConditionSet(conditions1);
        Assertions.assertThat(conditionSet1).hasToString("{}");

        Map<String, String> conditions2 = new HashMap<>();
        conditions2.put("cond1", "val1");
        ConditionSet conditionSet2 = new ConditionSet(conditions2);
        Assertions.assertThat(conditionSet2).hasToString("{cond1=val1}");

        Map<String, String> conditions3 = new HashMap<>();
        conditions3.put("cond1", "val1");
        conditions3.put("cond2", "val2");
        conditions3.put("cond3", "val3");
        ConditionSet conditionSet3 = new ConditionSet(conditions3);
        Assertions.assertThat(conditionSet3).toStringContains("cond1=val1");
        Assertions.assertThat(conditionSet3).toStringContains("cond2=val2");
        Assertions.assertThat(conditionSet3).toStringContains("cond3=val3");
    }

}
