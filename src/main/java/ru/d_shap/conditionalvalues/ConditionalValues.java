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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;

/**
 * Object holds {@link ru.d_shap.conditionalvalues.ValueSet} objects and performs lookup for the best
 * matching {@link ru.d_shap.conditionalvalues.ValueSet} objects based on the specified
 * {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 *
 * @param <T> generic type for the value.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValues<T> {

    private final Predicate _predicate;

    private final Comparator<T> _comparator;

    private final List<ValueSet<T>> _valueSets;

    private final List<ValueSetUniqueCondition> _allValueSetUniqueConditions;

    private final Set<T> _allValues;

    ConditionalValues(final Predicate predicate, final Comparator<T> comparator, final List<ValueSet<T>> valueSets) {
        super();
        _predicate = createPredicate(predicate);
        _comparator = comparator;
        _valueSets = createValueSets(valueSets);
        _allValueSetUniqueConditions = createAllValueSetUniqueConditions();
        _allValues = createAllValues();
    }

    private Predicate createPredicate(final Predicate predicate) {
        if (predicate == null) {
            return new EqualsPredicate();
        } else {
            return predicate;
        }
    }

    private List<ValueSet<T>> createValueSets(final List<ValueSet<T>> valueSets) {
        List<ValueSet<T>> result = new ArrayList<>();
        if (valueSets != null) {
            for (ValueSet<T> valueSet : valueSets) {
                if (valueSet != null) {
                    result.add(valueSet);
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    private List<ValueSetUniqueCondition> createAllValueSetUniqueConditions() {
        List<ValueSetUniqueCondition> result = new ArrayList<>();
        for (ValueSet<T> valueSet : _valueSets) {
            List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
            result.addAll(valueSetUniqueConditions);
        }
        return Collections.unmodifiableList(result);
    }

    private Set<T> createAllValues() {
        Set<T> result = Values.createSet(_comparator);
        for (ValueSet<T> valueSet : _valueSets) {
            Set<T> values = valueSet.getValues();
            result.addAll(values);
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get all condition names, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return all condition names.
     */
    public Set<String> getAllConditionNames() {
        Set<String> result = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            Set<String> conditionNames = valueSet.getAllConditionNames();
            result.addAll(conditionNames);
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get all condition values for the specified condition name, defined in all{@link ru.d_shap.conditionalvalues.ValueSet} objects.
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
     * Get all unique combinations of single conditions, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return all unique combinations of single conditions.
     */
    public List<ValueSetUniqueCondition> getAllValueSetUniqueConditions() {
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
        Set<ValueSet<T>> valueSets = getMatchingValueSets(conditionSet);
        removeLessSpecificValueSets(valueSets);
        return new Values<>(_comparator, valueSets, _allValues);
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
        Set<ValueSet<T>> result = new HashSet<>();
        if (conditionSet != null) {
            for (ValueSet<T> valueSet : _valueSets) {
                if (valueSet.isMatchConditions(conditionSet, _predicate)) {
                    result.add(valueSet);
                }
            }
        }
        return result;
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

}
