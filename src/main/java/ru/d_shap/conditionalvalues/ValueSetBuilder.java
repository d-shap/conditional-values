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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Builder class used to create {@link ru.d_shap.conditionalvalues.ValueSet} objects.
 * </p>
 * <p>
 * Object of this class is reusable. After calling the {@link #build()} method this object can be
 * used to create another {@link ru.d_shap.conditionalvalues.ValueSet} object.
 * </p>
 * <p>
 * The internal presentation of conditions is {@code Map<String, Set<String>>}.
 * </p>
 * <p>
 * Conditions with different names are ANDed. Conditions with the same name are ORed.
 * </p>
 * <p>
 * For example:
 * </p>
 * <pre>{@code
 * ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
 * valueSetBuilder.addCondition("type", "someType");
 * valueSetBuilder.addCondition("state", 1);
 * valueSetBuilder.addCondition("state", 2);
 * valueSetBuilder.addCondition("state", 3);
 * ValueSet<String> valueSet = valueSetBuilder.build();
 * }</pre>
 * <p>
 * means type = someType AND (state = 1 OR state = 2 OR state = 3).
 * </p>
 * <p>
 * Methods {@link #addCondition(String, boolean)}, {@link #addCondition(String, int)},
 * {@link #addCondition(String, long)}, {@link #addCondition(String, float)},
 * {@link #addCondition(String, double)} and {@link #addCondition(String, Object)}
 * are convenient methods for {@link #addCondition(String, String)}.
 * </p>
 * <p>
 * Methods {@link #removeCondition(String, boolean)}, {@link #removeCondition(String, int)},
 * {@link #removeCondition(String, long)}, {@link #removeCondition(String, float)},
 * {@link #removeCondition(String, double)} and {@link #removeCondition(String, Object)}
 * are convenient methods for {@link #removeCondition(String, String)}.
 * </p>
 * <p>
 * The internal presentation of values is {@code Set<T>}.
 * </p>
 *
 * @param <T> value type.
 * @author Dmitry Shapovalov
 */
public final class ValueSetBuilder<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<T> _values;

    ValueSetBuilder() {
        super();
        _conditions = new HashMap<String, Set<String>>();
        _values = new HashSet<T>();
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final String value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final boolean value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final int value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final long value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final float value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final double value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final Object value) {
        if (value != null) {
            doAddCondition(name, value.toString());
        }
        return this;
    }

    private void doAddCondition(final String name, final String value) {
        if (name != null && value != null) {
            Set<String> conditionValues = _conditions.get(name);
            if (conditionValues == null) {
                conditionValues = new HashSet<String>();
                _conditions.put(name, conditionValues);
            }
            conditionValues.add(value);
        }
    }

    /**
     * Remove conditions from the set.
     *
     * @param name condition name.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name) {
        _conditions.remove(name);
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final String value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final boolean value) {
        doRemoveCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final int value) {
        doRemoveCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final long value) {
        doRemoveCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final float value) {
        doRemoveCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final double value) {
        doRemoveCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final Object value) {
        if (value != null) {
            doRemoveCondition(name, value.toString());
        }
        return this;
    }

    private void doRemoveCondition(final String name, final String value) {
        if (name != null && value != null) {
            Set<String> conditionValues = _conditions.get(name);
            if (conditionValues != null) {
                conditionValues.remove(value);
                if (conditionValues.isEmpty()) {
                    _conditions.remove(name);
                }
            }
        }
    }

    /**
     * Clear all conditions.
     *
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> clearConditions() {
        _conditions.clear();
        return this;
    }

    /**
     * Add value to the set.
     *
     * @param value value to add.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> addValue(final T value) {
        if (value != null) {
            _values.add(value);
        }
        return this;
    }

    /**
     * Add values to the set.
     *
     * @param values values to add.
     * @return current object for chaining.
     */
    @SafeVarargs
    public final ValueSetBuilder<T> addValue(final T... values) {
        if (values != null) {
            for (T value : values) {
                addValue(value);
            }
        }
        return this;
    }

    /**
     * Remove value from the set.
     *
     * @param value value to remove.
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> removeValue(final T value) {
        _values.remove(value);
        return this;
    }

    /**
     * Remove values from the set.
     *
     * @param values values to remove.
     * @return current object for chaining.
     */
    @SafeVarargs
    public final ValueSetBuilder<T> removeValue(final T... values) {
        if (values != null) {
            for (T value : values) {
                removeValue(value);
            }
        }
        return this;
    }

    /**
     * Clear all values.
     *
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> clearValues() {
        _values.clear();
        return this;
    }

    /**
     * Clear all conditions and values.
     *
     * @return current object for chaining.
     */
    public ValueSetBuilder<T> clear() {
        clearConditions();
        clearValues();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object, populated with the values, added to this builder.
     */
    public ValueSet<T> build() {
        return new ValueSet<T>(_conditions, _values);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ValueSet} object and clear all conditions and values.
     *
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object, populated with the values, added to this builder.
     */
    public ValueSet<T> buildAndClear() {
        ValueSet<T> valueSet = new ValueSet<T>(_conditions, _values);
        clear();
        return valueSet;
    }

}
