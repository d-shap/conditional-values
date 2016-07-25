// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public final class ConditionalValuesTest {

    public ConditionalValuesTest() {
        super();
    }

    @Test
    public void getAllConditionNamesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31", "val32");
        valueSetBuilder.addCondition("cond4", "val41", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        Set<String> allConditionNames = conditionalValues.getAllConditionNames();
        Assert.assertEquals(4, allConditionNames.size());
        Assert.assertTrue(allConditionNames.contains("cond1"));
        Assert.assertTrue(allConditionNames.contains("cond2"));
        Assert.assertTrue(allConditionNames.contains("cond3"));
        Assert.assertTrue(allConditionNames.contains("cond4"));
    }

    @Test
    public void getAllConditionValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31", "val32");
        valueSetBuilder.addCondition("cond4", "val41", "val42");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        Set<String> allConditionValues1 = conditionalValues.getAllConditionValues("cond1");
        Assert.assertEquals(2, allConditionValues1.size());
        Assert.assertTrue(allConditionValues1.contains("val11"));
        Assert.assertTrue(allConditionValues1.contains("val12"));

        Set<String> allConditionValues2 = conditionalValues.getAllConditionValues("cond2");
        Assert.assertEquals(2, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val21"));
        Assert.assertTrue(allConditionValues2.contains("val22"));

        Set<String> allConditionValues3 = conditionalValues.getAllConditionValues("cond3");
        Assert.assertEquals(2, allConditionValues3.size());
        Assert.assertTrue(allConditionValues3.contains("val31"));
        Assert.assertTrue(allConditionValues3.contains("val32"));

        Set<String> allConditionValues4 = conditionalValues.getAllConditionValues("cond4");
        Assert.assertEquals(2, allConditionValues4.size());
        Assert.assertTrue(allConditionValues4.contains("val41"));
        Assert.assertTrue(allConditionValues4.contains("val42"));

        Set<String> allConditionValues5 = conditionalValues.getAllConditionValues("cond5");
        Assert.assertEquals(0, allConditionValues5.size());
    }

    @Test
    public void getValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31", "val32");
        valueSetBuilder.addCondition("cond4", "val41", "val42");
        valueSetBuilder.addValue("val6", "val2", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val22");
        ConditionSet conditionSet = conditionSetBuilder.build();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertFalse(values.isEmpty());
        Set<String> allValues = values.getAllValues();
        Iterator<String> iterator = allValues.iterator();
        Assert.assertEquals("val1", iterator.next());
        Assert.assertEquals("val5", iterator.next());
        Assert.assertEquals("val3", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void getEmptyValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val31", "val32");
        valueSetBuilder.addCondition("cond4", "val41", "val42");
        valueSetBuilder.addValue("val6", "val2", "val4");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet = conditionSetBuilder.build();

        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertTrue(values.isEmpty());
    }

    @Test
    public void getAndValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assert.assertTrue(values1.isEmpty());

        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assert.assertTrue(values2.isEmpty());

        conditionSetBuilder.addCondition("cond1", "val1");
        conditionSetBuilder.addCondition("cond2", "val2");
        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assert.assertFalse(values3.isEmpty());
    }

    @Test
    public void getOrValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val1", "val2");
        valueSetBuilder.addValue("val1", "val5", "val3");
        ValueSet<String> valueSet = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();

        conditionSetBuilder.addCondition("cond1", "val1");
        ConditionSet conditionSet1 = conditionSetBuilder.build();
        Values<String> values1 = conditionalValues.getValues(conditionSet1);
        Assert.assertFalse(values1.isEmpty());

        conditionSetBuilder.addCondition("cond1", "val2");
        ConditionSet conditionSet2 = conditionSetBuilder.build();
        Values<String> values2 = conditionalValues.getValues(conditionSet2);
        Assert.assertFalse(values2.isEmpty());

        ConditionSet conditionSet3 = conditionSetBuilder.build();
        Values<String> values3 = conditionalValues.getValues(conditionSet3);
        Assert.assertTrue(values3.isEmpty());
    }

    @Test
    public void getMaxCardinalityValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        valueSetBuilder.addCondition("cond3", "val31", "val32");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond3", "val31", "val23");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond4", "val41", "val42");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        valueSets.add(valueSet3);
        valueSets.add(valueSet4);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond3", "val31");
        ConditionSet conditionSet = conditionSetBuilder.build();
        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertFalse(values.isEmpty());
        Assert.assertTrue(values.contains("val1"));
        Assert.assertFalse(values.contains("val2"));
        Assert.assertFalse(values.contains("val3"));
        Assert.assertFalse(values.contains("val4"));
    }

    @Test
    public void getSameCardinalityValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        valueSetBuilder.addCondition("cond3", "val31", "val32");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond2", "val21", "val22");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond3", "val31", "val23");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond1", "val11", "val12");
        valueSetBuilder.addCondition("cond4", "val41", "val42");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        valueSets.add(valueSet3);
        valueSets.add(valueSet4);
        ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(valueSets);

        ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
        conditionSetBuilder.addCondition("cond1", "val11");
        conditionSetBuilder.addCondition("cond2", "val21");
        conditionSetBuilder.addCondition("cond4", "val41");
        ConditionSet conditionSet = conditionSetBuilder.build();
        Values<String> values = conditionalValues.getValues(conditionSet);
        Assert.assertFalse(values.isEmpty());
        Assert.assertFalse(values.contains("val1"));
        Assert.assertTrue(values.contains("val2"));
        Assert.assertFalse(values.contains("val3"));
        Assert.assertTrue(values.contains("val4"));
    }

}
