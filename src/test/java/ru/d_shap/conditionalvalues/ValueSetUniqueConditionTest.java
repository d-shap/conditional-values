// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ValueSetUniqueCondition}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetUniqueConditionTest {

    /**
     * Test class constructor.
     */
    public ValueSetUniqueConditionTest() {
        super();
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void createNewObjectTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        Map<String, String> conditions1 = valueSetUniqueCondition1.getConditions();
        Assert.assertNotNull(conditions1);
        Assert.assertEquals(0, conditions1.size());

        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        Map<String, String> conditions2 = valueSetUniqueCondition2.getConditions();
        Assert.assertNotNull(conditions2);
        Assert.assertEquals(1, conditions2.size());
        Assert.assertTrue(conditions2.containsKey("name1"));
        Assert.assertEquals("value1", conditions2.get("name1"));

        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");
        Map<String, String> conditions3 = valueSetUniqueCondition3.getConditions();
        Assert.assertNotNull(conditions3);
        Assert.assertEquals(2, conditions3.size());
        Assert.assertTrue(conditions3.containsKey("name1"));
        Assert.assertEquals("value1", conditions3.get("name1"));
        Assert.assertTrue(conditions3.containsKey("name2"));
        Assert.assertEquals("value2", conditions3.get("name2"));
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void equalsTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition();
        Assert.assertTrue(valueSetUniqueCondition11.equals(valueSetUniqueCondition21));
        Assert.assertTrue(valueSetUniqueCondition21.equals(valueSetUniqueCondition11));

        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assert.assertTrue(valueSetUniqueCondition12.equals(valueSetUniqueCondition22));
        Assert.assertTrue(valueSetUniqueCondition22.equals(valueSetUniqueCondition12));
        Assert.assertFalse(valueSetUniqueCondition12.equals(valueSetUniqueCondition21));
        Assert.assertFalse(valueSetUniqueCondition21.equals(valueSetUniqueCondition12));
        Assert.assertFalse(valueSetUniqueCondition22.equals(valueSetUniqueCondition11));
        Assert.assertFalse(valueSetUniqueCondition11.equals(valueSetUniqueCondition22));

        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value3");
        Assert.assertFalse(valueSetUniqueCondition13.equals(valueSetUniqueCondition23));
        Assert.assertFalse(valueSetUniqueCondition23.equals(valueSetUniqueCondition13));

        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition24 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name3", "value2");
        Assert.assertFalse(valueSetUniqueCondition14.equals(valueSetUniqueCondition24));
        Assert.assertFalse(valueSetUniqueCondition24.equals(valueSetUniqueCondition14));
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void equalsToSelfTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");

        Assert.assertEquals(valueSetUniqueCondition1, valueSetUniqueCondition1);
        Assert.assertEquals(valueSetUniqueCondition2, valueSetUniqueCondition2);
        Assert.assertEquals(valueSetUniqueCondition3, valueSetUniqueCondition3);
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void equalsWrongTypeTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");

        Assert.assertNotEquals(valueSetUniqueCondition1, "str");
        Assert.assertNotEquals(valueSetUniqueCondition2, new StringBuilder());
        Assert.assertNotEquals(valueSetUniqueCondition3, 5);
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void hashCodeTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition13, "name3", "value3");

        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition24 = new ValueSetUniqueCondition(valueSetUniqueCondition23, "name3", "value4");

        Assert.assertEquals(valueSetUniqueCondition11.hashCode(), valueSetUniqueCondition21.hashCode());
        Assert.assertEquals(valueSetUniqueCondition12.hashCode(), valueSetUniqueCondition22.hashCode());
        Assert.assertEquals(valueSetUniqueCondition13.hashCode(), valueSetUniqueCondition23.hashCode());
        Assert.assertNotEquals(valueSetUniqueCondition14.hashCode(), valueSetUniqueCondition24.hashCode());
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        String str1 = valueSetUniqueCondition1.toString();
        Assert.assertEquals("{}", str1);

        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        String str2 = valueSetUniqueCondition2.toString();
        Assert.assertEquals("{name1=value1}", str2);

        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");
        String str3 = valueSetUniqueCondition3.toString();
        Assert.assertTrue(str3.contains("name1=value1"));
        Assert.assertTrue(str3.contains("name2=value2"));

        ValueSetUniqueCondition valueSetUniqueCondition4 = new ValueSetUniqueCondition(valueSetUniqueCondition3, "name3", "value3");
        String str4 = valueSetUniqueCondition4.toString();
        Assert.assertTrue(str4.contains("name1=value1"));
        Assert.assertTrue(str4.contains("name2=value2"));
        Assert.assertTrue(str4.contains("name3=value3"));
    }

}
