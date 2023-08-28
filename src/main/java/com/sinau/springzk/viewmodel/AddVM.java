package com.sinau.springzk.viewmodel;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.sinau.springzk.dao.User;
import com.sinau.springzk.service.UserServiceImpl;
import com.sinau.springzk.utils.GenderEnum;

public class AddVM {
	private User user = new User();
	private ListModelList<GenderEnum> genderItem = new ListModelList<GenderEnum>(GenderEnum.values());
	
	@WireVariable
	private UserServiceImpl userService;
	
	@Command
	public void btnSave(@ContextParam(ContextType.VIEW) Window window) {
		List<User> users = userService.findAll();
		Integer lastId = 0;
		for(User objUser : users) {
			lastId = objUser.getId();
		}
		user.setId(lastId+1);
		userService.save(user);
		System.out.println(userService.findAll().toString());
		
		BindUtils.postGlobalCommand(null, null, "refreshUserData", null);
		Messagebox.show("Data berhasil disimpan", null, Messagebox.OK, Messagebox.INFORMATION);
		window.detach();
	}
	
	@GlobalCommand
	@NotifyChange("userData")
	public void refreshUserData() {}

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
