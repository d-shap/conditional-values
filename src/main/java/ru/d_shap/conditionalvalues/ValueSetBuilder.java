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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Builder class is used to create {@link ru.d_shap.conditionalvalues.ValueSet} objects.
 * </p>
 * <p>
 * Objects of this class are reusable. After calling the {@link #build()} or {@link #build(boolean)}
 * methods this object can be used to create another {@link ru.d_shap.conditionalvalues.ValueSet} object.
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
 * valueSetBuilder.addCondition("state", 1)
 *                .addCondition("state", 2)
 *                .addCondition("state", 3);
 * ValueSet<String> valueSet = valueSetBuilder.build();
 * }</pre>
 * <p>
 * means (type = someType AND (state = 1 OR state = 2 OR state = 3)).
 * </p>
 * <p>
 * The internal presentation of values is {@code Set<T>}, so no value duplicates are allowed.
 * </p>
 *
 * @param <T> generic value type.
 * @author Dmitry Shapovalov
 */
public final class ValueSetBuilder<T> {

    private final Map<String, Set<String>> _conditions;

    private final Set<T> _values;

    ValueSetBuilder() {
        super();
        _conditions = new HashMap<>();
        _values = new HashSet<>();
    }

    /**
     * Add condition to the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final Object value) {
        if (value != null) {
            doAddCondition(name, value.toString());
        }
        return this;
    }

    /**
     * Add all conditions of the specified {@link ru.d_shap.conditionalvalues.ValueSet} object to the set.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addConditions(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<String> conditionNames = valueSet.getAllConditionNames();
            for (String conditionName : conditionNames) {
                Set<String> conditionValues = valueSet.getAllConditionValues(conditionName);
                for (String conditionValue : conditionValues) {
                    doAddCondition(conditionName, conditionValue);
                }
            }
        }
        return this;
    }

    private void doAddCondition(final String name, final String value) {
        if (name != null && value != null) {
            Set<String> conditionValues = _conditions.get(name);
            if (conditionValues == null) {
                conditionValues = new HashSet<>();
                _conditions.put(name, conditionValues);
            }
            conditionValues.add(value);
        }
    }

    /**
     * Remove condition from the set.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
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
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final Object value) {
        if (value != null) {
            doRemoveCondition(name, value.toString());
        }
        return this;
    }

    /**
     * Remove all conditions with the specified condition name from the set.
     *
     * @param name the specified condition name.
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeConditions(final String name) {
        _conditions.remove(name);
        return this;
    }

    /**
     * Remove all conditions of the specified {@link ru.d_shap.conditionalvalues.ValueSet} object from the set.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeConditions(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<String> conditionNames = valueSet.getAllConditionNames();
            for (String conditionName : conditionNames) {
                Set<String> conditionValues = valueSet.getAllConditionValues(conditionName);
                for (String conditionValue : conditionValues) {
                    doRemoveCondition(conditionName, conditionValue);
                }
            }
        }
        return this;
    }

    private void doRemoveCondition(final String name, final String value) {
        Set<String> conditionValues = _conditions.get(name);
        if (conditionValues != null) {
            conditionValues.remove(value);
            if (conditionValues.isEmpty()) {
                _conditions.remove(name);
            }
        }
    }

    /**
     * Clear all conditions.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> clearConditions() {
        _conditions.clear();
        return this;
    }

    /**
     * Add value to the set.
     *
     * @param value value to add.
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addValue(final T value) {
        if (value != null) {
            _values.add(value);
        }
        return this;
    }

    /**
     * Add all values to the set.
     *
     * @param values values to add.
     * @return current object for the method chaining.
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
     * Add all values of the specified {@link ru.d_shap.conditionalvalues.ValueSet} object to the set.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addValues(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<T> values = valueSet.getAllValues();
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
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeValue(final T value) {
        _values.remove(value);
        return this;
    }

    /**
     * Remove all values from the set.
     *
     * @param values values to remove.
     * @return current object for the method chaining.
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
     * Remove all values of the specified {@link ru.d_shap.conditionalvalues.ValueSet} object from the set.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeValues(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<T> values = valueSet.getAllValues();
            for (T value : values) {
                removeValue(value);
            }
        }
        return this;
    }

    /**
     * Clear all values.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> clearValues() {
        _values.clear();
        return this;
    }

    /**
     * Clear all conditions and values.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> clear() {
        clearConditions();
        clearValues();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ValueSet} object and clear all conditions and values.
     *
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object, populated with conditions and values, added to this builder.
     */
    public ValueSet<T> build() {
        return build(true);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param clear whether to clear all conditions and values or not.
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object, populated with conditions and values, added to this builder.
     */
    public ValueSet<T> build(final boolean clear) {
        ValueSet<T> valueSet = new ValueSet<>(_conditions, _values);
        if (clear) {
            clear();
        }
        return valueSet;
    }

}
