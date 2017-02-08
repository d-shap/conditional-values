///////////////////////////////////////////////////////////////////////////////////////////////////
// Conditional values simplify conditional logic and get rid of if-statements in the code.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Conditional values.
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
        Map<String, Set<String>> conditions1 = new HashMap<>();
        Set<String> values1 = new HashSet<>();
        ValueSet<String> valueSet1 = new ValueSet<>(conditions1, values1);
        DuplicateValueSetException exception1 = new DuplicateValueSetException(valueSet1);
        Assertions.assertThat(exception1).hasMessage("Duplicate value set: {}");

        Map<String, Set<String>> conditions2 = new HashMap<>();
        Set<String> condition2 = new HashSet<>();
        condition2.add("val");
        conditions2.put("cond", condition2);
        Set<String> values2 = new HashSet<>();
        ValueSet<String> valueSet2 = new ValueSet<>(conditions2, values2);
        DuplicateValueSetException exception2 = new DuplicateValueSetException(valueSet2);
        Assertions.assertThat(exception2).hasMessage("Duplicate value set: {cond=[val]}");

        Map<String, Set<String>> conditions3 = new HashMap<>();
        Set<String> condition31 = new HashSet<>();
        condition31.add("val1");
        conditions3.put("cond1", condition31);
        Set<String> condition32 = new HashSet<>();
        condition32.add("val2");
        conditions3.put("cond2", condition32);
        Set<String> values3 = new HashSet<>();
        ValueSet<String> valueSet3 = new ValueSet<>(conditions3, values3);
        DuplicateValueSetException exception3 = new DuplicateValueSetException(valueSet3);
        Assertions.assertThat(exception3).toMessage().startsWith("Duplicate value set:");
        Assertions.assertThat(exception3).toMessage().contains("cond1=[val1]");
        Assertions.assertThat(exception3).toMessage().contains("cond2=[val2]");
    }

}
