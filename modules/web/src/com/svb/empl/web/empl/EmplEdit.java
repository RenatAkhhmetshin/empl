package com.svb.empl.web.empl;

import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.Empl;
import com.svb.empl.entity.OrgUnit;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@UiController("empl_Empl.edit")
@UiDescriptor("empl-edit.xml")
@EditedEntityContainer("emplDc")
@LoadDataBeforeShow
public class EmplEdit extends StandardEditor<Empl> {
	
	@Inject
	private Image photo;
	private static Logger logger = LoggerFactory.getLogger(EmplEdit.class);
	@Inject
	private Tree<OrgUnit> orgUnitsTree;
	@Inject
	private InstanceContainer<Empl> emplDc;
	@Inject
	private CollectionPropertyContainer<OrgUnit> orgunitsDc;

	@Inject
	private DataManager dataManager;
	
	@Inject
	private CollectionContainer<OrgUnit> orgUnitsDs1;
	
	@Inject
	private Label<Empl> chief;
	
	@Inject
	private Link idlink;
	
	@Subscribe
	private void onInitEntity(InitEntityEvent<Empl> event) {
	
	}
	
	@Override
	public void setEntityToEdit(Empl item) {
		super.setEntityToEdit(item);
		
		
		FileDescriptor photoFileDescriptor = item.getPhoto();
		if (photoFileDescriptor != null) {
			logger.info("setEntityToEdit Found photo employee: "+ item.getUser().getLogin());
			photo.setSource(FileDescriptorResource.class).setFileDescriptor (photoFileDescriptor);
			photo.setVisible(Boolean.TRUE);
		} else {
			logger.info("setEntityToEdit Not Found photo employee: ");
		}

	}
	
	@Subscribe
	private void onBeforeShow(BeforeShowEvent event) {
		Empl empl = getEditedEntity();
		
		
		try {
			// Подразделения
			List<OrgUnit> orgUnitList = empl.getOrgunits();
			if (! CollectionUtils.isEmpty(orgUnitList)) {
				logger.info ("Adding OrgUnits Info");
				for (OrgUnit du : orgUnitList) {
					orgUnitsDs1.getMutableItems().add(du);
					
					logger.info ("Get Parent OrgUnits");
					OrgUnit parent = du.getParent();
					
					if (parent != null) {
						orgUnitsDs1.getMutableItems().add(parent);
					}
				}
				
				logger.info(String.valueOf(orgUnitsDs1.getItems().size()));
			}
			orgUnitsTree.expandTree();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("onBeforeShow: "+
					e.getMessage());
		}
	}
	
}