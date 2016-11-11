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

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

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
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2", "val3");
        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));

        Set<String> allConditionValues1 = valueSet.getAllConditionValues("cond1");
        Assert.assertEquals(2, allConditionValues1.size());
        Assert.assertTrue(allConditionValues1.contains("val11"));
        Assert.assertTrue(allConditionValues1.contains("val12"));

        Set<String> allConditionValues2 = valueSet.getAllConditionValues("cond2");
        Assert.assertEquals(1, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val2"));

        Set<String> allConditionValues3 = valueSet.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues3.size());

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(3, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val3"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
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
        valueSetBuilder.addCondition(null, null);
        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));

        Set<String> allConditionValues1 = valueSet.getAllConditionValues("cond1");
        Assert.assertEquals(6, allConditionValues1.size());
        Assert.assertTrue(allConditionValues1.contains("true"));
        Assert.assertTrue(allConditionValues1.contains("1"));
        Assert.assertTrue(allConditionValues1.contains("2"));
        Assert.assertTrue(allConditionValues1.contains("3"));
        Assert.assertTrue(allConditionValues1.contains("3.5"));
        Assert.assertTrue(allConditionValues1.contains("4.9"));

        Set<String> allConditionValues2 = valueSet.getAllConditionValues("cond2");
        Assert.assertEquals(4, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val1"));
        Assert.assertTrue(allConditionValues2.contains("val2"));
        Assert.assertTrue(allConditionValues2.contains("val3"));
        Assert.assertTrue(allConditionValues2.contains("val4"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
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

        valueSetBuilder.removeCondition("cond1");
        valueSetBuilder.removeCondition(null);
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
        valueSetBuilder.removeCondition(null, null);

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond3"));
        Assert.assertTrue(allConditionNames.contains("cond4"));

        Set<String> allConditionValues1 = valueSet.getAllConditionValues("cond3");
        Assert.assertEquals(1, allConditionValues1.size());
        Assert.assertTrue(allConditionValues1.contains("4.9"));

        Set<String> allConditionValues2 = valueSet.getAllConditionValues("cond4");
        Assert.assertEquals(1, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val4"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue((String) null);
        valueSetBuilder.addValue((String[]) null);
        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(5, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val3"));
        Assert.assertTrue(allValues.contains("val4"));
        Assert.assertTrue(allValues.contains("val5"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("val1", "val3", "val2");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue("val20", "val17", "val19", "val18");

        valueSetBuilder.removeValue("val17", "val18");
        valueSetBuilder.removeValue("val3");
        valueSetBuilder.removeValue((String) null);
        valueSetBuilder.removeValue((String[]) null);

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(6, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val4"));
        Assert.assertTrue(allValues.contains("val5"));
        Assert.assertTrue(allValues.contains("val19"));
        Assert.assertTrue(allValues.contains("val20"));
    }

}
