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
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

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
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");
        ConditionSet conditionSet = new ConditionSet(conditions);

        Set<String> allNames = new HashSet<String>();
        allNames.add("cond1");
        allNames.add("cond2");
        allNames.add("cond3");

        Iterator<String> nameIterator = conditionSet.nameIterator();
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertFalse(nameIterator.hasNext());
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void getConditionTest() {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");

        ConditionSet conditionSet = new ConditionSet(conditions);
        Assert.assertEquals("val1", conditionSet.getCondition("cond1"));
        Assert.assertEquals("val2", conditionSet.getCondition("cond2"));
        Assert.assertEquals("val3", conditionSet.getCondition("cond3"));
        Assert.assertNull(conditionSet.getCondition("cond4"));
    }

    /**
     * {@link ConditionSet} class test.
     */
    @Test
    public void toStringTest() {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");

        ConditionSet conditionSet = new ConditionSet(conditions);
        String str = conditionSet.toString();
        Assert.assertTrue(str.contains("cond1=val1"));
        Assert.assertTrue(str.contains("cond2=val2"));
        Assert.assertTrue(str.contains("cond3=val3"));
    }

}
