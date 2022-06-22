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

import java.util.Set;

import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.SetPredicate;

/**
 * Predicate to check if the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
 * matches all values from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class AllValuesMatchSetPredicate implements SetPredicate {

    /**
     * Create new object.
     */
    public AllValuesMatchSetPredicate() {
        super();
    }

    @Override
    public boolean evaluate(final String conditionName, final Predicate predicate, final Object conditionSetValue, final Set<Object> valueSetValues) {
        boolean hasValues = false;
        if (predicate != null && valueSetValues != null) {
            for (Object valueSetValue : valueSetValues) {
                hasValues = true;
                if (!predicate.evaluate(conditionName, conditionSetValue, valueSetValue)) {
                    return false;
                }
            }
        }
        return hasValues;
    }

}
