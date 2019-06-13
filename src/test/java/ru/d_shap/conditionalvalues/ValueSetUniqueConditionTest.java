///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of conditional values.
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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

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
        Assertions.assertThat(conditions1).hasSize(0);

        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        Map<String, String> conditions2 = valueSetUniqueCondition2.getConditions();
        Assertions.assertThat(conditions2).hasSize(1);
        Assertions.assertThat(conditions2).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");
        Map<String, String> conditions3 = valueSetUniqueCondition3.getConditions();
        Assertions.assertThat(conditions3).hasSize(2);
        Assertions.assertThat(conditions3).containsEntry("name1", "value1");
        Assertions.assertThat(conditions3).containsEntry("name2", "value2");

        ValueSetUniqueCondition valueSetUniqueCondition4 = new ValueSetUniqueCondition(valueSetUniqueCondition3, "name3", "value3");
        Map<String, String> conditions4 = valueSetUniqueCondition4.getConditions();
        Assertions.assertThat(conditions4).hasSize(3);
        Assertions.assertThat(conditions4).containsEntry("name1", "value1");
        Assertions.assertThat(conditions4).containsEntry("name2", "value2");
        Assertions.assertThat(conditions4).containsEntry("name3", "value3");

        ValueSetUniqueCondition valueSetUniqueCondition5 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name1", "value2");
        Map<String, String> conditions5 = valueSetUniqueCondition5.getConditions();
        Assertions.assertThat(conditions5).hasSize(1);
        Assertions.assertThat(conditions5).containsEntry("name1", "value2");

        ValueSetUniqueCondition valueSetUniqueCondition6 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", null);
        Map<String, String> conditions6 = valueSetUniqueCondition6.getConditions();
        Assertions.assertThat(conditions6).hasSize(1);
        Assertions.assertThat(conditions6).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition7 = new ValueSetUniqueCondition(valueSetUniqueCondition2, null, "value2");
        Map<String, String> conditions7 = valueSetUniqueCondition7.getConditions();
        Assertions.assertThat(conditions7).hasSize(1);
        Assertions.assertThat(conditions7).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition8 = new ValueSetUniqueCondition(valueSetUniqueCondition2, null, null);
        Map<String, String> conditions8 = valueSetUniqueCondition8.getConditions();
        Assertions.assertThat(conditions8).hasSize(1);
        Assertions.assertThat(conditions8).containsEntry("name1", "value1");
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void isSameConditionTest() {
        // TODO
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition1).hasToString("{}");

        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition2).hasToString("{name1=value1}");

        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition3).toStringContains("name1=value1");
        Assertions.assertThat(valueSetUniqueCondition3).toStringContains("name2=value2");

        ValueSetUniqueCondition valueSetUniqueCondition4 = new ValueSetUniqueCondition(valueSetUniqueCondition3, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition4).toStringContains("name1=value1");
        Assertions.assertThat(valueSetUniqueCondition4).toStringContains("name2=value2");
        Assertions.assertThat(valueSetUniqueCondition4).toStringContains("name3=value3");
    }

}
