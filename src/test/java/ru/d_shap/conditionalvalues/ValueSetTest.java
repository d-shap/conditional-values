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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ValueSet}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetTest {

    /**
     * Test class constructor.
     */
    public ValueSetTest() {
        super();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllConditionNamesTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val21");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<>();
        ValueSet<String> valueSet = new ValueSet<>(conditions, values);

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllConditionValuesTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        condition1.add("val12");
        condition1.add("val13");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val21");
        condition2.add("val22");
        condition2.add("val23");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<>();
        ValueSet<String> valueSet = new ValueSet<>(conditions, values);

        Set<String> allConditionValues1 = valueSet.getAllConditionValues("cond1");
        Assert.assertEquals(3, allConditionValues1.size());
        Assert.assertTrue(allConditionValues1.contains("val11"));
        Assert.assertTrue(allConditionValues1.contains("val12"));
        Assert.assertTrue(allConditionValues1.contains("val13"));

        Set<String> allConditionValues2 = valueSet.getAllConditionValues("cond2");
        Assert.assertEquals(3, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val21"));
        Assert.assertTrue(allConditionValues2.contains("val22"));
        Assert.assertTrue(allConditionValues2.contains("val23"));

        Set<String> allConditionValues3 = valueSet.getAllConditionValues("cond3");
        Assert.assertEquals(0, allConditionValues3.size());
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void isMatchConditionsTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        condition1.add("val12");
        condition1.add("val13");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val21");
        condition2.add("val22");
        condition2.add("val23");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<>();
        ValueSet<String> valueSet = new ValueSet<>(conditions, values);

        Map<String, String> conditions1 = new HashMap<>();
        conditions1.put("cond1", "val11");
        conditions1.put("cond2", "val21");
        ConditionSet conditionSet1 = new ConditionSet(conditions1);
        Assert.assertTrue(valueSet.isMatchConditions(conditionSet1));

        Map<String, String> conditions2 = new HashMap<>();
        conditions2.put("cond1", "val12");
        conditions2.put("cond2", "val22");
        ConditionSet conditionSet2 = new ConditionSet(conditions2);
        Assert.assertTrue(valueSet.isMatchConditions(conditionSet2));

        Map<String, String> conditions3 = new HashMap<>();
        conditions3.put("cond1", "val13");
        ConditionSet conditionSet3 = new ConditionSet(conditions3);
        Assert.assertFalse(valueSet.isMatchConditions(conditionSet3));

        Map<String, String> conditions4 = new HashMap<>();
        ConditionSet conditionSet4 = new ConditionSet(conditions4);
        Assert.assertFalse(valueSet.isMatchConditions(conditionSet4));

        Map<String, String> conditions5 = new HashMap<>();
        conditions5.put("cond1", "val14");
        ConditionSet conditionSet5 = new ConditionSet(conditions5);
        Assert.assertFalse(valueSet.isMatchConditions(conditionSet5));

        Map<String, String> conditions6 = new HashMap<>();
        conditions6.put("cond1", "val12");
        conditions6.put("cond2", "val24");
        ConditionSet conditionSet6 = new ConditionSet(conditions6);
        Assert.assertFalse(valueSet.isMatchConditions(conditionSet6));

        Map<String, String> conditions7 = new HashMap<>();
        conditions7.put("cond1", "val12");
        conditions7.put("cond2", "val22");
        conditions7.put("cond3", "val32");
        conditions7.put("cond4", "val42");
        ConditionSet conditionSet7 = new ConditionSet(conditions7);
        Assert.assertTrue(valueSet.isMatchConditions(conditionSet7));
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void isMoreSpecificValueSetTest() {
        Map<String, Set<String>> conditions1 = new HashMap<>();
        Set<String> condition11 = new HashSet<>();
        condition11.add("val1");
        conditions1.put("cond1", condition11);
        Set<String> condition12 = new HashSet<>();
        condition12.add("val2");
        conditions1.put("cond2", condition12);
        Set<String> values1 = new HashSet<>();
        ValueSet<String> valueSet1 = new ValueSet<>(conditions1, values1);

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition21 = new HashSet<>();
        condition21.add("val1");
        conditions2.put("cond1", condition21);
        Set<String> condition22 = new HashSet<>();
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        Set<String> values2 = new HashSet<>();
        ValueSet<String> valueSet2 = new ValueSet<>(conditions2, values2);

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> values3 = new HashSet<>();
        ValueSet<String> valueSet3 = new ValueSet<>(conditions3, values3);

        Map<String, Set<String>> conditions4 = new HashMap<>();
        Set<String> condition41 = new HashSet<>();
        condition41.add("val5");
        conditions4.put("cond1", condition41);
        Set<String> values4 = new HashSet<>();
        ValueSet<String> valueSet4 = new ValueSet<>(conditions4, values4);

        Map<String, Set<String>> conditions5 = new HashMap<>();
        Set<String> condition51 = new HashSet<>();
        condition51.add("val1");
        conditions5.put("cond1", condition51);
        Set<String> condition52 = new HashSet<>();
        condition52.add("val2");
        conditions5.put("cond2", condition52);
        Set<String> condition53 = new HashSet<>();
        condition53.add("val3");
        conditions5.put("cond3", condition53);
        Set<String> condition54 = new HashSet<>();
        condition54.add("val4");
        conditions5.put("cond4", condition54);
        Set<String> values5 = new HashSet<>();
        ValueSet<String> valueSet5 = new ValueSet<>(conditions5, values5);

        Map<String, Set<String>> conditions6 = new HashMap<>();
        Set<String> condition61 = new HashSet<>();
        condition61.add("val8");
        conditions6.put("cond1", condition61);
        Set<String> condition62 = new HashSet<>();
        condition62.add("val8");
        conditions6.put("cond2", condition62);
        Set<String> condition63 = new HashSet<>();
        condition63.add("val8");
        conditions6.put("cond3", condition63);
        Set<String> condition64 = new HashSet<>();
        condition64.add("val8");
        conditions6.put("cond4", condition64);
        Set<String> values6 = new HashSet<>();
        ValueSet<String> valueSet6 = new ValueSet<>(conditions6, values6);

        Assert.assertFalse(valueSet1.isMoreSpecificValueSet(valueSet2));
        Assert.assertTrue(valueSet1.isMoreSpecificValueSet(valueSet3));
        Assert.assertTrue(valueSet1.isMoreSpecificValueSet(valueSet4));
        Assert.assertFalse(valueSet1.isMoreSpecificValueSet(valueSet5));
        Assert.assertFalse(valueSet1.isMoreSpecificValueSet(valueSet6));

        Assert.assertFalse(valueSet2.isMoreSpecificValueSet(valueSet1));
        Assert.assertTrue(valueSet2.isMoreSpecificValueSet(valueSet3));
        Assert.assertTrue(valueSet2.isMoreSpecificValueSet(valueSet4));
        Assert.assertFalse(valueSet2.isMoreSpecificValueSet(valueSet5));
        Assert.assertFalse(valueSet2.isMoreSpecificValueSet(valueSet6));

        Assert.assertFalse(valueSet3.isMoreSpecificValueSet(valueSet1));
        Assert.assertFalse(valueSet3.isMoreSpecificValueSet(valueSet2));
        Assert.assertFalse(valueSet3.isMoreSpecificValueSet(valueSet4));
        Assert.assertFalse(valueSet3.isMoreSpecificValueSet(valueSet5));
        Assert.assertFalse(valueSet3.isMoreSpecificValueSet(valueSet6));

        Assert.assertFalse(valueSet4.isMoreSpecificValueSet(valueSet1));
        Assert.assertFalse(valueSet4.isMoreSpecificValueSet(valueSet2));
        Assert.assertFalse(valueSet4.isMoreSpecificValueSet(valueSet3));
        Assert.assertFalse(valueSet4.isMoreSpecificValueSet(valueSet5));
        Assert.assertFalse(valueSet4.isMoreSpecificValueSet(valueSet6));

        Assert.assertTrue(valueSet5.isMoreSpecificValueSet(valueSet1));
        Assert.assertTrue(valueSet5.isMoreSpecificValueSet(valueSet2));
        Assert.assertTrue(valueSet5.isMoreSpecificValueSet(valueSet3));
        Assert.assertTrue(valueSet5.isMoreSpecificValueSet(valueSet4));
        Assert.assertFalse(valueSet5.isMoreSpecificValueSet(valueSet6));

        Assert.assertTrue(valueSet6.isMoreSpecificValueSet(valueSet1));
        Assert.assertTrue(valueSet6.isMoreSpecificValueSet(valueSet2));
        Assert.assertTrue(valueSet6.isMoreSpecificValueSet(valueSet3));
        Assert.assertTrue(valueSet6.isMoreSpecificValueSet(valueSet4));
        Assert.assertFalse(valueSet6.isMoreSpecificValueSet(valueSet5));
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getValueSetUniqueConditionsTest() {
        Map<String, Set<String>> conditions1 = new HashMap<>();
        Set<String> condition11 = new HashSet<>();
        condition11.add("val1");
        conditions1.put("cond1", condition11);
        Set<String> values1 = new HashSet<>();
        ValueSet<String> valueSet1 = new ValueSet<>(conditions1, values1);
        List<ValueSetUniqueCondition> valueSetUniqueConditions1 = valueSet1.getValueSetUniqueConditions();
        Assert.assertNotNull(valueSetUniqueConditions1);
        Assert.assertEquals(1, valueSetUniqueConditions1.size());

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition21 = new HashSet<>();
        condition21.add("val1");
        condition21.add("val2");
        conditions2.put("cond1", condition21);
        Set<String> values2 = new HashSet<>();
        ValueSet<String> valueSet2 = new ValueSet<>(conditions2, values2);
        List<ValueSetUniqueCondition> valueSetUniqueConditions2 = valueSet2.getValueSetUniqueConditions();
        Assert.assertNotNull(valueSetUniqueConditions2);
        Assert.assertEquals(2, valueSetUniqueConditions2.size());

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<>();
        condition32.add("val2");
        conditions3.put("cond2", condition32);
        Set<String> values3 = new HashSet<>();
        ValueSet<String> valueSet3 = new ValueSet<>(conditions3, values3);
        List<ValueSetUniqueCondition> valueSetUniqueConditions3 = valueSet3.getValueSetUniqueConditions();
        Assert.assertNotNull(valueSetUniqueConditions3);
        Assert.assertEquals(1, valueSetUniqueConditions3.size());

        Map<String, Set<String>> conditions4 = new HashMap<>();
        Set<String> condition41 = new HashSet<>();
        condition41.add("val1");
        condition41.add("val2");
        conditions4.put("cond1", condition41);
        Set<String> condition42 = new HashSet<>();
        condition42.add("val1");
        condition42.add("val2");
        conditions4.put("cond2", condition42);
        Set<String> values4 = new HashSet<>();
        ValueSet<String> valueSet4 = new ValueSet<>(conditions4, values4);
        List<ValueSetUniqueCondition> valueSetUniqueConditions4 = valueSet4.getValueSetUniqueConditions();
        Assert.assertNotNull(valueSetUniqueConditions4);
        Assert.assertEquals(4, valueSetUniqueConditions4.size());

        Map<String, Set<String>> conditions5 = new HashMap<>();
        Set<String> condition51 = new HashSet<>();
        condition51.add("val1");
        condition51.add("val2");
        conditions5.put("cond1", condition51);
        Set<String> condition52 = new HashSet<>();
        condition52.add("val1");
        condition52.add("val2");
        conditions5.put("cond2", condition52);
        Set<String> condition53 = new HashSet<>();
        condition53.add("val1");
        condition53.add("val2");
        condition53.add("val3");
        conditions5.put("cond3", condition53);
        Set<String> values5 = new HashSet<>();
        ValueSet<String> valueSet5 = new ValueSet<>(conditions5, values5);
        List<ValueSetUniqueCondition> valueSetUniqueConditions5 = valueSet5.getValueSetUniqueConditions();
        Assert.assertNotNull(valueSetUniqueConditions5);
        Assert.assertEquals(12, valueSetUniqueConditions5.size());
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllValuesTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val21");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<>();
        values.add("val1");
        values.add("val2");
        values.add("val3");
        ValueSet<String> valueSet = new ValueSet<>(conditions, values);

        Set<String> allValues = valueSet.getAllValues();
        Assert.assertEquals(3, allValues.size());
        Assert.assertTrue(allValues.contains("val1"));
        Assert.assertTrue(allValues.contains("val2"));
        Assert.assertTrue(allValues.contains("val3"));
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void toStringTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        condition1.add("val12");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val2");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<>();
        values.add("val1");
        values.add("val2");
        values.add("val3");
        ValueSet<String> valueSet = new ValueSet<>(conditions, values);

        String str = valueSet.toString();
        Assert.assertTrue(str.contains("cond1=["));
        Assert.assertTrue(str.contains("cond2=[val2]"));
    }

}
