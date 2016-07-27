// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ru.d_shap.conditionalvalues.ValueSetBuilder}.
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
     * build method test.
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
        Iterator<String> iterator = allValues.iterator();
        Assert.assertEquals("val1", iterator.next());
        Assert.assertEquals("val2", iterator.next());
        Assert.assertEquals("val3", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * addCondition method test.
     */
    @Test
    public void addConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond1", true, true);
        valueSetBuilder.addCondition("cond1", 1, 2);
        valueSetBuilder.addCondition("cond1", 1L, 3L);
        valueSetBuilder.addCondition("cond1", 3.5f);
        valueSetBuilder.addCondition("cond1", 4.9);
        valueSetBuilder.addObjectCondition("cond2", new StringBuilder().append("val1"), new StringBuilder().append("val2"));
        valueSetBuilder.addCondition("cond2", "val2", "val3");
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
     * removeCondition method test.
     */
    @Test
    public void removeConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond1", true, true);
        valueSetBuilder.addCondition("cond2", 1, 2);
        valueSetBuilder.addCondition("cond2", 1L, 3L);
        valueSetBuilder.addCondition("cond3", 3.5f);
        valueSetBuilder.addCondition("cond3", 4.9);
        valueSetBuilder.addObjectCondition("cond4", new StringBuilder().append("val1"), new StringBuilder().append("val2"));
        valueSetBuilder.addCondition("cond4", "val2", "val3");

        valueSetBuilder.removeCondition("cond1");
        valueSetBuilder.removeCondition("cond1", false, true);
        valueSetBuilder.removeCondition("cond2", 1);
        valueSetBuilder.removeCondition("cond2", "3");
        valueSetBuilder.removeCondition("cond2", 2L);
        valueSetBuilder.removeCondition("cond3", 3.5f, 3.6f, 3.7f);
        valueSetBuilder.removeCondition("cond3", 4.5, 4.6, 4.7);
        valueSetBuilder.removeCondition("cond4", "val1");
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
     * addValue method test.
     */
    @Test
    public void addValueTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond", "val");
        valueSetBuilder.addValue("val1", "val2", "val3");
        valueSetBuilder.addValue("val5");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Iterator<String> iterator = allValues.iterator();
        Assert.assertEquals("val1", iterator.next());
        Assert.assertEquals("val2", iterator.next());
        Assert.assertEquals("val3", iterator.next());
        Assert.assertEquals("val5", iterator.next());
        Assert.assertEquals("val4", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * removeValue method test.
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

        ValueSet<String> valueSet = valueSetBuilder.build();

        Set<String> allValues = valueSet.getAllValues();
        Iterator<String> iterator = allValues.iterator();
        Assert.assertEquals("val1", iterator.next());
        Assert.assertEquals("val2", iterator.next());
        Assert.assertEquals("val5", iterator.next());
        Assert.assertEquals("val4", iterator.next());
        Assert.assertEquals("val20", iterator.next());
        Assert.assertEquals("val19", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

}
