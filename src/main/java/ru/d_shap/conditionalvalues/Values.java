// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * The lookup result of the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects.
 * </p>
 * <p>
 * If {@link ru.d_shap.conditionalvalues.Values} object created for several matchig
 * {@link ru.d_shap.conditionalvalues.ValueSet} objects, then values are obtained from every
 * one {@link ru.d_shap.conditionalvalues.ValueSet} object.
 * </p>
 *
 * @param <T> value type.
 * @author Dmitry Shapovalov
 */
public final class Values<T> {

    private final List<ValueSet<T>> _valueSets;

    private final Set<T> _allValues;

    Values(final List<ValueSet<T>> valueSets) {
        super();
        _valueSets = Collections.unmodifiableList(new ArrayList<ValueSet<T>>(valueSets));
        Set<T> set = new HashSet<T>();
        for (ValueSet<T> valueSet : _valueSets) {
            set.addAll(valueSet.getAllValues());
        }
        _allValues = Collections.unmodifiableSet(set);
    }

    /**
     * Tests if this object contains any value.
     *
     * @return true if this object does not contain any value.
     */
    public boolean isEmpty() {
        return _allValues.isEmpty();
    }

    /**
     * Tests if this object contains specified value.
     *
     * @param value value to test.
     * @return true if this object contains specified value.
     */
    public boolean contains(final T value) {
        return _allValues.contains(value);
    }

    /**
     * Return all values contained in this object.
     *
     * @return all values.
     */
    public Set<T> getAllValues() {
        return _allValues;
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
