Conditional values
==================
Classes simplify conditional logic, getting rid of **if**-statements in the code.

The main purpose is to find the best result from many predefined conditions.

For example, we should edit form fields based on some conditions.

This conditions could be:
* form type with many different values (type1, type2, type3, etc.)
* form state with many different values (1, 2, 3, etc.)
* user role (viewer, editor, administrator, etc.)
* some other conditions

And based on this conditions there are different editable fields (field1, field2, field3, etc.).

First we create a `ValueSet` object for each distinct condition:
```
ValueSetBuilder<String> valueSetBuilder = ConditionalValues.createValueSetBuilder();

valueSetBuilder.addCondition("type", "type1");
valueSetBuilder.addCondition("state", 1);
valueSetBuilder.addCondition("role", "viewer");
valueSetBuilder.addValue("field1", "field2");
ValueSet<String> type1Viewer1ValueSet = valueSetBuilder.build();

valueSetBuilder.addCondition("type", "type1");
valueSetBuilder.addCondition("state", 2)
               .addCondition("state", 3);
valueSetBuilder.addCondition("role", "viewer");
valueSetBuilder.addValue("field2", "field3");
ValueSet<String> type1Viewer23ValueSet = valueSetBuilder.build();

valueSetBuilder.addCondition("type", "type1");
valueSetBuilder.addCondition("state", 2)
               .addCondition("state", 3);
valueSetBuilder.addCondition("role", "editor");
valueSetBuilder.addValue("field1", "field3");
ValueSet<String> type1Editor23ValueSet = valueSetBuilder.build();
```

Then we store this conditions in a single `ConditionalValues` object:
```
ConditionalValues<String> conditionalValues = ConditionalValues.createConditionalValues(type1Viewer1ValueSet,
                                                                                        type1Viewer23ValueSet,
                                                                                        type1Editor23ValueSet);
```

This `ConditionalValues` object could be created in static initializer.

In runtime now we can define editable fields based on current condition (current form type, current form state, current user role, etc).
To perform this we create a `ConditionSet` object:
```
ConditionSetBuilder conditionSetBuilder = ConditionalValues.createConditionSetBuilder();
conditionSetBuilder.addCondition("type", "type1");
conditionSetBuilder.addCondition("state", 2);
conditionSetBuilder.addCondition("role", "editor");
ConditionSet conditionSet = conditionSetBuilder.build();
```

And get matching `ValueSet` objects from `ConditionalValues` object:
```
Values<String> values = conditionalValues.lookup(conditionSet);
```

Now we can use `Values` to get all editable fields.

The lookup algorithm for the best matching `ValueSet` objects is the following:

First, all matching `ValueSet` objects are defined.
A `ValueSet` object matches if all the object's conditions match a `ConditionSet` object.

Then less specific `ValueSet` objects are removed.
The `ValueSet` object is less specific then another one if another object has all of the conditions this object has, and some more additional conditions.

Then values of remaining `ValueSet` objects are joined and returned as a lookup result.

For example, there are predefined conditions:

**Value Set 1**
* type: **type1**
* isViewer: **true**

**Value Set 2**
* type: **type1**
* isEditor: **true**

**Value Set 3**
* type: **type1**
* state: **1**
* isViewer: **true**

Then if we have runtime conditions (type = **type1**, state = **1**, isViewer = **true**), then the best matching value set is **Value Set 3** (the only one matching value set).

If we have runtime conditions (type = **type1**, state = **2**, isViewer = **true**), then the best matching value set is **Value Set 1** (the only one matching value set).

If we have runtime conditions (type = **type1**, isViewer = **true**, isEditor = **true**), then the best matching value sets are **Value Set 1** and **Value Set 2** (`Values` object contains values from both `ValueSet` objects).

If we have runtime conditions (type = **type1**, state = **1**, isViewer = **true**, isEditor = **true**), then the best matching value sets are **Value Set 2** and **Value Set 3** (**Value Set 1** also matches, but **Value Set 3** is more specific then **Value Set 1**).

If we have runtime conditions (type = **type1**, isViewer = **true**), then the best matching value set is **Value Set 1** (the only one matching value set).

If we have runtime conditions (isViewer = **true**, isEditor = **true**), then there are no matching value sets (`Values` object is empty).
