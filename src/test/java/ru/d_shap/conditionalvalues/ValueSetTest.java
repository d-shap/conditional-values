// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public final class ValueSetTest {

    public ValueSetTest() {
        super();
    }

    @Test
    public void getAllConditionNamesTest() {
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        Set<String> condition1 = new HashSet<String>();
        condition1.add("val11");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<String>();
        condition2.add("val21");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<String>();
        ValueSet<String> valueSet = new ValueSet<String>(conditions, values);

        Set<String> allConditionNames = valueSet.getAllConditionNames();
        Assert.assertEquals(2, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));
    }

    @Test
    public void getAllConditionValuesTest() {
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        Set<String> condition1 = new HashSet<String>();
        condition1.add("val11");
        condition1.add("val12");
        condition1.add("val13");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<String>();
        condition2.add("val21");
        condition2.add("val22");
        condition2.add("val23");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<String>();
        ValueSet<String> valueSet = new ValueSet<String>(conditions, values);

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

    @Test
    public void matchCardinalityTest() {
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        Set<String> condition1 = new HashSet<String>();
        condition1.add("val11");
        condition1.add("val12");
        condition1.add("val13");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<String>();
        condition2.add("val21");
        condition2.add("val22");
        condition2.add("val23");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<String>();
        ValueSet<String> valueSet = new ValueSet<String>(conditions, values);

        Map<String, String> conditions1 = new HashMap<String, String>();
        conditions1.put("cond1", "val11");
        conditions1.put("cond2", "val21");
        ConditionSet conditionSet1 = new ConditionSet(conditions1);
        Assert.assertEquals(2, valueSet.matchCardinality(conditionSet1));

        Map<String, String> conditions2 = new HashMap<String, String>();
        conditions2.put("cond1", "val12");
        conditions2.put("cond2", "val22");
        ConditionSet conditionSet2 = new ConditionSet(conditions2);
        Assert.assertEquals(2, valueSet.matchCardinality(conditionSet2));

        Map<String, String> conditions3 = new HashMap<String, String>();
        conditions3.put("cond1", "val13");
        ConditionSet conditionSet3 = new ConditionSet(conditions3);
        Assert.assertEquals(-1, valueSet.matchCardinality(conditionSet3));

        Map<String, String> conditions4 = new HashMap<String, String>();
        ConditionSet conditionSet4 = new ConditionSet(conditions4);
        Assert.assertEquals(-1, valueSet.matchCardinality(conditionSet4));

        Map<String, String> conditions5 = new HashMap<String, String>();
        conditions5.put("cond1", "val14");
        ConditionSet conditionSet5 = new ConditionSet(conditions5);
        Assert.assertEquals(-1, valueSet.matchCardinality(conditionSet5));

        Map<String, String> conditions6 = new HashMap<String, String>();
        conditions6.put("cond1", "val12");
        conditions6.put("cond2", "val24");
        ConditionSet conditionSet6 = new ConditionSet(conditions6);
        Assert.assertEquals(-1, valueSet.matchCardinality(conditionSet6));

        Map<String, String> conditions7 = new HashMap<String, String>();
        conditions7.put("cond1", "val12");
        conditions7.put("cond2", "val22");
        conditions7.put("cond3", "val32");
        conditions7.put("cond4", "val34");
        ConditionSet conditionSet7 = new ConditionSet(conditions7);
        Assert.assertEquals(2, valueSet.matchCardinality(conditionSet7));
    }

    @Test
    public void isEmptyTest() {
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        Set<String> condition1 = new HashSet<String>();
        condition1.add("val11");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<String>();
        condition2.add("val21");
        conditions.put("cond2", condition2);

        Set<String> values1 = new HashSet<String>();
        ValueSet<String> valueSet1 = new ValueSet<String>(conditions, values1);
        Assert.assertTrue(valueSet1.isEmpty());

        Set<String> values2 = new HashSet<String>();
        values2.add("val1");
        values2.add("val2");
        values2.add("val3");
        ValueSet<String> valueSet2 = new ValueSet<String>(conditions, values2);
        Assert.assertFalse(valueSet2.isEmpty());
    }

    @Test
    public void containsTest() {
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        Set<String> condition1 = new HashSet<String>();
        condition1.add("val11");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<String>();
        condition2.add("val21");
        conditions.put("cond2", condition2);
        Set<String> values = new HashSet<String>();
        values.add("val1");
        values.add("val2");
        values.add("val3");
        ValueSet<String> valueSet = new ValueSet<String>(conditions, values);
        Assert.assertTrue(valueSet.contains("val1"));
        Assert.assertTrue(valueSet.contains("val2"));
        Assert.assertTrue(valueSet.contains("val3"));
        Assert.assertFalse(valueSet.contains("val4"));
    }

    @Test
    public void getAllValuesTest() {
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        Set<String> condition1 = new HashSet<String>();
        condition1.add("val11");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<String>();
        condition2.add("val21");
        conditions.put("cond2", condition2);
        Set<String> values = new LinkedHashSet<String>();
        values.add("val1");
        values.add("val2");
        values.add("val3");
        ValueSet<String> valueSet = new ValueSet<String>(conditions, values);

        Set<String> allValues = valueSet.getAllValues();
        Iterator<String> iterator = allValues.iterator();
        Assert.assertEquals("val1", iterator.next());
        Assert.assertEquals("val2", iterator.next());
        Assert.assertEquals("val3", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void equalsTest() {
        Map<String, Set<String>> conditions1 = new HashMap<String, Set<String>>();
        Set<String> condition11 = new HashSet<String>();
        condition11.add("val1");
        conditions1.put("cond1", condition11);
        Set<String> condition12 = new HashSet<String>();
        condition12.add("val2");
        conditions1.put("cond2", condition12);
        Set<String> values1 = new LinkedHashSet<String>();
        values1.add("val11");
        values1.add("val12");
        values1.add("val13");
        ValueSet<String> valueSet1 = new ValueSet<String>(conditions1, values1);

        Map<String, Set<String>> conditions2 = new HashMap<String, Set<String>>();
        Set<String> condition21 = new HashSet<String>();
        condition21.add("val1");
        conditions2.put("cond1", condition21);
        Set<String> condition22 = new HashSet<String>();
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        Set<String> values2 = new LinkedHashSet<String>();
        values2.add("val21");
        values2.add("val22");
        values2.add("val23");
        ValueSet<String> valueSet2 = new ValueSet<String>(conditions2, values2);

        Map<String, Set<String>> conditions3 = new HashMap<String, Set<String>>();
        Set<String> condition31 = new HashSet<String>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<String>();
        condition32.add("val3");
        conditions3.put("cond2", condition32);
        Set<String> values3 = new LinkedHashSet<String>();
        values3.add("val31");
        values3.add("val32");
        values3.add("val33");
        ValueSet<String> valueSet3 = new ValueSet<String>(conditions3, values3);

        Assert.assertEquals(valueSet1, valueSet2);
        Assert.assertNotEquals(valueSet1, valueSet3);
        Assert.assertNotEquals(valueSet2, valueSet3);

        Map<String, Set<String>> conditions4 = new HashMap<String, Set<String>>();
        Set<String> condition41 = new HashSet<String>();
        condition41.add("val1");
        condition41.add("val2");
        condition41.add("val3");
        condition41.add("val4");
        conditions4.put("cond1", condition41);
        Set<String> values4 = new LinkedHashSet<String>();
        ValueSet<String> valueSet4 = new ValueSet<String>(conditions4, values4);

        Map<String, Set<String>> conditions5 = new HashMap<String, Set<String>>();
        Set<String> condition51 = new HashSet<String>();
        condition51.add("val1");
        condition51.add("val2");
        condition51.add("val3");
        condition51.add("val4");
        conditions5.put("cond1", condition51);
        Set<String> values5 = new LinkedHashSet<String>();
        ValueSet<String> valueSet5 = new ValueSet<String>(conditions5, values5);

        Assert.assertEquals(valueSet4, valueSet5);
    }

    @Test
    public void hashCodeTest() {
        Map<String, Set<String>> conditions1 = new HashMap<String, Set<String>>();
        Set<String> condition11 = new HashSet<String>();
        condition11.add("val1");
        conditions1.put("cond1", condition11);
        Set<String> condition12 = new HashSet<String>();
        condition12.add("val2");
        conditions1.put("cond2", condition12);
        Set<String> values1 = new LinkedHashSet<String>();
        values1.add("val11");
        values1.add("val12");
        values1.add("val13");
        ValueSet<String> valueSet1 = new ValueSet<String>(conditions1, values1);

        Map<String, Set<String>> conditions2 = new HashMap<String, Set<String>>();
        Set<String> condition21 = new HashSet<String>();
        condition21.add("val1");
        conditions2.put("cond1", condition21);
        Set<String> condition22 = new HashSet<String>();
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        Set<String> values2 = new LinkedHashSet<String>();
        values2.add("val21");
        values2.add("val22");
        values2.add("val23");
        ValueSet<String> valueSet2 = new ValueSet<String>(conditions2, values2);

        Map<String, Set<String>> conditions3 = new HashMap<String, Set<String>>();
        Set<String> condition31 = new HashSet<String>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<String>();
        condition32.add("val3");
        conditions3.put("cond2", condition32);
        Set<String> values3 = new LinkedHashSet<String>();
        values3.add("val31");
        values3.add("val32");
        values3.add("val33");
        ValueSet<String> valueSet3 = new ValueSet<String>(conditions3, values3);

        Assert.assertEquals(valueSet1.hashCode(), valueSet2.hashCode());
        Assert.assertNotEquals(valueSet1.hashCode(), valueSet3.hashCode());
        Assert.assertNotEquals(valueSet2.hashCode(), valueSet3.hashCode());

        Map<String, Set<String>> conditions4 = new HashMap<String, Set<String>>();
        Set<String> condition41 = new HashSet<String>();
        condition41.add("val1");
        condition41.add("val2");
        condition41.add("val3");
        condition41.add("val4");
        conditions4.put("cond1", condition41);
        Set<String> values4 = new LinkedHashSet<String>();
        ValueSet<String> valueSet4 = new ValueSet<String>(conditions4, values4);

        Map<String, Set<String>> conditions5 = new HashMap<String, Set<String>>();
        Set<String> condition51 = new HashSet<String>();
        condition51.add("val1");
        condition51.add("val2");
        condition51.add("val3");
        condition51.add("val4");
        conditions5.put("cond1", condition51);
        Set<String> values5 = new LinkedHashSet<String>();
        ValueSet<String> valueSet5 = new ValueSet<String>(conditions5, values5);

        Assert.assertEquals(valueSet4.hashCode(), valueSet5.hashCode());
    }

}
