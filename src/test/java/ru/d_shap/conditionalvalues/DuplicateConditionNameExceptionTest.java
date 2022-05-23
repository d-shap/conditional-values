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
 * Tests for {@link DuplicateConditionNameException}.
 *
 * @author Dmitry Shapovalov
 */
public final class DuplicateConditionNameExceptionTest {

    /**
     * Test class constructor.
     */
    public DuplicateConditionNameExceptionTest() {
        super();
    }

    /**
     * {@link DuplicateConditionNameException} class test.
     */
    @Test
    public void getMessageTest() {
        Assertions.assertThat(new DuplicateConditionNameException(null)).hasMessage("Duplicate condition name: null");
        Assertions.assertThat(new DuplicateConditionNameException("")).hasMessage("Duplicate condition name: ");
        Assertions.assertThat(new DuplicateConditionNameException("failed condition")).hasMessage("Duplicate condition name: failed condition");
    }

    /**
     * {@link DuplicateConditionNameException} class test.
     */
    @Test
    public void getCauseTest() {
        Assertions.assertThat(new DuplicateConditionNameException(null)).causeIsNull();
        Assertions.assertThat(new DuplicateConditionNameException("")).causeIsNull();
        Assertions.assertThat(new DuplicateConditionNameException("failed condition")).causeIsNull();
    }

}
