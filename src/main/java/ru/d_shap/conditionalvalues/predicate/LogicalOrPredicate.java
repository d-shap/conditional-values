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
 * Predicate to join other predicates with the logical OR operation.
 *
 * @author Dmitry Shapovalov
 */
public class LogicalOrPredicate implements Predicate {

    private final Predicate _predicate1;

    private final Predicate _predicate2;

    /**
     * Create new object.
     *
     * @param predicate1 the predicate to join with the logical operation.
     * @param predicate2 the predicate to join with the logical operation.
     */
    public LogicalOrPredicate(final Predicate predicate1, final Predicate predicate2) {
        super();
        _predicate1 = predicate1;
        _predicate2 = predicate2;
    }

    @Override
    public boolean evaluate(final String conditionName, final Object conditionSetValue, final Object valueSetValue) {
        boolean value = _predicate1.evaluate(conditionName, conditionSetValue, valueSetValue);
        if (value) {
            return true;
        }
        return _predicate2.evaluate(conditionName, conditionSetValue, valueSetValue);
    }

}
