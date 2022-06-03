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
package ru.d_shap.conditionalvalues.predicate;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.data.IsGreaterThenPredicate;
import ru.d_shap.conditionalvalues.data.IsLessThenPredicate;
import ru.d_shap.conditionalvalues.data.Tuple;
import ru.d_shap.conditionalvalues.data.TupleValue1Extractor;
import ru.d_shap.conditionalvalues.data.TupleValue2Extractor;
import ru.d_shap.conditionalvalues.data.TupleValue3Extractor;

/**
 * Tests for {@link LogicalOrPredicate}.
 *
 * @author Dmitry Shapovalov
 */
public final class LogicalOrPredicateTest {

    /**
     * Test class constructor.
     */
    public LogicalOrPredicateTest() {
        super();
    }

    /**
     * {@link LogicalOrPredicate} class test.
     */
    @Test
    public void evaluateTest() {
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate(null, null, null)).isTrue();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate(null, "value", null)).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate(null, null, "value")).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate(null, "value1", "value2")).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate(null, "value", "value")).isTrue();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate(null, "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("", null, null)).isTrue();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("", "value", null)).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("", null, "value")).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("", "value1", "value2")).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("", "value", "value")).isTrue();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("", "vaLUe", "ValUE")).isFalse();

        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("condition", null, null)).isTrue();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("condition", "value", null)).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("condition", null, "value")).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("condition", "value1", "value2")).isFalse();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("condition", "value", "value")).isTrue();
        Assertions.assertThat(new LogicalOrPredicate(new EqualsPredicate()).evaluate("condition", "vaLUe", "ValUE")).isFalse();

        Predicate predicate1 = new LogicalOrPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsLessThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsGreaterThenPredicate()));
        Assertions.assertThat(predicate1.evaluate("c", 1, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate1.evaluate("c", 9, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate1.evaluate("c", 10, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 11, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 15, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 19, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 20, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate1.evaluate("c", 21, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate1.evaluate("c", 29, new Tuple(10, 20))).isTrue();

        Predicate predicate2 = new LogicalOrPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsLessThenPredicate()), new LogicalNotPredicate(new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate())));
        Assertions.assertThat(predicate2.evaluate("c", 1, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 9, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 10, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 11, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 15, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 19, new Tuple(10, 20))).isFalse();
        Assertions.assertThat(predicate2.evaluate("c", 20, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 21, new Tuple(10, 20))).isTrue();
        Assertions.assertThat(predicate2.evaluate("c", 29, new Tuple(10, 20))).isTrue();

        Predicate predicate31 = new LogicalOrPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsLessThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsGreaterThenPredicate()), new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()));
        Assertions.assertThat(predicate31.evaluate("c", 1, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate31.evaluate("c", 9, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate31.evaluate("c", 10, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 11, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 15, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate31.evaluate("c", 19, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 20, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate31.evaluate("c", 21, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate31.evaluate("c", 29, new Tuple(10, 20, 15))).isTrue();

        Predicate predicate32 = new LogicalOrPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsLessThenPredicate()), new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsGreaterThenPredicate()));
        Assertions.assertThat(predicate32.evaluate("c", 1, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate32.evaluate("c", 9, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate32.evaluate("c", 10, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 11, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 15, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate32.evaluate("c", 19, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 20, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate32.evaluate("c", 21, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate32.evaluate("c", 29, new Tuple(10, 20, 15))).isTrue();

        Predicate predicate33 = new LogicalOrPredicate(new ValueSetFunctionPredicate(new TupleValue3Extractor(), new EqualsPredicate()), new ValueSetFunctionPredicate(new TupleValue1Extractor(), new IsLessThenPredicate()), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsGreaterThenPredicate()));
        Assertions.assertThat(predicate33.evaluate("c", 1, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate33.evaluate("c", 9, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate33.evaluate("c", 10, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 11, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 15, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate33.evaluate("c", 19, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 20, new Tuple(10, 20, 15))).isFalse();
        Assertions.assertThat(predicate33.evaluate("c", 21, new Tuple(10, 20, 15))).isTrue();
        Assertions.assertThat(predicate33.evaluate("c", 29, new Tuple(10, 20, 15))).isTrue();
    }

}
