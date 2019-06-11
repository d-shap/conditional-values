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
    public void equalsTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition11).isEqualTo(valueSetUniqueCondition21);
        Assertions.assertThat(valueSetUniqueCondition21).isEqualTo(valueSetUniqueCondition11);

        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition12).isEqualTo(valueSetUniqueCondition22);
        Assertions.assertThat(valueSetUniqueCondition22).isEqualTo(valueSetUniqueCondition12);
        Assertions.assertThat(valueSetUniqueCondition12).isNotEqualTo(valueSetUniqueCondition21);
        Assertions.assertThat(valueSetUniqueCondition21).isNotEqualTo(valueSetUniqueCondition12);
        Assertions.assertThat(valueSetUniqueCondition22).isNotEqualTo(valueSetUniqueCondition11);
        Assertions.assertThat(valueSetUniqueCondition11).isNotEqualTo(valueSetUniqueCondition22);

        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition13).isEqualTo(valueSetUniqueCondition23);
        Assertions.assertThat(valueSetUniqueCondition23).isEqualTo(valueSetUniqueCondition13);

        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition24 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value3");
        Assertions.assertThat(valueSetUniqueCondition14).isNotEqualTo(valueSetUniqueCondition24);
        Assertions.assertThat(valueSetUniqueCondition24).isNotEqualTo(valueSetUniqueCondition14);

        ValueSetUniqueCondition valueSetUniqueCondition15 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition25 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name3", "value2");
        Assertions.assertThat(valueSetUniqueCondition15).isNotEqualTo(valueSetUniqueCondition25);
        Assertions.assertThat(valueSetUniqueCondition25).isNotEqualTo(valueSetUniqueCondition15);

        ValueSetUniqueCondition valueSetUniqueCondition161 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition162 = new ValueSetUniqueCondition(valueSetUniqueCondition161, "name3", "value3");
        ValueSetUniqueCondition valueSetUniqueCondition26 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition162).isNotEqualTo(valueSetUniqueCondition26);
        Assertions.assertThat(valueSetUniqueCondition26).isNotEqualTo(valueSetUniqueCondition162);

        ValueSetUniqueCondition valueSetUniqueCondition17 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition271 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition272 = new ValueSetUniqueCondition(valueSetUniqueCondition271, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition17).isNotEqualTo(valueSetUniqueCondition272);
        Assertions.assertThat(valueSetUniqueCondition272).isNotEqualTo(valueSetUniqueCondition17);
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void equalsToSelfTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition1).isEqualTo(valueSetUniqueCondition1);

        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition2).isEqualTo(valueSetUniqueCondition2);

        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition3).isEqualTo(valueSetUniqueCondition3);
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void equalsWrongTypeTest() {
        ValueSetUniqueCondition valueSetUniqueCondition1 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition1).isNotEqualTo("str");

        ValueSetUniqueCondition valueSetUniqueCondition2 = new ValueSetUniqueCondition(valueSetUniqueCondition1, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition2).isNotEqualTo(new StringBuilder());

        ValueSetUniqueCondition valueSetUniqueCondition3 = new ValueSetUniqueCondition(valueSetUniqueCondition2, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition3).isNotEqualTo(5);
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void hashCodeTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition11).hasHashCode(1);
        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition12).hasHashCode(-1876645285);
        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition13).hasHashCode(-1446500779);
        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition13, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition14).hasHashCode(-394490897);

        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition21).hasHashCode(1);
        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition22).hasHashCode(-1876645285);
        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition23).hasHashCode(-1446500779);
        ValueSetUniqueCondition valueSetUniqueCondition24 = new ValueSetUniqueCondition(valueSetUniqueCondition23, "name3", "value4");
        Assertions.assertThat(valueSetUniqueCondition24).hasHashCode(-394490896);

        ValueSetUniqueCondition valueSetUniqueCondition31 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition31).hasHashCode(1);
        ValueSetUniqueCondition valueSetUniqueCondition32 = new ValueSetUniqueCondition(valueSetUniqueCondition31, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition32).hasHashCode(-1876645253);
        ValueSetUniqueCondition valueSetUniqueCondition33 = new ValueSetUniqueCondition(valueSetUniqueCondition32, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition33).hasHashCode(-1446500779);
        ValueSetUniqueCondition valueSetUniqueCondition34 = new ValueSetUniqueCondition(valueSetUniqueCondition33, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition34).hasHashCode(-394490897);

        Assertions.assertThat(valueSetUniqueCondition11.hashCode()).isEqualTo(valueSetUniqueCondition21.hashCode());
        Assertions.assertThat(valueSetUniqueCondition12.hashCode()).isEqualTo(valueSetUniqueCondition22.hashCode());
        Assertions.assertThat(valueSetUniqueCondition13.hashCode()).isEqualTo(valueSetUniqueCondition23.hashCode());
        Assertions.assertThat(valueSetUniqueCondition14.hashCode()).isNotEqualTo(valueSetUniqueCondition24.hashCode());
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
