///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Conditional values.
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Object holds {@link ru.d_shap.conditionalvalues.ValueSet} objects and performs lookup for the best matching
 * {@link ru.d_shap.conditionalvalues.ValueSet} objects based on specified
 * {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 *
 * @param <T> value type of {@link ru.d_shap.conditionalvalues.ValueSet} object.
 * @author Dmitry Shapovalov
 */
public final class ConditionalValues<T> {

    private final List<ValueSet<T>> _valueSets;

    private final Set<ValueSetUniqueCondition> _allValueSetUniqueConditions;

    private ConditionalValues(final List<ValueSet<T>> valueSets) {
        super();
        List<ValueSet<T>> list = new ArrayList<>();
        if (valueSets != null) {
            for (ValueSet<T> valueSet : valueSets) {
                if (valueSet != null) {
                    list.add(valueSet);
                }
            }
        }
        _valueSets = Collections.unmodifiableList(list);
        Set<ValueSetUniqueCondition> allValueSetUniqueConditions = getValueSetUniqueConditions(_valueSets);
        _allValueSetUniqueConditions = Collections.unmodifiableSet(allValueSetUniqueConditions);
    }

    private Set<ValueSetUniqueCondition> getValueSetUniqueConditions(final List<ValueSet<T>> valueSets) {
        Set<ValueSetUniqueCondition> allValueSetUniqueConditions = new HashSet<>();
        for (ValueSet<T> valueSet : valueSets) {
            List<ValueSetUniqueCondition> valueSetUniqueConditions = valueSet.getValueSetUniqueConditions();
            for (ValueSetUniqueCondition valueSetUniqueCondition : valueSetUniqueConditions) {
                if (!allValueSetUniqueConditions.add(valueSetUniqueCondition)) {
                    throw new DuplicateValueSetException(valueSet);
                }
            }
        }
        return allValueSetUniqueConditions;
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ValueSetBuilder} object.
     *
     * @param <T> value type of {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return created object.
     */
    public static <T> ValueSetBuilder<T> createValueSetBuilder() {
        return new ValueSetBuilder<>();
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionSetBuilder} object.
     *
     * @return created object.
     */
    public static ConditionSetBuilder createConditionSetBuilder() {
        return new ConditionSetBuilder();
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param valueSets all value sets, used for lookup.
     * @param <T>       value type of {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return created object.
     */
    public static <T> ConditionalValues<T> createConditionalValues(final List<ValueSet<T>> valueSets) {
        return new ConditionalValues<>(valueSets);
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param valueSets all value sets, used for lookup.
     * @param <T>       value type of {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return created object.
     */
    @SafeVarargs
    public static <T> ConditionalValues<T> createConditionalValues(final ValueSet<T>... valueSets) {
        if (valueSets == null) {
            return new ConditionalValues<>(null);
        } else {
            List<ValueSet<T>> valueSetsList = Arrays.asList(valueSets);
            return new ConditionalValues<>(valueSetsList);
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
        return result;
    }

    /**
     * Get all condition values for specified condition name, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @param conditionName condition name.
     * @return all condition values for specified condition name.
     */
    public Set<String> getAllConditionValues(final String conditionName) {
        Set<String> result = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getAllConditionValues(conditionName));
        }
        return result;
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
     * Performs lookup for the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @param conditionSet conditions, used to lookup.
     * @return the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     */
    public Values<T> getValues(final ConditionSet conditionSet) {
        List<ValueSet<T>> matchingValueSets = getMatchingValueSets(conditionSet);
        if (!matchingValueSets.isEmpty()) {
            removeLessSpecificValueSets(matchingValueSets);
        }
        return new Values<>(matchingValueSets);
    }

    private List<ValueSet<T>> getMatchingValueSets(final ConditionSet conditionSet) {
        List<ValueSet<T>> matchingValueSets = new ArrayList<>();
        if (conditionSet == null) {
            return matchingValueSets;
        }
        for (ValueSet<T> valueSet : _valueSets) {
            if (valueSet.isMatchConditions(conditionSet)) {
                matchingValueSets.add(valueSet);
            }
        }
        return matchingValueSets;
    }

    private void removeLessSpecificValueSets(final List<ValueSet<T>> valueSets) {
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
