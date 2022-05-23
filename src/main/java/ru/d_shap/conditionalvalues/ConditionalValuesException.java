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

/**
 * Base class for all exceptions.
 *
 * @author Dmitry Shapovalov
 */
public class ConditionalValuesException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Create a new object.
     *
     * @param message exception message.
     */
    protected ConditionalValuesException(final String message) {
        super(message);
    }

    /**
     * Create a new object.
     *
     * @param message exception message.
     * @param cause   the original exception.
     */
    protected ConditionalValuesException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Get the message of the throwable.
     *
     * @param throwable the throwable.
     *
     * @return the message of the throwable.
     */
    protected static String getThrowableMessage(final Throwable throwable) {
        if (throwable == null) {
            return null;
        } else {
            return throwable.getMessage();
        }
    }

}
