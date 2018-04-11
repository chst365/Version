package com.shudailaoshi.manager.generator.entity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.shudailaoshi.manager.generator.base.BaseGenerator;
import com.shudailaoshi.utils.DateUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成Entity和Mapper
 * 
 * @author Liaoyifan
 * @date 2016年8月18日
 *
 */
public class EntityGenerator extends BaseGenerator {

	public static void main(String[] args) {
		new EntityGenerator().generate("D:\\java", "ver_need","luohao");
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	private Connection getConnection() {
		try {
			String driverClassName = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://101.132.130.149:3306/sdls_version?useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
			String username = "aaabbbfff";
			String password = "aaabbb";
			Class.forName(driverClassName);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成entity和mapper
	 * 
	 * @param generatePath
	 * @param tableName
	 */
	public void generate(String generatePath, String tableName,
			String generatorName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			tableName = tableName.toLowerCase();
			String moduleName = tableName.substring(0, tableName.indexOf("_"));
			String entityName = this.getEntityName(tableName);
			String packageName = entityName.toLowerCase().charAt(0) + entityName.substring(1);
			conn = this.getConnection();
			System.out.println(DateUtil.getMillisecondString() + "==>>获取数据库连接成功");
			ps = conn.prepareStatement("SHOW FULL COLUMNS FROM " + tableName);
			rs = ps.executeQuery();
			List<ColumnVo> columns = new ArrayList<ColumnVo>();
			while (rs.next()) {
				ColumnVo vo = new ColumnVo();
				vo.setName(rs.getString("Field"));
				vo.setComment(rs.getString("Comment"));
				vo.setType(rs.getString("Type"));
				columns.add(vo);
			}
			System.out.println(DateUtil.getMillisecondString() + "==>>开始生成" + entityName + ".java");
			this.generateEntity(generatePath, tableName, moduleName, entityName, columns, generatorName);
			System.out.println(DateUtil.getMillisecondString() + "==>>开始生成" + entityName + "Mapper.xml");
			this.generateMapper(generatePath, moduleName, packageName, entityName, columns, tableName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void generateEntity(String generatePath, String tableName, String moduleName, String entityName,
			List<ColumnVo> columns,String generatorName) throws IOException, TemplateException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("moduleName", moduleName);
		params.put("tableName", tableName);
		params.put("entityName", entityName);
		params.put("generatorName", generatorName);
		params.put("currentDate", DateUtil.getDateString());
		System.out.println(DateUtil.getMillisecondString() + "==>>生成" + entityName + "字段");
		StringBuilder imports = new StringBuilder();
		StringBuilder efields = new StringBuilder();
		StringBuilder qfields = new StringBuilder();
		StringBuilder getSets = new StringBuilder();
		for (int i = 0; i < columns.size(); i++) {
			String columnName = columns.get(i).getName();
			if (!columnName.equals("id") && !columnName.equals("status") && !columnName.equals("create_time")
					&& !columnName.equals("modify_time")) {
				String fieldName = this.getFieldName(columnName);
				String javaType = this.getJavaType(columns.get(i).getType());
				// imports
				if (javaType.equals("BigDecimal")) {
					imports.append("import java.math.BigDecimal;");
				}
				efields.append("@Comment(\"" + columns.get(i).getComment() + "\")\r\n\t");
				qfields.append("@Comment(\"" + columns.get(i).getComment() + "\")\r\n\t");
				// fields
				efields.append("private " + javaType + " " + fieldName + ";");
				qfields.append("private " + javaType + " " + fieldName + ";");
				// getSets
				String upperFieldName = fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
				getSets.append("public " + javaType + " get" + upperFieldName + "() {\r\n\t\t");
				getSets.append("return " + fieldName + ";\r\n\t}\r\n\t");
				getSets.append("public void set" + upperFieldName + "(" + javaType + " " + fieldName + ") {\r\n\t\t");
				getSets.append("this." + fieldName + " = " + fieldName + ";\r\n\t}");
				if (columns.get(columns.size() - 1).getName().equals("status")
						|| columns.get(columns.size() - 1).getName().equals("id")) {
					if (i != columns.size() - 2) {
						// imports
						if (javaType.equals("BigDecimal")) {
							imports.append("\r\n");
						}
						efields.append("\r\n\t");
						qfields.append("\r\n\t");
						getSets.append("\r\n\t");
					}
				} else {
					if (i != columns.size() - 1) {
						// imports
						if (javaType.equals("BigDecimal")) {
							imports.append("\r\n");
						}
						efields.append("\r\n\t");
						qfields.append("\r\n\t");
						getSets.append("\r\n\t");
					}
				}
			}
		}
		params.put("imports", imports.toString());
		params.put("efields", efields.toString());
		params.put("qfields", qfields.toString());
		params.put("getSets", getSets.toString());

		super.config.getTemplate("entity.ftl").process(params, new OutputStreamWriter(FileUtils.openOutputStream(
				new File(generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + ".java"))));
		System.out.println(DateUtil.getMillisecondString() + "==>> entity.ftl 生成完毕");

		super.config.getTemplate("query.ftl").process(params, new OutputStreamWriter(FileUtils.openOutputStream(
				new File(generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + "Query.java"))));
		System.out.println(DateUtil.getMillisecondString() + "==>> query.ftl 生成完毕");
	}

	private void generateMapper(String generatePath, String moduleName, String packageName, String entityName,
			List<ColumnVo> columns, String tableName) throws IOException, TemplateException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("moduleName", moduleName);
		params.put("packageName", packageName);
		params.put("entityName", entityName);
		params.put("tableName", tableName);
		params.put("limit", "#{start},#{limit}");
		params.put("statusEnumDELETE", "${@com.qingtuanjian.entity.enums.StatusEnum@DELETE.getValue()}");
		System.out.println(DateUtil.getMillisecondString() + "==>>生成" + entityName + "Mapper.xml映射");
		StringBuilder results = new StringBuilder();
		StringBuilder wheres = new StringBuilder();
		List<String> columnList = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			String columnName = columns.get(i).getName();
			String columnType = this.getMapperType(columns.get(i).getType());
			String fieldName = this.getFieldName(columnName);
			if (columnName.equals("id")) {
				results.append("<id column=\"id\" property=\"id\" jdbcType=\"BIGINT\" />");
			} else {
				results.append("<result column=\"" + columnName + "\" property=\"" + fieldName + "\" jdbcType=\""
						+ columnType + "\" />");
			}
			if (i != columns.size() - 1) {
				results.append("\r\n\t\t");
			}
			if (!fieldName.equals("createTime") && !fieldName.equals("status") && !fieldName.equals("modifyTime")
					&& !fieldName.equals("id")) {
				if (columnType.equalsIgnoreCase("varchar") || columnType.equalsIgnoreCase("char")) {
					wheres.append("<if test=\"@com.qingtuanjian.utils.ValidUtil@isNotBlank(" + packageName + "Query."
							+ fieldName + ")\">\r\n\t\t\t\t\t");
					wheres.append(" and " + columnName + " like concat('%',#{" + packageName + "Query." + fieldName
							+ "},'%') \r\n\t\t\t\t");
					wheres.append("</if>\r\n\t\t\t\t");
				} else {
					wheres.append("<if test=\"" + packageName + "Query." + this.getFieldName(columnName)
							+ "!=null\">\r\n\t\t\t\t\t");
					wheres.append(
							" and " + columnName + " = #{" + packageName + "Query." + fieldName + "} \r\n\t\t\t\t");
					wheres.append("</if>\r\n\t\t\t\t");
				}
			}
			columnList.add(columnName);
		}
		params.put("wheres", wheres.toString());
		params.put("results", results.toString());
		params.put("columns", StringUtils.join(columnList, ","));
		params.put("startDate", "#{" + packageName + "Query.startDate}");
		params.put("endDate", "#{" + packageName + "Query.endDate}");
		System.out.println(DateUtil.getMillisecondString() + "==>>获取" + entityName + "Mapper.xml模板");
		Template template = super.config.getTemplate("mapper.ftl");
		String filePath = generatePath + "/" + this.getClass().getSimpleName() + "/" + entityName + "Mapper.xml";
		System.out.println(DateUtil.getMillisecondString() + "==>>写入" + entityName + "Mapper.xml");
		template.process(params, new OutputStreamWriter(FileUtils.openOutputStream(new File(filePath))));
		System.out.println(DateUtil.getMillisecondString() + "==>>" + filePath + "生成完毕");
	}

	private String getEntityName(String tableName) {
		String[] splits = tableName.substring(tableName.indexOf("_") + 1).split("_");
		StringBuilder sb = new StringBuilder();
		for (String str : splits)
			sb.append(str.toUpperCase().charAt(0) + str.toLowerCase().substring(1));
		return sb.toString();
	}

	private String getFieldName(String columnName) {
		String[] splits = columnName.split("_");
		StringBuilder sb = new StringBuilder();
		for (String str : splits)
			sb.append(str.toUpperCase().charAt(0) + str.toLowerCase().substring(1));
		String name = sb.toString();
		return name.toLowerCase().charAt(0) + name.substring(1);
	}

	private String getJavaType(String columnType) {
		switch (columnType) {
		case "double":
			return "Double";
		case "longtext":
			return "String";
		default:
			switch (columnType.substring(0, columnType.indexOf("("))) {
			case "varchar":
				return "String";
			case "int":
				return "Integer";
			case "bigint":
				return "Long";
			case "bit":
				return "Boolean";
			case "decimal":
				return "BigDecimal";
			case "char":
				return "String";
			default:
				return "NOT_FOUND";
			}
		}
	}

	private String getMapperType(String columnType) {
		switch (columnType) {
		case "double":
			return "DOUBLE";
		case "longtext":
			return "VARCHAR";
		default:
			switch (columnType.substring(0, columnType.indexOf("("))) {
			case "varchar":
				return "VARCHAR";
			case "int":
				return "INTEGER";
			case "bigint":
				return "BIGINT";
			case "bit":
				return "BIT";
			case "decimal":
				return "DECIMAL";
			case "char":
				return "CHAR";
			default:
				return "NOT_FOUND";
			}
		}
	}

	private class ColumnVo {

		private String comment;
		private String name;
		private String type;

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

}
