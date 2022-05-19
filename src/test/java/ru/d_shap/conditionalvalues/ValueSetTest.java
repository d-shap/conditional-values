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
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.predicate.EqualsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;

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
        Map<String, Set<String>> conditions01 = null;
        ValueSet<String> valueSet01 = new ValueSet<>(null, conditions01, new HashSet<String>());
        Assertions.assertThat(valueSet01.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions02 = new HashMap<>();
        ValueSet<String> valueSet02 = new ValueSet<>(null, conditions02, new HashSet<String>());
        Assertions.assertThat(valueSet02.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions03 = new HashMap<>();
        Set<String> condition03 = new HashSet<>();
        condition03.add("val");
        conditions03.put(null, condition03);
        ValueSet<String> valueSet03 = new ValueSet<>(null, conditions03, new HashSet<String>());
        Assertions.assertThat(valueSet03.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions04 = new HashMap<>();
        Set<String> condition04 = null;
        conditions04.put("cond", condition04);
        ValueSet<String> valueSet04 = new ValueSet<>(null, conditions04, new HashSet<String>());
        Assertions.assertThat(valueSet04.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions05 = new HashMap<>();
        Set<String> condition05 = new HashSet<>();
        conditions05.put("cond", condition05);
        ValueSet<String> valueSet05 = new ValueSet<>(null, conditions05, new HashSet<String>());
        Assertions.assertThat(valueSet05.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions06 = new HashMap<>();
        Set<String> condition06 = new HashSet<>();
        condition06.add(null);
        conditions06.put("cond", condition06);
        ValueSet<String> valueSet06 = new ValueSet<>(null, conditions06, new HashSet<String>());
        Assertions.assertThat(valueSet06.getAllConditionNames()).containsExactly();

        Map<String, Set<String>> conditions07 = new HashMap<>();
        Set<String> condition07 = new HashSet<>();
        condition07.add("val");
        conditions07.put("cond", condition07);
        ValueSet<String> valueSet07 = new ValueSet<>(null, conditions07, new HashSet<String>());
        Assertions.assertThat(valueSet07.getAllConditionNames()).containsExactly("cond");

        Map<String, Set<String>> conditions08 = new HashMap<>();
        Set<String> condition081 = new HashSet<>();
        condition081.add("val1");
        conditions08.put("cond1", condition081);
        Set<String> condition082 = new HashSet<>();
        condition082.add("val2");
        conditions08.put("cond2", condition082);
        ValueSet<String> valueSet08 = new ValueSet<>(null, conditions08, new HashSet<String>());
        Assertions.assertThat(valueSet08.getAllConditionNames()).containsExactly("cond1", "cond2");

        Map<String, Set<String>> conditions09 = new HashMap<>();
        Set<String> condition091 = new HashSet<>();
        condition091.add(null);
        condition091.add("val1");
        conditions09.put("cond1", condition091);
        Set<String> condition092 = new HashSet<>();
        condition092.add(null);
        condition092.add("val2");
        conditions09.put("cond2", condition092);
        Set<String> condition093 = null;
        conditions09.put("cond3", condition093);
        ValueSet<String> valueSet09 = new ValueSet<>(null, conditions09, new HashSet<String>());
        Assertions.assertThat(valueSet09.getAllConditionNames()).containsExactly("cond1", "cond2");

        Map<String, Set<String>> conditions10 = new HashMap<>();
        Set<String> condition101 = new HashSet<>();
        condition101.add("val11");
        condition101.add("val12");
        conditions10.put("cond1", condition101);
        Set<String> condition102 = new HashSet<>();
        condition102.add("val21");
        condition102.add("val22");
        conditions10.put("cond2", condition102);
        ValueSet<String> valueSet10 = new ValueSet<>(null, conditions10, new HashSet<String>());
        Assertions.assertThat(valueSet10.getAllConditionNames()).containsExactly("cond1", "cond2");
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
        condition22.add(null);
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        Set<String> condition23 = new HashSet<>();
        condition23.add("val3");
        conditions2.put("cond3", condition23);
        Set<String> condition24 = new HashSet<>();
        condition24.add("val4");
        conditions2.put(null, condition24);
        Set<String> condition25 = null;
        conditions2.put("cond5", condition25);
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

        Assertions.assertThat(valueSet.isMatchConditions(null, new EqualsPredicate())).isFalse();

        Map<String, String> conditions011 = new HashMap<>();
        conditions011.put("cond1", "val11");
        conditions011.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions011), null)).isFalse();

        Map<String, String> conditions012 = new HashMap<>();
        conditions012.put("cond1", "VAL11");
        conditions012.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions012), null)).isFalse();

        Map<String, String> conditions021 = new HashMap<>();
        conditions021.put("cond1", "val11");
        conditions021.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions021), new EqualsPredicate())).isTrue();

        Map<String, String> conditions022 = new HashMap<>();
        conditions022.put("cond1", "VAL11");
        conditions022.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions022), new EqualsPredicate())).isFalse();

        Map<String, String> conditions023 = new HashMap<>();
        conditions023.put("cond1", "val11");
        conditions023.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions023), new EqualsIgnoreCasePredicate())).isTrue();

        Map<String, String> conditions024 = new HashMap<>();
        conditions024.put("cond1", "VAL11");
        conditions024.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions024), new EqualsIgnoreCasePredicate())).isTrue();

        Map<String, String> conditions031 = new HashMap<>();
        conditions031.put("cond1", "val12");
        conditions031.put("cond2", "val22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions031), new EqualsPredicate())).isTrue();

        Map<String, String> conditions032 = new HashMap<>();
        conditions032.put("cond1", "VAL12");
        conditions032.put("cond2", "VAL22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions032), new EqualsPredicate())).isFalse();

        Map<String, String> conditions033 = new HashMap<>();
        conditions033.put("cond1", "val12");
        conditions033.put("cond2", "val22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions033), new EqualsIgnoreCasePredicate())).isTrue();

        Map<String, String> conditions034 = new HashMap<>();
        conditions034.put("cond1", "VAL12");
        conditions034.put("cond2", "VAL22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions034), new EqualsIgnoreCasePredicate())).isTrue();

        Map<String, String> conditions041 = new HashMap<>();
        conditions041.put("cond1", "val14");
        conditions041.put("cond2", "val23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions041), new EqualsPredicate())).isFalse();

        Map<String, String> conditions042 = new HashMap<>();
        conditions042.put("cond1", "VAL14");
        conditions042.put("cond2", "VAL23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions042), new EqualsPredicate())).isFalse();

        Map<String, String> conditions043 = new HashMap<>();
        conditions043.put("cond1", "val14");
        conditions043.put("cond2", "val23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions043), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions044 = new HashMap<>();
        conditions044.put("cond1", "VAL14");
        conditions044.put("cond2", "VAL23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions044), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions051 = new HashMap<>();
        conditions051.put("cond1", "val12");
        conditions051.put("cond2", "val24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions051), new EqualsPredicate())).isFalse();

        Map<String, String> conditions052 = new HashMap<>();
        conditions052.put("cond1", "VAL12");
        conditions052.put("cond2", "VAL24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions052), new EqualsPredicate())).isFalse();

        Map<String, String> conditions053 = new HashMap<>();
        conditions053.put("cond1", "val12");
        conditions053.put("cond2", "val24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions053), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions054 = new HashMap<>();
        conditions054.put("cond1", "VAL12");
        conditions054.put("cond2", "VAL24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions054), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions061 = new HashMap<>();
        conditions061.put("cond1", "val13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions061), new EqualsPredicate())).isFalse();

        Map<String, String> conditions062 = new HashMap<>();
        conditions062.put("cond1", "VAL13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions062), new EqualsPredicate())).isFalse();

        Map<String, String> conditions063 = new HashMap<>();
        conditions063.put("cond1", "val13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions063), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions064 = new HashMap<>();
        conditions064.put("cond1", "VAL13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions064), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions07 = new HashMap<>();
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions07), new EqualsPredicate())).isFalse();

        Map<String, String> conditions081 = new HashMap<>();
        conditions081.put("cond1", "val12");
        conditions081.put("cond2", "val22");
        conditions081.put("cond3", "val32");
        conditions081.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions081), new EqualsPredicate())).isTrue();

        Map<String, String> conditions082 = new HashMap<>();
        conditions082.put("cond1", "VAL12");
        conditions082.put("cond2", "VAL22");
        conditions082.put("cond3", "VAL32");
        conditions082.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions082), new EqualsPredicate())).isFalse();

        Map<String, String> conditions083 = new HashMap<>();
        conditions083.put("cond1", "val12");
        conditions083.put("cond2", "val22");
        conditions083.put("cond3", "val32");
        conditions083.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions083), new EqualsIgnoreCasePredicate())).isTrue();

        Map<String, String> conditions084 = new HashMap<>();
        conditions084.put("cond1", "VAL12");
        conditions084.put("cond2", "VAL22");
        conditions084.put("cond3", "VAL32");
        conditions084.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions084), new EqualsIgnoreCasePredicate())).isTrue();

        Map<String, String> conditions091 = new HashMap<>();
        conditions091.put("cond2", "val22");
        conditions091.put("cond3", "val32");
        conditions091.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions091), new EqualsPredicate())).isFalse();

        Map<String, String> conditions092 = new HashMap<>();
        conditions092.put("cond2", "VAL22");
        conditions092.put("cond3", "VAL32");
        conditions092.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions092), new EqualsPredicate())).isFalse();

        Map<String, String> conditions093 = new HashMap<>();
        conditions093.put("cond2", "val22");
        conditions093.put("cond3", "val32");
        conditions093.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions093), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions094 = new HashMap<>();
        conditions094.put("cond2", "VAL22");
        conditions094.put("cond3", "VAL32");
        conditions094.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions094), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions101 = new HashMap<>();
        conditions101.put("cond1", "val12");
        conditions101.put("cond3", "val32");
        conditions101.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions101), new EqualsPredicate())).isFalse();

        Map<String, String> conditions102 = new HashMap<>();
        conditions102.put("cond1", "VAL12");
        conditions102.put("cond3", "VAL32");
        conditions102.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions102), new EqualsPredicate())).isFalse();

        Map<String, String> conditions103 = new HashMap<>();
        conditions103.put("cond1", "val12");
        conditions103.put("cond3", "val32");
        conditions103.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions103), new EqualsIgnoreCasePredicate())).isFalse();

        Map<String, String> conditions104 = new HashMap<>();
        conditions104.put("cond1", "VAL12");
        conditions104.put("cond3", "VAL32");
        conditions104.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions104), new EqualsIgnoreCasePredicate())).isFalse();
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

        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(null)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet1.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(null)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet2.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(null)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet3.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(null)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet5)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet4.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(null)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet1)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet2)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet4)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet6)).isFalse();
        Assertions.assertThat(valueSet5.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(null)).isFalse();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet1)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet2)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet3)).isFalse();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet4)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet5)).isTrue();
        Assertions.assertThat(valueSet6.isMoreSpecificValueSet(valueSet7)).isFalse();

        Assertions.assertThat(valueSet7.isMoreSpecificValueSet(null)).isFalse();
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
    public void getValuesTest() {
        Set<String> values1 = null;
        ValueSet<String> valueSet1 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values1);
        Assertions.assertThat(valueSet1.getValues()).containsExactly();

        Set<String> values2 = new HashSet<>();
        ValueSet<String> valueSet2 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values2);
        Assertions.assertThat(valueSet2.getValues()).containsExactly();

        Set<String> values3 = new HashSet<>();
        values3.add(null);
        ValueSet<String> valueSet3 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values3);
        Assertions.assertThat(valueSet3.getValues()).containsExactly();

        Set<String> values4 = new HashSet<>();
        values4.add("val");
        ValueSet<String> valueSet4 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values4);
        Assertions.assertThat(valueSet4.getValues()).containsExactly("val");

        Set<String> values5 = new HashSet<>();
        values5.add(null);
        values5.add("val");
        ValueSet<String> valueSet5 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values5);
        Assertions.assertThat(valueSet5.getValues()).containsExactly("val");

        Set<String> values6 = new HashSet<>();
        values6.add("val1");
        values6.add("val2");
        values6.add("val3");
        ValueSet<String> valueSet6 = new ValueSet<>(null, new HashMap<String, Set<String>>(), values6);
        Assertions.assertThat(valueSet6.getValues()).containsExactly("val1", "val2", "val3");
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
        Map<String, Set<String>> conditions11 = null;
        Set<String> values11 = new HashSet<>();
        ValueSet<String> valueSet11 = new ValueSet<>(null, conditions11, values11);
        Assertions.assertThat(valueSet11).hasToString("{}");

        Map<String, Set<String>> conditions12 = null;
        Set<String> values12 = new HashSet<>();
        ValueSet<String> valueSet12 = new ValueSet<>("", conditions12, values12);
        Assertions.assertThat(valueSet12).hasToString("={}");

        Map<String, Set<String>> conditions13 = null;
        Set<String> values13 = new HashSet<>();
        ValueSet<String> valueSet13 = new ValueSet<>("id", conditions13, values13);
        Assertions.assertThat(valueSet13).hasToString("id={}");

        Map<String, Set<String>> conditions21 = new HashMap<>();
        Set<String> values21 = new HashSet<>();
        ValueSet<String> valueSet21 = new ValueSet<>(null, conditions21, values21);
        Assertions.assertThat(valueSet21).hasToString("{}");

        Map<String, Set<String>> conditions22 = new HashMap<>();
        Set<String> values22 = new HashSet<>();
        ValueSet<String> valueSet22 = new ValueSet<>("", conditions22, values22);
        Assertions.assertThat(valueSet22).hasToString("={}");

        Map<String, Set<String>> conditions23 = new HashMap<>();
        Set<String> values23 = new HashSet<>();
        ValueSet<String> valueSet23 = new ValueSet<>("id", conditions23, values23);
        Assertions.assertThat(valueSet23).hasToString("id={}");

        Map<String, Set<String>> conditions31 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val1");
        conditions31.put("cond1", condition31);
        Set<String> values31 = new HashSet<>();
        values31.add("val1");
        ValueSet<String> valueSet31 = new ValueSet<>(null, conditions31, values31);
        Assertions.assertThat(valueSet31).hasToString("{cond1=[val1]}");

        Map<String, Set<String>> conditions32 = new HashMap<>();
        Set<String> condition32 = new HashSet<>();
        condition32.add("val1");
        conditions32.put("cond1", condition32);
        Set<String> values32 = new HashSet<>();
        values32.add("val1");
        ValueSet<String> valueSet32 = new ValueSet<>("", conditions32, values32);
        Assertions.assertThat(valueSet32).hasToString("={cond1=[val1]}");

        Map<String, Set<String>> conditions33 = new HashMap<>();
        Set<String> condition33 = new HashSet<>();
        condition33.add("val1");
        conditions33.put("cond1", condition33);
        Set<String> values33 = new HashSet<>();
        values33.add("val1");
        ValueSet<String> valueSet33 = new ValueSet<>("id", conditions33, values33);
        Assertions.assertThat(valueSet33).hasToString("id={cond1=[val1]}");

        Map<String, Set<String>> conditions41 = new HashMap<>();
        Set<String> condition41 = new HashSet<>();
        condition41.add(null);
        condition41.add("val1");
        conditions41.put("cond1", condition41);
        Set<String> values41 = new HashSet<>();
        values41.add("val1");
        ValueSet<String> valueSet41 = new ValueSet<>(null, conditions41, values41);
        Assertions.assertThat(valueSet41).hasToString("{cond1=[val1]}");

        Map<String, Set<String>> conditions42 = new HashMap<>();
        Set<String> condition42 = new HashSet<>();
        condition42.add(null);
        condition42.add("val1");
        conditions42.put("cond1", condition42);
        Set<String> values42 = new HashSet<>();
        values42.add("val1");
        ValueSet<String> valueSet42 = new ValueSet<>("", conditions42, values42);
        Assertions.assertThat(valueSet42).hasToString("={cond1=[val1]}");

        Map<String, Set<String>> conditions43 = new HashMap<>();
        Set<String> condition43 = new HashSet<>();
        condition43.add(null);
        condition43.add("val1");
        conditions43.put("cond1", condition43);
        Set<String> values43 = new HashSet<>();
        values43.add("val1");
        ValueSet<String> valueSet43 = new ValueSet<>("id", conditions43, values43);
        Assertions.assertThat(valueSet43).hasToString("id={cond1=[val1]}");

        Map<String, Set<String>> conditions51 = new HashMap<>();
        Set<String> condition511 = new HashSet<>();
        condition511.add("val11");
        condition511.add("val12");
        conditions51.put("cond1", condition511);
        Set<String> condition512 = new HashSet<>();
        condition512.add("val2");
        conditions51.put("cond2", condition512);
        Set<String> values51 = new HashSet<>();
        values51.add("val1");
        values51.add("val2");
        values51.add("val3");
        ValueSet<String> valueSet51 = new ValueSet<>(null, conditions51, values51);
        Assertions.assertThat(valueSet51).toToString().startsWith("{");
        Assertions.assertThat(valueSet51).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet51).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet51).toToString().doesNotContain("val3");

        Map<String, Set<String>> conditions52 = new HashMap<>();
        Set<String> condition521 = new HashSet<>();
        condition521.add("val11");
        condition521.add("val12");
        conditions52.put("cond1", condition521);
        Set<String> condition522 = new HashSet<>();
        condition522.add("val2");
        conditions52.put("cond2", condition522);
        Set<String> values52 = new HashSet<>();
        values52.add("val1");
        values52.add("val2");
        values52.add("val3");
        ValueSet<String> valueSet52 = new ValueSet<>("", conditions52, values52);
        Assertions.assertThat(valueSet52).toToString().startsWith("={");
        Assertions.assertThat(valueSet52).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet52).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet52).toToString().doesNotContain("val3");

        Map<String, Set<String>> conditions53 = new HashMap<>();
        Set<String> condition531 = new HashSet<>();
        condition531.add("val11");
        condition531.add("val12");
        conditions53.put("cond1", condition531);
        Set<String> condition532 = new HashSet<>();
        condition532.add("val2");
        conditions53.put("cond2", condition532);
        Set<String> values53 = new HashSet<>();
        values53.add("val1");
        values53.add("val2");
        values53.add("val3");
        ValueSet<String> valueSet53 = new ValueSet<>("id", conditions53, values53);
        Assertions.assertThat(valueSet53).toToString().startsWith("id={");
        Assertions.assertThat(valueSet53).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet53).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet53).toToString().doesNotContain("val3");
    }

}
