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
import java.util.Map;

/**
 * <p>
 * Builder class used to create {@link ru.d_shap.conditionalvalues.ConditionSet} objects.
 * </p>
 * <p>
 * Object of this class is reusable. After calling the {@link #build()} or the {@link #buildAndClear()} method this
 * object can be used to create another {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 * </p>
 * <p>
 * The internal presentation of conditions is {@code Map<String, String>}.
 * </p>
 * <p>
 * Methods {@link #addCondition(String, boolean)}, {@link #addCondition(String, int)},
 * {@link #addCondition(String, long)}, {@link #addCondition(String, float)},
 * {@link #addCondition(String, double)} and {@link #addCondition(String, Object)}
 * are convenient methods for {@link #addCondition(String, String)}.
 * </p>
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetBuilder {

    private final Map<String, String> _conditions;

    ConditionSetBuilder() {
        super();
        _conditions = new HashMap<>();
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final String value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final boolean value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final int value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final long value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final float value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final double value) {
        doAddCondition(name, String.valueOf(value));
        return this;
    }

    /**
     * Add contition to the set. Replaces old value with the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     * @return current object for chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final Object value) {
        if (value != null) {
            doAddCondition(name, value.toString());
        }
        return this;
    }

    private void doAddCondition(final String name, final String value) {
        if (name != null && value != null) {
            _conditions.put(name, value);
        }
    }

    /**
     * Remove contition from the set.
     *
     * @param name condition name.
     * @return current object for chaining.
     */
    public ConditionSetBuilder removeCondition(final String name) {
        _conditions.remove(name);
        return this;
    }

    /**
     * Clear all conditions.
     *
     * @return current object for chaining.
     */
    public ConditionSetBuilder clear() {
        _conditions.clear();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionSet} object, populated with the values, added to this builder.
     */
    public ConditionSet build() {
        return new ConditionSet(_conditions);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionSet} object and clear all conditions.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionSet} object, populated with the values, added to this builder.
     */
    public ConditionSet buildAndClear() {
        ConditionSet conditionSet = new ConditionSet(_conditions);
        clear();
        return conditionSet;
    }

}
