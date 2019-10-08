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
import java.util.List;

import ru.d_shap.conditionalvalues.misc.ComparableComparator;
import ru.d_shap.conditionalvalues.misc.EqualsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.misc.EqualsPredicate;

/**
 * <p>
 * Builder class to create {@link ru.d_shap.conditionalvalues.ConditionalValues} objects.
 * </p>
 * <p>
 * Objects of this class are reusable. After calling the {@link #build()} or {@link #build(boolean)}
 * methods this object can be used to create another {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
 * </p>
 * <p>
 * Builder instance is NOT thread-safe.
 * </p>
 *
 * @param <T> generic value type.
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionalValuesBuilder<T> {

    private Predicate _predicate;

    private Comparator<T> _comparator;

    private final List<ValueSet<T>> _valueSets;

    private ConditionalValuesBuilder() {
        super();
        _predicate = null;
        _comparator = null;
        _valueSets = new ArrayList<>();
    }

    /**
     * Create new builder instance.
     *
     * @param <T> generic value type.
     *
     * @return created builder instance.
     */
    public static <T> ConditionalValuesBuilder<T> newInstance() {
        return new ConditionalValuesBuilder<>();
    }

    /**
     * Set the predicate to match the {@link ru.d_shap.conditionalvalues.ValueSet} objects against
     * the conditions in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
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
     * Set the predicate to match the {@link ru.d_shap.conditionalvalues.ValueSet} objects against
     * the conditions in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     * Predicate checks if the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is case-sensitive equal to the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setEqualsPredicate() {
        _predicate = new EqualsPredicate();
        return this;
    }

    /**
     * Set the predicate to match the {@link ru.d_shap.conditionalvalues.ValueSet} objects against
     * the conditions in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     * Predicate checks if the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object
     * is case-insensitive equal to the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> setEqualsIgnoreCasePredicate() {
        _predicate = new EqualsIgnoreCasePredicate();
        return this;
    }

    /**
     * Set the comparator to sort all values in the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @param comparator the comparator to sort all values.
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
     * Values should implement the {@link java.lang.Comparable} interface.
     *
     * @return current object for the method chaining.
     */
    @SuppressWarnings("unchecked")
    public ConditionalValuesBuilder<T> setNaturalOrderComparator() {
        _comparator = (Comparator<T>) new ComparableComparator<>();
        return this;
    }

    /**
     * Set the comparator to sort all values in the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     * Values are sorted in the order reverse to the natural order.
     * Values should implement the {@link java.lang.Comparable} interface.
     *
     * @return current object for the method chaining.
     */
    @SuppressWarnings("unchecked")
    public ConditionalValuesBuilder<T> setReverseOrderComparator() {
        _comparator = (Comparator<T>) Collections.reverseOrder(new ComparableComparator<>());
        return this;
    }

    /**
     * Add the {@link ru.d_shap.conditionalvalues.ValueSet} object to this builder.
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
     * Clear this builder.
     *
     * @return current object for the method chaining.
     */
    public ConditionalValuesBuilder<T> clear() {
        _predicate = null;
        _comparator = null;
        _valueSets.clear();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionalValues} object and clear this builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionalValues} object, populated with values, added to this builder.
     */
    public ConditionalValues<T> build() {
        return build(true);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
     *
     * @param clear true to clear this builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionalValues} object, populated with values, added to this builder.
     */
    public ConditionalValues<T> build(final boolean clear) {
        ConditionalValues<T> conditionalValues = new ConditionalValues<>(_predicate, _comparator, _valueSets);
        if (clear) {
            clear();
        }
        return conditionalValues;
    }

}
