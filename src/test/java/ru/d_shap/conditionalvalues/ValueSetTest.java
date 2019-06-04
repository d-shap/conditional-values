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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link ValueSet}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetTest {

    /**
     * Test class constructor.
     */
    public ValueSetTest() {
        super();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getIdTest() {
        ValueSet<String> valueSet1 = new ValueSet<>(null, new HashMap<String, Set<String>>(), new HashSet<String>());
        Assertions.assertThat(valueSet1.getId()).isNull();

        ValueSet<String> valueSet2 = new ValueSet<>("", new HashMap<String, Set<String>>(), new HashSet<String>());
        Assertions.assertThat(valueSet2.getId()).isEqualTo("");

        ValueSet<String> valueSet3 = new ValueSet<>(" ", new HashMap<String, Set<String>>(), new HashSet<String>());
        Assertions.assertThat(valueSet3.getId()).isEqualTo(" ");

        ValueSet<String> valueSet4 = new ValueSet<>("id", new HashMap<String, Set<String>>(), new HashSet<String>());
        Assertions.assertThat(valueSet4.getId()).isEqualTo("id");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllConditionNamesTest() {
        Map<String, Set<String>> conditions1 = new HashMap<>();
        ValueSet<String> valueSet1 = new ValueSet<>(null, conditions1, new HashSet<String>());
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition2 = new HashSet<>();
        condition2.add("val");
        conditions2.put("cond", condition2);
        ValueSet<String> valueSet2 = new ValueSet<>(null, conditions2, new HashSet<String>());
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond");

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<>();
        condition32.add("val2");
        conditions3.put("cond2", condition32);
        ValueSet<String> valueSet3 = new ValueSet<>(null, conditions3, new HashSet<String>());
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");

        Map<String, Set<String>> conditions4 = new HashMap<>();
        Set<String> condition41 = new HashSet<>();
        condition41.add("val11");
        condition41.add("val12");
        conditions4.put("cond1", condition41);
        Set<String> condition42 = new HashSet<>();
        condition42.add("val21");
        condition42.add("val22");
        conditions4.put("cond2", condition42);
        ValueSet<String> valueSet4 = new ValueSet<>(null, conditions4, new HashSet<String>());
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionNamesUnmodifiableFailTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        condition1.add("val12");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val21");
        condition2.add("val22");
        conditions.put("cond2", condition2);
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());
        Assertions.assertThat(valueSet.getAllConditionNames()).hasSize(2);
        valueSet.getAllConditionNames().add("cond");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionNamesUnmodifiableEmptyFailTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());
        Assertions.assertThat(valueSet.getAllConditionNames()).hasSize(0);
        valueSet.getAllConditionNames().add("cond");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllConditionValuesTest() {
        Map<String, Set<String>> conditions1 = new HashMap<>();
        ValueSet<String> valueSet1 = new ValueSet<>(null, conditions1, new HashSet<String>());
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).containsExactly();

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition21 = new HashSet<>();
        condition21.add("val1");
        conditions2.put("cond1", condition21);
        Set<String> condition22 = new HashSet<>();
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        Set<String> condition23 = new HashSet<>();
        condition23.add("val3");
        conditions2.put("cond3", condition23);
        ValueSet<String> valueSet2 = new ValueSet<>(null, conditions2, new HashSet<String>());
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly("val3");

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val11");
        condition31.add("val12");
        condition31.add("val13");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<>();
        condition32.add("val21");
        condition32.add("val22");
        condition32.add("val23");
        conditions3.put("cond2", condition32);
        ValueSet<String> valueSet3 = new ValueSet<>(null, conditions3, new HashSet<String>());
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val11", "val12", "val13");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val21", "val22", "val23");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).containsExactly();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionValuesUnmodifiableFailTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition = new HashSet<>();
        condition.add("val");
        conditions.put("cond", condition);
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());
        Assertions.assertThat(valueSet.getAllConditionValues("cond")).hasSize(1);
        valueSet.getAllConditionValues("cond").add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionValuesUnmodifiableEmptyFailTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());
        Assertions.assertThat(valueSet.getAllConditionValues("cond")).hasSize(0);
        valueSet.getAllConditionValues("cond").add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void isMatchConditionsTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val11");
        condition1.add("val12");
        condition1.add("val13");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val21");
        condition2.add("val22");
        condition2.add("val23");
        conditions.put("cond2", condition2);
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());

        Map<String, String> conditions11 = new HashMap<>();
        conditions11.put("cond1", "val11");
        conditions11.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions11), ConditionalValues.EQUALS_PREDICATE)).isTrue();

        Map<String, String> conditions12 = new HashMap<>();
        conditions12.put("cond1", "VAL11");
        conditions12.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions12), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions13 = new HashMap<>();
        conditions13.put("cond1", "val11");
        conditions13.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions13), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isTrue();

        Map<String, String> conditions14 = new HashMap<>();
        conditions14.put("cond1", "VAL11");
        conditions14.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions14), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isTrue();

        Map<String, String> conditions21 = new HashMap<>();
        conditions21.put("cond1", "val12");
        conditions21.put("cond2", "val22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions21), ConditionalValues.EQUALS_PREDICATE)).isTrue();

        Map<String, String> conditions22 = new HashMap<>();
        conditions22.put("cond1", "VAL12");
        conditions22.put("cond2", "VAL22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions22), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions23 = new HashMap<>();
        conditions23.put("cond1", "val12");
        conditions23.put("cond2", "val22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions23), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isTrue();

        Map<String, String> conditions24 = new HashMap<>();
        conditions24.put("cond1", "VAL12");
        conditions24.put("cond2", "VAL22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions24), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isTrue();

        Map<String, String> conditions31 = new HashMap<>();
        conditions31.put("cond1", "val14");
        conditions31.put("cond2", "val23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions31), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions32 = new HashMap<>();
        conditions32.put("cond1", "VAL14");
        conditions32.put("cond2", "VAL23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions32), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions33 = new HashMap<>();
        conditions33.put("cond1", "val14");
        conditions33.put("cond2", "val23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions33), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions34 = new HashMap<>();
        conditions34.put("cond1", "VAL14");
        conditions34.put("cond2", "VAL23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions34), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions41 = new HashMap<>();
        conditions41.put("cond1", "val12");
        conditions41.put("cond2", "val24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions41), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions42 = new HashMap<>();
        conditions42.put("cond1", "VAL12");
        conditions42.put("cond2", "VAL24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions42), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions43 = new HashMap<>();
        conditions43.put("cond1", "val12");
        conditions43.put("cond2", "val24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions43), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions44 = new HashMap<>();
        conditions44.put("cond1", "VAL12");
        conditions44.put("cond2", "VAL24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions44), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions51 = new HashMap<>();
        conditions51.put("cond1", "val13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions51), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions52 = new HashMap<>();
        conditions52.put("cond1", "VAL13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions52), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions53 = new HashMap<>();
        conditions53.put("cond1", "val13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions53), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions54 = new HashMap<>();
        conditions54.put("cond1", "VAL13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions54), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions6 = new HashMap<>();
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions6), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions71 = new HashMap<>();
        conditions71.put("cond1", "val12");
        conditions71.put("cond2", "val22");
        conditions71.put("cond3", "val32");
        conditions71.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions71), ConditionalValues.EQUALS_PREDICATE)).isTrue();

        Map<String, String> conditions72 = new HashMap<>();
        conditions72.put("cond1", "VAL12");
        conditions72.put("cond2", "VAL22");
        conditions72.put("cond3", "VAL32");
        conditions72.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions72), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions73 = new HashMap<>();
        conditions73.put("cond1", "val12");
        conditions73.put("cond2", "val22");
        conditions73.put("cond3", "val32");
        conditions73.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions73), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isTrue();

        Map<String, String> conditions74 = new HashMap<>();
        conditions74.put("cond1", "VAL12");
        conditions74.put("cond2", "VAL22");
        conditions74.put("cond3", "VAL32");
        conditions74.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions74), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isTrue();

        Map<String, String> conditions81 = new HashMap<>();
        conditions81.put("cond2", "val22");
        conditions81.put("cond3", "val32");
        conditions81.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions81), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions82 = new HashMap<>();
        conditions82.put("cond2", "VAL22");
        conditions82.put("cond3", "VAL32");
        conditions82.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions82), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions83 = new HashMap<>();
        conditions83.put("cond2", "val22");
        conditions83.put("cond3", "val32");
        conditions83.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions83), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions84 = new HashMap<>();
        conditions84.put("cond2", "VAL22");
        conditions84.put("cond3", "VAL32");
        conditions84.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions84), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions91 = new HashMap<>();
        conditions91.put("cond1", "val12");
        conditions91.put("cond3", "val32");
        conditions91.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions91), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions92 = new HashMap<>();
        conditions92.put("cond1", "VAL12");
        conditions92.put("cond3", "VAL32");
        conditions92.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions92), ConditionalValues.EQUALS_PREDICATE)).isFalse();

        Map<String, String> conditions93 = new HashMap<>();
        conditions93.put("cond1", "val12");
        conditions93.put("cond3", "val32");
        conditions93.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions93), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();

        Map<String, String> conditions94 = new HashMap<>();
        conditions94.put("cond1", "VAL12");
        conditions94.put("cond3", "VAL32");
        conditions94.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions94), ConditionalValues.EQUALS_IGNORE_CASE_PREDICATE)).isFalse();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void isMoreSpecificValueSetTest() {
        Map<String, Set<String>> conditions1 = new HashMap<>();
        Set<String> condition11 = new HashSet<>();
        condition11.add("val1");
        conditions1.put("cond1", condition11);
        Set<String> condition12 = new HashSet<>();
        condition12.add("val2");
        conditions1.put("cond2", condition12);
        ValueSet<String> valueSet1 = new ValueSet<>(null, conditions1, new HashSet<String>());

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition21 = new HashSet<>();
        condition21.add("val1");
        conditions2.put("cond1", condition21);
        Set<String> condition22 = new HashSet<>();
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        ValueSet<String> valueSet2 = new ValueSet<>(null, conditions2, new HashSet<String>());

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<>();
        condition32.add("val8");
        conditions3.put("cond8", condition32);
        ValueSet<String> valueSet3 = new ValueSet<>(null, conditions3, new HashSet<String>());

        Map<String, Set<String>> conditions4 = new HashMap<>();
        Set<String> condition4 = new HashSet<>();
        condition4.add("val1");
        conditions4.put("cond1", condition4);
        ValueSet<String> valueSet4 = new ValueSet<>(null, conditions4, new HashSet<String>());

        Map<String, Set<String>> conditions5 = new HashMap<>();
        Set<String> condition5 = new HashSet<>();
        condition5.add("val8");
        conditions5.put("cond1", condition5);
        ValueSet<String> valueSet5 = new ValueSet<>(null, conditions5, new HashSet<String>());

        Map<String, Set<String>> conditions6 = new HashMap<>();
        Set<String> condition61 = new HashSet<>();
        condition61.add("val1");
        conditions6.put("cond1", condition61);
        Set<String> condition62 = new HashSet<>();
        condition62.add("val2");
        conditions6.put("cond2", condition62);
        Set<String> condition63 = new HashSet<>();
        condition63.add("val3");
        conditions6.put("cond3", condition63);
        Set<String> condition64 = new HashSet<>();
        condition64.add("val4");
        conditions6.put("cond4", condition64);
        ValueSet<String> valueSet6 = new ValueSet<>(null, conditions6, new HashSet<String>());

        Map<String, Set<String>> conditions7 = new HashMap<>();
        Set<String> condition71 = new HashSet<>();
        condition71.add("val8");
        conditions7.put("cond1", condition71);
        Set<String> condition72 = new HashSet<>();
        condition72.add("val8");
        conditions7.put("cond2", condition72);
        Set<String> condition73 = new HashSet<>();
        condition73.add("val8");
        conditions7.put("cond3", condition73);
        Set<String> condition74 = new HashSet<>();
        condition74.add("val8");
        conditions7.put("cond4", condition74);
        ValueSet<String> valueSet7 = new ValueSet<>(null, conditions7, new HashSet<String>());

        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet5)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet4)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet1)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet2)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(valueSet1)).isTrue();
        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(valueSet2)).isTrue();
        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(valueSet6)).isFalse();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getValueSetUniqueConditionsTest() {
        Map<String, Set<String>> conditions1 = new HashMap<>();
        ValueSet<String> valueSet1 = new ValueSet<>(null, conditions1, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions1 = valueSet1.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions1).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions1).hasSize(0);

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition2 = new HashSet<>();
        condition2.add("val1");
        conditions2.put("cond1", condition2);
        ValueSet<String> valueSet2 = new ValueSet<>(null, conditions2, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions2 = valueSet2.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions2).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions2).hasSize(1);

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition3 = new HashSet<>();
        condition3.add("val1");
        condition3.add("val2");
        conditions3.put("cond1", condition3);
        ValueSet<String> valueSet3 = new ValueSet<>(null, conditions3, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions3 = valueSet3.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions3).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions3).hasSize(2);

        Map<String, Set<String>> conditions4 = new HashMap<>();
        Set<String> condition41 = new HashSet<>();
        condition41.add("val1");
        conditions4.put("cond1", condition41);
        Set<String> condition42 = new HashSet<>();
        condition42.add("val2");
        conditions4.put("cond2", condition42);
        ValueSet<String> valueSet4 = new ValueSet<>(null, conditions4, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions4 = valueSet4.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions4).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions4).hasSize(1);

        Map<String, Set<String>> conditions5 = new HashMap<>();
        Set<String> condition51 = new HashSet<>();
        condition51.add("val1");
        condition51.add("val2");
        conditions5.put("cond1", condition51);
        Set<String> condition52 = new HashSet<>();
        condition52.add("val1");
        condition52.add("val2");
        conditions5.put("cond2", condition52);
        ValueSet<String> valueSet5 = new ValueSet<>(null, conditions5, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions5 = valueSet5.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions5).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions5).hasSize(4);

        Map<String, Set<String>> conditions6 = new HashMap<>();
        Set<String> condition61 = new HashSet<>();
        condition61.add("val1");
        condition61.add("val2");
        conditions6.put("cond1", condition61);
        Set<String> condition62 = new HashSet<>();
        condition62.add("val1");
        condition62.add("val2");
        conditions6.put("cond2", condition62);
        Set<String> condition63 = new HashSet<>();
        condition63.add("val1");
        condition63.add("val2");
        condition63.add("val3");
        conditions6.put("cond3", condition63);
        ValueSet<String> valueSet6 = new ValueSet<>(null, conditions6, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions6 = valueSet6.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions6).isNotNull();
        Assertions.assertThat(valueSetUniqueConditions6).hasSize(12);
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValueSetUniqueConditionsUnmodifiableFailTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        Set<String> condition1 = new HashSet<>();
        condition1.add("val1");
        condition1.add("val2");
        conditions.put("cond1", condition1);
        Set<String> condition2 = new HashSet<>();
        condition2.add("val1");
        condition2.add("val2");
        condition2.add("val3");
        conditions.put("cond2", condition2);
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions).hasSize(6);
        valueSetUniqueConditions.add(new ValueSetUniqueCondition());
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValueSetUniqueConditionsUnmodifiableEmptyFailTest() {
        Map<String, Set<String>> conditions = new HashMap<>();
        ValueSet<String> valueSet = new ValueSet<>(null, conditions, new HashSet<String>());
        List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions).hasSize(0);
        valueSetUniqueConditions.add(new ValueSetUniqueCondition());
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getValuesTest() {
        Set<String> values1 = new HashSet<>();
        ValueSet<String> valueSet1 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values1);
        Assertions.assertThat(valueSet1.getValues()).containsExactly();

        Set<String> values2 = new HashSet<>();
        values2.add("val");
        ValueSet<String> valueSet2 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values2);
        Assertions.assertThat(valueSet2.getValues()).containsExactly("val");

        Set<String> values3 = new HashSet<>();
        values3.add("val1");
        values3.add("val2");
        values3.add("val3");
        ValueSet<String> valueSet3 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values3);
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableFailTest() {
        Set<String> values = new HashSet<>();
        values.add("val1");
        values.add("val2");
        values.add("val3");
        ValueSet<String> valueSet = new ValueSet<>(null, new HashMap<String, Set<String>>(), values);
        Assertions.assertThat(valueSet.getValues()).hasSize(3);
        valueSet.getValues().add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableEmptyFailTest() {
        Set<String> values = new HashSet<>();
        ValueSet<String> valueSet = new ValueSet<>(null, new HashMap<String, Set<String>>(), values);
        Assertions.assertThat(valueSet.getValues()).hasSize(0);
        valueSet.getValues().add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void toStringTest() {
        Map<String, Set<String>> conditions11 = new HashMap<>();
        Set<String> values11 = new HashSet<>();
        ValueSet<String> valueSet11 = new ValueSet<>(null, conditions11, values11);
        Assertions.assertThat(valueSet11).hasToString("{}");

        Map<String, Set<String>> conditions12 = new HashMap<>();
        Set<String> values12 = new HashSet<>();
        ValueSet<String> valueSet12 = new ValueSet<>("", conditions12, values12);
        Assertions.assertThat(valueSet12).hasToString("={}");

        Map<String, Set<String>> conditions13 = new HashMap<>();
        Set<String> values13 = new HashSet<>();
        ValueSet<String> valueSet13 = new ValueSet<>("id", conditions13, values13);
        Assertions.assertThat(valueSet13).hasToString("id={}");

        Map<String, Set<String>> conditions21 = new HashMap<>();
        Set<String> condition21 = new HashSet<>();
        condition21.add("val1");
        conditions21.put("cond1", condition21);
        Set<String> values21 = new HashSet<>();
        values21.add("val1");
        ValueSet<String> valueSet21 = new ValueSet<>(null, conditions21, values21);
        Assertions.assertThat(valueSet21).hasToString("{cond1=[val1]}");

        Map<String, Set<String>> conditions22 = new HashMap<>();
        Set<String> condition22 = new HashSet<>();
        condition22.add("val1");
        conditions22.put("cond1", condition22);
        Set<String> values22 = new HashSet<>();
        values22.add("val1");
        ValueSet<String> valueSet22 = new ValueSet<>("", conditions22, values22);
        Assertions.assertThat(valueSet22).hasToString("={cond1=[val1]}");

        Map<String, Set<String>> conditions23 = new HashMap<>();
        Set<String> condition23 = new HashSet<>();
        condition23.add("val1");
        conditions23.put("cond1", condition23);
        Set<String> values23 = new HashSet<>();
        values23.add("val1");
        ValueSet<String> valueSet23 = new ValueSet<>("id", conditions23, values23);
        Assertions.assertThat(valueSet23).hasToString("id={cond1=[val1]}");

        Map<String, Set<String>> conditions31 = new HashMap<>();
        Set<String> condition311 = new HashSet<>();
        condition311.add("val11");
        condition311.add("val12");
        conditions31.put("cond1", condition311);
        Set<String> condition312 = new HashSet<>();
        condition312.add("val2");
        conditions31.put("cond2", condition312);
        Set<String> values31 = new HashSet<>();
        values31.add("val1");
        values31.add("val2");
        values31.add("val3");
        ValueSet<String> valueSet31 = new ValueSet<>(null, conditions31, values31);
        Assertions.assertThat(valueSet31).toToString().startsWith("{");
        Assertions.assertThat(valueSet31).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet31).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet31).toToString().doesNotContain("val3");

        Map<String, Set<String>> conditions32 = new HashMap<>();
        Set<String> condition321 = new HashSet<>();
        condition321.add("val11");
        condition321.add("val12");
        conditions32.put("cond1", condition321);
        Set<String> condition322 = new HashSet<>();
        condition322.add("val2");
        conditions32.put("cond2", condition322);
        Set<String> values32 = new HashSet<>();
        values32.add("val1");
        values32.add("val2");
        values32.add("val3");
        ValueSet<String> valueSet32 = new ValueSet<>("", conditions32, values32);
        Assertions.assertThat(valueSet32).toToString().startsWith("={");
        Assertions.assertThat(valueSet32).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet32).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet32).toToString().doesNotContain("val3");

        Map<String, Set<String>> conditions33 = new HashMap<>();
        Set<String> condition331 = new HashSet<>();
        condition331.add("val11");
        condition331.add("val12");
        conditions33.put("cond1", condition331);
        Set<String> condition332 = new HashSet<>();
        condition332.add("val2");
        conditions33.put("cond2", condition332);
        Set<String> values33 = new HashSet<>();
        values33.add("val1");
        values33.add("val2");
        values33.add("val3");
        ValueSet<String> valueSet33 = new ValueSet<>("id", conditions33, values33);
        Assertions.assertThat(valueSet33).toToString().startsWith("id={");
        Assertions.assertThat(valueSet33).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet33).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet33).toToString().doesNotContain("val3");
    }

}
