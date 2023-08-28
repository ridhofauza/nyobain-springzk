package com.sinau.springzk.viewmodel;

import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.sinau.springzk.dao.User;
import com.sinau.springzk.service.UserServiceImpl;
import com.sinau.springzk.utils.GenderEnum;

public class EditVM {
	
	private User user = new User();
	private Integer userId;
	private ListModelList<GenderEnum> genderItem = new ListModelList<GenderEnum>(GenderEnum.values());
	
	@WireVariable
	private UserServiceImpl userService;

//	@Init
//	public void init(@ExecutionArgParam("userId") Integer userId) {
//		User user = userService.findById(userId);
//		this.user = user;
//		System.out.println("userId: " + userId);
//	}
	
	@AfterCompose
	public void doAfterCompose(@ExecutionArgParam("userId") Integer userId) {
		this.userId = userId;
		User user = userService.findById(userId);
		this.user = user;
		System.out.println("userId: " + userId);
	}
	
	@Command
	public void btnUpdate(@ContextParam(ContextType.VIEW) final Window window) {
		userService.update(userId, user);
		System.out.println("Name: " + user.getName());
		// show message box
		Messagebox.show("Data berhasil diupdate", null, Messagebox.OK, Messagebox.INFORMATION, new EventListener<Event>() {

			public void onEvent(Event event) throws Exception {
				if(event.getData().equals(Messagebox.OK)) {
					window.detach();
				}
			}
			
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ListModelList<GenderEnum> getGenderItem() {
		return genderItem;
	}
	
	
	
	
}
