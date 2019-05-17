package com.svb.empl.web.orgunit;

import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.OrgUnit;

@UiController("empl_OrgUnit.edit")
@UiDescriptor("org-unit-edit.xml")
@EditedEntityContainer("orgUnitDc")
@LoadDataBeforeShow
public class OrgUnitEdit extends StandardEditor<OrgUnit> {
}