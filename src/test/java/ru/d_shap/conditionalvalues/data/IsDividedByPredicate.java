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
package ru.d_shap.conditionalvalues.data;

import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.predicate.WrongConditionSetValueException;
import ru.d_shap.conditionalvalues.predicate.WrongValueSetValueException;

/**
 * Predicate to check if the value in the {@link ru.d_shap.conditionalvalues.ConditionSet} object
 * is divided by the value in the {@link ru.d_shap.conditionalvalues.ValueSet}.
 *
 * @author Dmitry Shapovalov
 */
public final class IsDividedByPredicate implements Predicate {

    /**
     * Create new object.
     */
    public IsDividedByPredicate() {
        super();
    }

    @Override
    public boolean evaluate(final String conditionName, final Object conditionSetValue, final Object valueSetValue) {
        int valueSetValueInt = getValueSetValueAsInt(conditionName, valueSetValue);
        int conditionSetValueInt = getConditionSetValueAsInt(conditionName, conditionSetValue);
        return conditionSetValueInt % valueSetValueInt == 0;
    }

    private static int getValueSetValueAsInt(final String conditionName, final Object valueSetValue) {
        if (valueSetValue instanceof Integer) {
            return (Integer) valueSetValue;
        } else {
            throw new WrongValueSetValueException(conditionName, valueSetValue, Integer.class);
        }
    }

    private static int getConditionSetValueAsInt(final String conditionName, final Object conditionSetValue) {
        if (conditionSetValue instanceof Integer) {
            return (Integer) conditionSetValue;
        } else {
            throw new WrongConditionSetValueException(conditionName, conditionSetValue, Integer.class);
        }
    }

}
