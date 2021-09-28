# Conditional values
Conditional values simplify conditional logic and get rid of **if**-statements in the code.

The main purpose is to find the best result from many predefined conditions.

For example, we should edit form fields based on some conditions.
These conditions could be:
* form type with many values (contract, order, incoming document, etc.)
* form state with many values (draft, approval, active, etc.)
* user role (viewer, editor, administrator, etc.)
* some other conditions

And based on these conditions there are different editable fields (title, subject, due date, etc.).

First we create a `ValueSet` object for each distinct condition and store this objects in a single `ConditionalValues` object:
```
ConditionalValuesBuilder<String> conditionalValuesBuilder = ConditionalValuesBuilder.newInstance();
ValueSetBuilder<String> valueSetBuilder = ValueSetBuilder.newInstance();

valueSetBuilder.addCondition("type", "contract");
valueSetBuilder.addCondition("state", "draft");
valueSetBuilder.addCondition("role", "viewer");
valueSetBuilder.addValues("title", "subject");
conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

valueSetBuilder.addCondition("type", "contract");
valueSetBuilder.addCondition("state", "approval")
               .addCondition("state", "active");
valueSetBuilder.addCondition("role", "viewer");
valueSetBuilder.addValues("subject", "due date");
conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

valueSetBuilder.addCondition("type", "contract");
valueSetBuilder.addCondition("state", "approval")
               .addCondition("state", "active");
valueSetBuilder.addCondition("role", "editor");
valueSetBuilder.addValues("title", "due date");
conditionalValuesBuilder.addValueSet(valueSetBuilder.build());

ConditionalValues<String> conditionalValues = conditionalValuesBuilder.build();
```

This `ConditionalValues` object could be created during design-time, for example in the class static initializer.

In runtime now we can define editable fields based on current condition (current form type, current form state, current user role, etc).
To perform this we create a `ConditionSet` object:
```
ConditionSetBuilder conditionSetBuilder = ConditionSetBuilder.newInstance();

conditionSetBuilder.addCondition("type", "contract");
conditionSetBuilder.addCondition("state", "approval");
conditionSetBuilder.addCondition("role", "editor");

ConditionSet conditionSet = conditionSetBuilder.build();
```

And perform lookup for the best matching `ValueSet` objects from `ConditionalValues` object:
```
Values<String> values = conditionalValues.lookup(conditionSet);
```

Now we can use `Values` to get all editable fields.

The lookup algorithm for the best matching `ValueSet` objects is the following.
First, all matching `ValueSet` objects are defined.
A `ValueSet` object matches if all the object's conditions match a `ConditionSet` object.
Then less specific `ValueSet` objects are removed.
The `ValueSet` object is less specific than another one if another object has all the conditions this object has, and some more additional conditions.
And, according to the previous step, both objects match a `ConditionSet` object.
Then values of remaining `ValueSet` objects are joined and returned as a lookup result.

For example, there are predefined conditions:

**Value Set 1**
* type: **contract**
* isViewer: **true**

**Value Set 2**
* type: **contract**
* isEditor: **true**

**Value Set 3**
* type: **contract**
* state: **draft**
* isViewer: **true**

Then if we have runtime conditions (type = **contract**, state = **draft**, isViewer = **true**), then the best matching value set is **Value Set 3** (the only one matching value set).

If we have runtime conditions (type = **contract**, state = **approval**, isViewer = **true**), then the best matching value set is **Value Set 1** (the only one matching value set).

If we have runtime conditions (type = **contract**, isViewer = **true**, isEditor = **true**), then the best matching value sets are **Value Set 1** and **Value Set 2** (`Values` object contains values from both `ValueSet` objects).

If we have runtime conditions (type = **contract**, state = **draft**, isViewer = **true**, isEditor = **true**), then the best matching value sets are **Value Set 2** and **Value Set 3** (**Value Set 1** also matches, but **Value Set 3** is more specific than **Value Set 1**).

If we have runtime conditions (type = **contract**, isViewer = **true**), then the best matching value set is **Value Set 1** (the only one matching value set).

If we have runtime conditions (isViewer = **true**, isEditor = **true**), then there are no matching value sets (`Values` object is empty).

To select conditions the following should be considered.
Form in the example above could be only in one state at any given moment of time.
So the condition with the name **state** and values **draft**, **approval**, **active** is good enough.
If the user in the examample above could have only one role, then condition with the name **role** and values **viewer**, **editor**, **administrator** is also good enough.
But if the user can have several roles simultaneously, then this condition would not work.
In this case several conditions should be used: condition with the name **isViewer** and values **true** and **false**, condition with the name **isEditor** and values **true** and **false**, condition with the name **isAdministrator** and values **true** and **false**.
Then if the user has several roles, then this user could edit form fields available for each role.

# The latest release
Conditional values:
* **&lt;groupId&gt;**: ru.d-shap
* **&lt;artifactId&gt;**: conditional-values
* **&lt;version&gt;**: 1.2

# Donation
If you find my code useful, you can [bye me a coffee](https://www.paypal.me/dshapovalov)
