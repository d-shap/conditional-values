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
package ru.d_shap.conditionalvalues;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class represents a unique combination of single conditions in the {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetUniqueCondition {

    private final String _id;

    private final Map<String, String> _conditions;

    ValueSetUniqueCondition(final String id) {
        super();
        _id = id;
        _conditions = Collections.unmodifiableMap(new TreeMap<String, String>());
    }

    ValueSetUniqueCondition(final ValueSetUniqueCondition valueSetUniqueCondition, final String conditionName, final String conditionValue) {
        super();
        _id = valueSetUniqueCondition._id;
        Map<String, String> map = new TreeMap<>(valueSetUniqueCondition._conditions);
        if (conditionName != null && conditionValue != null) {
            if (map.containsKey(conditionName)) {
                throw new DuplicateConditionNameException(conditionName);
            } else {
                map.put(conditionName, conditionValue);
            }
        }
        _conditions = Collections.unmodifiableMap(map);
    }

    /**
     * Get the ID of the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return the ID of the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     */
    public String getId() {
        return _id;
    }

    /**
     * Get a combination of conditions.
     *
     * @return Combination of conditions
     */
    public Map<String, String> getConditions() {
        return _conditions;
    }

    @Override
    public String toString() {
        String conditions = _conditions.toString();
        if (_id != null) {
            conditions = _id + "=" + conditions;
        }
        return conditions;
    }

}
