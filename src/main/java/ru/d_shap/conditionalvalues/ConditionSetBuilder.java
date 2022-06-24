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
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * Builder class to create {@link ru.d_shap.conditionalvalues.ConditionSet} objects.
 * </p>
 * <p>
 * Objects of this class are reusable. After calling the {@link #build()} or {@link #build(boolean)}
 * methods this object can be used to create another {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 * </p>
 * <p>
 * Builder instance is NOT thread-safe.
 * </p>
 *
 * @author Dmitry Shapovalov
 */
public final class ConditionSetBuilder {

    private final Map<String, Object> _conditions;

    private ConditionSetBuilder() {
        super();
        _conditions = new HashMap<>();
    }

    /**
     * Create new builder instance.
     *
     * @return created builder instance.
     */
    public static ConditionSetBuilder newInstance() {
        return new ConditionSetBuilder();
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final String value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final boolean value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final char value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final int value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final long value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final float value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final double value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add contition to the condition set.
     * An old value is replaced by the new one.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addCondition(final String name, final Object value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add all conditions of the specified {@link ru.d_shap.conditionalvalues.ConditionSet} object to the condition set.
     * Old values are replaced by the new ones.
     *
     * @param conditionSet the specified {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder addConditions(final ConditionSet conditionSet) {
        if (conditionSet != null) {
            Iterator<String> conditionNameIterator = conditionSet.nameIterator();
            while (conditionNameIterator.hasNext()) {
                String conditionName = conditionNameIterator.next();
                Object conditionValue = conditionSet.getValue(conditionName);
                doAddCondition(conditionName, conditionValue);
            }
        }
        return this;
    }

    private void doAddCondition(final String name, final Object value) {
        if (name != null && value != null) {
            _conditions.put(name, value);
        }
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final String value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final boolean value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final char value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final int value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final long value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final float value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final double value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name, final Object value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove contition from the condition set.
     *
     * @param name condition name.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder removeCondition(final String name) {
        _conditions.remove(name);
        return this;
    }

    /**
     * Remove all conditions with the names obtained from the specified
     * {@link ru.d_shap.conditionalvalues.ConditionSet} object from the condition set.
     *
     * @param conditionSet the specified {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return urrent object for the method chaining.
     */
    public ConditionSetBuilder removeConditionNames(final ConditionSet conditionSet) {
        if (conditionSet != null) {
            Iterator<String> conditionNameIterator = conditionSet.nameIterator();
            while (conditionNameIterator.hasNext()) {
                String conditionName = conditionNameIterator.next();
                _conditions.remove(conditionName);
            }
        }
        return this;
    }

    /**
     * Remove all conditions with the names and values obtained from the specified
     * {@link ru.d_shap.conditionalvalues.ConditionSet} object from the condition set.
     *
     * @param conditionSet the specified {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @return urrent object for the method chaining.
     */
    public ConditionSetBuilder removeConditionValues(final ConditionSet conditionSet) {
        if (conditionSet != null) {
            Iterator<String> conditionNameIterator = conditionSet.nameIterator();
            while (conditionNameIterator.hasNext()) {
                String conditionName = conditionNameIterator.next();
                Object conditionValue = conditionSet.getValue(conditionName);
                doRemoveCondition(conditionName, conditionValue);
            }
        }
        return this;
    }

    private void doRemoveCondition(final String name, final Object value) {
        Object oldValue = _conditions.get(name);
        if (oldValue != null && oldValue.equals(value)) {
            _conditions.remove(name);
        }
    }

    /**
     * Clear this builder.
     *
     * @return current object for the method chaining.
     */
    public ConditionSetBuilder clear() {
        _conditions.clear();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionSet} object and clear this builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionSet} object, populated with conditions, added to this builder.
     */
    public ConditionSet build() {
        return build(true);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param clear true to clear this builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ConditionSet} object, populated with conditions, added to this builder.
     */
    public ConditionSet build(final boolean clear) {
        ConditionSet conditionSet = new ConditionSet(_conditions);
        if (clear) {
            clear();
        }
        return conditionSet;
    }

}
