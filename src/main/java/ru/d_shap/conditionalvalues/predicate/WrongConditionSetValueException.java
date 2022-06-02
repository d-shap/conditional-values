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

/**
 * Exception is thrown when the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object is wrong.
 *
 * @author Dmitry Shapovalov
 */
public final class WrongConditionSetValueException extends EvaluationException {

    private static final long serialVersionUID = 1L;

    /**
     * Create new object.
     *
     * @param conditionName     the name of condition.
     * @param conditionSetValue the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     * @param expectedClass     the expected class for the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     */
    public WrongConditionSetValueException(final String conditionName, final Object conditionSetValue, final Class<?> expectedClass) {
        super(getMessage(conditionName, getClassName(conditionSetValue), getClassName(expectedClass)));
    }

    private static String getMessage(final String conditionName, final String actualClassName, final String expectedClassName) {
        return "Condition with name " + conditionName + " has a wrong class, expected " + expectedClassName + ", but was " + actualClassName;
    }

}
