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

import java.io.IOException;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link EvaluationException}.
 *
 * @author Dmitry Shapovalov
 */
public final class EvaluationExceptionTest {

    /**
     * Test class constructor.
     */
    public EvaluationExceptionTest() {
        super();
    }

    /**
     * {@link EvaluationException} class test.
     */
    @Test
    public void getMessageTest() {
        Assertions.assertThat(new EvaluationException((String) null)).messageIsNull();
        Assertions.assertThat(new EvaluationException("")).hasMessage("");
        Assertions.assertThat(new EvaluationException("message")).hasMessage("message");

        Assertions.assertThat(new EvaluationException((Throwable) null)).messageIsNull();
        Assertions.assertThat(new EvaluationException(new IOException())).messageIsNull();
        Assertions.assertThat(new EvaluationException(new IOException("io message"))).hasMessage("io message");

        Assertions.assertThat(new EvaluationException(null, null)).messageIsNull();
        Assertions.assertThat(new EvaluationException("", null)).hasMessage("");
        Assertions.assertThat(new EvaluationException("message", null)).hasMessage("message");
        Assertions.assertThat(new EvaluationException(null, new IOException())).messageIsNull();
        Assertions.assertThat(new EvaluationException("", new IOException())).hasMessage("");
        Assertions.assertThat(new EvaluationException("message", new IOException())).hasMessage("message");
        Assertions.assertThat(new EvaluationException(null, new IOException("io message"))).messageIsNull();
        Assertions.assertThat(new EvaluationException("", new IOException("io message"))).hasMessage("");
        Assertions.assertThat(new EvaluationException("message", new IOException("io message"))).hasMessage("message");
    }

    /**
     * {@link EvaluationException} class test.
     */
    @Test
    public void getCauseTest() {
        Assertions.assertThat(new EvaluationException((String) null)).causeIsNull();
        Assertions.assertThat(new EvaluationException("")).causeIsNull();
        Assertions.assertThat(new EvaluationException("message")).causeIsNull();

        Assertions.assertThat(new EvaluationException((Throwable) null)).causeIsNull();
        Assertions.assertThat(new EvaluationException(new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException(new IOException())).causeMessageIsNull();
        Assertions.assertThat(new EvaluationException(new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException(new IOException("io message"))).hasCauseMessage("io message");

        Assertions.assertThat(new EvaluationException(null, null)).causeIsNull();
        Assertions.assertThat(new EvaluationException("", null)).causeIsNull();
        Assertions.assertThat(new EvaluationException("message", null)).causeIsNull();
        Assertions.assertThat(new EvaluationException(null, new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException(null, new IOException())).causeMessageIsNull();
        Assertions.assertThat(new EvaluationException("", new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException("", new IOException())).causeMessageIsNull();
        Assertions.assertThat(new EvaluationException("message", new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException("message", new IOException())).causeMessageIsNull();
        Assertions.assertThat(new EvaluationException(null, new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException(null, new IOException("io message"))).hasCauseMessage("io message");
        Assertions.assertThat(new EvaluationException("", new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException("", new IOException("io message"))).hasCauseMessage("io message");
        Assertions.assertThat(new EvaluationException("message", new IOException("io message"))).hasCause(IOException.class);
        Assertions.assertThat(new EvaluationException("message", new IOException("io message"))).hasCauseMessage("io message");
    }

}
