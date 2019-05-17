package com.svb.empl.web.orgunit;

import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.OrgUnit;

@UiController("empl_OrgUnit.browse")
@UiDescriptor("org-unit-browse.xml")
@LookupComponent("orgUnitsTable")
@LoadDataBeforeShow
public class OrgUnitBrowse extends StandardLookup<OrgUnit> {
}