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
package ru.d_shap.conditionalvalues.misc;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator for the comparable values (natural order).
 *
 * @param <T> generic type for the value.
 *
 * @author Dmitry Shapovalov
 */
public final class NaturalOrderComparator<T extends Comparable<T>> implements Comparator<T>, Serializable {

    private static final int POSITIVE = 1;

    private static final int NEGATIVE = -1;

    private static final long serialVersionUID = 1L;

    private final int _comparable1Null;

    private final int _comparable2Null;

    /**
     * Create new object.
     */
    public NaturalOrderComparator() {
        this(false);
    }

    /**
     * Create new object.
     *
     * @param nullsFirst if true, then null values precede the other values.
     */
    public NaturalOrderComparator(final boolean nullsFirst) {
        super();
        if (nullsFirst) {
            _comparable1Null = NEGATIVE;
            _comparable2Null = POSITIVE;
        } else {
            _comparable1Null = POSITIVE;
            _comparable2Null = NEGATIVE;
        }
    }

    @Override
    public int compare(final T comparable1, final T comparable2) {
        if (comparable1 == null) {
            if (comparable2 == null) {
                return 0;
            } else {
                return _comparable1Null;
            }
        } else {
            if (comparable2 == null) {
                return _comparable2Null;
            } else {
                return comparable1.compareTo(comparable2);
            }
        }
    }

}
