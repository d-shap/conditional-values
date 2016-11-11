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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

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
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Iterator<String> conditionSet1Names = conditionSet1.nameIterator();
        Assert.assertEquals("cond1", conditionSet1Names.next());
        Assert.assertFalse(conditionSet1Names.hasNext());
        Assert.assertEquals("val1", conditionSet1.getCondition("cond1"));

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Iterator<String> conditionSet2Names = conditionSet2.nameIterator();
        Assert.assertEquals("cond2", conditionSet2Names.next());
        Assert.assertFalse(conditionSet2Names.hasNext());
        Assert.assertEquals("val2", conditionSet2.getCondition("cond2"));

        conditionSetBuilder.addCondition("cond3", "val3");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Iterator<String> conditionSet3Names = conditionSet3.nameIterator();
        Assert.assertEquals("cond3", conditionSet3Names.next());
        Assert.assertFalse(conditionSet3Names.hasNext());
        Assert.assertEquals("val3", conditionSet3.getCondition("cond3"));
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

        Set<String> allNames = new HashSet<String>();
        allNames.add("cond1");
        allNames.add("cond2");
        allNames.add("cond3");
        allNames.add("cond4");
        allNames.add("cond5");
        allNames.add("cond6");
        allNames.add("cond7");
        allNames.add("cond8");
        allNames.add("cond9");
        allNames.add("cond10");

        ConditionSet conditionSet = conditionSetBuilder.build();
        Iterator<String> nameIterator = conditionSet.nameIterator();
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertFalse(nameIterator.hasNext());

        Assert.assertEquals("val1", conditionSet.getCondition("cond1"));
        Assert.assertEquals("true", conditionSet.getCondition("cond2"));
        Assert.assertEquals("false", conditionSet.getCondition("cond3"));
        Assert.assertEquals("1", conditionSet.getCondition("cond4"));
        Assert.assertEquals("2", conditionSet.getCondition("cond5"));
        Assert.assertEquals("7", conditionSet.getCondition("cond6"));
        Assert.assertEquals("3.5", conditionSet.getCondition("cond7"));
        Assert.assertEquals("4.9", conditionSet.getCondition("cond8"));
        Assert.assertEquals("val2", conditionSet.getCondition("cond9"));
        Assert.assertEquals("val3", conditionSet.getCondition("cond10"));
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

        Set<String> allNames = new HashSet<String>();
        allNames.add("cond1");
        allNames.add("cond2");
        allNames.add("cond4");
        allNames.add("cond6");
        allNames.add("cond7");
        allNames.add("cond8");
        allNames.add("cond10");

        ConditionSet conditionSet = conditionSetBuilder.build();
        Iterator<String> nameIterator = conditionSet.nameIterator();
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertFalse(nameIterator.hasNext());

        Assert.assertEquals("val1", conditionSet.getCondition("cond1"));
        Assert.assertEquals("true", conditionSet.getCondition("cond2"));
        Assert.assertNull(conditionSet.getCondition("cond3"));
        Assert.assertEquals("1", conditionSet.getCondition("cond4"));
        Assert.assertNull(conditionSet.getCondition("cond5"));
        Assert.assertEquals("7", conditionSet.getCondition("cond6"));
        Assert.assertEquals("3.5", conditionSet.getCondition("cond7"));
        Assert.assertEquals("4.9", conditionSet.getCondition("cond8"));
        Assert.assertNull(conditionSet.getCondition("cond9"));
        Assert.assertEquals("val3", conditionSet.getCondition("cond10"));
    }

}
