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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link DuplicateValueSetException}.
 *
 * @author Dmitry Shapovalov
 */
public final class DuplicateValueSetExceptionTest {

    /**
     * Test class constructor.
     */
    public DuplicateValueSetExceptionTest() {
        super();
    }

    /**
     * {@link DuplicateValueSetException} class test.
     */
    @Test
    public void messageTest() {
        Map<String, Set<String>> conditions11 = new HashMap<>();
        Set<String> values11 = new HashSet<>();
        ValueSet<String> valueSet11 = new ValueSet<>(null, conditions11, values11);
        DuplicateValueSetException exception11 = new DuplicateValueSetException(valueSet11);
        Assertions.assertThat(exception11).hasMessage("Duplicate value set: {}");

        Map<String, Set<String>> conditions12 = new HashMap<>();
        Set<String> values12 = new HashSet<>();
        ValueSet<String> valueSet12 = new ValueSet<>("", conditions12, values12);
        DuplicateValueSetException exception12 = new DuplicateValueSetException(valueSet12);
        Assertions.assertThat(exception12).hasMessage("Duplicate value set: ={}");

        Map<String, Set<String>> conditions13 = new HashMap<>();
        Set<String> values13 = new HashSet<>();
        ValueSet<String> valueSet13 = new ValueSet<>("id", conditions13, values13);
        DuplicateValueSetException exception13 = new DuplicateValueSetException(valueSet13);
        Assertions.assertThat(exception13).hasMessage("Duplicate value set: id={}");

        Map<String, Set<String>> conditions21 = new HashMap<>();
        Set<String> condition21 = new HashSet<>();
        condition21.add("val");
        conditions21.put("cond", condition21);
        Set<String> values21 = new HashSet<>();
        ValueSet<String> valueSet21 = new ValueSet<>(null, conditions21, values21);
        DuplicateValueSetException exception21 = new DuplicateValueSetException(valueSet21);
        Assertions.assertThat(exception21).hasMessage("Duplicate value set: {cond=[val]}");

        Map<String, Set<String>> conditions22 = new HashMap<>();
        Set<String> condition22 = new HashSet<>();
        condition22.add("val");
        conditions22.put("cond", condition22);
        Set<String> values22 = new HashSet<>();
        ValueSet<String> valueSet22 = new ValueSet<>("", conditions22, values22);
        DuplicateValueSetException exception22 = new DuplicateValueSetException(valueSet22);
        Assertions.assertThat(exception22).hasMessage("Duplicate value set: ={cond=[val]}");

        Map<String, Set<String>> conditions23 = new HashMap<>();
        Set<String> condition23 = new HashSet<>();
        condition23.add("val");
        conditions23.put("cond", condition23);
        Set<String> values23 = new HashSet<>();
        ValueSet<String> valueSet23 = new ValueSet<>("id", conditions23, values23);
        DuplicateValueSetException exception23 = new DuplicateValueSetException(valueSet23);
        Assertions.assertThat(exception23).hasMessage("Duplicate value set: id={cond=[val]}");

        Map<String, Set<String>> conditions31 = new HashMap<>();
        Set<String> condition311 = new HashSet<>();
        condition311.add("val1");
        conditions31.put("cond1", condition311);
        Set<String> condition312 = new HashSet<>();
        condition312.add("val2");
        conditions31.put("cond2", condition312);
        Set<String> values31 = new HashSet<>();
        ValueSet<String> valueSet31 = new ValueSet<>(null, conditions31, values31);
        DuplicateValueSetException exception31 = new DuplicateValueSetException(valueSet31);
        Assertions.assertThat(exception31).messageContains("Duplicate value set: {");
        Assertions.assertThat(exception31).messageContains("cond1=[val1]");
        Assertions.assertThat(exception31).messageContains("cond2=[val2]");

        Map<String, Set<String>> conditions32 = new HashMap<>();
        Set<String> condition321 = new HashSet<>();
        condition321.add("val1");
        conditions32.put("cond1", condition321);
        Set<String> condition322 = new HashSet<>();
        condition322.add("val2");
        conditions32.put("cond2", condition322);
        Set<String> values32 = new HashSet<>();
        ValueSet<String> valueSet32 = new ValueSet<>("", conditions32, values32);
        DuplicateValueSetException exception32 = new DuplicateValueSetException(valueSet32);
        Assertions.assertThat(exception32).messageContains("Duplicate value set: ={");
        Assertions.assertThat(exception32).messageContains("cond1=[val1]");
        Assertions.assertThat(exception32).messageContains("cond2=[val2]");

        Map<String, Set<String>> conditions33 = new HashMap<>();
        Set<String> conditions331 = new HashSet<>();
        conditions331.add("val1");
        conditions33.put("cond1", conditions331);
        Set<String> conditions332 = new HashSet<>();
        conditions332.add("val2");
        conditions33.put("cond2", conditions332);
        Set<String> values33 = new HashSet<>();
        ValueSet<String> valueSet33 = new ValueSet<>("id", conditions33, values33);
        DuplicateValueSetException exception33 = new DuplicateValueSetException(valueSet33);
        Assertions.assertThat(exception33).messageContains("Duplicate value set: id={");
        Assertions.assertThat(exception33).messageContains("cond1=[val1]");
        Assertions.assertThat(exception33).messageContains("cond2=[val2]");
    }

}
