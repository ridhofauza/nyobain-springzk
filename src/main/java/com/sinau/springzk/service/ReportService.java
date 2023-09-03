package com.sinau.springzk.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.sinau.springzk.dao.User;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	public byte[] generateReport(String fileReportName, Map<String, Object> params, List<User> users);
	public OutputStream showReportDatasource(String fileReportName, Map<String, Object> params, List<User> users);
}
