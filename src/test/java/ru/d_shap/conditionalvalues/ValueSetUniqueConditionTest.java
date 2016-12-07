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

        ValueSetUniqueCondition valueSetUniqueCondition4 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", null);
        Map<String, String> conditions4 = valueSetUniqueCondition4.getConditions();
        Assert.assertNotNull(conditions4);
        Assert.assertEquals(1, conditions4.size());
        Assert.assertTrue(conditions4.containsKey("name1"));
        Assert.assertEquals("value1", conditions4.get("name1"));

        ValueSetUniqueCondition valueSetUniqueCondition5 = new ValueSetUniqueCondition(valueSetUniqueCondition2, null, "value2");
        Map<String, String> conditions5 = valueSetUniqueCondition5.getConditions();
        Assert.assertNotNull(conditions5);
        Assert.assertEquals(1, conditions5.size());
        Assert.assertTrue(conditions5.containsKey("name1"));
        Assert.assertEquals("value1", conditions5.get("name1"));

        ValueSetUniqueCondition valueSetUniqueCondition6 = new ValueSetUniqueCondition(valueSetUniqueCondition2, null, null);
        Map<String, String> conditions6 = valueSetUniqueCondition6.getConditions();
        Assert.assertNotNull(conditions6);
        Assert.assertEquals(1, conditions6.size());
        Assert.assertTrue(conditions6.containsKey("name1"));
        Assert.assertEquals("value1", conditions6.get("name1"));
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void equalsTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition();
        Assert.assertEquals(valueSetUniqueCondition11, valueSetUniqueCondition21);
        Assert.assertEquals(valueSetUniqueCondition21, valueSetUniqueCondition11);

        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assert.assertEquals(valueSetUniqueCondition12, valueSetUniqueCondition22);
        Assert.assertEquals(valueSetUniqueCondition22, valueSetUniqueCondition12);
        Assert.assertNotEquals(valueSetUniqueCondition12, valueSetUniqueCondition21);
        Assert.assertNotEquals(valueSetUniqueCondition21, valueSetUniqueCondition12);
        Assert.assertNotEquals(valueSetUniqueCondition22, valueSetUniqueCondition11);
        Assert.assertNotEquals(valueSetUniqueCondition11, valueSetUniqueCondition22);

        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value3");
        Assert.assertNotEquals(valueSetUniqueCondition13, valueSetUniqueCondition23);
        Assert.assertNotEquals(valueSetUniqueCondition23, valueSetUniqueCondition13);

        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition24 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name3", "value2");
        Assert.assertNotEquals(valueSetUniqueCondition14, valueSetUniqueCondition24);
        Assert.assertNotEquals(valueSetUniqueCondition24, valueSetUniqueCondition14);

        ValueSetUniqueCondition valueSetUniqueCondition15 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition25 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value3");
        Assert.assertNotEquals(valueSetUniqueCondition15, valueSetUniqueCondition25);
        Assert.assertNotEquals(valueSetUniqueCondition25, valueSetUniqueCondition15);

        ValueSetUniqueCondition valueSetUniqueCondition161 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition162 = new ValueSetUniqueCondition(valueSetUniqueCondition161, "name3", "value3");
        ValueSetUniqueCondition valueSetUniqueCondition26 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        Assert.assertNotEquals(valueSetUniqueCondition162, valueSetUniqueCondition26);
        Assert.assertNotEquals(valueSetUniqueCondition26, valueSetUniqueCondition162);

        ValueSetUniqueCondition valueSetUniqueCondition17 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition271 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition272 = new ValueSetUniqueCondition(valueSetUniqueCondition271, "name3", "value3");
        Assert.assertNotEquals(valueSetUniqueCondition17, valueSetUniqueCondition272);
        Assert.assertNotEquals(valueSetUniqueCondition272, valueSetUniqueCondition17);
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
        Assert.assertEquals(1, valueSetUniqueCondition11.hashCode());
        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        Assert.assertEquals(-1876645285, valueSetUniqueCondition12.hashCode());
        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        Assert.assertEquals(-1446500779, valueSetUniqueCondition13.hashCode());
        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition13, "name3", "value3");
        Assert.assertEquals(-394490897, valueSetUniqueCondition14.hashCode());

        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition();
        Assert.assertEquals(1, valueSetUniqueCondition21.hashCode());
        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assert.assertEquals(-1876645285, valueSetUniqueCondition22.hashCode());
        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        Assert.assertEquals(-1446500779, valueSetUniqueCondition23.hashCode());
        ValueSetUniqueCondition valueSetUniqueCondition24 = new ValueSetUniqueCondition(valueSetUniqueCondition23, "name3", "value4");
        Assert.assertEquals(-394490896, valueSetUniqueCondition24.hashCode());

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
