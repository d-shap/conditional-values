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

import java.util.Comparator;

/**
 * Comparator to sort strings by length.
 *
 * @author Dmitry Shapovalov
 */
public final class StringLengthComparator implements Comparator<String> {

    /**
     * Create new object.
     */
    public StringLengthComparator() {
        super();
    }

    @Override
    public int compare(final String str1, final String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (str2 == null) {
                return 1;
            } else {
                int lng1 = str1.length();
                int lng2 = str2.length();
                return lng1 - lng2;
            }
        }
    }

}
