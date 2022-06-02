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

/**
 * Tests for {@link WrongConditionSetValueException}.
 *
 * @author Dmitry Shapovalov
 */
public final class WrongConditionSetValueExceptionTest {

    /**
     * Test class constructor.
     */
    public WrongConditionSetValueExceptionTest() {
        super();
    }

    /**
     * {@link WrongConditionSetValueException} class test.
     */
    @Test
    public void getMessageTest() {
        Assertions.assertThat(new WrongConditionSetValueException(null, null, null)).hasMessage("Condition with name null has a wrong class, expected null, but was null");
        Assertions.assertThat(new WrongConditionSetValueException("condition", "value", StringBuilder.class)).hasMessage("Condition with name condition has a wrong class, expected java.lang.StringBuilder, but was java.lang.String");
        Assertions.assertThat(new WrongConditionSetValueException("cnd", 11, Double.class)).hasMessage("Condition with name cnd has a wrong class, expected java.lang.Double, but was java.lang.Integer");
    }

    /**
     * {@link WrongConditionSetValueException} class test.
     */
    @Test
    public void getCauseTest() {
        Assertions.assertThat(new WrongConditionSetValueException(null, null, null)).causeIsNull();
        Assertions.assertThat(new WrongConditionSetValueException("condition", "value", StringBuilder.class)).causeIsNull();
        Assertions.assertThat(new WrongConditionSetValueException("cnd", 11, Double.class)).causeIsNull();
    }

}
