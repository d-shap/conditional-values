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

/**
 * Predicate interface to match the {@link ru.d_shap.conditionalvalues.ValueSet} object against the conditions
 * in the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 * Predicate is applied to each value in {@link ru.d_shap.conditionalvalues.ValueSet} object.
 *
 * @author Dmitry Shapovalov
 */
public interface Predicate {

    /**
     * Evaluate the predicate for the specified values from the {@link ru.d_shap.conditionalvalues.ValueSet}
     * object and the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     *
     * @param conditionName     the name of condition.
     * @param conditionSetValue the value from the {@link ru.d_shap.conditionalvalues.ConditionSet} object.
     * @param valueSetValue     the value from the {@link ru.d_shap.conditionalvalues.ValueSet} object.
     *
     * @return true, if the values match.
     */
    boolean evaluate(String conditionName, Object conditionSetValue, Object valueSetValue);

}
