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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.Actionable;
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.ValueSetBuilder;

/**
 * Tests for {@link ActionableAction}.
 *
 * @author Dmitry Shapovalov
 */
public final class ActionableActionTest {

    /**
     * Test class constructor.
     */
    public ActionableActionTest() {
        super();
    }

    /**
     * {@link ActionableAction} class test.
     */
    @Test
    public void performTest() {
        Set<String> set1 = new HashSet<>();
        new ActionableAction<ActionableImpl>().perform(null);
        Assertions.assertThat(set1).containsExactly();

        Set<String> set2 = new HashSet<>();
        new ActionableAction<ActionableImpl>().perform(new ActionableImpl(set2, "val"));
        Assertions.assertThat(set2).containsExactly("proc_val");

        Set<String> set3 = new HashSet<>();
        new ActionableAction<ActionableImpl>().perform(new ActionableImpl(set3, "val1"));
        new ActionableAction<ActionableImpl>().perform(new ActionableImpl(set3, "val2"));
        new ActionableAction<ActionableImpl>().perform(new ActionableImpl(set3, "val3"));
        new ActionableAction<ActionableImpl>().perform(new ActionableImpl(set3, "val4"));
        Assertions.assertThat(set3).containsExactly("proc_val1", "proc_val2", "proc_val3", "proc_val4");
    }

    /**
     * {@link ActionableAction} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<ActionableImpl> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<ActionableImpl> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();
        Set<String> set = new HashSet<>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue(new ActionableImpl(set, "val11"));
        valueSetBuilder.addValue(new ActionableImpl(set, "val12"));
        valueSetBuilder.addValue(new ActionableImpl(set, "val13"));
        valueSetBuilder.addValue(new ActionableImpl(set, "val14"));
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addValue(new ActionableImpl(set, "val21"));
        valueSetBuilder.addValue(new ActionableImpl(set, "val22"));
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<ActionableImpl> conditionalValues = conditionalValuesBuilder.build();

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val1").build(), new ActionableAction<ActionableImpl>());
        Assertions.assertThat(set).containsExactly("proc_val11", "proc_val12", "proc_val13", "proc_val14");

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "val2").build(), new ActionableAction<ActionableImpl>());
        Assertions.assertThat(set).containsExactly("proc_val21", "proc_val22");

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2").build(), new ActionableAction<ActionableImpl>());
        Assertions.assertThat(set).containsExactly("proc_val11", "proc_val12", "proc_val13", "proc_val14");

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.build(), new ActionableAction<ActionableImpl>());
        Assertions.assertThat(set).containsExactly("proc_val21", "proc_val22");
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class ActionableImpl implements Actionable {

        private final Set<String> _values;

        private final String _value;

        ActionableImpl(final Set<String> values, final String value) {
            super();
            _values = values;
            _value = value;
        }

        @Override
        public void perform() {
            _values.add("proc_" + _value);
        }

    }

}
