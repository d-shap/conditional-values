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

/**
 * Tests for {@link ru.d_shap.conditionalvalues.Values}.
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
     * {@link ru.d_shap.conditionalvalues.Values} class test.
     */
    @Test
    public void isEmptyTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        ValueSet<String> valueSet11 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        ValueSet<String> valueSet12 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets1 = new ArrayList<ValueSet<String>>();
        valueSets1.add(valueSet11);
        valueSets1.add(valueSet12);
        Values<String> values1 = new Values<String>(valueSets1);
        Assert.assertTrue(values1.isEmpty());

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet21 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
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
     * {@link ru.d_shap.conditionalvalues.Values} class test.
     */
    @Test
    public void containsTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
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
     * {@link ru.d_shap.conditionalvalues.Values} class test.
     */
    @Test
    public void getAllValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = new ValueSetBuilder<String>();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val1", "val4", "val2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addValue("val6");
        valueSetBuilder.addValue("val3");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        List<ValueSet<String>> valueSets = new ArrayList<ValueSet<String>>();
        valueSets.add(valueSet1);
        valueSets.add(valueSet2);
        Values<String> values = new Values<String>(valueSets);

        Set<String> allValues = values.getAllValues();
        Iterator<String> iterator = allValues.iterator();
        Assert.assertEquals("val1", iterator.next());
        Assert.assertEquals("val4", iterator.next());
        Assert.assertEquals("val2", iterator.next());
        Assert.assertEquals("val6", iterator.next());
        Assert.assertEquals("val3", iterator.next());
        Assert.assertEquals("val5", iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

}
