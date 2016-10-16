// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright(C)2016 Dmitry Shapovalov.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * Class represents distinct condition with corresponding values for this condition.
 *
 * @param <T> value type.
 * @author Dmitry Shapovalov
 */
public final class ValueSet<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<T> _values;

    ValueSet(final Map<String, Set<String>> conditions, final Set<T> values) {
        super();
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        for (Map.Entry<String, Set<String>> entry : conditions.entrySet()) {
            map.put(entry.getKey(), Collections.unmodifiableSet(new HashSet<String>(entry.getValue())));
        }
        _conditions = Collections.unmodifiableMap(map);
        _values = Collections.unmodifiableSet(new HashSet<T>(values));
    }

    /**
     * Get all condition names, defined in this object.
     *
     * @return all condition names.
     */
    public Set<String> getAllConditionNames() {
        return _conditions.keySet();
    }

    /**
     * Get all condition values for specified condition name, defined in this object.
     *
     * @param conditionName condition name.
     * @return all condition values for specified condition name.
     */
    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> values = _conditions.get(conditionName);
        if (values == null) {
            return Collections.unmodifiableSet(new HashSet<String>());
        } else {
            return values;
        }
    }

    boolean isMatchConditions(final ConditionSet conditionSet) {
        int matchCount = 0;
        Iterator<String> conditionNameIterator = conditionSet.nameIterator();
        while (conditionNameIterator.hasNext()) {
            String conditionName = conditionNameIterator.next();
            Set<String> conditionValues = _conditions.get(conditionName);
            if (conditionValues != null) {
                String conditionValue = conditionSet.getCondition(conditionName);
                if (conditionValues.contains(conditionValue)) {
                    matchCount++;
                }
            }
        }
        return matchCount == _conditions.size();
    }

    boolean isMoreSpecificValueSet(final ValueSet<T> valueSet) {
        int matchCount = 0;
        Iterator<String> conditionNameIterator = _conditions.keySet().iterator();
        while (conditionNameIterator.hasNext()) {
            String conditionName = conditionNameIterator.next();
            if (valueSet._conditions.containsKey(conditionName)) {
                matchCount++;
            }
        }
        return matchCount == valueSet._conditions.size() && matchCount < _conditions.size();
    }

    List<ValueSetUniqueCondition> getValueSetUniqueConditions() {
        List<ValueSetUniqueCondition> currentUniqueConditions = new ArrayList<ValueSetUniqueCondition>();
        currentUniqueConditions.add(new ValueSetUniqueCondition());
        for (Map.Entry<String, Set<String>> entry : _conditions.entrySet()) {
            currentUniqueConditions = addConditionValuesToCurrentUniqueConditions(currentUniqueConditions, entry.getKey(), entry.getValue());
        }
        return currentUniqueConditions;
    }

    private List<ValueSetUniqueCondition> addConditionValuesToCurrentUniqueConditions(final List<ValueSetUniqueCondition> currentUniqueConditions, final String conditionName, final Set<String> conditionValues) {
        List<ValueSetUniqueCondition> result = new ArrayList<ValueSetUniqueCondition>();
        for (ValueSetUniqueCondition valueSetUniqueCondition : currentUniqueConditions) {
            for (String conditionValue : conditionValues) {
                ValueSetUniqueCondition newUniqueCondition = new ValueSetUniqueCondition(valueSetUniqueCondition, conditionName, conditionValue);
                result.add(newUniqueCondition);
            }
        }
        return result;
    }

    Set<T> getAllValues() {
        return _values;
    }

    @Override
    public String toString() {
        return _conditions.toString();
    }

}
