// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class represents some values, wich are selected in runtime based on specified conditions.
 *
 * @param <T> Value type.
 * @author Dmitry Shapovalov
 */
public final class ValueSet<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<T> _values;

    ValueSet(final Map<String, Set<String>> conditionMap, final Set<T> values) {
        super();
        Map<String, Set<String>> conditions = new HashMap<String, Set<String>>();
        for (Map.Entry<String, Set<String>> entry : conditionMap.entrySet()) {
            String key = entry.getKey();
            Set<String> value = entry.getValue();
            conditions.put(key, Collections.unmodifiableSet(new HashSet<String>(value)));
        }
        _conditions = Collections.unmodifiableMap(conditions);
        _values = Collections.unmodifiableSet(new LinkedHashSet<T>(values));
    }

    /**
     * Get all condition names, defined in this object.
     *
     * @return Set with unique condition names.
     */
    public Set<String> getAllConditionNames() {
        return _conditions.keySet();
    }

    /**
     * Get all condition values, defined for specified condition names.
     *
     * @param conditionName Condition name.
     * @return Set with unique condition values for specified condition name.
     */
    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> values = _conditions.get(conditionName);
        if (values == null) {
            return new HashSet<String>();
        } else {
            return values;
        }
    }

    int matchCardinality(final ConditionSet conditionSet) {
        if (conditionSet == null) {
            return -1;
        }
        int cardinality = 0;
        Iterator<String> iterator = conditionSet.nameIterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (_conditions.containsKey(key)) {
                Set<String> currentValues = _conditions.get(key);
                String otherValue = conditionSet.getCondition(key);
                if (currentValues.contains(otherValue)) {
                    cardinality++;
                }
            }
        }
        if (cardinality == _conditions.size()) {
            return cardinality;
        } else {
            return -1;
        }
    }

    Set<T> getAllValues() {
        return _values;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ValueSet)) {
            return false;
        }
        ValueSet<?> other = (ValueSet<?>) object;
        Set<String> conditionKeys = _conditions.keySet();
        Set<String> otherConditionKeys = other._conditions.keySet();
        if (!conditionKeys.containsAll(otherConditionKeys) || !otherConditionKeys.containsAll(conditionKeys)) {
            return false;
        }
        for (String key : conditionKeys) {
            Set<String> conditionValues = _conditions.get(key);
            Set<String> otherConditionValues = other._conditions.get(key);

            if (!conditionValues.containsAll(otherConditionValues) || !otherConditionValues.containsAll(conditionValues)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Map.Entry<String, Set<String>> entry : _conditions.entrySet()) {
            String conditionKey = entry.getKey();
            result = result * 31 + conditionKey.hashCode();

            Set<String> conditionValues = entry.getValue();
            for (String conditionValue : conditionValues) {
                result = result * 31 + conditionValue.hashCode();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return _conditions.toString();
    }

}
