// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ConditionalValues<T> {

    private final List<ValueSet<T>> _valueSets;

    private ConditionalValues(final List<ValueSet<T>> valueSets) {
        super();
        List<ValueSet<T>> list = new ArrayList<ValueSet<T>>();
        for (ValueSet<T> valueSet : valueSets) {
            if (valueSet != null) {
                list.add(valueSet);
            }
        }
        _valueSets = Collections.unmodifiableList(list);
    }

    public static <T> ValueSetBuilder<T> createValueSetBuilder() {
        return new ValueSetBuilder<T>();
    }

    public static ConditionSetBuilder createConditionSetBuilder() {
        return new ConditionSetBuilder();
    }

    public static <T> ConditionalValues<T> createConditionalValues(final List<ValueSet<T>> valueSets) {
        if (valueSets == null) {
            List<ValueSet<T>> valueSetList = new ArrayList<ValueSet<T>>();
            return new ConditionalValues<T>(valueSetList);
        } else {
            validateValueSets(valueSets);
            return new ConditionalValues<T>(valueSets);
        }
    }

    private static <T> void validateValueSets(final List<ValueSet<T>> valueSets) {
        Set<ValueSet<T>> set = new HashSet<ValueSet<T>>();
        for (ValueSet<T> valueSet : valueSets) {
            if (valueSet != null && !set.add(valueSet)) {
                throw new DuplicateValueSetException(valueSet);
            }
        }
    }

    public Set<String> getAllConditionNames() {
        Set<String> result = new HashSet<String>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllConditionNames());
        }
        return result;
    }

    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> result = new HashSet<String>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllConditionValues(conditionName));
        }
        return result;
    }

    public Values<T> getValues(final ConditionSet conditionSet) {
        int cardinality = getMaxCardinality(conditionSet);
        List<ValueSet<T>> values = filterValues(conditionSet, cardinality);
        return new Values<T>(values);
    }

    private int getMaxCardinality(final ConditionSet conditionSet) {
        int result = Integer.MIN_VALUE;
        for (ValueSet<T> valueSet : _valueSets) {
            int cardinality = valueSet.matchCardinality(conditionSet);
            result = Math.max(result, cardinality);
        }
        return result;
    }

    private List<ValueSet<T>> filterValues(final ConditionSet conditionSet, final int cardinality) {
        List<ValueSet<T>> values = new ArrayList<ValueSet<T>>();
        if (cardinality >= 0) {
            for (ValueSet<T> valueSet : _valueSets) {
                if (valueSet.matchCardinality(conditionSet) == cardinality) {
                    values.add(valueSet);
                }
            }
        }
        return values;
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
