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

import java.io.IOException;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link ConditionalValuesException}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValuesExceptionTest {

    /**
     * Test class constructor.
     */
    public ConditionalValuesExceptionTest() {
        super();
    }

    /**
     * {@link ConditionalValuesException} class test.
     */
    @Test
    public void getMessageTest() {
        Assertions.assertThat(new ConditionalValuesException((String) null)).messageIsNull();
        Assertions.assertThat(new ConditionalValuesException("")).hasMessage("");
        Assertions.assertThat(new ConditionalValuesException("message")).hasMessage("message");

        Assertions.assertThat(new ConditionalValuesException((Throwable) null)).messageIsNull();
        Assertions.assertThat(new ConditionalValuesException(new IOException())).messageIsNull();
        Assertions.assertThat(new ConditionalValuesException(new IOException("io message"))).hasMessage("io message");

        Assertions.assertThat(new ConditionalValuesException(null, null)).messageIsNull();
        Assertions.assertThat(new ConditionalValuesException("", null)).hasMessage("");
        Assertions.assertThat(new ConditionalValuesException("message", null)).hasMessage("message");
        Assertions.assertThat(new ConditionalValuesException(null, new IOException())).messageIsNull();
        Assertions.assertThat(new ConditionalValuesException(null, new IOException("io message"))).messageIsNull();
        Assertions.assertThat(new ConditionalValuesException("message", new IOException("io message"))).hasMessage("message");
    }

    /**
     * {@link ConditionalValuesException} class test.
     */
    @Test
    public void getCauseTest() {
        Assertions.assertThat(new ConditionalValuesException((String) null)).causeIsNull();
        Assertions.assertThat(new ConditionalValuesException("")).causeIsNull();
        Assertions.assertThat(new ConditionalValuesException("message")).causeIsNull();

        Assertions.assertThat(new ConditionalValuesException((Throwable) null)).causeIsNull();
        Assertions.assertThat(new ConditionalValuesException(new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new ConditionalValuesException(new IOException())).causeMessageIsNull();
        Assertions.assertThat(new ConditionalValuesException(new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new ConditionalValuesException(new IOException("io message"))).hasCauseMessage("io message");

        Assertions.assertThat(new ConditionalValuesException(null, null)).causeIsNull();
        Assertions.assertThat(new ConditionalValuesException("", null)).causeIsNull();
        Assertions.assertThat(new ConditionalValuesException("message", null)).causeIsNull();
        Assertions.assertThat(new ConditionalValuesException(null, new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new ConditionalValuesException(null, new IOException())).causeMessageIsNull();
        Assertions.assertThat(new ConditionalValuesException(null, new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new ConditionalValuesException(null, new IOException("io message"))).hasCauseMessage("io message");
        Assertions.assertThat(new ConditionalValuesException("message", new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new ConditionalValuesException("message", new IOException("io message"))).hasCauseMessage("io message");
    }

}
