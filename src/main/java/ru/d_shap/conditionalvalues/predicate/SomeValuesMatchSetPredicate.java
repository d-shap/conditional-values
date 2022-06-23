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
 * matches the specified number of values from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class SomeValuesMatchSetPredicate implements SetPredicate {

    private final int _matchCountMin;

    private final int _matchCountMax;

    /**
     * Create new object.
     *
     * @param matchCountMin the minimum number of matches.
     * @param matchCountMax the minimum number of matches.
     */
    public SomeValuesMatchSetPredicate(final int matchCountMin, final int matchCountMax) {
        super();
        _matchCountMin = matchCountMin;
        _matchCountMax = matchCountMax;
    }

    @Override
    public boolean evaluate(final String conditionName, final Predicate predicate, final Object conditionSetValue, final Set<Object> valueSetValues) {
        int matchCount = 0;
        if (predicate != null && valueSetValues != null) {
            for (Object valueSetValue : valueSetValues) {
                if (predicate.evaluate(conditionName, conditionSetValue, valueSetValue)) {
                    matchCount++;
                }
            }
        }
        boolean minCountValid = matchCount >= _matchCountMin;
        boolean maxCountValid = _matchCountMax < 0 || matchCount <= _matchCountMax;
        return matchCount > 0 && minCountValid && maxCountValid;
    }

}
