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
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.data.ConcatStringActionable;

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
        new ActionableAction<ConcatStringActionable>().perform(null);

        Set<String> set1 = new HashSet<>();
        new ActionableAction<ConcatStringActionable>().perform(new ConcatStringActionable(set1, "val", "proc_", 0));
        Assertions.assertThat(set1).containsExactly("proc_val");

        Set<String> set2 = new HashSet<>();
        new ActionableAction<ConcatStringActionable>().perform(new ConcatStringActionable(set2, "val1", "proc_", 0));
        new ActionableAction<ConcatStringActionable>().perform(new ConcatStringActionable(set2, "val2", "proc_", 0));
        new ActionableAction<ConcatStringActionable>().perform(new ConcatStringActionable(set2, "val3", "proc_", 0));
        new ActionableAction<ConcatStringActionable>().perform(new ConcatStringActionable(set2, "val4", "proc_", 0));
        Assertions.assertThat(set2).containsExactly("proc_val1", "proc_val2", "proc_val3", "proc_val4");
    }

    /**
     * {@link ActionableAction} class test.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<ConcatStringActionable> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<ConcatStringActionable> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();
        Set<String> set = new HashSet<>();

        valueSetBuilder.addCondition("cond1", "val1");
        valueSetBuilder.addValue(new ConcatStringActionable(set, "val11", "proc_", 0));
        valueSetBuilder.addValue(new ConcatStringActionable(set, "val12", "proc_", 0));
        valueSetBuilder.addValue(new ConcatStringActionable(set, "val13", "proc_", 0));
        valueSetBuilder.addValue(new ConcatStringActionable(set, "val14", "proc_", 0));
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addValue(new ConcatStringActionable(set, "val21", "proc_", 0));
        valueSetBuilder.addValue(new ConcatStringActionable(set, "val22", "proc_", 0));
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        ConditionalValues<ConcatStringActionable> conditionalValues = conditionalValuesBuilder.build();

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val1").build(), new ActionableAction<ConcatStringActionable>());
        Assertions.assertThat(set).containsExactly("proc_val11", "proc_val12", "proc_val13", "proc_val14");

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.addCondition("cond2", "val2").build(), new ActionableAction<ConcatStringActionable>());
        Assertions.assertThat(set).containsExactly("proc_val21", "proc_val22");

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.addCondition("cond1", "val1").addCondition("cond2", "val2").build(), new ActionableAction<ConcatStringActionable>());
        Assertions.assertThat(set).containsExactly("proc_val11", "proc_val12", "proc_val13", "proc_val14");

        set.clear();
        conditionalValues.lookup(conditionSetBuilder.build(), new ActionableAction<ConcatStringActionable>());
        Assertions.assertThat(set).containsExactly("proc_val21", "proc_val22");
    }

}
