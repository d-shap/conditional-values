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
package ru.d_shap.conditionalvalues.usecase;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.conditionalvalues.ConditionSetBuilder;
import ru.d_shap.conditionalvalues.ConditionalValues;
import ru.d_shap.conditionalvalues.ConditionalValuesBuilder;
import ru.d_shap.conditionalvalues.Predicate;
import ru.d_shap.conditionalvalues.ValueSetBuilder;
import ru.d_shap.conditionalvalues.data.IsLessThenPredicate;
import ru.d_shap.conditionalvalues.data.SumAction;
import ru.d_shap.conditionalvalues.data.Tuple;
import ru.d_shap.conditionalvalues.data.TupleValue1Extractor;
import ru.d_shap.conditionalvalues.data.TupleValue2Extractor;
import ru.d_shap.conditionalvalues.predicate.EqualsPredicate;
import ru.d_shap.conditionalvalues.predicate.LogicalAndPredicate;
import ru.d_shap.conditionalvalues.predicate.LogicalNotPredicate;
import ru.d_shap.conditionalvalues.predicate.ValueSetFunctionPredicate;

/**
 * Lookup example implementation.
 *
 * @author Dmitry Shapovalov
 */
public final class ScoringTest {

    /**
     * Test class constructor.
     */
    public ScoringTest() {
        super();
    }

    /**
     * Lookup example.
     */
    @Test
    public void lookupTest() {
        ConditionalValuesBuilder<Integer> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
        ValueSetBuilder<Integer> valueSetBuilder = ValueSetBuilder.newInstance();
        ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

        Predicate agePredicate = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new LogicalNotPredicate(new IsLessThenPredicate())), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()));
        conditionalValuesBuilder.setPredicate("age", agePredicate);
        Predicate genderPredicate = new EqualsPredicate();
        conditionalValuesBuilder.setPredicate("gender", genderPredicate);
        Predicate salaryPredicate = new LogicalAndPredicate(new ValueSetFunctionPredicate(new TupleValue1Extractor(), new LogicalNotPredicate(new IsLessThenPredicate())), new ValueSetFunctionPredicate(new TupleValue2Extractor(), new IsLessThenPredicate()));
        conditionalValuesBuilder.setPredicate("salary", salaryPredicate);
        Predicate dependentsPredicate = new EqualsPredicate();
        conditionalValuesBuilder.setPredicate("dependents", dependentsPredicate);

        valueSetBuilder.addCondition("age", new Tuple(0, 18));
        valueSetBuilder.addValue(0);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("age", new Tuple(18, 25));
        valueSetBuilder.addValue(5);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("age", new Tuple(25, 45));
        valueSetBuilder.addValue(9);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("age", new Tuple(45, 55));
        valueSetBuilder.addValue(4);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("age", new Tuple(55, 100));
        valueSetBuilder.addValue(0);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition("gender", "male");
        valueSetBuilder.addValue(10);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("gender", "female");
        valueSetBuilder.addValue(8);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition("salary", new Tuple(0, 20000));
        valueSetBuilder.addValue(0);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("salary", new Tuple(20000, 50000));
        valueSetBuilder.addValue(10);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("salary", new Tuple(50000, 150000));
        valueSetBuilder.addValue(20);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("salary", new Tuple(150000, 500000));
        valueSetBuilder.addValue(50);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("salary", new Tuple(500000, 100000000));
        valueSetBuilder.addValue(150);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition("dependents", 0);
        valueSetBuilder.addValue(10);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("dependents", 1);
        valueSetBuilder.addValue(0);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("dependents", 2);
        valueSetBuilder.addValue(-10);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());
        valueSetBuilder.addCondition("dependents", 3);
        valueSetBuilder.addValue(-30);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        valueSetBuilder.addCondition("age", new Tuple(20, 35));
        valueSetBuilder.addCondition("gender", "female");
        valueSetBuilder.addValue(-20);
        conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

        ConditionalValues<Integer> conditionalValues = conditionalValuesBuilder.build();

        conditionSetBuilder.addCondition("age", 30);
        conditionSetBuilder.addCondition("gender", "male");
        conditionSetBuilder.addCondition("salary", 280000);
        conditionSetBuilder.addCondition("dependents", 0);
        SumAction sumAction1 = new SumAction();
        conditionalValues.lookup(conditionSetBuilder.build(), sumAction1);
        Assertions.assertThat(sumAction1.getSum()).isEqualTo(79);

        conditionSetBuilder.addCondition("age", 30);
        conditionSetBuilder.addCondition("gender", "female");
        conditionSetBuilder.addCondition("salary", 180000);
        conditionSetBuilder.addCondition("dependents", 1);
        SumAction sumAction2 = new SumAction();
        conditionalValues.lookup(conditionSetBuilder.build(), sumAction2);
        Assertions.assertThat(sumAction2.getSum()).isEqualTo(30);
    }

}
