package com.sinau.springzk.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.sinau.springzk.dao.User;
import com.sinau.springzk.service.UserServiceImpl;

public class MainListVM {
	
	private ArrayList<User> userData = new ArrayList<User>();
	
	@WireVariable
	private UserServiceImpl userService;

//	@Init
//	public void init(@ExecutionArgParam("userData") ArrayList<User> paramUserData) {
//		userData = paramUserData;
//		System.out.println("User Data:"+userData);
//	}
	
	@Init
	public void init(@BindingParam("params") Map<String, Object> params) {
//		userData = paramUserData;
//		System.out.println("User Data:"+userData);
		
		userData = (ArrayList<User>) params.get("userData");
		System.out.println(userData);
		System.out.println("test list");
	}
	
	@Command("cmdAddData")
	public void btnTambahData(@ContextParam(ContextType.VIEW) Window window) {
		Window winIdentifyUpdate = (Window) Executions.createComponents("/addlist.zul", window, null);
		winIdentifyUpdate.doModal();
	}
	
	@Command
	public void btnEdit(@ContextParam(ContextType.VIEW) Window window, @BindingParam("userId") Integer userId) {
		// Show Modal
		Map<String, Object> paramUserId = new HashMap<String, Object>();
		paramUserId.put("userId", userId);
		System.out.println("userId: " + userId);
		Window winIdentifyUpdate = (Window) Executions.createComponents("/editlist.zul", window, paramUserId);
		//Window winIdentifyUpdate = (Window) Executions.createComponents("/editlist.zul", window, null);
		winIdentifyUpdate.doModal();
	}
	
	@Command
	public void btnDelete(@BindingParam("user") final User user) {
		//Messagebox.show("Apakah Anda yakin ingin menghapus data \"\" ?");
		Messagebox.show("Apakah Anda yakin ingin menghapus data \""+ user.getName() +"\" ?", "Confirmation", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION, new EventListener<Event>() {

			public void onEvent(Event event) throws Exception {
				if(event.getData().equals(Messagebox.OK)) {
					// delete
					userService.delete(user.getId());
					BindUtils.postNotifyChange(null, null, MainListVM.this, "userData");
				} else {
					return;
				}
			}
			
		});
	}
	
	@Command("printIdCard")
	public void generateIdCard(@ContextParam(ContextType.VIEW) Window window, @BindingParam("user") User user) {
		System.out.println("Generate ID Card");
		System.out.println("Generate ID Card: " + user.toString());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("title", user.getName());
		Window winIdentifyUpdate = (Window) Executions.createComponents("/report.zul", window, params);
		winIdentifyUpdate.doModal();
	}
	
	@GlobalCommand
	@NotifyChange("userData")
	public void refreshUserData() {}

	public ArrayList<User> getUserData() {
		return userData;
	}

	public void setUserData(ArrayList<User> userData) {
		this.userData = userData;
	}	
	
}
