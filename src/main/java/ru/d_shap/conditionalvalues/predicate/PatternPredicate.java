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

import java.util.regex.Pattern;

import ru.d_shap.conditionalvalues.Predicate;

/**
 * Predicate to check if the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
 * matches to the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class PatternPredicate implements Predicate {

    /**
     * Create new object.
     */
    public PatternPredicate() {
        super();
    }

    @Override
    public boolean evaluate(final String conditionName, final Object conditionSetValue, final Object valueSetValue) {
        if (valueSetValue == null) {
            return conditionSetValue == null;
        } else {
            Pattern valueSetValueObj = getValueSetValue(conditionName, valueSetValue);
            String conditionSetValueObj = getConditionSetValue(conditionName, conditionSetValue);
            return valueSetValueObj.matcher(conditionSetValueObj).matches();
        }
    }

    private static Pattern getValueSetValue(final String conditionName, final Object valueSetValue) {
        if (valueSetValue instanceof Pattern) {
            return (Pattern) valueSetValue;
        } else {
            throw new WrongValueSetValueException(conditionName, valueSetValue, Pattern.class);
        }
    }

    private static String getConditionSetValue(final String conditionName, final Object conditionSetValue) {
        if (conditionSetValue instanceof CharSequence) {
            if (conditionSetValue instanceof String) {
                return (String) conditionSetValue;
            } else {
                return ((CharSequence) conditionSetValue).toString();
            }
        } else {
            throw new WrongConditionSetValueException(conditionName, conditionSetValue, CharSequence.class);
        }
    }

}
