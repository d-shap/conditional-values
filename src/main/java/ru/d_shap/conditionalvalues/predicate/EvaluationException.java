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

import ru.d_shap.conditionalvalues.ConditionalValuesException;

/**
 * Base class for all predicate evaluation exceptions.
 *
 * @author Dmitry Shapovalov
 */
public class EvaluationException extends ConditionalValuesException {

    private static final long serialVersionUID = 1L;

    /**
     * Create a new object.
     *
     * @param message exception message.
     */
    protected EvaluationException(final String message) {
        super(message);
    }

    /**
     * Create a new object.
     *
     * @param cause the original exception.
     */
    protected EvaluationException(final Throwable cause) {
        super(cause);
    }

    /**
     * Create a new object.
     *
     * @param message exception message.
     * @param cause   the original exception.
     */
    protected EvaluationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
