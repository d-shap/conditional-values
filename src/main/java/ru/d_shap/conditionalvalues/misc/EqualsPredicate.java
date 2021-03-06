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
package ru.d_shap.conditionalvalues.misc;

import ru.d_shap.conditionalvalues.Predicate;

/**
 * Predicate to check if the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object
 * is case-sensitive equal to the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class EqualsPredicate implements Predicate {

    /**
     * Create new object.
     */
    public EqualsPredicate() {
        super();
    }

    @Override
    public boolean isSameValue(final String conditionName, final String value1, final String value2) {
        return value1 != null && value1.equals(value2);
    }

    @Override
    public boolean evaluate(final String conditionName, final String conditionValue, final String checkValue) {
        return conditionValue != null && conditionValue.equals(checkValue);
    }

}
