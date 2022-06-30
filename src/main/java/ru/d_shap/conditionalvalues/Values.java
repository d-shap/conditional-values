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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
 * @param <T> generic type for the value.
 *
 * @author Dmitry Shapovalov
 */
public final class Values<T> {

    private final Comparator<T> _comparator;

    private final List<ValueSet<T>> _valueSets;

    private final Set<String> _ids;

    private final List<T> _values;

    private final Set<T> _uniqueValues;

    private final List<T> _allValues;

    private final Set<T> _allUniqueValues;

    Values(final Comparator<T> comparator, final List<ValueSet<T>> valueSets, final List<T> allValues) {
        super();
        _comparator = comparator;
        _valueSets = createValueSets(valueSets);
        _ids = createIds();
        _values = createValues();
        _uniqueValues = createSet(_values);
        _allValues = createAllValues(allValues);
        _allUniqueValues = createSet(_allValues);
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

    private Set<String> createIds() {
        Set<String> result = new TreeSet<>();
        for (ValueSet<T> valueSet : _valueSets) {
            String id = valueSet.getId();
            if (id != null) {
                result.add(id);
            }
        }
        return Collections.unmodifiableSet(result);
    }

    private List<T> createValues() {
        List<T> result = new ArrayList<>();
        for (ValueSet<T> valueSet : _valueSets) {
            result.addAll(valueSet.getValues());
        }
        if (_comparator != null) {
            Collections.sort(result, _comparator);
        }
        return Collections.unmodifiableList(result);
    }

    private List<T> createAllValues(final List<T> allValues) {
        List<T> result = new ArrayList<>();
        if (allValues != null) {
            for (T value : allValues) {
                if (value != null) {
                    result.add(value);
                }
            }
        }
        if (_comparator != null) {
            Collections.sort(result, _comparator);
        }
        return Collections.unmodifiableList(result);
    }

    private Set<T> createSet(final List<T> values) {
        Set<T> result;
        if (_comparator == null) {
            result = new HashSet<>();
        } else {
            result = new TreeSet<>(_comparator);
        }
        result.addAll(values);
        return result;
    }

    /**
     * Get the IDs of the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return the IDs of the {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     */
    public Set<String> getIds() {
        return _ids;
    }

    /**
     * Check if the result contains no values.
     *
     * @return true, if the result contains no values.
     */
    public boolean isEmpty() {
        return _uniqueValues.isEmpty();
    }

    /**
     * Check if the result contains the specified value.
     *
     * @param value the specified value.
     *
     * @return true, if the result contains the specified value.
     */
    public boolean contains(final T value) {
        return _uniqueValues.contains(value);
    }

    /**
     * Check if the result does not contain the specified value.
     *
     * @param value the specified value.
     *
     * @return true, if the result does not contain the specified value.
     */
    public boolean doesNotContain(final T value) {
        return _allUniqueValues.contains(value) && !_uniqueValues.contains(value);
    }

    /**
     * Check if all values contain the specified value.
     *
     * @param value the specified value.
     *
     * @return true, if all values contain the specified value.
     */
    public boolean allValuesContain(final T value) {
        return _allUniqueValues.contains(value);
    }

    /**
     * Check if all values does not contain the specified value.
     *
     * @param value the specified value.
     *
     * @return true, if all values does not contain the specified value.
     */
    public boolean allValuesDoesNotContain(final T value) {
        return !_allUniqueValues.contains(value);
    }

    /**
     * Get the result values.
     *
     * @return the result values.
     */
    public List<T> getValues() {
        return _values;
    }

    /**
     * Get the unique result values.
     *
     * @return the unique result values.
     */
    public Set<T> getUniqueValues() {
        return _uniqueValues;
    }

    /**
     * Get all values.
     *
     * @return all values.
     */
    public List<T> getAllValues() {
        return _allValues;
    }

    /**
     * Get all unique values.
     *
     * @return all unique values.
     */
    public Set<T> getAllUniqueValues() {
        return _allUniqueValues;
    }

    /**
     * Perform the specified action on each value.
     *
     * @param action the specified action.
     *
     * @return current object for the method chaining.
     */
    public Values<T> performAction(final Action<T> action) {
        if (action != null) {
            for (T value : _values) {
                action.perform(value);
            }
        }
        return this;
    }

    /**
     * Perform the specified actions on each value.
     *
     * @param actions the specified actions.
     *
     * @return current object for the method chaining.
     */
    @SafeVarargs
    public final Values<T> performAction(final Action<T>... actions) {
        if (actions != null) {
            for (T value : _values) {
                for (Action<T> action : actions) {
                    if (action != null) {
                        action.perform(value);
                    }
                }
            }
        }
        return this;
    }

    /**
     * Perform the specified action on each unique value.
     *
     * @param action the specified action.
     *
     * @return current object for the method chaining.
     */
    public Values<T> performUniqueAction(final Action<T> action) {
        if (action != null) {
            for (T value : _uniqueValues) {
                action.perform(value);
            }
        }
        return this;
    }

    /**
     * Perform the specified actions on each unique value.
     *
     * @param actions the specified actions.
     *
     * @return current object for the method chaining.
     */
    @SafeVarargs
    public final Values<T> performUniqueAction(final Action<T>... actions) {
        if (actions != null) {
            for (T value : _uniqueValues) {
                for (Action<T> action : actions) {
                    if (action != null) {
                        action.perform(value);
                    }
                }
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
