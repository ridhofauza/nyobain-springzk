package com.sinau.springzk.viewmodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.util.ResourceUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.ContentTypes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Iframe;

import com.sinau.springzk.dao.User;
import com.sinau.springzk.service.ReportServiceImpl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ReportVM {

	@Wire
	private Iframe reportframe;

	private String titleIdCard;
	private User user;

	@WireVariable
	private ReportServiceImpl reportService;

	@Init
	public void init(@ExecutionArgParam("user") User paramUser, @ExecutionArgParam("title") String paramTitle) {
		this.user = paramUser;
		titleIdCard = paramTitle;
		System.out.println("User: " + paramUser.toString());
	}

	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		byte[] byteArrays = generateReport();
		System.out.println("BYTE ARR: " + byteArrays.toString());
		if (byteArrays != null) {
			AMedia amedia = new AMedia("idcard.pdf", "pdf", ContentTypes.getContentType("pdf"),
					new ByteArrayInputStream(byteArrays));
			reportframe.setContent(amedia);
		}
	}

	private byte[] generateReport() {
		try {
			List<User> users = new ArrayList<User>();
			users.add(this.user);
			return reportService.generateReport("idcard.jasper", null, users);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failure: " + e.getCause());
		}
		return null;
	}

	@Command
	public void download() throws JRException {
		System.out.println("Download ID Card");
	}

	public String getTitleIdCard() {
		return "ID Card " + titleIdCard;
	}

}
