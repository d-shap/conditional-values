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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class represents a distinct condition with a corresponding values for this condition.
 *
 * @param <T> generic type for the value.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSet<T> {

    private final String _id;

    private final Map<String, Set<Object>> _conditions;

    private final Set<String> _conditionNames;

    private final Set<T> _values;

    /**
     * Create new object.
     *
     * @param id         the ID of the value set.
     * @param conditions the conditions of the value set.
     * @param values     the values of the value set.
     */
    public ValueSet(final String id, final Map<String, Set<Object>> conditions, final Set<T> values) {
        super();
        _id = id;
        _conditions = createConditions(conditions);
        _conditionNames = createConditionNames();
        _values = createValues(values);
    }

    private Map<String, Set<Object>> createConditions(final Map<String, Set<Object>> conditions) {
        Map<String, Set<Object>> result = new HashMap<>();
        if (conditions != null) {
            for (Map.Entry<String, Set<Object>> entry : conditions.entrySet()) {
                String key = entry.getKey();
                Set<Object> values = createConditionValues(entry.getValue());
                if (key != null && !values.isEmpty()) {
                    result.put(key, values);
                }
            }
        }
        return Collections.unmodifiableMap(result);
    }

    private Set<Object> createConditionValues(final Set<Object> values) {
        Set<Object> result = new HashSet<>();
        if (values != null) {
            for (Object value : values) {
                if (value != null) {
                    result.add(value);
                }
            }
        }
        return Collections.unmodifiableSet(result);
    }

    private Set<String> createConditionNames() {
        Set<String> result = _conditions.keySet();
        return Collections.unmodifiableSet(result);
    }

    private Set<T> createValues(final Set<T> values) {
        Set<T> result = new HashSet<>();
        if (values != null) {
            for (T value : values) {
                if (value != null) {
                    result.add(value);
                }
            }
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get the ID of this object.
     *
     * @return the ID of this object.
     */
    public String getId() {
        return _id;
    }

    /**
     * Get all condition names, defined in this object.
     *
     * @return all condition names.
     */
    public Set<String> getAllConditionNames() {
        return _conditionNames;
    }

    /**
     * Get all condition values for the specified condition name, defined in this object.
     *
     * @param conditionName the specified condition name.
     *
     * @return all condition values for the specified condition name.
     */
    public Set<Object> getAllConditionValues(final String conditionName) {
        Set<Object> values = _conditions.get(conditionName);
        if (values == null) {
            return Collections.emptySet();
        } else {
            return values;
        }
    }

    boolean isMatchConditions(final ConditionSet conditionSet, final Predicate predicate) {
        if (conditionSet == null || predicate == null) {
            return false;
        }
        int matchCount = 0;
        Iterator<String> conditionNameIterator = conditionSet.nameIterator();
        while (conditionNameIterator.hasNext()) {
            String conditionName = conditionNameIterator.next();
            Object conditionSetValue = conditionSet.getValue(conditionName);
            Set<Object> valueSetValues = _conditions.get(conditionName);
            if (valueSetValues != null) {
                for (Object valueSetValue : valueSetValues) {
                    if (predicate.evaluate(conditionName, conditionSetValue, valueSetValue)) {
                        matchCount++;
                        break;
                    }
                }
            }
        }
        return matchCount == _conditions.size();
    }

    boolean isMoreSpecificValueSet(final ValueSet<T> valueSet) {
        if (valueSet == null) {
            return false;
        }
        int matchCount = 0;
        Iterator<String> conditionNameIterator = _conditionNames.iterator();
        while (conditionNameIterator.hasNext()) {
            String conditionName = conditionNameIterator.next();
            if (valueSet._conditions.containsKey(conditionName)) {
                matchCount++;
            }
        }
        return matchCount == valueSet._conditions.size() && matchCount < _conditions.size();
    }

    List<ValueSetUniqueCondition> getValueSetUniqueConditions() {
        if (_conditions.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ValueSetUniqueCondition> currentUniqueConditions = new ArrayList<>();
            currentUniqueConditions.add(new ValueSetUniqueCondition(_id));
            for (Map.Entry<String, Set<Object>> entry : _conditions.entrySet()) {
                currentUniqueConditions = addConditionValuesToCurrentUniqueConditions(currentUniqueConditions, entry.getKey(), entry.getValue());
            }
            return Collections.unmodifiableList(currentUniqueConditions);
        }
    }

    private List<ValueSetUniqueCondition> addConditionValuesToCurrentUniqueConditions(final List<ValueSetUniqueCondition> currentUniqueConditions, final String conditionName, final Set<Object> conditionValues) {
        List<ValueSetUniqueCondition> newUniqueConditions = new ArrayList<>();
        for (ValueSetUniqueCondition currentUniqueCondition : currentUniqueConditions) {
            for (Object conditionValue : conditionValues) {
                ValueSetUniqueCondition newUniqueCondition = new ValueSetUniqueCondition(currentUniqueCondition, conditionName, conditionValue);
                newUniqueConditions.add(newUniqueCondition);
            }
        }
        return newUniqueConditions;
    }

    Set<T> getValues() {
        return _values;
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
