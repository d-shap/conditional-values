///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Conditional values.
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Object represents conditions, used to lookup the best mathcing {@link ru.d_shap.conditionalvalues.ValueSet} objects.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSet {

    private final Map<String, String> _conditions;

    private final Set<String> _conditionNames;

    ConditionSet(final Map<String, String> conditions) {
        super();
        _conditions = Collections.unmodifiableMap(new HashMap<String, String>(conditions));
        _conditionNames = Collections.unmodifiableSet(_conditions.keySet());
    }

    Iterator<String> nameIterator() {
        return _conditionNames.iterator();
    }

    String getCondition(final String name) {
        return _conditions.get(name);
    }

    @Override
    public String toString() {
        return _conditions.toString();
    }

}
