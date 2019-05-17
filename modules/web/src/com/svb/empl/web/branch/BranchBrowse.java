package com.svb.empl.web.branch;

import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.Branch;

@UiController("empl_Branch.browse")
@UiDescriptor("branch-browse.xml")
@LookupComponent("branchesTable")
@LoadDataBeforeShow
public class BranchBrowse extends StandardLookup<Branch> {
}