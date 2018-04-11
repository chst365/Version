package com.shudailaoshi.manager.generator.java;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.shudailaoshi.manager.generator.base.BaseGenerator;
import com.shudailaoshi.utils.DateUtil;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 生成dao,service,controller
 * 
 * @author Liaoyifan
 * @date 2016年8月21日
 */
public class JavaGenerator extends BaseGenerator {

	public static void main(String[] args) {
		new JavaGenerator().generate("D:\\java", "ver", "need","需求","luohao");
	}

	/**
	 * 生成java
	 * 
	 * @param generatePath
	 * @param moduleName
	 * @param entityName
	 */
	public void generate(String generatePath, String moduleName, String entityName, String serviceName,
			String generatorName) {
		try {
			moduleName = moduleName.toLowerCase();
			entityName = entityName.toUpperCase().charAt(0) + entityName.substring(1);
			String packageName = entityName.toLowerCase().charAt(0) + entityName.substring(1);
			System.out.println(DateUtil.getMillisecondString() + "==>>开始生成" + entityName + "Dao.java");
			this.generateDao(generatePath, moduleName, entityName, packageName, serviceName, generatorName);
			System.out.println(DateUtil.getMillisecondString() + "==>>开始生成" + entityName + "Service.java");
			this.generateService(generatePath, moduleName, entityName, packageName, serviceName, generatorName);
			System.out.println(DateUtil.getMillisecondString() + "==>>开始生成" + entityName + "ServiceImpl.java");
			this.generateServiceImpl(generatePath, moduleName, entityName, packageName, serviceName, generatorName);
			System.out.println(DateUtil.getMillisecondString() + "==>>开始生成" + entityName + "Controller.java");
			this.generateController(generatePath, moduleName, entityName, packageName, serviceName, generatorName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generateDao(String generatePath, String moduleName, String entityName, String packageName, String serviceName,
			String generatorName)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("moduleName", moduleName);
		params.put("entityName", entityName);
		params.put("packageName", packageName);
		params.put("serviceName", serviceName);
		params.put("generatorName", generatorName);
		params.put("currentDate", DateUtil.getDateString());
		System.out.println(DateUtil.getMillisecondString() + "==>>获取" + entityName + "Dao.java模板");
		Template template = super.config.getTemplate("dao.ftl");
		String filePath = generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + "Dao.java";
		System.out.println(DateUtil.getMillisecondString() + "==>>写入" + entityName + "Dao.java");
		template.process(params, new OutputStreamWriter(FileUtils.openOutputStream(new File(filePath))));
		System.out.println(DateUtil.getMillisecondString() + "==>>" + filePath + "生成完毕");
	}

	private void generateService(String generatePath, String moduleName, String entityName, String packageName, String serviceName,
			String generatorName)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("moduleName", moduleName);
		params.put("packageName", packageName);
		params.put("entityName", entityName);
		params.put("serviceName", serviceName);
		params.put("generatorName", generatorName);
		params.put("currentDate", DateUtil.getDateString());
		System.out.println(DateUtil.getMillisecondString() + "==>>获取" + entityName + "Service.java模板");
		Template template = super.config.getTemplate("service.ftl");
		String filePath = generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + "Service.java";
		System.out.println(DateUtil.getMillisecondString() + "==>>写入" + entityName + "Service.java");
		template.process(params, new OutputStreamWriter(FileUtils.openOutputStream(new File(filePath))));
		System.out.println(DateUtil.getMillisecondString() + "==>>" + filePath + "生成完毕");
	}

	private void generateServiceImpl(String generatePath, String moduleName, String entityName, String packageName, String serviceName,
			String generatorName)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("moduleName", moduleName);
		params.put("entityName", entityName);
		params.put("packageName", packageName);
		System.out.println(DateUtil.getMillisecondString() + "==>>获取" + entityName + "ServiceImpl.java模板");
		Template template = super.config.getTemplate("serviceImpl.ftl");
		String filePath = generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + "ServiceImpl.java";
		System.out.println(DateUtil.getMillisecondString() + "==>>写入" + entityName + "ServiceImpl.java");
		template.process(params, new OutputStreamWriter(FileUtils.openOutputStream(new File(filePath))));
		System.out.println(DateUtil.getMillisecondString() + "==>>" + filePath + "生成完毕");
	}

	private void generateController(String generatePath, String moduleName, String entityName, String packageName, String serviceName,
			String generatorName)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("moduleName", moduleName);
		params.put("entityName", entityName);
		params.put("packageName", packageName);
		params.put("serviceName", serviceName);
		params.put("generatorName", generatorName);
		params.put("currentDate", DateUtil.getDateString());
		System.out.println(DateUtil.getMillisecondString() + "==>>获取" + entityName + "Controller.java模板");
		Template template = super.config.getTemplate("controller.ftl");
		String filePath = generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + "Controller.java";
		System.out.println(DateUtil.getMillisecondString() + "==>>写入" + entityName + "Controller.java");
		template.process(params, new OutputStreamWriter(FileUtils.openOutputStream(new File(filePath))));
		System.out.println(DateUtil.getMillisecondString() + "==>>" + filePath + "生成完毕");
	}
}
