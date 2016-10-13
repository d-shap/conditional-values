// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
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
        valueSetBuilder.addStringCondition("cond1", "val11");
        valueSetBuilder.addStringCondition("cond1", "val12");
        valueSetBuilder.addStringCondition("cond2", "val2");
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
        valueSetBuilder.addBooleanCondition("cond1", true, true);
        valueSetBuilder.addIntegerCondition("cond1", 1, 2);
        valueSetBuilder.addLongCondition("cond1", 1L, 3L);
        valueSetBuilder.addFloatCondition("cond1", 3.5f);
        valueSetBuilder.addDoubleCondition("cond1", 4.9);
        valueSetBuilder.addObjectCondition("cond2", new StringBuilder().append("val1"), new StringBuilder().append("val2"));
        valueSetBuilder.addStringCondition("cond2", "val2", "val3");
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
        Assert.assertEquals(3, allConditionValues2.size());
        Assert.assertTrue(allConditionValues2.contains("val1"));
        Assert.assertTrue(allConditionValues2.contains("val2"));
        Assert.assertTrue(allConditionValues2.contains("val3"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addBooleanCondition("cond1", true, true);
        valueSetBuilder.addIntegerCondition("cond2", 1, 2);
        valueSetBuilder.addLongCondition("cond2", 1L, 3L);
        valueSetBuilder.addFloatCondition("cond3", 3.5f);
        valueSetBuilder.addDoubleCondition("cond3", 4.9);
        valueSetBuilder.addObjectCondition("cond4", new StringBuilder().append("val1"), new StringBuilder().append("val2"));
        valueSetBuilder.addStringCondition("cond4", "val2", "val3");

        valueSetBuilder.removeCondition("cond1");
        valueSetBuilder.removeBooleanCondition("cond1", false, true);
        valueSetBuilder.removeIntegerCondition("cond2", 1);
        valueSetBuilder.removeStringCondition("cond2", "3");
        valueSetBuilder.removeLongCondition("cond2", 2L);
        valueSetBuilder.removeFloatCondition("cond3", 3.5f, 3.6f, 3.7f);
        valueSetBuilder.removeDoubleCondition("cond3", 4.5, 4.6, 4.7);
        valueSetBuilder.removeStringCondition("cond4", "val1");
        valueSetBuilder.removeObjectCondition("cond4", new StringBuilder().append("val1"), new StringBuilder().append("val2"));

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
        Assert.assertTrue(allConditionValues2.contains("val3"));
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addStringCondition("cond", "val");
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
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
        valueSetBuilder.addStringCondition("cond", "val");
        valueSetBuilder.addValue("val1", "val3", "val2");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
        valueSetBuilder.addValue("val20", "val17", "val19", "val18");

        valueSetBuilder.removeValue("val17", "val18");
        valueSetBuilder.removeValue("val3");

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
