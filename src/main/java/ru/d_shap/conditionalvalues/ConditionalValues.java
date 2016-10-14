// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Object holds {@link ru.d_shap.conditionalvalues.ValueSet} objects and performs lookup for the best matching
 * {@link ru.d_shap.conditionalvalues.ValueSet} objects based on specified {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 * </p>
 * <p>
 * Methods {@link #createStringConditionalValues(ValueSet...)}, {@link #createBooleanConditionalValues(ValueSet...)},
 * {@link #createIntegerConditionalValues(ValueSet...)}, {@link #createLongConditionalValues(ValueSet...)},
 * {@link #createFloatConditionalValues(ValueSet...)}, {@link #createDoubleConditionalValues(ValueSet...)} and
 * {@link #createObjectConditionalValues(ValueSet...)} are convenient methods to create
 * {@link ru.d_shap.conditionalvalues.ConditionalValues} object.
 * But this methods are NOT type-safe!
 * </p>
 *
 * @param <T> value type of {@link ru.d_shap.conditionalvalues.ValueSet} object.
 * @author Dmitry Shapovalov
 */
public final class ConditionalValues<T> {

    private final List<ValueSet<T>> _valueSets;

    private final Set<ValueSetUniqueCondition> _allValueSetUniqueConditions;

    private ConditionalValues(final List<ValueSet<T>> valueSets) {
        super();
        List<ValueSet<T>> list = new ArrayList<ValueSet<T>>();
        for (ValueSet<T> valueSet : valueSets) {
            if (valueSet != null) {
                list.add(valueSet);
            }
        }
        _valueSets = Collections.unmodifiableList(list);
        _allValueSetUniqueConditions = Collections.unmodifiableSet(getValueSetUniqueConditions(_valueSets));
    }

    private Set<ValueSetUniqueCondition> getValueSetUniqueConditions(final List<ValueSet<T>> valueSets) {
        Set<ValueSetUniqueCondition> allValueSetUniqueConditions = new HashSet<ValueSetUniqueCondition>();
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
        return new ValueSetBuilder<T>();
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
        if (valueSets == null) {
            List<ValueSet<T>> valueSetList = new ArrayList<ValueSet<T>>();
            return new ConditionalValues<T>(valueSetList);
        } else {
            return new ConditionalValues<T>(valueSets);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with String
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<String> createStringConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<String>> valueSetList = new ArrayList<ValueSet<String>>();
            return new ConditionalValues<String>(valueSetList);
        } else {
            List<ValueSet<String>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<String>(valueSetsList);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with Boolean
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<Boolean> createBooleanConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<Boolean>> valueSetList = new ArrayList<ValueSet<Boolean>>();
            return new ConditionalValues<Boolean>(valueSetList);
        } else {
            List<ValueSet<Boolean>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<Boolean>(valueSetsList);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with Integer
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<Integer> createIntegerConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<Integer>> valueSetList = new ArrayList<ValueSet<Integer>>();
            return new ConditionalValues<Integer>(valueSetList);
        } else {
            List<ValueSet<Integer>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<Integer>(valueSetsList);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with Long
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<Long> createLongConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<Long>> valueSetList = new ArrayList<ValueSet<Long>>();
            return new ConditionalValues<Long>(valueSetList);
        } else {
            List<ValueSet<Long>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<Long>(valueSetsList);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with Float
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<Float> createFloatConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<Float>> valueSetList = new ArrayList<ValueSet<Float>>();
            return new ConditionalValues<Float>(valueSetList);
        } else {
            List<ValueSet<Float>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<Float>(valueSetsList);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with Double
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<Double> createDoubleConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<Double>> valueSetList = new ArrayList<ValueSet<Double>>();
            return new ConditionalValues<Double>(valueSetList);
        } else {
            List<ValueSet<Double>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<Double>(valueSetsList);
        }
    }

    /**
     * Create {@link ru.d_shap.conditionalvalues.ConditionalValues} object with Object
     * {@link ru.d_shap.conditionalvalues.ValueSet} type.
     * This call is NOT type-safe!
     *
     * @param valueSets all value sets, used for lookup.
     * @return created object.
     */
    public static ConditionalValues<?> createObjectConditionalValues(final ValueSet<?>... valueSets) {
        if (valueSets == null) {
            List<ValueSet<Object>> valueSetList = new ArrayList<ValueSet<Object>>();
            return new ConditionalValues<Object>(valueSetList);
        } else {
            List<ValueSet<Object>> valueSetsList = createValueSetsList(valueSets);
            return new ConditionalValues<Object>(valueSetsList);
        }
    }

    @SuppressWarnings("unchecked")
    private static <E> List<ValueSet<E>> createValueSetsList(final ValueSet<?>... valueSets) {
        List<ValueSet<E>> valueSetsList = new ArrayList<ValueSet<E>>();
        if (valueSets != null) {
            for (ValueSet<?> valueSet : valueSets) {
                valueSetsList.add((ValueSet<E>) valueSet);
            }
        }
        return valueSetsList;
    }

    /**
     * Get all condition names, defined in all {@link ru.d_shap.conditionalvalues.ValueSet} objects.
     *
     * @return all condition names.
     */
    public Set<String> getAllConditionNames() {
        Set<String> result = new HashSet<String>();
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
        Set<String> result = new HashSet<String>();
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
        return new Values<T>(matchingValueSets);
    }

    private List<ValueSet<T>> getMatchingValueSets(final ConditionSet conditionSet) {
        List<ValueSet<T>> matchingValueSets = new ArrayList<ValueSet<T>>();
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
