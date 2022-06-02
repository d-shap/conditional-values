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
 * Predicate to join other predicates with the logical XOR operation.
 *
 * @author Dmitry Shapovalov
 */
public final class LogicalXorPredicate implements Predicate {

    private final Predicate _first;

    private final Predicate _second;

    private final Predicate[] _next;

    /**
     * Create new object.
     *
     * @param first  the first predicate to join with the logical operation.
     * @param second the second predicate to join with the logical operation.
     * @param next   the next predicated to join with the logical operation.
     */
    public LogicalXorPredicate(final Predicate first, final Predicate second, final Predicate... next) {
        super();
        _first = first;
        _second = second;
        _next = new Predicate[next.length];
        System.arraycopy(next, 0, _next, 0, next.length);
    }

    @Override
    public boolean evaluate(final String conditionName, final Object conditionSetValue, final Object valueSetValue) {
        boolean valueFirst = _first.evaluate(conditionName, conditionSetValue, valueSetValue);
        boolean valueSecond = _second.evaluate(conditionName, conditionSetValue, valueSetValue);
        boolean result = valueFirst ^ valueSecond;
        for (Predicate predicate : _next) {
            boolean value = predicate.evaluate(conditionName, conditionSetValue, valueSetValue);
            result = result ^ value;
        }
        return result;
    }

}
