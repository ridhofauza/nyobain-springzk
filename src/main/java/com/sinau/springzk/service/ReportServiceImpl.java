package com.sinau.springzk.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.util.ResourceUtils;

import com.sinau.springzk.dao.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ReportServiceImpl implements ReportService {

	public byte[] generateReport(String fileReportName, Map<String, Object> params, List<User> users) {
		ByteArrayOutputStream os;
		os = (ByteArrayOutputStream) showReportDatasource(fileReportName, params, users);
		Validate.notNull(os);
		byte[] datastream = os.toByteArray();
		Validate.notNull(datastream);
		return datastream;
	}

	public OutputStream showReportDatasource(String fileReportName, Map<String, Object> params, List<User> users) {
		try {
			InputStream in;
			in = ResourceUtils.getURL("classpath:reports/" + fileReportName).openStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(users);
			JasperPrint jasperPrint = JasperFillManager.fillReport(in, params, beanColDataSource);
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
			exporter.exportReport();
			return os;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
