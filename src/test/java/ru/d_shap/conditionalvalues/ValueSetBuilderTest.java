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
import ru.d_shap.assertions.Raw;
import ru.d_shap.assertions.util.DataHelper;
import ru.d_shap.conditionalvalues.predicate.AnyValueMatchesTuplePredicate;
import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringContainsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringEqualsIgnoreCasePredicate;

/**
 * Tests for {@link ValueSetBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetBuilderTest {

    /**
     * Test class constructor.
     */
    public ValueSetBuilderTest() {
        super();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void newInstanceTest() {
        ValueSetBuilder<String> valueSetBuilder1 = ValueSetBuilder.newInstance();
        Assertions.assertThat(valueSetBuilder1).isNotNull();

        ValueSetBuilder<String> valueSetBuilder2 = ValueSetBuilder.newInstance();
        Assertions.assertThat(valueSetBuilder2).isNotNull();

        Assertions.assertThat(valueSetBuilder1).isNotSameAs(valueSetBuilder2);
        Assertions.assertThat(valueSetBuilder2).isNotSameAs(valueSetBuilder1);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setIdTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        Assertions.assertThat(valueSetBuilder.setId(null).build().getId()).isNull();
        Assertions.assertThat(valueSetBuilder.setId("").build().getId()).isEqualTo("");
        Assertions.assertThat(valueSetBuilder.setId(" ").build().getId()).isEqualTo(" ");
        Assertions.assertThat(valueSetBuilder.setId("id").build().getId()).isEqualTo("id");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setPredicateDefaultTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "vaL1");
        valueSetBuilder.addCondition("cond2", "vaL2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();

        Map<String, Object> conditions11 = DataHelper.createHashMap();
        conditions11.put("cond1", "vaL1");
        conditions11.put("cond2", "vaL2");
        ConditionSet conditionSet11 = new ConditionSet(conditions11);
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet11, new AnyValueMatchesTuplePredicate(), null, null)).isFalse();
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet11, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions12 = DataHelper.createHashMap();
        conditions12.put("cond1", "val1");
        conditions12.put("cond2", "val2");
        ConditionSet conditionSet12 = new ConditionSet(conditions12);
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet12, new AnyValueMatchesTuplePredicate(), null, null)).isFalse();
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet12, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        valueSetBuilder.setPredicate(new EqualsPredicate());
        valueSetBuilder.addCondition("cond1", "vaL1");
        valueSetBuilder.addCondition("cond2", "vaL2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        Map<String, Object> conditions21 = DataHelper.createHashMap();
        conditions21.put("cond1", "vaL1");
        conditions21.put("cond2", "vaL2");
        ConditionSet conditionSet21 = new ConditionSet(conditions21);
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet21, new AnyValueMatchesTuplePredicate(), null, null)).isTrue();
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet21, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions22 = DataHelper.createHashMap();
        conditions22.put("cond1", "val1");
        conditions22.put("cond2", "val2");
        ConditionSet conditionSet22 = new ConditionSet(conditions22);
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet22, new AnyValueMatchesTuplePredicate(), null, null)).isFalse();
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet22, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setPredicateConditionTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.setPredicate("cond1", new StringContainsPredicate());
        valueSetBuilder.setPredicate("cond2", new StringEqualsIgnoreCasePredicate());
        valueSetBuilder.addCondition("cond1", "vaL1");
        valueSetBuilder.addCondition("cond2", "vaL2");
        ValueSet<String> valueSet1 = valueSetBuilder.build();

        Map<String, Object> conditions11 = DataHelper.createHashMap();
        conditions11.put("cond1", "vaL1");
        conditions11.put("cond2", "vaL2");
        ConditionSet conditionSet11 = new ConditionSet(conditions11);
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet11, new AnyValueMatchesTuplePredicate(), null, null)).isTrue();
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet11, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions12 = DataHelper.createHashMap();
        conditions12.put("cond1", "xxxvaL1xxxx");
        conditions12.put("cond2", "VAl2");
        ConditionSet conditionSet12 = new ConditionSet(conditions12);
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet12, new AnyValueMatchesTuplePredicate(), null, null)).isTrue();
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet12, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions13 = DataHelper.createHashMap();
        conditions13.put("cond1", "VAL1");
        conditions13.put("cond2", "_val2_");
        ConditionSet conditionSet13 = new ConditionSet(conditions13);
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet13, new AnyValueMatchesTuplePredicate(), null, null)).isFalse();
        Assertions.assertThat(valueSet1.isMatchConditions(conditionSet13, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();

        valueSetBuilder.setPredicate("cond1", new StringContainsPredicate());
        valueSetBuilder.setPredicate("cond2", new StringContainsPredicate());
        valueSetBuilder.setPredicate("cond2", null);
        valueSetBuilder.addCondition("cond1", "vaL1");
        valueSetBuilder.addCondition("cond2", "vaL2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();

        Map<String, Object> conditions21 = DataHelper.createHashMap();
        conditions21.put("cond1", "_vaL1_");
        conditions21.put("cond2", "vaL2");
        ConditionSet conditionSet21 = new ConditionSet(conditions21);
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet21, new AnyValueMatchesTuplePredicate(), null, null)).isFalse();
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet21, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isTrue();

        Map<String, Object> conditions22 = DataHelper.createHashMap();
        conditions22.put("cond1", "_vaL1_");
        conditions22.put("cond2", "_vaL2_");
        ConditionSet conditionSet22 = new ConditionSet(conditions22);
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet22, new AnyValueMatchesTuplePredicate(), null, null)).isFalse();
        Assertions.assertThat(valueSet2.isMatchConditions(conditionSet22, new AnyValueMatchesTuplePredicate(), null, new EqualsPredicate())).isFalse();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setEqualsPredicateDefaultTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setEqualsPredicateConditionTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setStringEqualsIgnoreCasePredicateDefaultTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setStringEqualsIgnoreCasePredicateConditionTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setStringContainsPredicateDefaultTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setStringContainsPredicateConditionTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setStringContainsIgnoreCasePredicateDefaultTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void setStringContainsIgnoreCasePredicateConditionTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removePredicateTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearPredicatesTest() {
        // TODO
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder.addCondition(null, "val");
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();

        valueSetBuilder.addCondition("cond", null);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly();

        valueSetBuilder.addCondition(null, null);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionBooleanTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", true);
        valueSetBuilder.addCondition("cond1", false);
        valueSetBuilder.addCondition("cond2", false).addCondition("cond2", true);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(true, false);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(true, false);

        valueSetBuilder.addCondition(null, true);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionCharTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", '1');
        valueSetBuilder.addCondition("cond1", '2');
        valueSetBuilder.addCondition("cond2", '3').addCondition("cond2", '4');
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly('1', '2');
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly('3', '4');

        valueSetBuilder.addCondition(null, '1');
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionIntTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1);
        valueSetBuilder.addCondition("cond1", 2);
        valueSetBuilder.addCondition("cond2", 3).addCondition("cond2", 4);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1, 2);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3, 4);

        valueSetBuilder.addCondition(null, 0);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionLongTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1L);
        valueSetBuilder.addCondition("cond1", 2L);
        valueSetBuilder.addCondition("cond2", 3L).addCondition("cond2", 4L);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1L, 2L);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3L, 4L);

        valueSetBuilder.addCondition(null, 0L);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionFloatTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1.0f);
        valueSetBuilder.addCondition("cond1", 2.0f);
        valueSetBuilder.addCondition("cond2", 3.0f).addCondition("cond2", 4.0f);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1.0f, 2.0f);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3.0f, 4.0f);

        valueSetBuilder.addCondition(null, 0.0f);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionDoubleTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1.0);
        valueSetBuilder.addCondition("cond1", 2.0);
        valueSetBuilder.addCondition("cond2", 3.0).addCondition("cond2", 4.0);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1.0, 2.0);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3.0, 4.0);

        valueSetBuilder.addCondition(null, 0.0);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionObjectTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        StringBuilder val = new StringBuilder().append("val");
        StringBuilder val1 = new StringBuilder().append("val1");
        StringBuilder val2 = new StringBuilder().append("val2");
        StringBuilder val3 = new StringBuilder().append("val3");
        StringBuilder val4 = new StringBuilder().append("val4");

        valueSetBuilder.addCondition("cond1", val1);
        valueSetBuilder.addCondition("cond1", val2);
        valueSetBuilder.addCondition("cond2", val3).addCondition("cond2", val4);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(val1, val2);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(val3, val4);

        valueSetBuilder.addCondition(null, val);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();

        valueSetBuilder.addCondition("cond", (Object) null);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly();

        valueSetBuilder.addCondition(null, (Object) null);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(0);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond2", "val4").addCondition("cond2", "val5");
        valueSetBuilder.addCondition("cond3", "val6");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val3", "val4", "val5");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly("val6");

        valueSetBuilder.addCondition("cond2", "val4").addCondition("cond2", "val5");
        valueSetBuilder.addCondition("cond3", "val6");
        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val3", "val4", "val5");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).containsExactly("val6");

        valueSetBuilder = valueSetBuilder.addConditions(null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly();

        valueSetBuilder = valueSetBuilder.addConditions(null);
        valueSetBuilder.addCondition("cond2", "val4").addCondition("cond2", "val5");
        valueSetBuilder.addCondition("cond3", "val6");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        Assertions.assertThat(valueSet5.getAllConditionNames()).containsExactly("cond2", "cond3");
        Assertions.assertThat(valueSet5.getAllConditionValues("cond2")).containsExactly("val4", "val5");
        Assertions.assertThat(valueSet5.getAllConditionValues("cond3")).containsExactly("val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionStringTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", "val1").addCondition("cond3", "val2");
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", "val3").removeCondition("cond1", "val4");
        valueSetBuilder.removeCondition("cond2", "val1").removeCondition("cond2", "val2");
        valueSetBuilder.removeCondition("cond3", "val1").removeCondition("cond3", "val2");
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, "val");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition("cond", null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionBooleanTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", true).addCondition("cond1", true).addCondition("cond1", false).addCondition("cond1", false);
        valueSetBuilder.addCondition("cond2", true).addCondition("cond2", true).addCondition("cond2", false).addCondition("cond2", false);
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", true).addCondition("cond3", false);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", true).removeCondition("cond1", true);
        valueSetBuilder.removeCondition("cond2", false).removeCondition("cond2", false);
        valueSetBuilder.removeCondition("cond3", true).removeCondition("cond3", false);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(false);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(true);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, true);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly(true, false);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly(true, false);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionCharTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", '1').addCondition("cond1", '2').addCondition("cond1", '3').addCondition("cond1", '4');
        valueSetBuilder.addCondition("cond2", '1').addCondition("cond2", '2').addCondition("cond2", '3').addCondition("cond2", '4');
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", '1').addCondition("cond3", '2');
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", '3').removeCondition("cond1", '4');
        valueSetBuilder.removeCondition("cond2", '1').removeCondition("cond2", '2');
        valueSetBuilder.removeCondition("cond3", '1').removeCondition("cond3", '2');
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly('1', '2');
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly('3', '4');

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, '1');
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly('1', '2', '3', '4');
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly('1', '2', '3', '4');
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionIntTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1).addCondition("cond1", 2).addCondition("cond1", 3).addCondition("cond1", 4);
        valueSetBuilder.addCondition("cond2", 1).addCondition("cond2", 2).addCondition("cond2", 3).addCondition("cond2", 4);
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", 1).addCondition("cond3", 2);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", 3).removeCondition("cond1", 4);
        valueSetBuilder.removeCondition("cond2", 1).removeCondition("cond2", 2);
        valueSetBuilder.removeCondition("cond3", 1).removeCondition("cond3", 2);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1, 2);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3, 4);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, 0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly(1, 2, 3, 4);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly(1, 2, 3, 4);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionLongTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1L).addCondition("cond1", 2L).addCondition("cond1", 3L).addCondition("cond1", 4L);
        valueSetBuilder.addCondition("cond2", 1L).addCondition("cond2", 2L).addCondition("cond2", 3L).addCondition("cond2", 4L);
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", 1L).addCondition("cond3", 2L);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", 3L).removeCondition("cond1", 4L);
        valueSetBuilder.removeCondition("cond2", 1L).removeCondition("cond2", 2L);
        valueSetBuilder.removeCondition("cond3", 1L).removeCondition("cond3", 2L);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1L, 2L);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3L, 4L);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, 0L);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly(1L, 2L, 3L, 4L);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly(1L, 2L, 3L, 4L);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionFloatTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1.0f).addCondition("cond1", 2.0f).addCondition("cond1", 3.0f).addCondition("cond1", 4.0f);
        valueSetBuilder.addCondition("cond2", 1.0f).addCondition("cond2", 2.0f).addCondition("cond2", 3.0f).addCondition("cond2", 4.0f);
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", 1.0f).addCondition("cond3", 2.0f);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", 3.0f).removeCondition("cond1", 4.0f);
        valueSetBuilder.removeCondition("cond2", 1.0f).removeCondition("cond2", 2.0f);
        valueSetBuilder.removeCondition("cond3", 1.0f).removeCondition("cond3", 2.0f);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1.0f, 2.0f);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3.0f, 4.0f);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, 0.0f);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly(1.0f, 2.0f, 3.0f, 4.0f);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly(1.0f, 2.0f, 3.0f, 4.0f);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionDoubleTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", 1.0).addCondition("cond1", 2.0).addCondition("cond1", 3.0).addCondition("cond1", 4.0);
        valueSetBuilder.addCondition("cond2", 1.0).addCondition("cond2", 2.0).addCondition("cond2", 3.0).addCondition("cond2", 4.0);
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", 1.0).addCondition("cond3", 2.0);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", 3.0).removeCondition("cond1", 4.0);
        valueSetBuilder.removeCondition("cond2", 1.0).removeCondition("cond2", 2.0);
        valueSetBuilder.removeCondition("cond3", 1.0).removeCondition("cond3", 2.0);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(1.0, 2.0);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(3.0, 4.0);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, 0.0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly(1.0, 2.0, 3.0, 4.0);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly(1.0, 2.0, 3.0, 4.0);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionObjectTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        StringBuilder val = new StringBuilder().append("val");
        StringBuilder val1 = new StringBuilder().append("val1");
        StringBuilder val2 = new StringBuilder().append("val2");
        StringBuilder val3 = new StringBuilder().append("val3");
        StringBuilder val4 = new StringBuilder().append("val4");

        valueSetBuilder.addCondition("cond1", val1).addCondition("cond1", val2).addCondition("cond1", val3).addCondition("cond1", val4);
        valueSetBuilder.addCondition("cond2", val1).addCondition("cond2", val2).addCondition("cond2", val3).addCondition("cond2", val4);
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.addCondition("cond3", val1).addCondition("cond3", val2);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(3);
        valueSetBuilder.removeCondition("cond1", val3).removeCondition("cond1", val4);
        valueSetBuilder.removeCondition("cond2", val1).removeCondition("cond2", val2);
        valueSetBuilder.removeCondition("cond3", val1).removeCondition("cond3", val2);
        Assertions.assertThat(valueSetBuilder, "_conditions", Raw.mapAssertion()).hasSize(2);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly(val1, val2);
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly(val3, val4);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, val);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly(val1, val2, val3, val4);
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly(val1, val2, val3, val4);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition("cond", (Object) null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly(val1, val2, val3, val4);
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly(val1, val2, val3, val4);

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder.removeCondition(null, (Object) null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly(val1, val2, val3, val4);
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly(val1, val2, val3, val4);
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions("cond1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions("cond1");
        valueSetBuilder = valueSetBuilder.removeConditions("cond2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditions(null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionNamesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        valueSetBuilder.addCondition("cond3", "val1").addCondition("cond3", "val2").addCondition("cond3", "val3").addCondition("cond3", "val4");
        valueSetBuilder.addCondition("cond4", "val1").addCondition("cond4", "val2").addCondition("cond4", "val3").addCondition("cond4", "val4");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder.addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2");
        ValueSet<String> removeTemplate1 = valueSetBuilder.build();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond3", "val5");
        valueSetBuilder.addCondition("cond4", "val7");
        valueSetBuilder.addCondition("cond5", "val1");
        ValueSet<String> removeTemplate2 = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionNames(removeTemplate1);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond3", "cond4");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond4")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionNames(removeTemplate2);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionNames(addTemplate);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionNames(null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3", "cond4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond3")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond4")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeConditionValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2").addCondition("cond2", "val3").addCondition("cond2", "val4");
        valueSetBuilder.addCondition("cond3", "val1").addCondition("cond3", "val2").addCondition("cond3", "val3").addCondition("cond3", "val4");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder.addCondition("cond1", "val3").addCondition("cond1", "val4").addCondition("cond1", "val7");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond4", "val2");
        ValueSet<String> removeTemplate1 = valueSetBuilder.build();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2").addCondition("cond1", "val3").addCondition("cond1", "val4");
        valueSetBuilder.addCondition("cond2", "val1").addCondition("cond2", "val2");
        valueSetBuilder.addCondition("cond3", "val3");
        valueSetBuilder.addCondition("cond4", "val4");
        ValueSet<String> removeTemplate2 = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionValues(removeTemplate1);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1", "val2");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly("val3", "val4");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionValues(removeTemplate2);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond2", "cond3");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val3", "val4");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly("val1", "val2", "val4");

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionValues(addTemplate);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly();

        valueSetBuilder = valueSetBuilder.addConditions(addTemplate);
        valueSetBuilder = valueSetBuilder.removeConditionValues(null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2", "cond3");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val1", "val2", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond3")).containsExactly("val1", "val2", "val3", "val4");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearConditionsTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        ValueSet<String> valueSet = valueSetBuilder.clearConditions().build();
        Assertions.assertThat(valueSet.getAllConditionNames()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValueTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1", "val2", "val3");

        valueSetBuilder.addValue(null);
        Assertions.assertThat(valueSetBuilder, "_values", Raw.listAssertion()).hasSize(0);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getValues()).containsExactly();

        valueSetBuilder.addValue("val1");
        Assertions.assertThat(valueSetBuilder, "_values", Raw.listAssertion()).hasSize(1);
        valueSetBuilder.addValue(null);
        Assertions.assertThat(valueSetBuilder, "_values", Raw.listAssertion()).hasSize(1);
        valueSetBuilder.addValue("val2").addValue("val3");
        Assertions.assertThat(valueSetBuilder, "_values", Raw.listAssertion()).hasSize(3);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val2", "val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValuesArrayTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValues("val1", "val2");
        valueSetBuilder.addValues("val3", "val4").addValues("val5", "val6");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder.addValues((String[]) null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getValues()).containsExactly();

        valueSetBuilder.addValues("val1", "val2");
        valueSetBuilder.addValues((String[]) null);
        valueSetBuilder.addValues("val3", "val4").addValues("val5", "val6");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder.addValues("val1", null);
        valueSetBuilder.addValues(null, "val2").addValues(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getValues()).containsExactly("val1", "val2");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void addValuesValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValues("val1", "val2");
        valueSetBuilder.addValues("val3", "val4").addValues("val5", "val6");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.addValues("val3", "val5", "val7");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6", "val7");

        valueSetBuilder.addValues("val3", "val5", "val7");
        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6", "val7");

        valueSetBuilder = valueSetBuilder.addValues((ValueSet<String>) null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getValues()).containsExactly();

        valueSetBuilder = valueSetBuilder.addValues((ValueSet<String>) null);
        valueSetBuilder.addValues("val3", "val5", "val7");
        ValueSet<String> valueSet5 = valueSetBuilder.build();
        Assertions.assertThat(valueSet5.getValues()).containsExactly("val3", "val5", "val7");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValueTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValues("val1", "val2", "val3", "val4", "val5", "val6");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValue("val2");
        valueSetBuilder.removeValue("val4").removeValue("val6");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1", "val3", "val5");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValue(null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValue("val2");
        valueSetBuilder.removeValue(null);
        valueSetBuilder.removeValue("val4").removeValue("val6");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val3", "val5");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValuesArrayTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValues("val1", "val2", "val3", "val4", "val5", "val6");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValues("val1", "val2");
        valueSetBuilder.removeValues("val2", "val3").removeValues("val3", "val4");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValue(null);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValues("val1", "val2");
        valueSetBuilder.removeValues((String[]) null);
        valueSetBuilder.removeValues("val2", "val3").removeValues("val3", "val4");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val5", "val6");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder.removeValues("val1", null);
        valueSetBuilder.removeValues(null, "val2").removeValues(null, null);
        ValueSet<String> valueSet4 = valueSetBuilder.build();
        Assertions.assertThat(valueSet4.getValues()).containsExactly("val3", "val4", "val5", "val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void removeValuesValueSetTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValues("val1", "val2", "val3", "val4", "val5", "val6");
        ValueSet<String> addTemplate = valueSetBuilder.build();

        valueSetBuilder.addValues("val4", "val5", "val6");
        ValueSet<String> removeTemplate = valueSetBuilder.build();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder = valueSetBuilder.removeValues(removeTemplate);
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1", "val2", "val3");

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder = valueSetBuilder.removeValues(addTemplate);
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getValues()).containsExactly();

        valueSetBuilder = valueSetBuilder.addValues(addTemplate);
        valueSetBuilder = valueSetBuilder.removeValues((ValueSet<String>) null);
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val2", "val3", "val4", "val5", "val6");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearValuesTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet = valueSetBuilder.clearValues().build();
        Assertions.assertThat(valueSet.getValues()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void clearTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.addCondition("cond1", "val1").addCondition("cond1", "val2");
        valueSetBuilder.addCondition("cond2", "val3").addCondition("cond2", "val4");
        valueSetBuilder.addValue("val1");
        valueSetBuilder.addValue("val2").addValue("val3");
        ValueSet<String> valueSet = valueSetBuilder.clear().build();
        Assertions.assertThat(valueSet.getAllConditionNames()).containsExactly();
        Assertions.assertThat(valueSet.getValues()).containsExactly();
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.setId("id1");
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build();
        Assertions.assertThat(valueSet1.getId()).isEqualTo("id1");
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1");

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build();
        Assertions.assertThat(valueSet2.getId()).isNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly();
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet2.getValues()).containsExactly("val2");

        valueSetBuilder.setId("id3");
        valueSetBuilder.addCondition("cond1", "val3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build();
        Assertions.assertThat(valueSet3.getId()).isEqualTo("id3");
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val3");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val3");
    }

    /**
     * {@link ValueSetBuilder} class test.
     */
    @Test
    public void buildClearTest() {
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

        valueSetBuilder.setId("id1");
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("val1");
        ValueSet<String> valueSet1 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet1.getId()).isEqualTo("id1");
        Assertions.assertThat(valueSet1.getAllConditionNames()).containsExactly("cond1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet1.getAllConditionValues("cond2")).containsExactly();
        Assertions.assertThat(valueSet1.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet1.getValues()).containsExactly("val1");

        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("val2");
        ValueSet<String> valueSet2 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet2.getId()).isNull();
        Assertions.assertThat(valueSet2.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond1")).containsExactly("val1");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet2.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet2.getValues()).containsExactly("val1", "val2");

        valueSetBuilder.setId("id2");
        valueSetBuilder.addCondition("cond1", "val3");
        valueSetBuilder.addValue("val3");
        ValueSet<String> valueSet3 = valueSetBuilder.build(false);
        Assertions.assertThat(valueSet3.getId()).isEqualTo("id2");
        Assertions.assertThat(valueSet3.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond1")).containsExactly("val1", "val3");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet3.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet3.getValues()).containsExactly("val1", "val2", "val3");

        valueSetBuilder.setId("id3");
        valueSetBuilder.addCondition("cond1", "val4");
        valueSetBuilder.addValue("val4");
        ValueSet<String> valueSet4 = valueSetBuilder.build(true);
        Assertions.assertThat(valueSet4.getId()).isEqualTo("id3");
        Assertions.assertThat(valueSet4.getAllConditionNames()).containsExactly("cond1", "cond2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond1")).containsExactly("val1", "val3", "val4");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond2")).containsExactly("val2");
        Assertions.assertThat(valueSet4.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet4.getValues()).containsExactly("val1", "val2", "val3", "val4");

        valueSetBuilder.addCondition("cond2", "val5");
        valueSetBuilder.addValue("val5");
        ValueSet<String> valueSet5 = valueSetBuilder.build(true);
        Assertions.assertThat(valueSet5.getId()).isNull();
        Assertions.assertThat(valueSet5.getAllConditionNames()).containsExactly("cond2");
        Assertions.assertThat(valueSet5.getAllConditionValues("cond1")).containsExactly();
        Assertions.assertThat(valueSet5.getAllConditionValues("cond2")).containsExactly("val5");
        Assertions.assertThat(valueSet5.getAllConditionValues("cond3")).containsExactly();
        Assertions.assertThat(valueSet5.getValues()).containsExactly("val5");
    }

}
