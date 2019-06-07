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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
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
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setEqualsIgnoreCasePredicateTest() {
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setComparatorTest() {
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setNaturalOrderComparatorTest() {
        // TODO
    }

    /**
     * {@link ConditionalValuesBuilder} class test.
     */
    @Test
    public void setReverseOrderComparatorTest() {
        // TODO
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

}
