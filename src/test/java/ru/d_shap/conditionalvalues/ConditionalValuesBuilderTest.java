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

import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.misc.ComparableComparator;
import ru.d_shap.conditionalvalues.misc.EqualsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.misc.EqualsPredicate;

/**
 * Tests for {@link ConditionalValuesBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValuesBuilderTest {

    /**
     * Test class constructor.
     */
    public ConditionalValuesBuilderTest() {
        super();
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void newInstanceTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder1 = ConditionalValuesBuilder.newInstance();
        ConditionalValuesBuilder<String> conditionalValuesBuilder2 = ConditionalValuesBuilder.newInstance();

        Assertions.assertThat(conditionalValuesBuilder1).isNotNull();
        Assertions.assertThat(conditionalValuesBuilder2).isNotNull();
        Assertions.assertThat(conditionalValuesBuilder1).isNotSameAs(conditionalValuesBuilder2);
        Assertions.assertThat(conditionalValuesBuilder2).isNotSameAs(conditionalValuesBuilder1);
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setPredicateTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setPredicate(new EqualsPredicate());
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly();

        conditionalValuesBuilder.setPredicate(new EqualsIgnoreCasePredicate());
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly("value2");

        conditionalValuesBuilder.setPredicate(new PredicateImpl());
        valueSetBuilder.addCondition("a", "a");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("b", "c");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("a", "a").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("b", "c").build()).getValues()).containsExactly();
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setEqualsPredicateTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setEqualsPredicate();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly();
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setEqualsIgnoreCasePredicateTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setEqualsIgnoreCasePredicate();
        valueSetBuilder.addCondition("cond1", "val");
        valueSetBuilder.addValue("value1");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "vAl");
        valueSetBuilder.addValue("value2");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val").build()).getValues()).containsExactly("value1");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "val").build()).getValues()).containsExactly("value2");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "vAl").build()).getValues()).containsExactly("value2");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setComparatorTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setComparator(new ComparableComparator<String>());
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues1 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues1.getAllValues()).containsExactly("a", "bbb", "cc", "dddd");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("a", "bbb");
        Assertions.assertThat(conditionalValues1.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("cc", "dddd");

        conditionalValuesBuilder.setComparator(Collections.reverseOrder(new ComparableComparator<String>()));
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues2 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues2.getAllValues()).containsExactly("dddd", "cc", "bbb", "a");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("bbb", "a");
        Assertions.assertThat(conditionalValues2.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("dddd", "cc");

        conditionalValuesBuilder.setComparator(new ComparatorImpl());
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues3 = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues3.getAllValues()).containsExactly("a", "cc", "bbb", "dddd");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("a", "bbb");
        Assertions.assertThat(conditionalValues3.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("cc", "dddd");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setNaturalOrderComparatorTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setNaturalOrderComparator();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues.getAllValues()).containsExactly("a", "bbb", "cc", "dddd");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("a", "bbb");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("cc", "dddd");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setReverseOrderComparatorTest() {
        ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        conditionalValuesBuilder.setReverseOrderComparator();
        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue("a");
        valueSetBuilder.addValue("bbb");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("cond2", "val2");
        valueSetBuilder.addValue("cc");
        valueSetBuilder.addValue("dddd");
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();
        Assertions.assertThat(conditionalValues.getAllValues()).containsExactly("dddd", "cc", "bbb", "a");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val1").build()).getValues()).containsExactly("bbb", "a");
        Assertions.assertThat(conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "val2").build()).getValues()).containsExactly("dddd", "cc");
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void addValueSetTest() {
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void clearTest() {
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void buildTest() {
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void buildClearTest() {
        // TODO
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
        public boolean evaluate(final String conditionName, final String conditionValue, final String value) {
            return conditionName != null && conditionName.equals(conditionValue) && conditionName.equals(value);
        }

    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class ComparatorImpl implements Comparator<String> {

        ComparatorImpl() {
            super();
        }

        @Override
        public int compare(final String str1, final String str2) {
            if (str1 == null) {
                if (str2 == null) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                if (str2 == null) {
                    return 1;
                } else {
                    int lng1 = str1.length();
                    int lng2 = str2.length();
                    return lng1 - lng2;
                }
            }
        }

    }

}
