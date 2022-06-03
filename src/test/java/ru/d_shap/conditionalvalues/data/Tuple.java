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
package ru.d_shap.conditionalvalues.data;

/**
 * Integer tuple.
 *
 * @author Dmitry Shapovalov
 */
public final class Tuple {

    private final int _value1;

    private final int _value2;

    private final int _value3;

    private final int _value4;

    /**
     * Create new object.
     *
     * @param value1 the value 1.
     * @param value2 the value 2.
     */
    public Tuple(final int value1, final int value2) {
        this(value1, value2, 0, 0);
    }

    /**
     * Create new object.
     *
     * @param value1 the value 1.
     * @param value2 the value 2.
     * @param value3 the value 3.
     */
    public Tuple(final int value1, final int value2, final int value3) {
        this(value1, value2, value3, 0);
    }

    /**
     * Create new object.
     *
     * @param value1 the value 1.
     * @param value2 the value 2.
     * @param value3 the value 3.
     * @param value4 the value 4.
     */
    public Tuple(final int value1, final int value2, final int value3, final int value4) {
        super();
        _value1 = value1;
        _value2 = value2;
        _value3 = value3;
        _value4 = value4;
    }

    /**
     * Get the value 1.
     *
     * @return the value 1.
     */
    public int getValue1() {
        return _value1;
    }

    /**
     * Get the value 2.
     *
     * @return the value 2.
     */
    public int getValue2() {
        return _value2;
    }

    /**
     * Get the value 3.
     *
     * @return the value 3.
     */
    public int getValue3() {
        return _value3;
    }

    /**
     * Get the value 4.
     *
     * @return the value 4.
     */
    public int getValue4() {
        return _value4;
    }

}
