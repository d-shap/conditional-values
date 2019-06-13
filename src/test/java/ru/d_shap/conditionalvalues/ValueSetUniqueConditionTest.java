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
import ru.d_shap.conditionalvalues.misc.EqualsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.misc.EqualsPredicate;

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
        ValueSetUniqueCondition valueSetUniqueCondition101 = new ValueSetUniqueCondition();
        ValueSetUniqueCondition valueSetUniqueCondition201 = new ValueSetUniqueCondition();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(valueSetUniqueCondition101, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(valueSetUniqueCondition201, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(valueSetUniqueCondition201, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(valueSetUniqueCondition201, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition101.isSameCondition(valueSetUniqueCondition201, new PredicateImpl())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(valueSetUniqueCondition201, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(valueSetUniqueCondition101, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(valueSetUniqueCondition101, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(valueSetUniqueCondition101, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition201.isSameCondition(valueSetUniqueCondition101, new PredicateImpl())).isTrue();

        ValueSetUniqueCondition valueSetUniqueCondition102 = new ValueSetUniqueCondition(valueSetUniqueCondition101, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition202 = new ValueSetUniqueCondition(valueSetUniqueCondition201, "name1", "value1");
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(valueSetUniqueCondition102, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(valueSetUniqueCondition202, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(valueSetUniqueCondition202, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(valueSetUniqueCondition202, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition102.isSameCondition(valueSetUniqueCondition202, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(valueSetUniqueCondition202, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(valueSetUniqueCondition102, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(valueSetUniqueCondition102, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(valueSetUniqueCondition102, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition202.isSameCondition(valueSetUniqueCondition102, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition103 = new ValueSetUniqueCondition(valueSetUniqueCondition101, "name1", "value1");
        ValueSetUniqueCondition valueSetUniqueCondition203 = new ValueSetUniqueCondition(valueSetUniqueCondition201, "name1", "vaLUe1");
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(valueSetUniqueCondition103, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(valueSetUniqueCondition203, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(valueSetUniqueCondition203, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(valueSetUniqueCondition203, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition103.isSameCondition(valueSetUniqueCondition203, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(valueSetUniqueCondition203, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(valueSetUniqueCondition103, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(valueSetUniqueCondition103, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(valueSetUniqueCondition103, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition203.isSameCondition(valueSetUniqueCondition103, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition104 = new ValueSetUniqueCondition(valueSetUniqueCondition102, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition204 = new ValueSetUniqueCondition(valueSetUniqueCondition202, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(valueSetUniqueCondition104, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(valueSetUniqueCondition204, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(valueSetUniqueCondition204, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(valueSetUniqueCondition204, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition104.isSameCondition(valueSetUniqueCondition204, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(valueSetUniqueCondition204, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(valueSetUniqueCondition104, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(valueSetUniqueCondition104, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(valueSetUniqueCondition104, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition204.isSameCondition(valueSetUniqueCondition104, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition105 = new ValueSetUniqueCondition(valueSetUniqueCondition103, "name2", "VAluE2");
        ValueSetUniqueCondition valueSetUniqueCondition205 = new ValueSetUniqueCondition(valueSetUniqueCondition203, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(valueSetUniqueCondition105, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(valueSetUniqueCondition205, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(valueSetUniqueCondition205, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(valueSetUniqueCondition205, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition105.isSameCondition(valueSetUniqueCondition205, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(valueSetUniqueCondition205, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(valueSetUniqueCondition105, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(valueSetUniqueCondition105, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(valueSetUniqueCondition105, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition205.isSameCondition(valueSetUniqueCondition105, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition106 = new ValueSetUniqueCondition(valueSetUniqueCondition102, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition206 = new ValueSetUniqueCondition(valueSetUniqueCondition202, "name2", "value3");
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(valueSetUniqueCondition106, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(valueSetUniqueCondition206, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(valueSetUniqueCondition206, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(valueSetUniqueCondition206, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition106.isSameCondition(valueSetUniqueCondition206, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(valueSetUniqueCondition206, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(valueSetUniqueCondition106, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(valueSetUniqueCondition106, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(valueSetUniqueCondition106, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition206.isSameCondition(valueSetUniqueCondition106, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition107 = new ValueSetUniqueCondition(valueSetUniqueCondition103, "name2", "VAluE2");
        ValueSetUniqueCondition valueSetUniqueCondition207 = new ValueSetUniqueCondition(valueSetUniqueCondition203, "name2", "value3");
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(valueSetUniqueCondition107, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(valueSetUniqueCondition207, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(valueSetUniqueCondition207, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(valueSetUniqueCondition207, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition107.isSameCondition(valueSetUniqueCondition207, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(valueSetUniqueCondition207, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(valueSetUniqueCondition107, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(valueSetUniqueCondition107, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(valueSetUniqueCondition107, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition207.isSameCondition(valueSetUniqueCondition107, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition1081 = new ValueSetUniqueCondition(valueSetUniqueCondition102, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition1082 = new ValueSetUniqueCondition(valueSetUniqueCondition1081, "name3", "value3");
        ValueSetUniqueCondition valueSetUniqueCondition208 = new ValueSetUniqueCondition(valueSetUniqueCondition202, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(valueSetUniqueCondition1082, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(valueSetUniqueCondition208, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(valueSetUniqueCondition208, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(valueSetUniqueCondition208, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1082.isSameCondition(valueSetUniqueCondition208, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(valueSetUniqueCondition208, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(valueSetUniqueCondition1082, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(valueSetUniqueCondition1082, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(valueSetUniqueCondition1082, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition208.isSameCondition(valueSetUniqueCondition1082, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition1091 = new ValueSetUniqueCondition(valueSetUniqueCondition103, "name2", "VAluE2");
        ValueSetUniqueCondition valueSetUniqueCondition1092 = new ValueSetUniqueCondition(valueSetUniqueCondition1091, "name3", "VAluE3");
        ValueSetUniqueCondition valueSetUniqueCondition209 = new ValueSetUniqueCondition(valueSetUniqueCondition203, "name2", "value2");
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(valueSetUniqueCondition1092, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(valueSetUniqueCondition209, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(valueSetUniqueCondition209, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(valueSetUniqueCondition209, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition1092.isSameCondition(valueSetUniqueCondition209, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(valueSetUniqueCondition209, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(valueSetUniqueCondition1092, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(valueSetUniqueCondition1092, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(valueSetUniqueCondition1092, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition209.isSameCondition(valueSetUniqueCondition1092, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition110 = new ValueSetUniqueCondition(valueSetUniqueCondition102, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition2101 = new ValueSetUniqueCondition(valueSetUniqueCondition202, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition2102 = new ValueSetUniqueCondition(valueSetUniqueCondition2101, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(valueSetUniqueCondition110, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(valueSetUniqueCondition2102, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(valueSetUniqueCondition2102, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(valueSetUniqueCondition2102, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition110.isSameCondition(valueSetUniqueCondition2102, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(valueSetUniqueCondition2102, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(valueSetUniqueCondition110, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(valueSetUniqueCondition110, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(valueSetUniqueCondition110, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2102.isSameCondition(valueSetUniqueCondition110, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition111 = new ValueSetUniqueCondition(valueSetUniqueCondition103, "name2", "VAluE2");
        ValueSetUniqueCondition valueSetUniqueCondition2111 = new ValueSetUniqueCondition(valueSetUniqueCondition203, "name2", "value2");
        ValueSetUniqueCondition valueSetUniqueCondition2112 = new ValueSetUniqueCondition(valueSetUniqueCondition2111, "name3", "value3");
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(valueSetUniqueCondition111, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(valueSetUniqueCondition2112, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(valueSetUniqueCondition2112, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(valueSetUniqueCondition2112, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition111.isSameCondition(valueSetUniqueCondition2112, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(valueSetUniqueCondition2112, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(valueSetUniqueCondition111, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(valueSetUniqueCondition111, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(valueSetUniqueCondition111, new EqualsIgnoreCasePredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition2112.isSameCondition(valueSetUniqueCondition111, new PredicateImpl())).isFalse();

        ValueSetUniqueCondition valueSetUniqueCondition112 = new ValueSetUniqueCondition(valueSetUniqueCondition101, "aaa", "aaa");
        ValueSetUniqueCondition valueSetUniqueCondition212 = new ValueSetUniqueCondition(valueSetUniqueCondition201, "aaa", "aaa");
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(valueSetUniqueCondition112, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(valueSetUniqueCondition212, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(valueSetUniqueCondition212, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(valueSetUniqueCondition212, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition112.isSameCondition(valueSetUniqueCondition212, new PredicateImpl())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(valueSetUniqueCondition212, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(valueSetUniqueCondition112, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(valueSetUniqueCondition112, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(valueSetUniqueCondition112, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition212.isSameCondition(valueSetUniqueCondition112, new PredicateImpl())).isTrue();

        ValueSetUniqueCondition valueSetUniqueCondition113 = new ValueSetUniqueCondition(valueSetUniqueCondition112, "bbb", "bbb");
        ValueSetUniqueCondition valueSetUniqueCondition213 = new ValueSetUniqueCondition(valueSetUniqueCondition212, "bbb", "bbb");
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(valueSetUniqueCondition113, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(valueSetUniqueCondition213, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(valueSetUniqueCondition213, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(valueSetUniqueCondition213, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition113.isSameCondition(valueSetUniqueCondition213, new PredicateImpl())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(valueSetUniqueCondition213, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(valueSetUniqueCondition113, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(valueSetUniqueCondition113, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(valueSetUniqueCondition113, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition213.isSameCondition(valueSetUniqueCondition113, new PredicateImpl())).isTrue();

        ValueSetUniqueCondition valueSetUniqueCondition114 = new ValueSetUniqueCondition(valueSetUniqueCondition113, "name", "value");
        ValueSetUniqueCondition valueSetUniqueCondition214 = new ValueSetUniqueCondition(valueSetUniqueCondition213, "name", "value");
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(valueSetUniqueCondition114, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(valueSetUniqueCondition214, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(valueSetUniqueCondition214, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(valueSetUniqueCondition214, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition114.isSameCondition(valueSetUniqueCondition214, new PredicateImpl())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(null, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(null, new EqualsPredicate())).isFalse();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(valueSetUniqueCondition214, null)).isTrue();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(valueSetUniqueCondition114, null)).isFalse();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(valueSetUniqueCondition114, new EqualsPredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(valueSetUniqueCondition114, new EqualsIgnoreCasePredicate())).isTrue();
        Assertions.assertThat(valueSetUniqueCondition214.isSameCondition(valueSetUniqueCondition114, new PredicateImpl())).isFalse();
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

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class PredicateImpl implements Predicate {

        PredicateImpl() {
            super();
        }

        @Override
        public boolean isSameValue(final String conditionName, final String value1, final String value2) {
            return conditionName != null && conditionName.equals(value1) && conditionName.equals(value2);
        }

        @Override
        public boolean evaluate(final String conditionName, final String conditionValue, final String value) {
            return conditionName != null && conditionName.equals(conditionValue) && conditionName.equals(value);
        }

    }

}
