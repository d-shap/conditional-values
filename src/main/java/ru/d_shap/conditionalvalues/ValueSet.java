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
 * @param <T> generic value type.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSet<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<String> _conditionNames;

    private final Set<T> _values;

    ValueSet(final Map<String, Set<String>> conditions, final Set<T> values) {
        super();
        Map<String, Set<String>> map = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : conditions.entrySet()) {
            map.put(entry.getKey(), Collections.unmodifiableSet(new HashSet<>(entry.getValue())));
        }
        _conditions = Collections.unmodifiableMap(map);
        _conditionNames = Collections.unmodifiableSet(_conditions.keySet());
        _values = Collections.unmodifiableSet(new HashSet<>(values));
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
    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> values = _conditions.get(conditionName);
        if (values == null) {
            return Collections.emptySet();
        } else {
            return values;
        }
    }

    boolean isMatchConditions(final ConditionSet conditionSet, final Predicate predicate) {
        int matchCount = 0;
        Iterator<String> conditionNameIterator = conditionSet.nameIterator();
        while (conditionNameIterator.hasNext()) {
            String conditionName = conditionNameIterator.next();
            String conditionValue = conditionSet.getValue(conditionName);
            Set<String> checkValues = _conditions.get(conditionName);
            if (checkValues != null) {
                for (String checkValue : checkValues) {
                    if (predicate.evaluate(conditionName, conditionValue, checkValue)) {
                        matchCount++;
                        break;
                    }
                }
            }
        }
        return matchCount == _conditions.size();
    }

    boolean isMoreSpecificValueSet(final ValueSet<T> valueSet) {
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
            currentUniqueConditions.add(new ValueSetUniqueCondition());
            for (Map.Entry<String, Set<String>> entry : _conditions.entrySet()) {
                currentUniqueConditions = addConditionValuesToCurrentUniqueConditions(currentUniqueConditions, entry.getKey(), entry.getValue());
            }
            return Collections.unmodifiableList(currentUniqueConditions);
        }
    }

    private List<ValueSetUniqueCondition> addConditionValuesToCurrentUniqueConditions(final List<ValueSetUniqueCondition> currentUniqueConditions, final String conditionName, final Set<String> conditionValues) {
        List<ValueSetUniqueCondition> newUniqueConditions = new ArrayList<>();
        for (ValueSetUniqueCondition currentUniqueCondition : currentUniqueConditions) {
            for (String conditionValue : conditionValues) {
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
        return _conditions.toString();
    }

}
