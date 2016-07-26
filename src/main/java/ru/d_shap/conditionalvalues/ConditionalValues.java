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

/**
 * Conditional values.
 *
 * @param <T> Value type.
 * @author Dmitry Shapovalov
 */
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

    /**
     * Creates ValueSetBuilder object.
     *
     * @param <T> value type.
     * @return created object.
     */
    public static <T> ValueSetBuilder<T> createValueSetBuilder() {
        return new ValueSetBuilder<T>();
    }

    /**
     * Creates ConditionSetBuilder object.
     *
     * @return created object.
     */
    public static ConditionSetBuilder createConditionSetBuilder() {
        return new ConditionSetBuilder();
    }

    /**
     * Create ConditionalValues object.
     *
     * @param valueSets value sets for object.
     * @param <T>       value type.
     * @return created object.
     */
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

    /**
     * Get all condition names.
     *
     * @return all condition names.
     */
    public Set<String> getAllConditionNames() {
        Set<String> result = new HashSet<String>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllConditionNames());
        }
        return result;
    }

    /**
     * Get all values for specified condition name.
     *
     * @param conditionName condition name.
     * @return all values.
     */
    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> result = new HashSet<String>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllConditionValues(conditionName));
        }
        return result;
    }

    /**
     * Create Values object for specified conditions.
     *
     * @param conditionSet runtime conditions.
     * @return created Values object.
     */
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
