package com.svb.empl.web.branch;

import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.Branch;

@UiController("empl_Branch.edit")
@UiDescriptor("branch-edit.xml")
@EditedEntityContainer("branchDc")
@LoadDataBeforeShow
public class BranchEdit extends StandardEditor<Branch> {
}