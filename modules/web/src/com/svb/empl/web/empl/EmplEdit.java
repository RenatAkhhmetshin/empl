package com.svb.empl.web.empl;

import com.haulmont.bali.events.Subscription;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.theme.HaloTheme;
import com.svb.empl.entity.Empl;
import com.svb.empl.entity.OrgUnit;
import com.svb.empl.service.EmplService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
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
	private VBoxLayout staffVbox;
	
	@Inject
	private Messages messages;
	
	@Inject
	private UiComponents uiComponents;
	
	@Inject
	private Form form;
	
	@Inject
	private UserSession userSession;
	@Inject
	private EmplService emplService;
	
	@Subscribe
	private void onInitEntity(InitEntityEvent<Empl> event) {
	    try{
		  
		    
	    } catch (Exception e) {
	    	logger.error("EmplEdit onInitEntity error \n"+e.getMessage());
		    throw new RuntimeException(e.getMessage());
	    }
		
	}
	
	@Override
	public void setEntityToEdit(Empl item) {
		super.setEntityToEdit(item);
		if (! PersistenceHelper.isLoadedWithView(item,"empl-view_1")) {
			item = dataManager.reload(item, "empl-view_1");
		}
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
		if (! PersistenceHelper.isLoadedWithView(empl,"empl-view_1")) {
			logger.info("datamanager reload");
			empl = dataManager.reload(empl, "empl-view_1");
		}
		
		try {
			Empl chiefEmpl = empl.getChief();
			
			
			if (chiefEmpl != null) {
				logger.info("Chief Empl Name: "+
						chiefEmpl.getUser().getName());
				if (!PersistenceHelper.isLoadedWithView(chiefEmpl, "empl-view_1")) {
					chiefEmpl = dataManager.reload(chiefEmpl, "empl-view_1");
				}
			
				
				
				UUID chiefUUID = chiefEmpl.getId();
				
				Optional<Empl> optionalChiefEmpl = dataManager.load (Empl.class).view ("empl-view_1").
						id (chiefUUID).optional ();
				
				if (optionalChiefEmpl.isPresent()) {
					chiefEmpl = optionalChiefEmpl.get();
					logger.info("Adding chief link to screen");
					EntityLinkField<Empl> chiefEmplEntityLinkField = uiComponents.create(EntityLinkField.NAME);
					chiefEmplEntityLinkField.setValueSource(new ContainerValueSource<>(emplDc,
							"chief"));
					chiefEmplEntityLinkField.setValue(chiefEmpl);
					chiefEmplEntityLinkField.setStyleName(HaloTheme.LABEL_H1);
					chiefEmplEntityLinkField.setCaption(messages.getMessage(EmplEdit.class, "chief"));
					staffVbox.add(chiefEmplEntityLinkField);
				}
			
			}
			
			
			
			
			// Подразделения
			List<OrgUnit> orgUnitList = empl.getOrgunits();
			
			
			if (! CollectionUtils.isEmpty(orgUnitList)) {
				logger.info("OrgUnits list Count:"+ orgUnitList.size());
				logger.info ("Adding OrgUnits Info");
				for (OrgUnit du : orgUnitList) {
					orgUnitsDs1.getMutableItems().add(du);
					
				/*	logger.info ("Get Parent OrgUnits");
					OrgUnit parent = du.getParent();
					
					if (parent != null) {
						if (! orgUnitsDs1.getMutableItems().contains(parent)) {
							logger.info("Adding parent: "+ parent.getShortname());
							orgUnitsDs1.getMutableItems().add(parent);
						}
						
					}*/
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