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
 * Conditional values simplify conditional logic and get rid of <b>if</b>-statements in the code.
 * </p>
 * <p>
 * The main purpose is to find the best result from many predefined conditions.
 * </p>
 * <p>
 * For example, we should edit form fields based on some conditions. This conditions could be:
 * </p>
 * <ul>
 * <li>form type with many different values (contract, order, incoming document, etc.)</li>
 * <li>form state with many different values (draft, approval, active, etc.)</li>
 * <li>user role (viewer, editor, administrator, etc.)</li>
 * <li>some other conditions</li>
 * </ul>
 * <p>
 * And based on this conditions there are different editable fields (title, subject, due date, etc.).
 * </p>
 * <p>
 * First we create a {@link ru.d_shap.conditionalvalues.ValueSet} object for each distinct condition
 * and store this objects in a single {@link ru.d_shap.conditionalvalues.ConditionalValues} object:
 * </p>
 * <pre>{@code
 * ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
 * ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();
 *
 * valueSetBuilder.addCondition("type", "contract");
 * valueSetBuilder.addCondition("state", "draft");
 * valueSetBuilder.addCondition("role", "viewer");
 * valueSetBuilder.addValues("title", "subject");
 * conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
 *
 * valueSetBuilder.addCondition("type", "contract");
 * valueSetBuilder.addCondition("state", "approval")
 *                .addCondition("state", "active");
 * valueSetBuilder.addCondition("role", "viewer");
 * valueSetBuilder.addValues("subject", "due date");
 * conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
 *
 * valueSetBuilder.addCondition("type", "contract");
 * valueSetBuilder.addCondition("state", "approval")
 *                .addCondition("state", "active");
 * valueSetBuilder.addCondition("role", "editor");
 * valueSetBuilder.addValues("title", "due date");
 * conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
 *
 * ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();
 * }</pre>
 * <p>
 * This {@link ru.d_shap.conditionalvalues.ConditionalValues} object could be created during design-time,
 * for example in the class static initializer.
 * </p>
 * <p>
 * In runtime now we can define editable fields based on current condition (current form type, current form state,
 * current user role, etc). To perform this we create a {@link ru.d_shap.conditionalvalues.ConditionSet} object:
 * </p>
 * <pre>{@code
 * ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();
 *
 * conditionSetBuilder.addCondition("type", "contract");
 * conditionSetBuilder.addCondition("state", "approval");
 * conditionSetBuilder.addCondition("role", "editor");
 *
 * ConditionSet conditionSet = conditionSetBuilder.build();
 * }</pre>
 * <p>
 * And perform lookup for the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects from
 * {@link ru.d_shap.conditionalvalues.ConditionalValues} object:
 * </p>
 * <pre>{@code
 * Values<String> values = conditionalValues.lookup(conditionSet);
 * }</pre>
 * <p>
 * Now we can use {@link ru.d_shap.conditionalvalues.Values} to get all editable fields.
 * </p>
 * <p>
 * The lookup algorithm for the best matching {@link ru.d_shap.conditionalvalues.ValueSet} objects
 * is the following. First, all matching {@link ru.d_shap.conditionalvalues.ValueSet} objects are defined.
 * A {@link ru.d_shap.conditionalvalues.ValueSet} object matches if all the object's conditions match a
 * {@link ru.d_shap.conditionalvalues.ConditionSet} object. Then less specific {@link ru.d_shap.conditionalvalues.ValueSet}
 * objects are removed. The {@link ru.d_shap.conditionalvalues.ValueSet} object is less specific than another one if
 * another object has all of the conditions this object has, and some more additional conditions.
 * And, according to the previous step, both objects match a {@link ru.d_shap.conditionalvalues.ConditionSet}
 * object. Then values of remaining {@link ru.d_shap.conditionalvalues.ValueSet} objects are joined and
 * returned as a lookup result.
 * </p>
 * <p>
 * For example, there are predefined conditions:
 * </p>
 * <p>
 * <b>Value Set 1</b>
 * </p>
 * <ul>
 * <li>type: <b>contract</b></li>
 * <li>isViewer: <b>true</b></li>
 * </ul>
 * <p>
 * <b>Value Set 2</b>
 * </p>
 * <ul>
 * <li>type: <b>contract</b></li>
 * <li>isEditor: <b>true</b></li>
 * </ul>
 * <p>
 * <b>Value Set 3</b>
 * </p>
 * <ul>
 * <li>type: <b>contract</b></li>
 * <li>state: <b>draft</b></li>
 * <li>isViewer: <b>true</b></li>
 * </ul>
 * <p>
 * Then if we have runtime conditions (type = <b>contract</b>, state = <b>draft</b>, isViewer = <b>true</b>),
 * then the best matching value set is <b>Value Set 3</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>contract</b>, state = <b>approval</b>, isViewer = <b>true</b>),
 * then the best matching value set is <b>Value Set 1</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>contract</b>, isViewer = <b>true</b>, isEditor = <b>true</b>),
 * then the best matching value sets are <b>Value Set 1</b> and <b>Value Set 2</b>
 * ({@link ru.d_shap.conditionalvalues.Values} object contains values from both
 * {@link ru.d_shap.conditionalvalues.ValueSet} objects).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>contract</b>, state = <b>draft</b>, isViewer = <b>true</b>, isEditor = <b>true</b>),
 * then the best matching value sets are <b>Value Set 2</b> and <b>Value Set 3</b> (<b>Value Set 1</b> also matches,
 * but <b>Value Set 3</b> is more specific then <b>Value Set 1</b>).
 * </p>
 * <p>
 * If we have runtime conditions (type = <b>contract</b>, isViewer = <b>true</b>),
 * then the best matching value set is <b>Value Set 1</b> (the only one matching value set).
 * </p>
 * <p>
 * If we have runtime conditions (isViewer = <b>true</b>, isEditor = <b>true</b>),
 * then there are no matching value sets ({@link ru.d_shap.conditionalvalues.Values} object is empty).
 * </p>
 * <p>
 * To select conditions the following should be considered.
 * Form in the example above could be only in one state at any given moment of time. So the condition
 * with the name <b>state</b> and values <b>draft</b>, <b>approval</b>, <b>active</b> is good enough.
 * If the user in the examample above could have only one role, then condition with the name <b>role</b>
 * and values <b>viewer</b>, <b>editor</b>, <b>administrator</b> is also good enough.
 * But if the user can have several roles simultaneously, then this condition would not work. In this
 * case several conditions should be used: condition with the name <b>isViewer</b> and values
 * <b>true</b> and <b>false</b>, condition with the name <b>isEditor</b> and values <b>true</b> and
 * <b>false</b>, condition with the name <b>isAdministrator</b> and values <b>true</b> and <b>false</b>.
 * Then if the user has several roles, then this user could edit form fields available for each role.
 * </p>
 */
package ru.d_shap.conditionalvalues;
