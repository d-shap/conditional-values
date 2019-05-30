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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * The lookup result of the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects.
 * </p>
 * <p>
 * If {@link ru.d_shap.conditionalvalues.Values} object created from several matchig
 * {@link ru.d_shap.conditionalvalues.ValueSet} objects, then values are obtained from every
 * one {@link ru.d_shap.conditionalvalues.ValueSet} object.
 * </p>
 *
 * @param <T> generic value type.
 *
 * @author Dmitry Shapovalov
 */
public final class Values<T> {

    private final Set<ValueSet<T>> _valueSets;

    private final Set<T> _values;

    private final Set<T> _allValues;

    Values(final Set<ValueSet<T>> valueSets, final Set<T> allValues) {
        super();
        _valueSets = Collections.unmodifiableSet(new HashSet<>(valueSets));
        Set<T> set = new HashSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            set.addAll(valueSet.getAllValues());
        }
        _values = Collections.unmodifiableSet(set);
        _allValues = Collections.unmodifiableSet(allValues);
    }

    /**
     * Check if this object contains no value.
     *
     * @return true, if this object contains no value.
     */
    public boolean isEmpty() {
        return _values.isEmpty();
    }

    /**
     * Check if this object contains the specified value.
     *
     * @param value the specified value.
     *
     * @return true, if this object contains the specified value.
     */
    public boolean contains(final T value) {
        return _values.contains(value);
    }

    /**
     * Check if all values contain the specified value.
     *
     * @param value the specified value.
     *
     * @return true, if all values contain the specified value.
     */
    public boolean allValuesContains(final T value) {
        return _allValues.contains(value);
    }

    /**
     * Get values contained in this object.
     *
     * @return all values.
     */
    public Set<T> getValues() {
        return _values;
    }

    /**
     * Get all values.
     *
     * @return all values.
     */
    public Set<T> getAllValues() {
        return _allValues;
    }

    /**
     * Perform the specified action for each value in this object.
     *
     * @param action the specified action.
     */
    public void performAction(final Action<T> action) {
        if (action != null) {
            for (T value : _values) {
                action.perform(value);
            }
        }
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
