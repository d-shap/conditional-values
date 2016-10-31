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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

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
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();

        valueSetBuilder.addStringCondition("cond1", "val1");
        valueSetBuilder.addStringCondition("cond2", "val2");
        ValueSet<String> valueSet11 = valueSetBuilder.build();
        valueSetBuilder.addStringCondition("cond3", "val3");
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets1 = new ArrayList<ValueSet<String>>();
        valueSets1.add(valueSet11);
        valueSets1.add(valueSet12);
        Values<String> values1 = new Values<String>(valueSets1);
        Assert.assertTrue(values1.isEmpty());

        valueSetBuilder.addStringCondition("cond1", "val1");
        valueSetBuilder.addStringCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        valueSetBuilder.addStringCondition("cond3", "val3");
        ValueSet<String> valueSet22 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets2 = new ArrayList<ValueSet<String>>();
        valueSets2.add(valueSet21);
        valueSets2.add(valueSet22);
        Values<String> values2 = new Values<String>(valueSets2);
        Assert.assertFalse(values2.isEmpty());

        List<ValueSet<String>> valueSets3 = new ArrayList<ValueSet<String>>();
        valueSets3.add(valueSet22);
        Values<String> values3 = new Values<String>(valueSets3);
        Assert.assertTrue(values3.isEmpty());
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void containsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addStringCondition("cond1", "val1");
        valueSetBuilder.addStringCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addStringCondition("cond3", "val3");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);

        Values<String> values = new Values<String>(valueSets);
        Assert.assertTrue(values.contains("val1"));
        Assert.assertTrue(values.contains("val2"));
        Assert.assertTrue(values.contains("val3"));
        Assert.assertTrue(values.contains("val4"));
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void getAllValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addStringCondition("cond1", "val1");
        valueSetBuilder.addStringCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addStringCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<String>(valueSets);

        Set<String> allValues = values.getAllValues();
        Assert.assertEquals(6, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val3"));
        Assert.assertTrue(allValues.contains("val4"));
        Assert.assertTrue(allValues.contains("val5"));
        Assert.assertTrue(allValues.contains("val6"));
    }

    /**
     * {@link Values} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addStringCondition("cond1", "val11", "val12");
        valueSetBuilder.addStringCondition("cond2", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addStringCondition("cond3", "val31", "val32");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<String>(valueSets);
        String str = values.toString();
        Assert.assertTrue(str.contains("cond1=["));
        Assert.assertTrue(str.contains("cond2=[val2]"));
        Assert.assertTrue(str.contains("cond3=["));
    }

}
