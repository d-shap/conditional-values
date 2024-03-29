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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.d_shap.conditionalvalues.misc.NaturalOrderComparator;
import ru.d_shap.conditionalvalues.predicate.AllValuesMatchTuplePredicate;
import ru.d_shap.conditionalvalues.predicate.AnyValueMatchesTuplePredicate;
import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringContainsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.predicate.StringContainsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringEqualsIgnoreCasePredicate;

/**
 * <p>
 * Builder to create {@link ru.d_shap.conditionalvalues.ConditionalValues} objects.
 * </p>
 * <p>
 * Objects of this class are reusable. After calling the {@link #build()} or {@link #build(boolean)}
 * methods this object can be used to create another object.
 * </p>
 *
 * @param <T> generic type for the value.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValuesBuilder<T> {

    private TuplePredicate _tuplePredicate;

    private Predicate _predicate;

    private final Map<String, Predicate> _predicates;

    private Comparator<T> _comparator;

    private final List<ValueSet<T>> _valueSets;

    private ConditionalValuesBuilder() {
        super();
        _tuplePredicate = null;
        _predicate = null;
        _predicates = new HashMap<>();
        _comparator = null;
        _valueSets = new ArrayList<>();
    }

    /**
     * Create new builder instance.
     *
     * @param <T> generic type for the value.
     *
     * @return new builder instance.
     */
    public static <T> ConditionalValuesBuilder<T> newInstance() {
        return new ConditionalValuesBuilder<>();
    }

    /**
     * Set the predicate to match the {@link ru.d_shap.conditionalvalues.ValueSet} object against the conditions
     * in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param tuplePredicate the tuple predicate.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setTuplePredicate(final TuplePredicate tuplePredicate) {
        _tuplePredicate = tuplePredicate;
        return this;
    }

    /**
     * Set the predicate to check if any Object from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * matches to the Object from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
     * according to the {@link ru.d_shap.conditionalvalues.Predicate} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setAnyValueMatchesTuplePredicate() {
        _tuplePredicate = new AnyValueMatchesTuplePredicate();
        return this;
    }

    /**
     * Set the predicate to check if all Objects from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * matches to the Object from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
     * according to the {@link ru.d_shap.conditionalvalues.Predicate} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setAllValuesMatchTuplePredicate() {
        _tuplePredicate = new AllValuesMatchTuplePredicate();
        return this;
    }

    /**
     * Set the predicate to match the {@link ru.d_shap.conditionalvalues.ValueSet} object against the conditions
     * in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param predicate the predicate.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setPredicate(final Predicate predicate) {
        _predicate = predicate;
        return this;
    }

    /**
     * Set the predicate to match the {@link ru.d_shap.conditionalvalues.ValueSet} object against the conditions
     * in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param conditionName the condition name.
     * @param predicate     the predicate.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setPredicate(final String conditionName, final Predicate predicate) {
        if (conditionName != null) {
            if (predicate == null) {
                _predicates.remove(conditionName);
            } else {
                _predicates.put(conditionName, predicate);
            }
        }
        return this;
    }

    /**
     * Set the predicate to check if the Object from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is equal to the Object from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setEqualsPredicate() {
        _predicate = new EqualsPredicate();
        return this;
    }

    /**
     * Set the predicate to check if the Object from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is equal to the Object from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param conditionName the condition name.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setEqualsPredicate(final String conditionName) {
        if (conditionName != null) {
            Predicate predicate = new EqualsPredicate();
            _predicates.put(conditionName, predicate);
        }
        return this;
    }

    /**
     * Set the predicate to check if the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is case-insensitively equal to the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setStringEqualsIgnoreCasePredicate() {
        _predicate = new StringEqualsIgnoreCasePredicate();
        return this;
    }

    /**
     * Set the predicate to check if the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is case-insensitively equal to the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param conditionName the condition name.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setStringEqualsIgnoreCasePredicate(final String conditionName) {
        if (conditionName != null) {
            Predicate predicate = new StringEqualsIgnoreCasePredicate();
            _predicates.put(conditionName, predicate);
        }
        return this;
    }

    /**
     * Set the predicate to check if the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
     * contains the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setStringContainsPredicate() {
        _predicate = new StringContainsPredicate();
        return this;
    }

    /**
     * Set the predicate to check if the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
     * contains the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param conditionName the condition name.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setStringContainsPredicate(final String conditionName) {
        if (conditionName != null) {
            Predicate predicate = new StringContainsPredicate();
            _predicates.put(conditionName, predicate);
        }
        return this;
    }

    /**
     * Set the predicate to check if the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
     * case-insensitively contains the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setStringContainsIgnoreCasePredicate() {
        _predicate = new StringContainsIgnoreCasePredicate();
        return this;
    }

    /**
     * Set the predicate to check if the String from the {@link ru.d_shap.conditionalvalues.ConditionSet} object
     * case-insensitively contains the String from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param conditionName the condition name.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setStringContainsIgnoreCasePredicate(final String conditionName) {
        if (conditionName != null) {
            Predicate predicate = new StringContainsIgnoreCasePredicate();
            _predicates.put(conditionName, predicate);
        }
        return this;
    }

    /**
     * Remove the predicate.
     *
     * @param conditionName the condition name.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> removePredicate(final String conditionName) {
        _predicates.remove(conditionName);
        return this;
    }

    /**
     * Clear all predicates.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> clearPredicates() {
        _predicates.clear();
        return this;
    }

    /**
     * Set the comparator to sort all values in the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @param comparator the comparator.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setComparator(final Comparator<T> comparator) {
        _comparator = comparator;
        return this;
    }

    /**
     * Set the comparator to sort all values in the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     * Values are sorted in natural order.
     *
     * @return current object for the method chaining.
     */
    @SuppressWarnings("unchecked")
    public ConditionalValuesBuilder<T> setNaturalOrderComparator() {
        _comparator = (Comparator<T>) new NaturalOrderComparator<>(false);
        return this;
    }

    /**
     * Set the comparator to sort all values in the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     * Values are sorted in the order reverse to the natural order.
     *
     * @return current object for the method chaining.
     */
    @SuppressWarnings("unchecked")
    public ConditionalValuesBuilder<T> setReverseOrderComparator() {
        _comparator = (Comparator<T>) Collections.reverseOrder(new NaturalOrderComparator<>(true));
        return this;
    }

    /**
     * Add the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSet the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> addValueSet(final ValueSet<T> valueSet) {
        _valueSets.add(valueSet);
        return this;
    }

    /**
     * Clear all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> clearValueSets() {
        _valueSets.clear();
        return this;
    }

    /**
     * Clear the builder.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> clear() {
        _tuplePredicate = null;
        _predicate = null;
        clearPredicates();
        _comparator = null;
        clearValueSets();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionalValues} object and clear the builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     */
    public ConditionalValues<T> build() {
        return build(true);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param clear true to clear the builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     */
    public ConditionalValues<T> build(final boolean clear) {
        ConditionalValues<T> conditionalValues = new ConditionalValues<>(_tuplePredicate, _predicate, _predicates, _comparator, _valueSets);
        if (clear) {
            clear();
        }
        return conditionalValues;
    }

}
