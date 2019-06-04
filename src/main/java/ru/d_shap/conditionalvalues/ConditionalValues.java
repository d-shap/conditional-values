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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Object holds {@link ru.d_shap.conditionalvalues.ValueSet} objects and performs lookup for the best
 * matching {@link ru.d_shap.conditionalvalues.ValueSet} objects based on the specified
 * {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 *
 * @param <T> generic value type.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValues<T> {

    public static final Predicate EQUALS_PREDICATE = new EqualsPredicate();

    public static final Predicate EQUALS_IGNORE_CASE_PREDICATE = new EqualsIgnoreCasePredicate();

    private final Predicate _predicate;

    private final List<ValueSet<T>> _valueSets;

    private final Set<ValueSetUniqueCondition> _allValueSetUniqueConditions;

    private final Set<T> _allValues;

    @SafeVarargs
    private ConditionalValues(final Predicate predicate, final ValueSet<T>... valueSets) {
        super();
        if (predicate == null) {
            _predicate = EQUALS_PREDICATE;
        } else {
            _predicate = predicate;
        }
        List<ValueSet<T>> list = new ArrayList<>();
        if (valueSets != null) {
            for (ValueSet<T> valueSet : valueSets) {
                if (valueSet != null) {
                    list.add(valueSet);
                }
            }
        }
        _valueSets = Collections.unmodifiableList(list);
        Set<ValueSetUniqueCondition> allValueSetUniqueConditions = createValueSetUniqueConditions();
        _allValueSetUniqueConditions = Collections.unmodifiableSet(allValueSetUniqueConditions);
        Set<T> allValues = createAllValues();
        _allValues = Collections.unmodifiableSet(allValues);
    }

    private Set<ValueSetUniqueCondition> createValueSetUniqueConditions() {
        Map<ValueSetUniqueCondition, Set<T>> valueSetUniqueConditionMap = new HashMap<>();
        Set<ValueSetUniqueCondition> valueSetUniqueConditionSet = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
            Set<T> values = valueSet.getValues();
            for (ValueSetUniqueCondition valueSetUniqueCondition : valueSetUniqueConditions) {
                Set<T> oldValues = valueSetUniqueConditionMap.get(valueSetUniqueCondition);
                if (oldValues == null) {
                    valueSetUniqueConditionMap.put(valueSetUniqueCondition, values);
                    valueSetUniqueConditionSet.add(valueSetUniqueCondition);
                } else if (!oldValues.containsAll(values) || !values.containsAll(oldValues)) {
                    throw new DuplicateValueSetException(valueSet);
                }
            }
        }
        return valueSetUniqueConditionSet;
    }

    private Set<T> createAllValues() {
        Set<T> allValues = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            allValues.addAll(valueSet.getValues());
        }
        return allValues;
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param valueSets all value sets, used for lookup.
     * @param <T>       generic value type.
     *
     * @return created object.
     */
    @SafeVarargs
    public static <T> ConditionalValues<T> createConditionalValues(final ValueSet<T>... valueSets) {
        return createConditionalValues(null, valueSets);
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param predicate predicate to match values from the {@link ru.d_shap.conditionalvalues.ValueSet}
     *                  and the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     * @param valueSets all value sets, used for lookup.
     * @param <T>       generic value type.
     *
     * @return created object.
     */
    @SafeVarargs
    public static <T> ConditionalValues<T> createConditionalValues(final Predicate predicate, final ValueSet<T>... valueSets) {
        return new ConditionalValues<>(predicate, valueSets);
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param valueSets all value sets, used for lookup.
     * @param <T>       generic value type.
     *
     * @return created object.
     */
    public static <T> ConditionalValues<T> createConditionalValues(final Collection<ValueSet<T>> valueSets) {
        return createConditionalValues(null, valueSets);
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param predicate predicate to match values from the {@link ru.d_shap.conditionalvalues.ValueSet}
     *                  and the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     * @param valueSets all value sets, used for lookup.
     * @param <T>       generic value type.
     *
     * @return created object.
     */
    @SuppressWarnings("unchecked")
    public static <T> ConditionalValues<T> createConditionalValues(final Predicate predicate, final Collection<ValueSet<T>> valueSets) {
        if (valueSets == null) {
            return new ConditionalValues<>(predicate);
        } else {
            ValueSet<T>[] array = (ValueSet<T>[]) new ValueSet<?>[valueSets.size()];
            valueSets.toArray(array);
            return new ConditionalValues<>(predicate, array);
        }
    }

    /**
     * Get all condition names, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return all condition names.
     */
    public Set<String> getAllConditionNames() {
        Set<String> result = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllConditionNames());
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get all condition values for the specified condition name, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @param conditionName the specified condition name.
     *
     * @return all condition values for the specified condition name.
     */
    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> result = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            Set<String> allConditionValues = valueSet.getAllConditionValues(conditionName);
            result.addAll(allConditionValues);
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get all single unique combinations of conditions, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return all unique combinations of conditions.
     */
    public Set<ValueSetUniqueCondition> getAllValueSetUniqueConditions() {
        return _allValueSetUniqueConditions;
    }

    /**
     * Get all values, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return all values.
     */
    public Set<T> getAllValues() {
        return _allValues;
    }

    /**
     * Performs lookup for the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @param conditionSet conditions, used for lookup.
     *
     * @return the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     */
    public Values<T> lookup(final ConditionSet conditionSet) {
        Set<ValueSet<T>> matchingValueSets = getMatchingValueSets(conditionSet);
        removeLessSpecificValueSets(matchingValueSets);
        return new Values<>(matchingValueSets, _allValues);
    }

    /**
     * Performs lookup for the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects, and perform the specified action for each value.
     *
     * @param conditionSet conditions, used for lookup.
     * @param action       the specified action.
     */
    public void lookup(final ConditionSet conditionSet, final Action<T> action) {
        Values<T> values = lookup(conditionSet);
        values.performAction(action);
    }

    private Set<ValueSet<T>> getMatchingValueSets(final ConditionSet conditionSet) {
        if (conditionSet == null) {
            return Collections.emptySet();
        } else {
            Set<ValueSet<T>> matchingValueSets = new HashSet<>();
            for (ValueSet<T> valueSet : _valueSets) {
                if (valueSet.isMatchConditions(conditionSet, _predicate)) {
                    matchingValueSets.add(valueSet);
                }
            }
            return matchingValueSets;
        }
    }

    private void removeLessSpecificValueSets(final Set<ValueSet<T>> valueSets) {
        Iterator<ValueSet<T>> valueSetIterator = valueSets.iterator();
        while (valueSetIterator.hasNext()) {
            ValueSet<T> valueSet = valueSetIterator.next();
            for (ValueSet<T> checkValueSet : valueSets) {
                if (checkValueSet.isMoreSpecificValueSet(valueSet)) {
                    valueSetIterator.remove();
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

    /**
     * Predicate to check if the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is case-sensitive equal to the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @author Dmitry Shapovalov
     */
    private static final class EqualsPredicate implements Predicate {

        EqualsPredicate() {
            super();
        }

        @Override
        public boolean evaluate(final String conditionName, final String conditionValue, final String checkValue) {
            return conditionValue.equals(checkValue);
        }

    }

    /**
     * Predicate to check if the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is case-insensitive equal to the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @author Dmitry Shapovalov
     */
    private static final class EqualsIgnoreCasePredicate implements Predicate {

        EqualsIgnoreCasePredicate() {
            super();
        }

        @Override
        public boolean evaluate(final String conditionName, final String conditionValue, final String checkValue) {
            return conditionValue.equalsIgnoreCase(checkValue);
        }

    }

}
