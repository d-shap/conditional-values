// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ru.d_shap.conditionalvalues.ConditionSet}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetTest {

    /**
     * Test class constructor.
     */
    public ConditionSetTest() {
        super();
    }

    /**
     * {@link ru.d_shap.conditionalvalues.ConditionSet} class test.
     */
    @Test
    public void nameIteratorTest() {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");
        ConditionSet conditionSet = new ConditionSet(conditions);

        Set<String> allNames = new HashSet<String>();
        allNames.add("cond1");
        allNames.add("cond2");
        allNames.add("cond3");

        Iterator<String> nameIterator = conditionSet.nameIterator();
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertTrue(allNames.contains(nameIterator.next()));
        Assert.assertFalse(nameIterator.hasNext());
    }

    /**
     * {@link ru.d_shap.conditionalvalues.ConditionSet} class test.
     */
    @Test
    public void getConditionTest() {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("cond1", "val1");
        conditions.put("cond2", "val2");
        conditions.put("cond3", "val3");

        ConditionSet conditionSet = new ConditionSet(conditions);
        Assert.assertEquals("val1", conditionSet.getCondition("cond1"));
        Assert.assertEquals("val2", conditionSet.getCondition("cond2"));
        Assert.assertEquals("val3", conditionSet.getCondition("cond3"));
        Assert.assertNull(conditionSet.getCondition("cond4"));
    }

}
