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
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Set<String> allConditionNames1 = valueSet1.getAllConditionNames();
        Assert.assertEquals(1, allConditionNames1.size());
        Assert.assertTrue(allConditionNames1.contains("cond1"));
        Assert.assertFalse(allConditionNames1.contains("cond2"));
        Assert.assertFalse(allConditionNames1.contains("cond3"));
        Set<String> allConditionValues11 = valueSet1.getAllConditionValues("cond1");
        Assert.assertEquals(1, allConditionValues11.size());
        Assert.assertTrue(allConditionValues11.contains("val11"));
        Assert.assertFalse(allConditionValues11.contains("val12"));
        Assert.assertFalse(allConditionValues11.contains("val13"));
        Set<String> allConditionValues12 = valueSet1.getAllConditionValues("cond2");
        Assert.assertEquals(0, allConditionValues12.size());
        Set<String> allConditionValues13 = valueSet1.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues13.size());
        Set<String> allValues1 = valueSet1.getAllValues();
        Assert.assertEquals(1, allValues1.size());
        Assert.assertTrue(allValues1.contains("val1"));
        Assert.assertFalse(allValues1.contains("val2"));
        Assert.assertFalse(allValues1.contains("val3"));
        Assert.assertFalse(allValues1.contains("val4"));

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Set<String> allConditionNames2 = valueSet2.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames2.size());
        Assert.assertTrue(allConditionNames2.contains("cond1"));
        Assert.assertTrue(allConditionNames2.contains("cond2"));
        Assert.assertFalse(allConditionNames2.contains("cond3"));
        Set<String> allConditionValues21 = valueSet2.getAllConditionValues("cond1");
        Assert.assertEquals(1, allConditionValues21.size());
        Assert.assertTrue(allConditionValues21.contains("val11"));
        Assert.assertFalse(allConditionValues21.contains("val12"));
        Assert.assertFalse(allConditionValues21.contains("val13"));
        Set<String> allConditionValues22 = valueSet2.getAllConditionValues("cond2");
        Assert.assertEquals(1, allConditionValues22.size());
        Assert.assertTrue(allConditionValues22.contains("val2"));
        Set<String> allConditionValues23 = valueSet2.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues23.size());
        Set<String> allValues2 = valueSet2.getAllValues();
        Assert.assertEquals(2, allValues2.size());
        Assert.assertTrue(allValues2.contains("val1"));
        Assert.assertTrue(allValues2.contains("val2"));
        Assert.assertFalse(allValues2.contains("val3"));
        Assert.assertFalse(allValues2.contains("val4"));

        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Set<String> allConditionNames3 = valueSet3.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames3.size());
        Assert.assertTrue(allConditionNames3.contains("cond1"));
        Assert.assertTrue(allConditionNames3.contains("cond2"));
        Assert.assertFalse(allConditionNames3.contains("cond3"));
        Set<String> allConditionValues31 = valueSet3.getAllConditionValues("cond1");
        Assert.assertEquals(2, allConditionValues31.size());
        Assert.assertTrue(allConditionValues31.contains("val11"));
        Assert.assertTrue(allConditionValues31.contains("val12"));
        Assert.assertFalse(allConditionValues31.contains("val13"));
        Set<String> allConditionValues32 = valueSet3.getAllConditionValues("cond2");
        Assert.assertEquals(1, allConditionValues32.size());
        Assert.assertTrue(allConditionValues32.contains("val2"));
        Set<String> allConditionValues33 = valueSet3.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues33.size());
        Set<String> allValues3 = valueSet3.getAllValues();
        Assert.assertEquals(3, allValues3.size());
        Assert.assertTrue(allValues3.contains("val1"));
        Assert.assertTrue(allValues3.contains("val2"));
        Assert.assertTrue(allValues3.contains("val3"));
        Assert.assertFalse(allValues3.contains("val4"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildAndClearTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();

        valueSetBuilder.addCondition("cond1", "val11");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.buildAndClear();
        Set<String> allConditionNames1 = valueSet1.getAllConditionNames();
        Assert.assertEquals(1, allConditionNames1.size());
        Assert.assertTrue(allConditionNames1.contains("cond1"));
        Assert.assertFalse(allConditionNames1.contains("cond2"));
        Assert.assertFalse(allConditionNames1.contains("cond3"));
        Set<String> allConditionValues11 = valueSet1.getAllConditionValues("cond1");
        Assert.assertEquals(1, allConditionValues11.size());
        Assert.assertTrue(allConditionValues11.contains("val11"));
        Assert.assertFalse(allConditionValues11.contains("val12"));
        Assert.assertFalse(allConditionValues11.contains("val13"));
        Set<String> allConditionValues12 = valueSet1.getAllConditionValues("cond2");
        Assert.assertEquals(0, allConditionValues12.size());
        Set<String> allConditionValues13 = valueSet1.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues13.size());
        Set<String> allValues1 = valueSet1.getAllValues();
        Assert.assertEquals(1, allValues1.size());
        Assert.assertTrue(allValues1.contains("val1"));
        Assert.assertFalse(allValues1.contains("val2"));
        Assert.assertFalse(allValues1.contains("val3"));
        Assert.assertFalse(allValues1.contains("val4"));

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.buildAndClear();
        Set<String> allConditionNames2 = valueSet2.getAllConditionNames();
        Assert.assertEquals(1, allConditionNames2.size());
        Assert.assertFalse(allConditionNames2.contains("cond1"));
        Assert.assertTrue(allConditionNames2.contains("cond2"));
        Assert.assertFalse(allConditionNames2.contains("cond3"));
        Set<String> allConditionValues21 = valueSet2.getAllConditionValues("cond1");
        Assert.assertEquals(0, allConditionValues21.size());
        Set<String> allConditionValues22 = valueSet2.getAllConditionValues("cond2");
        Assert.assertEquals(1, allConditionValues22.size());
        Assert.assertTrue(allConditionValues22.contains("val2"));
        Set<String> allConditionValues23 = valueSet2.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues23.size());
        Set<String> allValues2 = valueSet2.getAllValues();
        Assert.assertEquals(1, allValues2.size());
        Assert.assertFalse(allValues2.contains("val1"));
        Assert.assertTrue(allValues2.contains("val2"));
        Assert.assertFalse(allValues2.contains("val3"));
        Assert.assertFalse(allValues2.contains("val4"));

        valueSetBuilder.addCondition("cond1", "val12");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.buildAndClear();
        Set<String> allConditionNames3 = valueSet3.getAllConditionNames();
        Assert.assertEquals(1, allConditionNames3.size());
        Assert.assertTrue(allConditionNames3.contains("cond1"));
        Assert.assertFalse(allConditionNames3.contains("cond2"));
        Assert.assertFalse(allConditionNames3.contains("cond3"));
        Set<String> allConditionValues31 = valueSet3.getAllConditionValues("cond1");
        Assert.assertEquals(1, allConditionValues31.size());
        Assert.assertFalse(allConditionValues31.contains("val11"));
        Assert.assertTrue(allConditionValues31.contains("val12"));
        Assert.assertFalse(allConditionValues31.contains("val13"));
        Set<String> allConditionValues32 = valueSet3.getAllConditionValues("cond2");
        Assert.assertEquals(0, allConditionValues32.size());
        Set<String> allConditionValues33 = valueSet3.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues33.size());
        Set<String> allValues3 = valueSet3.getAllValues();
        Assert.assertEquals(1, allValues3.size());
        Assert.assertFalse(allValues3.contains("val1"));
        Assert.assertFalse(allValues3.contains("val2"));
        Assert.assertTrue(allValues3.contains("val3"));
        Assert.assertFalse(allValues3.contains("val4"));
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
        valueSetBuilder.addCondition(null, (Object) null);

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
    public void addConditionChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder = valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder = valueSetBuilder.addCondition("cond2", true);
        valueSetBuilder = valueSetBuilder.addCondition("cond3", 1);
        valueSetBuilder = valueSetBuilder.addCondition("cond4", 2L);
        valueSetBuilder = valueSetBuilder.addCondition("cond5", 1.1f);
        valueSetBuilder = valueSetBuilder.addCondition("cond6", 1.2);
        valueSetBuilder = valueSetBuilder.addCondition("cond7", new StringBuilder("val2"));

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(7, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond1").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond1").contains("val1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond2").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond2").contains("true"));
        Assert.assertTrue(allConditionNames.contains("cond3"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond3").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond3").contains("1"));
        Assert.assertTrue(allConditionNames.contains("cond4"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond4").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond4").contains("2"));
        Assert.assertTrue(allConditionNames.contains("cond5"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond5").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond5").contains("1.1"));
        Assert.assertTrue(allConditionNames.contains("cond6"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond6").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond6").contains("1.2"));
        Assert.assertTrue(allConditionNames.contains("cond7"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond7").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond7").contains("val2"));
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
        valueSetBuilder.removeCondition(null, (Object) null);

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
    public void removeConditionChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
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
        valueSetBuilder = valueSetBuilder.removeCondition("cond8");

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(7, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond1").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond1").contains("val2"));
        Assert.assertTrue(allConditionNames.contains("cond2"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond2").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond2").contains("false"));
        Assert.assertTrue(allConditionNames.contains("cond3"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond3").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond3").contains("3"));
        Assert.assertTrue(allConditionNames.contains("cond4"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond4").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond4").contains("4"));
        Assert.assertTrue(allConditionNames.contains("cond5"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond5").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond5").contains("1.3"));
        Assert.assertTrue(allConditionNames.contains("cond6"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond6").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond6").contains("1.4"));
        Assert.assertTrue(allConditionNames.contains("cond7"));
        Assert.assertEquals(1, valueSet.getAllConditionValues("cond7").size());
        Assert.assertTrue(valueSet.getAllConditionValues("cond7").contains("val3"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", true);
        valueSetBuilder.addCondition("cond3", 1);
        valueSetBuilder.addCondition("cond4", 2L);
        valueSetBuilder.addCondition("cond5", 1.1f);
        valueSetBuilder.addCondition("cond6", 1.2);
        valueSetBuilder.addCondition("cond7", new StringBuilder("val2"));

        valueSetBuilder = valueSetBuilder.clearConditions();

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(0, allConditionNames.size());
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
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
    public void addValueChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder = valueSetBuilder.addValue("val1", "val2");
        valueSetBuilder = valueSetBuilder.addValue("val3");
        valueSetBuilder = valueSetBuilder.addValue("val4");

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(4, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val3"));
        Assert.assertTrue(allValues.contains("val4"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
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

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueChainTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val4", "val5");
        valueSetBuilder.addValue("val6");

        valueSetBuilder = valueSetBuilder.removeValue("val1", "val3");
        valueSetBuilder = valueSetBuilder.removeValue("val5");

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(3, allValues.size());
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val4"));
        Assert.assertTrue(allValues.contains("val6"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val4", "val5");
        valueSetBuilder.addValue("val6");

        valueSetBuilder = valueSetBuilder.clearValues();

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(0, allValues.size());
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
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

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(0, allConditionNames.size());
        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(0, allValues.size());
    }

}
