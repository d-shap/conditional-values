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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.util.DataHelper;
import ru.d_shap.conditionalvalues.predicate.AnyValueMatchesTuplePredicate;
import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringEqualsIgnoreCasePredicate;

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
        ValueSet<String> valueSet1 = new ValueSet<>(null, null, null, null, null);
        Assertions.assertThat(valueSet1.getId()).isNull();

        ValueSet<String> valueSet2 = new ValueSet<>("", null, null, null, null);
        Assertions.assertThat(valueSet2.getId()).isEqualTo("");

        ValueSet<String> valueSet3 = new ValueSet<>(" ", null, null, null, null);
        Assertions.assertThat(valueSet3.getId()).isEqualTo(" ");

        ValueSet<String> valueSet4 = new ValueSet<>("id", null, null, null, null);
        Assertions.assertThat(valueSet4.getId()).isEqualTo("id");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllConditionNamesTest() {
        Map<String, Set<Object>> conditions01 = null;
        ValueSet<String> valueSet01 = new ValueSet<>(null, null, null, conditions01, null);
        Assertions.assertThat(valueSet01.getAllConditionNames()).containsExactly();

        Map<String, Set<Object>> conditions02 = DataHelper.createHashMap();
        ValueSet<String> valueSet02 = new ValueSet<>(null, null, null, conditions02, null);
        Assertions.assertThat(valueSet02.getAllConditionNames()).containsExactly();

        Map<String, Set<Object>> conditions03 = DataHelper.createHashMap();
        Set<Object> condition03 = DataHelper.createHashSet((Object) "val");
        conditions03.put(null, condition03);
        ValueSet<String> valueSet03 = new ValueSet<>(null, null, null, conditions03, null);
        Assertions.assertThat(valueSet03.getAllConditionNames()).containsExactly();

        Map<String, Set<Object>> conditions04 = DataHelper.createHashMap();
        Set<Object> condition04 = null;
        conditions04.put("cond", condition04);
        ValueSet<String> valueSet04 = new ValueSet<>(null, null, null, conditions04, null);
        Assertions.assertThat(valueSet04.getAllConditionNames()).containsExactly();

        Map<String, Set<Object>> conditions05 = DataHelper.createHashMap();
        Set<Object> condition05 = DataHelper.createHashSet();
        conditions05.put("cond", condition05);
        ValueSet<String> valueSet05 = new ValueSet<>(null, null, null, conditions05, null);
        Assertions.assertThat(valueSet05.getAllConditionNames()).containsExactly();

        Map<String, Set<Object>> conditions06 = DataHelper.createHashMap();
        Set<Object> condition06 = DataHelper.createHashSet((Object) null);
        conditions06.put("cond", condition06);
        ValueSet<String> valueSet06 = new ValueSet<>(null, null, null, conditions06, null);
        Assertions.assertThat(valueSet06.getAllConditionNames()).containsExactly();

        Map<String, Set<Object>> conditions07 = DataHelper.createHashMap();
        Set<Object> condition07 = DataHelper.createHashSet((Object) "val");
        conditions07.put("cond", condition07);
        ValueSet<String> valueSet07 = new ValueSet<>(null, null, null, conditions07, null);
        Assertions.assertThat(valueSet07.getAllConditionNames()).containsExactly("cond");

        Map<String, Set<Object>> conditions08 = DataHelper.createHashMap();
        Set<Object> condition081 = DataHelper.createHashSet((Object) "val1");
        conditions08.put("cond1", condition081);
        Set<Object> condition082 = DataHelper.createHashSet((Object) "val2");
        conditions08.put("cond2", condition082);
        ValueSet<String> valueSet08 = new ValueSet<>(null, null, null, conditions08, null);
        Assertions.assertThat(valueSet08.getAllConditionNames()).containsExactly("cond1", "cond2");

        Map<String, Set<Object>> conditions09 = DataHelper.createHashMap();
        Set<Object> condition091 = DataHelper.createHashSet((Object) null, "val1");
        conditions09.put("cond1", condition091);
        Set<Object> condition092 = DataHelper.createHashSet((Object) null, "val2");
        conditions09.put("cond2", condition092);
        Set<Object> condition093 = null;
        conditions09.put("cond3", condition093);
        ValueSet<String> valueSet09 = new ValueSet<>(null, null, null, conditions09, null);
        Assertions.assertThat(valueSet09.getAllConditionNames()).containsExactly("cond1", "cond2");

        Map<String, Set<Object>> conditions10 = DataHelper.createHashMap();
        Set<Object> condition101 = DataHelper.createHashSet((Object) "val11", "val12");
        conditions10.put("cond1", condition101);
        Set<Object> condition102 = DataHelper.createHashSet((Object) "val21", "val22");
        conditions10.put("cond2", condition102);
        ValueSet<String> valueSet10 = new ValueSet<>(null, null, null, conditions10, null);
        Assertions.assertThat(valueSet10.getAllConditionNames()).containsExactly("cond1", "cond2");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionNamesUnmodifiableFailTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        Set<Object> condition1 = DataHelper.createHashSet((Object) "val11", "val12");
        conditions.put("cond1", condition1);
        Set<Object> condition2 = DataHelper.createHashSet((Object) "val21", "val22");
        conditions.put("cond2", condition2);
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);
        Assertions.assertThat(valueSet.getAllConditionNames()).hasSize(2);

        valueSet.getAllConditionNames().add("cond");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionNamesUnmodifiableEmptyFailTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);
        Assertions.assertThat(valueSet.getAllConditionNames()).hasSize(0);

        valueSet.getAllConditionNames().add("cond");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getAllConditionValuesTest() {
        Map<String, Set<Object>> conditions1 = DataHelper.createHashMap();
        ValueSet<String> valueSet1 = new ValueSet<>(null, null, null, conditions1, null);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).containsExactly();

        Map<String, Set<Object>> conditions2 = DataHelper.createHashMap();
        Set<Object> condition21 = DataHelper.createHashSet((Object) "val1");
        conditions2.put("cond1", condition21);
        Set<Object> condition22 = DataHelper.createHashSet((Object) null, "val2");
        conditions2.put("cond2", condition22);
        Set<Object> condition23 = DataHelper.createHashSet((Object) "val3");
        conditions2.put("cond3", condition23);
        Set<Object> condition24 = DataHelper.createHashSet((Object) "val4");
        conditions2.put(null, condition24);
        Set<Object> condition25 = null;
        conditions2.put("cond5", condition25);
        ValueSet<String> valueSet2 = new ValueSet<>(null, null, null, conditions2, null);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly("val3");

        Map<String, Set<Object>> conditions3 = DataHelper.createHashMap();
        Set<Object> condition31 = DataHelper.createHashSet((Object) "val11", "val12", "val13");
        conditions3.put("cond1", condition31);
        Set<Object> condition32 = DataHelper.createHashSet((Object) "val21", "val22", "val23");
        conditions3.put("cond2", condition32);
        ValueSet<String> valueSet3 = new ValueSet<>(null, null, null, conditions3, null);
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val11", "val12", "val13");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val21", "val22", "val23");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).containsExactly();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionValuesUnmodifiableFailTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        Set<Object> condition = DataHelper.createHashSet((Object) "val");
        conditions.put("cond", condition);
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);
        Assertions.assertThat(valueSet.getAllConditionValues("cond")).hasSize(1);

        valueSet.getAllConditionValues("cond").add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getAllConditionValuesUnmodifiableEmptyFailTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);
        Assertions.assertThat(valueSet.getAllConditionValues("cond")).hasSize(0);

        valueSet.getAllConditionValues("cond").add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void isMatchConditionsDefaultConditionalValuesPredicateTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        Set<Object> condition1 = DataHelper.createHashSet((Object) "val11", "val12", "val13");
        conditions.put("cond1", condition1);
        Set<Object> condition2 = DataHelper.createHashSet((Object) "val21", "val22", "val23");
        conditions.put("cond2", condition2);
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);

        Assertions.assertThat(valueSet.isMatchConditions(null, null, null, new EqualsPredicate())).isFalse();

        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(null), null, null, new EqualsPredicate())).isFalse();

        Assertions.assertThat(valueSet.isMatchConditions(null, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(null), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions011 = DataHelper.createHashMap();
        conditions011.put("cond1", "val11");
        conditions011.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions011), new AnyValueMatchesTuplePredicate(), null, null)).isFalse();

        Map<String, Object> conditions012 = DataHelper.createHashMap();
        conditions012.put("cond1", "VAL11");
        conditions012.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions012), new AnyValueMatchesTuplePredicate(), null, null)).isFalse();

        Map<String, Object> conditions021 = DataHelper.createHashMap();
        conditions021.put("cond1", "val11");
        conditions021.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions021), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions022 = DataHelper.createHashMap();
        conditions022.put("cond1", "VAL11");
        conditions022.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions022), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions023 = DataHelper.createHashMap();
        conditions023.put("cond1", "val11");
        conditions023.put("cond2", "val21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions023), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isTrue();

        Map<String, Object> conditions024 = DataHelper.createHashMap();
        conditions024.put("cond1", "VAL11");
        conditions024.put("cond2", "VAL21");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions024), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isTrue();

        Map<String, Object> conditions031 = DataHelper.createHashMap();
        conditions031.put("cond1", "val12");
        conditions031.put("cond2", "val22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions031), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions032 = DataHelper.createHashMap();
        conditions032.put("cond1", "VAL12");
        conditions032.put("cond2", "VAL22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions032), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions033 = DataHelper.createHashMap();
        conditions033.put("cond1", "val12");
        conditions033.put("cond2", "val22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions033), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isTrue();

        Map<String, Object> conditions034 = DataHelper.createHashMap();
        conditions034.put("cond1", "VAL12");
        conditions034.put("cond2", "VAL22");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions034), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isTrue();

        Map<String, Object> conditions041 = DataHelper.createHashMap();
        conditions041.put("cond1", "val14");
        conditions041.put("cond2", "val23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions041), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions042 = DataHelper.createHashMap();
        conditions042.put("cond1", "VAL14");
        conditions042.put("cond2", "VAL23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions042), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions043 = DataHelper.createHashMap();
        conditions043.put("cond1", "val14");
        conditions043.put("cond2", "val23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions043), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions044 = DataHelper.createHashMap();
        conditions044.put("cond1", "VAL14");
        conditions044.put("cond2", "VAL23");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions044), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions051 = DataHelper.createHashMap();
        conditions051.put("cond1", "val12");
        conditions051.put("cond2", "val24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions051), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions052 = DataHelper.createHashMap();
        conditions052.put("cond1", "VAL12");
        conditions052.put("cond2", "VAL24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions052), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions053 = DataHelper.createHashMap();
        conditions053.put("cond1", "val12");
        conditions053.put("cond2", "val24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions053), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions054 = DataHelper.createHashMap();
        conditions054.put("cond1", "VAL12");
        conditions054.put("cond2", "VAL24");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions054), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions061 = DataHelper.createHashMap();
        conditions061.put("cond1", "val13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions061), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions062 = DataHelper.createHashMap();
        conditions062.put("cond1", "VAL13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions062), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions063 = DataHelper.createHashMap();
        conditions063.put("cond1", "val13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions063), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions064 = DataHelper.createHashMap();
        conditions064.put("cond1", "VAL13");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions064), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions07 = DataHelper.createHashMap();
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions07), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions081 = DataHelper.createHashMap();
        conditions081.put("cond1", "val12");
        conditions081.put("cond2", "val22");
        conditions081.put("cond3", "val32");
        conditions081.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions081), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions082 = DataHelper.createHashMap();
        conditions082.put("cond1", "VAL12");
        conditions082.put("cond2", "VAL22");
        conditions082.put("cond3", "VAL32");
        conditions082.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions082), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions083 = DataHelper.createHashMap();
        conditions083.put("cond1", "val12");
        conditions083.put("cond2", "val22");
        conditions083.put("cond3", "val32");
        conditions083.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions083), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isTrue();

        Map<String, Object> conditions084 = DataHelper.createHashMap();
        conditions084.put("cond1", "VAL12");
        conditions084.put("cond2", "VAL22");
        conditions084.put("cond3", "VAL32");
        conditions084.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions084), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isTrue();

        Map<String, Object> conditions091 = DataHelper.createHashMap();
        conditions091.put("cond2", "val22");
        conditions091.put("cond3", "val32");
        conditions091.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions091), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions092 = DataHelper.createHashMap();
        conditions092.put("cond2", "VAL22");
        conditions092.put("cond3", "VAL32");
        conditions092.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions092), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions093 = DataHelper.createHashMap();
        conditions093.put("cond2", "val22");
        conditions093.put("cond3", "val32");
        conditions093.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions093), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions094 = DataHelper.createHashMap();
        conditions094.put("cond2", "VAL22");
        conditions094.put("cond3", "VAL32");
        conditions094.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions094), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions101 = DataHelper.createHashMap();
        conditions101.put("cond1", "val12");
        conditions101.put("cond3", "val32");
        conditions101.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions101), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions102 = DataHelper.createHashMap();
        conditions102.put("cond1", "VAL12");
        conditions102.put("cond3", "VAL32");
        conditions102.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions102), new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        Map<String, Object> conditions103 = DataHelper.createHashMap();
        conditions103.put("cond1", "val12");
        conditions103.put("cond3", "val32");
        conditions103.put("cond4", "val42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions103), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();

        Map<String, Object> conditions104 = DataHelper.createHashMap();
        conditions104.put("cond1", "VAL12");
        conditions104.put("cond3", "VAL32");
        conditions104.put("cond4", "VAL42");
        Assertions.assertThat(valueSet.isMatchConditions(new ConditionSet(conditions104), new AnyValueMatchesTuplePredicate(), null, new StringEqualsIgnoreCasePredicate())).isFalse();
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void isMoreSpecificValueSetTest() {
        Map<String, Set<Object>> conditions1 = DataHelper.createHashMap();
        Set<Object> condition11 = DataHelper.createHashSet();
        condition11.add("val1");
        conditions1.put("cond1", condition11);
        Set<Object> condition12 = DataHelper.createHashSet();
        condition12.add("val2");
        conditions1.put("cond2", condition12);
        ValueSet<String> valueSet1 = new ValueSet<>(null, null, null, conditions1, null);

        Map<String, Set<Object>> conditions2 = DataHelper.createHashMap();
        Set<Object> condition21 = DataHelper.createHashSet();
        condition21.add("val1");
        conditions2.put("cond1", condition21);
        Set<Object> condition22 = DataHelper.createHashSet();
        condition22.add("val2");
        conditions2.put("cond2", condition22);
        ValueSet<String> valueSet2 = new ValueSet<>(null, null, null, conditions2, null);

        Map<String, Set<Object>> conditions3 = DataHelper.createHashMap();
        Set<Object> condition31 = DataHelper.createHashSet();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<Object> condition32 = DataHelper.createHashSet();
        condition32.add("val8");
        conditions3.put("cond8", condition32);
        ValueSet<String> valueSet3 = new ValueSet<>(null, null, null, conditions3, null);

        Map<String, Set<Object>> conditions4 = DataHelper.createHashMap();
        Set<Object> condition4 = DataHelper.createHashSet();
        condition4.add("val1");
        conditions4.put("cond1", condition4);
        ValueSet<String> valueSet4 = new ValueSet<>(null, null, null, conditions4, null);

        Map<String, Set<Object>> conditions5 = DataHelper.createHashMap();
        Set<Object> condition5 = DataHelper.createHashSet();
        condition5.add("val8");
        conditions5.put("cond1", condition5);
        ValueSet<String> valueSet5 = new ValueSet<>(null, null, null, conditions5, null);

        Map<String, Set<Object>> conditions6 = DataHelper.createHashMap();
        Set<Object> condition61 = DataHelper.createHashSet();
        condition61.add("val1");
        conditions6.put("cond1", condition61);
        Set<Object> condition62 = DataHelper.createHashSet();
        condition62.add("val2");
        conditions6.put("cond2", condition62);
        Set<Object> condition63 = DataHelper.createHashSet();
        condition63.add("val3");
        conditions6.put("cond3", condition63);
        Set<Object> condition64 = DataHelper.createHashSet();
        condition64.add("val4");
        conditions6.put("cond4", condition64);
        ValueSet<String> valueSet6 = new ValueSet<>(null, null, null, conditions6, null);

        Map<String, Set<Object>> conditions7 = DataHelper.createHashMap();
        Set<Object> condition71 = DataHelper.createHashSet();
        condition71.add("val8");
        conditions7.put("cond1", condition71);
        Set<Object> condition72 = DataHelper.createHashSet();
        condition72.add("val8");
        conditions7.put("cond2", condition72);
        Set<Object> condition73 = DataHelper.createHashSet();
        condition73.add("val8");
        conditions7.put("cond3", condition73);
        Set<Object> condition74 = DataHelper.createHashSet();
        condition74.add("val8");
        conditions7.put("cond4", condition74);
        ValueSet<String> valueSet7 = new ValueSet<>(null, null, null, conditions7, null);

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
    public void getValueSetUniqueConditionsTest() {
        Map<String, Set<Object>> conditions01 = null;
        ValueSet<String> valueSet01 = new ValueSet<>(null, null, null, conditions01, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions01 = valueSet01.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions01).hasSize(0);

        Map<String, Set<Object>> conditions02 = DataHelper.createHashMap();
        ValueSet<String> valueSet02 = new ValueSet<>(null, null, null, conditions02, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions02 = valueSet02.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions02).hasSize(0);

        Map<String, Set<Object>> conditions03 = DataHelper.createHashMap();
        Set<Object> condition03 = DataHelper.createHashSet();
        condition03.add("val");
        conditions03.put(null, condition03);
        ValueSet<String> valueSet03 = new ValueSet<>(null, null, null, conditions03, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions03 = valueSet03.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions03).hasSize(0);

        Map<String, Set<Object>> conditions04 = DataHelper.createHashMap();
        Set<Object> condition04 = null;
        conditions04.put("cond", condition04);
        ValueSet<String> valueSet04 = new ValueSet<>(null, null, null, conditions04, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions04 = valueSet04.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions04).hasSize(0);

        Map<String, Set<Object>> conditions05 = DataHelper.createHashMap();
        Set<Object> condition05 = DataHelper.createHashSet();
        conditions05.put("cond", condition05);
        ValueSet<String> valueSet05 = new ValueSet<>(null, null, null, conditions05, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions05 = valueSet05.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions05).hasSize(0);

        Map<String, Set<Object>> conditions06 = DataHelper.createHashMap();
        Set<Object> condition06 = DataHelper.createHashSet();
        condition06.add(null);
        conditions06.put("cond", condition06);
        ValueSet<String> valueSet06 = new ValueSet<>(null, null, null, conditions06, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions06 = valueSet06.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions06).hasSize(0);

        Map<String, Set<Object>> conditions07 = DataHelper.createHashMap();
        Set<Object> condition07 = DataHelper.createHashSet();
        condition07.add("val");
        conditions07.put("cond", condition07);
        ValueSet<String> valueSet07 = new ValueSet<>(null, null, null, conditions07, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions07 = valueSet07.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions07).hasSize(1);

        Map<String, Set<Object>> conditions08 = DataHelper.createHashMap();
        Set<Object> condition081 = DataHelper.createHashSet();
        condition081.add("val1");
        conditions08.put("cond1", condition081);
        Set<Object> condition082 = DataHelper.createHashSet();
        condition082.add("val2");
        conditions08.put("cond2", condition082);
        ValueSet<String> valueSet08 = new ValueSet<>(null, null, null, conditions08, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions08 = valueSet08.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions08).hasSize(1);

        Map<String, Set<Object>> conditions09 = DataHelper.createHashMap();
        Set<Object> condition091 = DataHelper.createHashSet();
        condition091.add(null);
        condition091.add("val1");
        conditions09.put("cond1", condition091);
        Set<Object> condition092 = DataHelper.createHashSet();
        condition092.add(null);
        condition092.add("val2");
        conditions09.put("cond2", condition092);
        Set<Object> condition093 = null;
        conditions09.put("cond3", condition093);
        ValueSet<String> valueSet09 = new ValueSet<>(null, null, null, conditions09, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions09 = valueSet09.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions09).hasSize(1);

        Map<String, Set<Object>> conditions10 = DataHelper.createHashMap();
        Set<Object> condition101 = DataHelper.createHashSet();
        condition101.add("val11");
        condition101.add("val12");
        conditions10.put("cond1", condition101);
        Set<Object> condition102 = DataHelper.createHashSet();
        condition102.add("val21");
        condition102.add("val22");
        conditions10.put("cond2", condition102);
        ValueSet<String> valueSet10 = new ValueSet<>(null, null, null, conditions10, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions10 = valueSet10.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions10).hasSize(4);

        Map<String, Set<Object>> conditions11 = DataHelper.createHashMap();
        Set<Object> condition111 = DataHelper.createHashSet();
        condition111.add("val1");
        condition111.add("val2");
        conditions11.put("cond1", condition111);
        Set<Object> condition112 = DataHelper.createHashSet();
        condition112.add("val1");
        condition112.add("val2");
        conditions11.put("cond2", condition112);
        Set<Object> condition113 = DataHelper.createHashSet();
        condition113.add("val1");
        condition113.add("val2");
        condition113.add("val3");
        conditions11.put("cond3", condition113);
        ValueSet<String> valueSet11 = new ValueSet<>(null, null, null, conditions11, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions11 = valueSet11.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions11).hasSize(12);
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValueSetUniqueConditionsUnmodifiableFailTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        Set<Object> condition1 = DataHelper.createHashSet();
        condition1.add("val1");
        condition1.add("val2");
        conditions.put("cond1", condition1);
        Set<Object> condition2 = DataHelper.createHashSet();
        condition2.add("val1");
        condition2.add("val2");
        condition2.add("val3");
        conditions.put("cond2", condition2);
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions).hasSize(6);

        valueSetUniqueConditions.add(new ValueSetUniqueCondition(null));
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValueSetUniqueConditionsUnmodifiableEmptyFailTest() {
        Map<String, Set<Object>> conditions = DataHelper.createHashMap();
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, conditions, null);
        List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
        Assertions.assertThat(valueSetUniqueConditions).hasSize(0);

        valueSetUniqueConditions.add(new ValueSetUniqueCondition(null));
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void getValuesTest() {
        List<String> values1 = null;
        ValueSet<String> valueSet1 = new ValueSet<>(null, null, null, null, values1);
        Assertions.assertThat(valueSet1.getValues()).containsExactly();

        List<String> values2 = DataHelper.createArrayList();
        ValueSet<String> valueSet2 = new ValueSet<>(null, null, null, null, values2);
        Assertions.assertThat(valueSet2.getValues()).containsExactly();

        List<String> values3 = DataHelper.createArrayList();
        values3.add(null);
        ValueSet<String> valueSet3 = new ValueSet<>(null, null, null, null, values3);
        Assertions.assertThat(valueSet3.getValues()).containsExactly();

        List<String> values4 = DataHelper.createArrayList();
        values4.add("val");
        ValueSet<String> valueSet4 = new ValueSet<>(null, null, null, null, values4);
        Assertions.assertThat(valueSet4.getValues()).containsExactly("val");

        List<String> values5 = DataHelper.createArrayList();
        values5.add(null);
        values5.add("val");
        ValueSet<String> valueSet5 = new ValueSet<>(null, null, null, null, values5);
        Assertions.assertThat(valueSet5.getValues()).containsExactly("val");

        List<String> values6 = DataHelper.createArrayList();
        values6.add("val1");
        values6.add("val2");
        values6.add("val3");
        ValueSet<String> valueSet6 = new ValueSet<>(null, null, null, null, values6);
        Assertions.assertThat(valueSet6.getValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableFailTest() {
        List<String> values = DataHelper.createArrayList();
        values.add("val1");
        values.add("val2");
        values.add("val3");
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, null, values);
        Assertions.assertThat(valueSet.getValues()).hasSize(3);

        valueSet.getValues().add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void getValuesUnmodifiableEmptyFailTest() {
        List<String> values = DataHelper.createArrayList();
        ValueSet<String> valueSet = new ValueSet<>(null, null, null, null, values);
        Assertions.assertThat(valueSet.getValues()).hasSize(0);

        valueSet.getValues().add("value");
    }

    /**
     * {@link ValueSet} class test.
     */
    @Test
    public void toStringTest() {
        Map<String, Set<Object>> conditions11 = null;
        List<String> values11 = DataHelper.createArrayList();
        ValueSet<String> valueSet11 = new ValueSet<>(null, null, null, conditions11, values11);
        Assertions.assertThat(valueSet11).hasToString("{}");

        Map<String, Set<Object>> conditions12 = null;
        List<String> values12 = DataHelper.createArrayList();
        ValueSet<String> valueSet12 = new ValueSet<>("", null, null, conditions12, values12);
        Assertions.assertThat(valueSet12).hasToString("={}");

        Map<String, Set<Object>> conditions13 = null;
        List<String> values13 = DataHelper.createArrayList();
        ValueSet<String> valueSet13 = new ValueSet<>("id", null, null, conditions13, values13);
        Assertions.assertThat(valueSet13).hasToString("id={}");

        Map<String, Set<Object>> conditions21 = DataHelper.createHashMap();
        List<String> values21 = DataHelper.createArrayList();
        ValueSet<String> valueSet21 = new ValueSet<>(null, null, null, conditions21, values21);
        Assertions.assertThat(valueSet21).hasToString("{}");

        Map<String, Set<Object>> conditions22 = DataHelper.createHashMap();
        List<String> values22 = DataHelper.createArrayList();
        ValueSet<String> valueSet22 = new ValueSet<>("", null, null, conditions22, values22);
        Assertions.assertThat(valueSet22).hasToString("={}");

        Map<String, Set<Object>> conditions23 = DataHelper.createHashMap();
        List<String> values23 = DataHelper.createArrayList();
        ValueSet<String> valueSet23 = new ValueSet<>("id", null, null, conditions23, values23);
        Assertions.assertThat(valueSet23).hasToString("id={}");

        Map<String, Set<Object>> conditions31 = DataHelper.createHashMap();
        Set<Object> condition31 = DataHelper.createHashSet((Object) "val1");
        conditions31.put("cond1", condition31);
        List<String> values31 = DataHelper.createArrayList("val1");
        ValueSet<String> valueSet31 = new ValueSet<>(null, null, null, conditions31, values31);
        Assertions.assertThat(valueSet31).hasToString("{cond1=[val1]}");

        Map<String, Set<Object>> conditions32 = DataHelper.createHashMap();
        Set<Object> condition32 = DataHelper.createHashSet((Object) "val1");
        conditions32.put("cond1", condition32);
        List<String> values32 = DataHelper.createArrayList("val1");
        ValueSet<String> valueSet32 = new ValueSet<>("", null, null, conditions32, values32);
        Assertions.assertThat(valueSet32).hasToString("={cond1=[val1]}");

        Map<String, Set<Object>> conditions33 = DataHelper.createHashMap();
        Set<Object> condition33 = DataHelper.createHashSet((Object) "val1");
        conditions33.put("cond1", condition33);
        List<String> values33 = DataHelper.createArrayList("val1");
        ValueSet<String> valueSet33 = new ValueSet<>("id", null, null, conditions33, values33);
        Assertions.assertThat(valueSet33).hasToString("id={cond1=[val1]}");

        Map<String, Set<Object>> conditions41 = DataHelper.createHashMap();
        Set<Object> condition41 = DataHelper.createHashSet((Object) null, "val1");
        conditions41.put("cond1", condition41);
        List<String> values41 = DataHelper.createArrayList("val1");
        ValueSet<String> valueSet41 = new ValueSet<>(null, null, null, conditions41, values41);
        Assertions.assertThat(valueSet41).hasToString("{cond1=[val1]}");

        Map<String, Set<Object>> conditions42 = DataHelper.createHashMap();
        Set<Object> condition42 = DataHelper.createHashSet((Object) null, "val1");
        conditions42.put("cond1", condition42);
        List<String> values42 = DataHelper.createArrayList("val1");
        ValueSet<String> valueSet42 = new ValueSet<>("", null, null, conditions42, values42);
        Assertions.assertThat(valueSet42).hasToString("={cond1=[val1]}");

        Map<String, Set<Object>> conditions43 = DataHelper.createHashMap();
        Set<Object> condition43 = DataHelper.createHashSet((Object) null, "val1");
        conditions43.put("cond1", condition43);
        List<String> values43 = DataHelper.createArrayList("val1");
        ValueSet<String> valueSet43 = new ValueSet<>("id", null, null, conditions43, values43);
        Assertions.assertThat(valueSet43).hasToString("id={cond1=[val1]}");

        Map<String, Set<Object>> conditions51 = DataHelper.createHashMap();
        Set<Object> condition511 = DataHelper.createHashSet((Object) "val11", "val12");
        conditions51.put("cond1", condition511);
        Set<Object> condition512 = DataHelper.createHashSet((Object) "val2");
        conditions51.put("cond2", condition512);
        List<String> values51 = DataHelper.createArrayList("val1", "val2", "val3");
        ValueSet<String> valueSet51 = new ValueSet<>(null, null, null, conditions51, values51);
        Assertions.assertThat(valueSet51).toToString().startsWith("{");
        Assertions.assertThat(valueSet51).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet51).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet51).toToString().doesNotContain("val3");

        Map<String, Set<Object>> conditions52 = DataHelper.createHashMap();
        Set<Object> condition521 = DataHelper.createHashSet((Object) "val11", "val12");
        conditions52.put("cond1", condition521);
        Set<Object> condition522 = DataHelper.createHashSet((Object) "val2");
        conditions52.put("cond2", condition522);
        List<String> values52 = DataHelper.createArrayList("val1", "val2", "val3");
        ValueSet<String> valueSet52 = new ValueSet<>("", null, null, conditions52, values52);
        Assertions.assertThat(valueSet52).toToString().startsWith("={");
        Assertions.assertThat(valueSet52).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet52).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet52).toToString().doesNotContain("val3");

        Map<String, Set<Object>> conditions53 = DataHelper.createHashMap();
        Set<Object> condition531 = DataHelper.createHashSet((Object) "val11", "val12");
        conditions53.put("cond1", condition531);
        Set<Object> condition532 = DataHelper.createHashSet((Object) "val2");
        conditions53.put("cond2", condition532);
        List<String> values53 = DataHelper.createArrayList("val1", "val2", "val3");
        ValueSet<String> valueSet53 = new ValueSet<>("id", null, null, conditions53, values53);
        Assertions.assertThat(valueSet53).toToString().startsWith("id={");
        Assertions.assertThat(valueSet53).toStringContains("cond1=[val1");
        Assertions.assertThat(valueSet53).toStringContains("cond2=[val2]");
        Assertions.assertThat(valueSet53).toToString().doesNotContain("val3");
    }

}
