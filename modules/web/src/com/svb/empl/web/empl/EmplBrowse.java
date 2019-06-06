package com.svb.empl.web.empl;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.actions.list.CreateAction;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.svb.empl.entity.Empl;
import com.svb.empl.service.DominoService;
import com.svb.empl.service.EmplService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.Employee;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.function.Consumer;

@UiController("empl_Empl.browse")
@UiDescriptor("empl-browse.xml")
@LookupComponent("emplsTable")
@LoadDataBeforeShow
public class EmplBrowse extends StandardLookup<Empl> {
	
	@Inject
	private DominoService dominoService;
	
	@Inject
	private Dialogs dialogs;
	
	@Inject
	private Messages messages;
	
	@Inject
	private ComponentsFactory componentsFactory;
	@Inject
	private GroupTable<Empl> emplsTable;
	private static Logger logger = LoggerFactory.getLogger(EmplBrowse.class);
	
	
	@Inject
	private UserSession userSession;
	@Named("emplsTable.create")
	private CreateAction emplsTableCreate;
	@Inject
	private EmplService emplService;
	
	@Inject
	private ScreenBuilders screenBuilders;
	@Inject
	private Notifications notifications;
	
	public void runDocminoservice () {
		dominoService.getDominoEmployees();
	 
		String endMessage = messages.getMessage(getClass(), "endcreateUsers");
		String messageCaption = messages.getMessage(getClass(), "endcreateUsersCaption");
		
		dialogs.createMessageDialog().
				withCaption(messageCaption).
				withMessage(endMessage).show();
	}
	
	@Subscribe
	private void onInit(InitEvent event) {
		
		emplsTable.addGeneratedColumn("photo", entity -> {
			Image image = componentsFactory.createComponent(Image.class);
			image.setScaleMode(Image.ScaleMode.CONTAIN);
			image.setHeight("70");
			image.setWidth("70");
			
			FileDescriptor userImageFile = entity.getPhoto ();
			HBoxLayout hBox = componentsFactory.createComponent(HBoxLayout.class);
			if (userImageFile!=null) {
				image.setSource(FileDescriptorResource.class).setFileDescriptor(userImageFile);
				hBox.setCaption ("");
				hBox.add(image);
			}
			
			return hBox;
		});
		
		
	}
	
	
	public void getEmployybyTab () {
		
		String inputDlgTexrt = messages.getMessage(getClass(), "enterPersonTab");
		//String inputValue = showInputDialog(inputDlgTexrt);
		
		dialogs.createOptionDialog().withContentMode(ContentMode.TEXT).show();
		String inputValue = null;
		logger.info("Input value: "+ inputValue);
		
		if (inputValue == null) return;
		
		Employee employee = dominoService.getDominoEmployeebyTabNumber(inputValue);
		
		String messageCaption = messages.getMessage(getClass(), "foundUser");
		String messageText  = (employee == null) ? messages.getMessage(getClass(), "notfoundUser") :
				messages.getMessage(getClass(), "foundUser");
		
		dialogs.createMessageDialog().
				withCaption(messageCaption).
				withMessage(messageText).show();
	}
	
/*	@Subscribe("searchEmpl")
	private void onSearchEmplClick(Button.ClickEvent event) {
		dialogs.createMessageDialog()
				.withCaption("Warning")
				.withMessage("tetetetet")
				.withType(Dialogs.MessageType.WARNING)
				.withContentMode(ContentMode.PREFORMATTED)
				
				.withCloseOnClickOutside(true)
				.show();
		
		
		
	}*/
	
	@Subscribe("rubService")
	private void onRubServiceClick(Button.ClickEvent event) {
		dominoService.getDominoEmployees();
		
		String endMessage = messages.getMessage(getClass(), "endcreateUsers");
		String messageCaption = messages.getMessage(getClass(), "endcreateUsersCaption");
		
		dialogs.createMessageDialog().
				withCaption(messageCaption).
				withMessage(endMessage).show();
	}
	
	@Subscribe("emplsTable.create")
	private void onEmplsTableCreate(Action.ActionPerformedEvent event) {
		logger.info("onInitEntity start");
		
		User currentOrSubstitutedUser = userSession.getCurrentOrSubstitutedUser();
		String curUserLpgin = currentOrSubstitutedUser.getLogin();
		Empl cuEmpl = emplService.getEmplbyLogin(curUserLpgin);
		
		if (cuEmpl == null) {
			String messageText = messages.getMessage(getClass(), "employeeNotFound");
			messageText = messageText.replace("<<empllogin>>" , curUserLpgin);
			
			notifications.create(Notifications.NotificationType.WARNING).
					withCaption(messageText).
					
					show();
			
			return;
		}
		
		screenBuilders.editor(emplsTable).
				newEntity().
				withScreenClass(EmplEdit.class).
				withLaunchMode(OpenMode.NEW_TAB).
				build().
				show();
	
		
		logger.info("onInitEntity end");
	}
	
	
	
	
	
	
	
	
}