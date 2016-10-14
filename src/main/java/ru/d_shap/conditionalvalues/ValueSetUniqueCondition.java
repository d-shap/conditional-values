// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class represents single unique combination of conditions in a value set.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueSetUniqueCondition {

    private final Map<String, String> _conditions;

    ValueSetUniqueCondition() {
        super();
        _conditions = Collections.unmodifiableMap(new HashMap<String, String>());
    }

    ValueSetUniqueCondition(final ValueSetUniqueCondition valueSetUniqueCondition, final String conditionName, final String conditionValue) {
        super();
        Map<String, String> map = new HashMap<String, String>(valueSetUniqueCondition._conditions);
        if (conditionName != null && conditionValue != null) {
            map.put(conditionName, conditionValue);
        }
        _conditions = Collections.unmodifiableMap(map);
    }

    /**
     * Get combination of conditions.
     *
     * @return Combination of conditions
     */
    public Map<String, String> getConditions() {
        return _conditions;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ValueSetUniqueCondition)) {
            return false;
        }
        ValueSetUniqueCondition other = (ValueSetUniqueCondition) object;
        Set<String> conditionKeys = _conditions.keySet();
        Set<String> otherConditionKeys = other._conditions.keySet();
        if (!conditionKeys.containsAll(otherConditionKeys) || !otherConditionKeys.containsAll(conditionKeys)) {
            return false;
        }
        for (String key : conditionKeys) {
            String conditionValue = _conditions.get(key);
            String otherConditionValue = other._conditions.get(key);
            if (!conditionValue.equals(otherConditionValue)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Map.Entry<String, String> entry : _conditions.entrySet()) {
            result = result * 31 + entry.getKey().hashCode();
            result = result * 31 + entry.getValue().hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        return _conditions.toString();
    }

}
