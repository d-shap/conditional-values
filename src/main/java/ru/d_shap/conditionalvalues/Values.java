// //////////////////////////////
// This code is authored
// Dmitry Shapovalov
// //////////////////////////////
package ru.d_shap.conditionalvalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class Values<T> {

    private final List<ValueSet<T>> _valueSets;

    private final Set<T> _allValues;

    Values(final List<ValueSet<T>> valueSets) {
        super();
        _valueSets = Collections.unmodifiableList(new ArrayList<ValueSet<T>>(valueSets));
        Set<T> allValues = new LinkedHashSet<T>();
        for (ValueSet<T> valueSet : _valueSets) {
            allValues.addAll(valueSet.getAllValues());
        }
        _allValues = Collections.unmodifiableSet(allValues);
    }

    public boolean isEmpty() {
        return _allValues.isEmpty();
    }

    public boolean contains(final T value) {
        return _allValues.contains(value);
    }

    public Set<T> getAllValues() {
        return _allValues;
    }

    @Override
    public String toString() {
        return _valueSets.toString();
    }

}
