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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringContainsIgnoreCasePredicate;
import ru.d_shap.conditionalvalues.predicate.StringContainsPredicate;
import ru.d_shap.conditionalvalues.predicate.StringEqualsIgnoreCasePredicate;

/**
 * <p>
 * Builder to create {@link ru.d_shap.conditionalvalues.ValueSet} objects.
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
public final class ValueSetBuilder<T> {

    private String _id;

    private Predicate _predicate;

    private final Map<String, Predicate> _predicates;

    private final Map<String, Set<Object>> _conditions;

    private final List<T> _values;

    private ValueSetBuilder() {
        super();
        _id = null;
        _predicate = null;
        _predicates = new HashMap<>();
        _conditions = new HashMap<>();
        _values = new ArrayList<>();
    }

    /**
     * Create new builder instance.
     *
     * @param <T> generic type for the value.
     *
     * @return new builder instance.
     */
    public static <T> ValueSetBuilder<T> newInstance() {
        return new ValueSetBuilder<>();
    }

    /**
     * Set the ID.
     *
     * @param id the ID.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> setId(final String id) {
        _id = id;
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
    public ValueSetBuilder<T> setPredicate(final Predicate predicate) {
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
    public ValueSetBuilder<T> setPredicate(final String conditionName, final Predicate predicate) {
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
    public ValueSetBuilder<T> setEqualsPredicate() {
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
    public ValueSetBuilder<T> setEqualsPredicate(final String conditionName) {
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
    public ValueSetBuilder<T> setStringEqualsIgnoreCasePredicate() {
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
    public ValueSetBuilder<T> setStringEqualsIgnoreCasePredicate(final String conditionName) {
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
    public ValueSetBuilder<T> setStringContainsPredicate() {
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
    public ValueSetBuilder<T> setStringContainsPredicate(final String conditionName) {
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
    public ValueSetBuilder<T> setStringContainsIgnoreCasePredicate() {
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
    public ValueSetBuilder<T> setStringContainsIgnoreCasePredicate(final String conditionName) {
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
    public ValueSetBuilder<T> removePredicate(final String conditionName) {
        _predicates.remove(conditionName);
        return this;
    }

    /**
     * Clear all predicates.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> clearPredicates() {
        _predicates.clear();
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final String value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final boolean value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final char value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final int value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final long value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final float value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final double value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addCondition(final String name, final Object value) {
        doAddCondition(name, value);
        return this;
    }

    /**
     * Add all conditions from the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addConditions(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<String> conditionNames = valueSet.getAllConditionNames();
            for (String conditionName : conditionNames) {
                Set<Object> conditionValues = valueSet.getAllConditionValues(conditionName);
                for (Object conditionValue : conditionValues) {
                    doAddCondition(conditionName, conditionValue);
                }
            }
        }
        return this;
    }

    private void doAddCondition(final String name, final Object value) {
        if (name != null && value != null) {
            Set<Object> conditionValues = _conditions.get(name);
            if (conditionValues == null) {
                conditionValues = new HashSet<>();
                _conditions.put(name, conditionValues);
            }
            conditionValues.add(value);
        }
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final String value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final boolean value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final char value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final int value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final long value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final float value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final double value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove the condition.
     *
     * @param name  condition name.
     * @param value condition value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeCondition(final String name, final Object value) {
        doRemoveCondition(name, value);
        return this;
    }

    /**
     * Remove all conditions with the specified condition name.
     *
     * @param name the specified condition name.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeConditions(final String name) {
        _conditions.remove(name);
        return this;
    }

    /**
     * Remove all conditions with the names obtained from the specified
     * {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeConditionNames(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<String> conditionNames = valueSet.getAllConditionNames();
            for (String conditionName : conditionNames) {
                removeConditions(conditionName);
            }
        }
        return this;
    }

    /**
     * Remove all conditions with the names and values obtained from the specified
     * {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeConditionValues(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            Set<String> conditionNames = valueSet.getAllConditionNames();
            for (String conditionName : conditionNames) {
                Set<Object> conditionValues = valueSet.getAllConditionValues(conditionName);
                for (Object conditionValue : conditionValues) {
                    doRemoveCondition(conditionName, conditionValue);
                }
            }
        }
        return this;
    }

    private void doRemoveCondition(final String name, final Object value) {
        Set<Object> conditionValues = _conditions.get(name);
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
     * Add the value.
     *
     * @param value the value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addValue(final T value) {
        if (value != null) {
            _values.add(value);
        }
        return this;
    }

    /**
     * Add the values.
     *
     * @param values the values.
     *
     * @return current object for the method chaining.
     */
    @SafeVarargs
    public final ValueSetBuilder<T> addValues(final T... values) {
        if (values != null) {
            for (T value : values) {
                addValue(value);
            }
        }
        return this;
    }

    /**
     * Add all values from the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> addValues(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            List<T> values = valueSet.getValues();
            for (T value : values) {
                addValue(value);
            }
        }
        return this;
    }

    /**
     * Remove the value.
     *
     * @param value the value.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeValue(final T value) {
        _values.remove(value);
        return this;
    }

    /**
     * Remove the values.
     *
     * @param values the values.
     *
     * @return current object for the method chaining.
     */
    @SafeVarargs
    public final ValueSetBuilder<T> removeValues(final T... values) {
        if (values != null) {
            for (T value : values) {
                removeValue(value);
            }
        }
        return this;
    }

    /**
     * Remove all values from the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param valueSet the specified {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> removeValues(final ValueSet<T> valueSet) {
        if (valueSet != null) {
            List<T> values = valueSet.getValues();
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
     * Clear the builder.
     *
     * @return current object for the method chaining.
     */
    public ValueSetBuilder<T> clear() {
        _id = null;
        _predicate = null;
        clearPredicates();
        clearConditions();
        clearValues();
        return this;
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ValueSet} object and clear the builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object.
     */
    public ValueSet<T> build() {
        return build(true);
    }

    /**
     * Create new {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @param clear true to clear the builder.
     *
     * @return {@link ru.d_shap.conditionalvalues.ValueSet} object.
     */
    public ValueSet<T> build(final boolean clear) {
        ValueSet<T> valueSet = new ValueSet<>(_id, _predicate, _predicates, _conditions, _values);
        _id = null;
        if (clear) {
            clear();
        }
        return valueSet;
    }

}
