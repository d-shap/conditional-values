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

import ru.d_shap.conditionalvalues.predicate.ValueSetFunction;

/**
 * ValueSet function to extract value 4 from tuple.
 *
 * @author Dmitry Shapovalov
 */
public final class TupleValue4Extractor implements ValueSetFunction {

    /**
     * Create new object.
     */
    public TupleValue4Extractor() {
        super();
    }

    @Override
    public Object apply(final Object valueSetValue) {
        return ((Tuple) valueSetValue).getValue4();
    }

}
