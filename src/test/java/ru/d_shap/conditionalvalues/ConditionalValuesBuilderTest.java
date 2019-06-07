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
        // TODO
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

}
