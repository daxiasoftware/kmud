package com.daxia.mud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 测试生成代码
 * </p>
 *
 * @author K神
 * @date 2017/12/18
 */
public class GeneratorServiceEntity {
	private static String projectDir = "/Users/kevin/git/kmud";
	private static String sourceDir = "/Users/kevin/git/kmud/src/main/java";
	private static String table = "bag";
	private static String Model = getModel(table);
	
	public static void main(String[] args) throws Exception {
		new GeneratorServiceEntity().generateCode();
	}

    private static String getModel(String t) {
    	String s = "";
    	String[] arr = t.split("_");
    	for (String string : arr) {
			s += org.apache.commons.lang3.StringUtils.capitalize(string);
		}
		return s;
	}

	public void generateCode() {
        String packageName = "com.daxia.mud";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, packageName, table);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/mud";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("sa")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                //.setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        	
        config.setActiveRecord(true)
                .setAuthor("daxia")
                .setOutputDir("/Users/kevin/git/kmud/src/main/java")
                .setOpen(false)
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
        
        try {
        	new File(sourceDir + "/com/daxia/mud/service/" + Model + "Service.java").delete();
			Process p = Runtime.getRuntime().exec("rm -rf " + sourceDir + "/com/daxia/mud/service/impl");
			p.waitFor();
        	
        	String content = FileUtils.readFileToString(new File(projectDir + "/src/main/resources/ModelService.txt"), "utf-8");
        	content = content.replace("{Model}", Model);
        	FileUtils.write(new File(sourceDir + "/com/daxia/mud/service/" + Model + "Service.java"), content, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
