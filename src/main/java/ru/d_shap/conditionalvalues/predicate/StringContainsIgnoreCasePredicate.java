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

import ru.d_shap.conditionalvalues.Predicate;

/**
 * Predicate checks if the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
 * case-insensitively contains the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class StringContainsIgnoreCasePredicate implements Predicate {

    /**
     * Create new object.
     */
    public StringContainsIgnoreCasePredicate() {
        super();
    }

    @Override
    public boolean evaluate(final String conditionName, final Object conditionSetValue, final Object valueSetValue) {
        if (valueSetValue == null) {
            return conditionSetValue == null;
        } else {
            if (conditionSetValue == null) {
                return false;
            } else {
                String valueSetValueStr = getValueSetValueAsString(conditionName, valueSetValue);
                String conditionSetValueStr = getConditionSetValueAsString(conditionName, conditionSetValue);
                return containsIgnoreCase(conditionSetValueStr, valueSetValueStr);
            }
        }
    }

    private static String getValueSetValueAsString(final String conditionName, final Object valueSetValue) {
        if (valueSetValue instanceof String) {
            return (String) valueSetValue;
        } else {
            throw new WrongValueSetValueException(conditionName, valueSetValue, String.class);
        }
    }

    private static String getConditionSetValueAsString(final String conditionName, final Object conditionSetValue) {
        if (conditionSetValue instanceof String) {
            return (String) conditionSetValue;
        } else {
            throw new WrongConditionSetValueException(conditionName, conditionSetValue, String.class);
        }
    }

    private static boolean containsIgnoreCase(final String str, final String searchStr) {
        int searchStrLength = searchStr.length();
        if (searchStrLength == 0) {
            return true;
        }
        for (int i = str.length() - searchStrLength; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, searchStrLength)) {
                return true;
            }
        }
        return false;
    }

}
