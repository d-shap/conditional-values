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
    public void getIdTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition(null);
        Assertions.assertThat(valueSetUniqueCondition11.getId()).isNull();

        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition12.getId()).isNull();

        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition13.getId()).isNull();

        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition("");
        Assertions.assertThat(valueSetUniqueCondition21.getId()).isEqualTo("");

        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition22.getId()).isEqualTo("");

        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition23.getId()).isEqualTo("");

        ValueSetUniqueCondition valueSetUniqueCondition31 = new ValueSetUniqueCondition("id");
        Assertions.assertThat(valueSetUniqueCondition31.getId()).isEqualTo("id");

        ValueSetUniqueCondition valueSetUniqueCondition32 = new ValueSetUniqueCondition(valueSetUniqueCondition31, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition32.getId()).isEqualTo("id");

        ValueSetUniqueCondition valueSetUniqueCondition33 = new ValueSetUniqueCondition(valueSetUniqueCondition32, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition33.getId()).isEqualTo("id");
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void getConditionsTest() {
        ValueSetUniqueCondition valueSetUniqueCondition01 = new ValueSetUniqueCondition(null);
        Map<String, String> conditions01 = valueSetUniqueCondition01.getConditions();
        Assertions.assertThat(conditions01).hasSize(0);

        ValueSetUniqueCondition valueSetUniqueCondition02 = new ValueSetUniqueCondition(valueSetUniqueCondition01, "name1", "value1");
        Map<String, String> conditions02 = valueSetUniqueCondition02.getConditions();
        Assertions.assertThat(conditions02).hasSize(1);
        Assertions.assertThat(conditions02).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition03 = new ValueSetUniqueCondition(valueSetUniqueCondition02, "name2", "value2");
        Map<String, String> conditions03 = valueSetUniqueCondition03.getConditions();
        Assertions.assertThat(conditions03).hasSize(2);
        Assertions.assertThat(conditions03).containsEntry("name1", "value1");
        Assertions.assertThat(conditions03).containsEntry("name2", "value2");

        ValueSetUniqueCondition valueSetUniqueCondition04 = new ValueSetUniqueCondition(valueSetUniqueCondition03, "name3", "value3");
        Map<String, String> conditions04 = valueSetUniqueCondition04.getConditions();
        Assertions.assertThat(conditions04).hasSize(3);
        Assertions.assertThat(conditions04).containsEntry("name1", "value1");
        Assertions.assertThat(conditions04).containsEntry("name2", "value2");
        Assertions.assertThat(conditions04).containsEntry("name3", "value3");

        ValueSetUniqueCondition valueSetUniqueCondition05 = new ValueSetUniqueCondition(valueSetUniqueCondition02, null, null);
        Map<String, String> conditions08 = valueSetUniqueCondition05.getConditions();
        Assertions.assertThat(conditions08).hasSize(1);
        Assertions.assertThat(conditions08).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition06 = new ValueSetUniqueCondition(valueSetUniqueCondition02, "name4", null);
        Map<String, String> conditions06 = valueSetUniqueCondition06.getConditions();
        Assertions.assertThat(conditions06).hasSize(1);
        Assertions.assertThat(conditions06).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition07 = new ValueSetUniqueCondition(valueSetUniqueCondition02, null, "value4");
        Map<String, String> conditions07 = valueSetUniqueCondition07.getConditions();
        Assertions.assertThat(conditions07).hasSize(1);
        Assertions.assertThat(conditions07).containsEntry("name1", "value1");

        ValueSetUniqueCondition valueSetUniqueCondition08 = new ValueSetUniqueCondition(valueSetUniqueCondition02, "name4", "value4");
        Map<String, String> conditions05 = valueSetUniqueCondition08.getConditions();
        Assertions.assertThat(conditions05).hasSize(2);
        Assertions.assertThat(conditions05).containsEntry("name1", "value1");
        Assertions.assertThat(conditions05).containsEntry("name4", "value4");

        ValueSetUniqueCondition valueSetUniqueCondition09 = new ValueSetUniqueCondition(valueSetUniqueCondition02, "name5", "value1");
        Map<String, String> conditions09 = valueSetUniqueCondition09.getConditions();
        Assertions.assertThat(conditions09).hasSize(2);
        Assertions.assertThat(conditions09).containsEntry("name1", "value1");
        Assertions.assertThat(conditions09).containsEntry("name5", "value1");

        try {
            new ValueSetUniqueCondition(valueSetUniqueCondition02, "name1", "value5");
            Assertions.fail("ValueSetUniqueCondition test fail");
        } catch (DuplicateConditionNameException ex) {
            Assertions.assertThat(ex).hasMessage("Duplicate condition name: name1");
        }
    }

    /**
     * {@link ValueSetUniqueCondition} class test.
     */
    @Test
    public void toStringTest() {
        ValueSetUniqueCondition valueSetUniqueCondition11 = new ValueSetUniqueCondition(null);
        Assertions.assertThat(valueSetUniqueCondition11).hasToString("{}");

        ValueSetUniqueCondition valueSetUniqueCondition12 = new ValueSetUniqueCondition(valueSetUniqueCondition11, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition12).hasToString("{name1=value1}");

        ValueSetUniqueCondition valueSetUniqueCondition13 = new ValueSetUniqueCondition(valueSetUniqueCondition12, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition13).toStringContains("name1=value1");
        Assertions.assertThat(valueSetUniqueCondition13).toStringContains("name2=value2");

        ValueSetUniqueCondition valueSetUniqueCondition14 = new ValueSetUniqueCondition(valueSetUniqueCondition13, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition14).toStringContains("name1=value1");
        Assertions.assertThat(valueSetUniqueCondition14).toStringContains("name2=value2");
        Assertions.assertThat(valueSetUniqueCondition14).toStringContains("name3=value3");

        ValueSetUniqueCondition valueSetUniqueCondition21 = new ValueSetUniqueCondition("");
        Assertions.assertThat(valueSetUniqueCondition21).hasToString("={}");

        ValueSetUniqueCondition valueSetUniqueCondition22 = new ValueSetUniqueCondition(valueSetUniqueCondition21, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition22).hasToString("={name1=value1}");

        ValueSetUniqueCondition valueSetUniqueCondition23 = new ValueSetUniqueCondition(valueSetUniqueCondition22, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition23).toStringContains("={");
        Assertions.assertThat(valueSetUniqueCondition23).toStringContains("name1=value1");
        Assertions.assertThat(valueSetUniqueCondition23).toStringContains("name2=value2");

        ValueSetUniqueCondition valueSetUniqueCondition31 = new ValueSetUniqueCondition("id");
        Assertions.assertThat(valueSetUniqueCondition31).hasToString("id={}");

        ValueSetUniqueCondition valueSetUniqueCondition32 = new ValueSetUniqueCondition(valueSetUniqueCondition31, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition32).hasToString("id={name1=value1}");

        ValueSetUniqueCondition valueSetUniqueCondition33 = new ValueSetUniqueCondition(valueSetUniqueCondition32, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition33).toStringContains("id={");
        Assertions.assertThat(valueSetUniqueCondition33).toStringContains("name1=value1");
        Assertions.assertThat(valueSetUniqueCondition33).toStringContains("name2=value2");
    }

}
