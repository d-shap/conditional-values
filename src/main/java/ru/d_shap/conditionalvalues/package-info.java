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
/**
 * <p>
 * Classes simplify conditional logic, getting rid of <b>if</b>-statements in the code.
 * </p>
 * <p>
 * The main purpose is to find the best result from many predefined conditions.
 * </p>
 * <p>
 * For example, we should edit form fields based on some conditions.
 * </p>
 * <p>
 * This conditions could be:
 * </p>
 * <ul>
 * <li>form type with many different values (type1, type2, type3, etc.)</li>
 * <li>form state with many different values (1, 2, 3, etc.)</li>
 * <li>user role (viewer, editor, administrator, etc.)</li>
 * <li>some other conditions</li>
 * </ul>
 * <p>
 * And based on this conditions there are different editable fields (field1, field2, field3, etc.).
 * </p>
 * <p>
 * First we create a {@link ru.d_shap.conditionalvalues.ValueSet} object for each distinct condition:
 * </p>
 * <pre>{@code
 * ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();
 *
 * valueSetBuilder.addCondition("type", "type1");
 * valueSetBuilder.addCondition("state", 1);
 * valueSetBuilder.addCondition("role", "viewer");
 * valueSetBuilder.addValue("field1", "field2");
 * ValueSet<String> type1Viewer1ValueSet = valueSetBuilder.build();
 *
 * valueSetBuilder.addCondition("type", "type1");
 * valueSetBuilder.addCondition("state", 2)
 *                .addCondition("state", 3);
 * valueSetBuilder.addCondition("role", "viewer");
 * valueSetBuilder.addValue("field2", "field3");
 * ValueSet<String> type1Viewer23ValueSet = valueSetBuilder.build();
 *
 * valueSetBuilder.addCondition("type", "type1");
 * valueSetBuilder.addCondition("state", 2)
 *                .addCondition("state", 3);
 * valueSetBuilder.addCondition("role", "editor");
 * valueSetBuilder.addValue("field1", "field3");
 * ValueSet<String> type1Editor23ValueSet = valueSetBuilder.build();
 * }</pre>
 * <p>
 * Then we store this conditions in a single {@link ru.d_shap.conditionalvalues.ConditionalValues} object:
 * </p>
 * <pre>{@code
 * ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(type1Viewer1ValueSet,
 *                                                                                         type1Viewer23ValueSet,
 *                                                                                         type1Editor23ValueSet);
 * }</pre>
 * <p>
 * This {@link ru.d_shap.conditionalvalues.ConditionalValues} object could be created in static initializer.
 * </p>
 * <p>
 * In runtime now we can define editable fields based on current condition (current form type, current form state,
 * current user role, etc).
 * To perform this we create a {@link ru.d_shap.conditionalvalues.ConditionSet} object:
 * </p>
 * <pre>{@code
 * ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
 * conditionSetBuilder.addCondition("type", "type1");
 * conditionSetBuilder.addCondition("state", 2);
 * conditionSetBuilder.addCondition("role", "editor");
 * ConditionSet conditionSet = conditionSetBuilder.build();
 * }</pre>
 * <p>
 * And get matching {@link ru.d_shap.conditionalvalues.ValueSet} objects from
 * {@link ru.d_shap.conditionalvalues.ConditionalValues} object:
 * </p>
 * <pre>{@code
 * Values<String> values = conditionalValues.lookup(conditionSet);
 * }</pre>
 * <p>
 * Now we can use {@link ru.d_shap.conditionalvalues.Values} to get all editable fields.
 * </p>
 * <p>
 * The lookup algorithm  for the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects
 * is the following:
 * </p>
 * <p>
 * First, all matching {@link ru.d_shap.conditionalvalues.ValueSet} objects are defined. A
 * {@link ru.d_shap.conditionalvalues.ValueSet} object matches if all the object's conditions match a
 * {@link ru.d_shap.conditionalvalues.ConditionSet} object.
 * </p>
 * <p>
 * Then less specific {@link ru.d_shap.conditionalvalues.ValueSet} objects are removed. The
 * {@link ru.d_shap.conditionalvalues.ValueSet} object is less specific then another one if
 * another object has all of the conditions this object have, and some more additional conditions.
 * </p>
 * <p>
 * Then values of remaining {@link ru.d_shap.conditionalvalues.ValueSet} objects are joined and
 * returned as a lookup result.
 * </p>
 * <p>
 * For example, there are predefined conditions:
 * </p>
 * <p>
 * <b>Value Set 1</b>
 * </p>
 * <ul>
 * <li>type: <b>type1</b></li>
 * <li>isViewer: <b>true</b></li>
 * </ul>
 * <p>
 * <b>Value Set 2</b>
 * </p>
 * <ul>
 * <li>type: <b>type1</b></li>
 * <li>isEditor: <b>true</b></li>
 * </ul>
 * <p>
 * <b>Value Set 3</b>
 * </p>
 * <ul>
 * <li>type: <b>type1</b></li>
 * <li>state: <b>1</b></li>
 * <li>isViewer: <b>true</b></li>
 * </ul>
 * <p>
 * Then if we have runtime conditions (type = <b>type1</b>, state = <b>1</b>, isViewer = <b>true</b>),
 * then the best matching value set is <b>Value Set 3</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, state = <b>2</b>, isViewer = <b>true</b>),
 * then the best matching value set is <b>Value Set 1</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, isViewer = <b>true</b>, isEditor = <b>true</b>),
 * then the best matching value sets are <b>Value Set 1</b> and <b>Value Set 2</b>
 * ({@link ru.d_shap.conditionalvalues.Values} object contains values from both
 * {@link ru.d_shap.conditionalvalues.ValueSet} objects).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, state = <b>1</b>, isViewer = <b>true</b>, isEditor = <b>true</b>),
 * then the best matching value sets are <b>Value Set 2</b> and <b>Value Set 3</b> (<b>Value Set 1</b> also matches,
 * but <b>Value Set 3</b> is more specific then <b>Value Set 1</b>).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>type1</b>, isViewer = <b>true</b>),
 * then the best matching value set is <b>Value Set 1</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (isViewer = <b>true</b>, isEditor = <b>true</b>),
 * then there are no matching value sets ({@link ru.d_shap.conditionalvalues.Values} object is empty).
 * </p>
 */
package ru.d_shap.conditionalvalues;
