package com.qzhp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Collections;

/**
 * 代码生成器
 *
 * @author qcb
 * @date 2022/05/21 16:46.
 */
public class Generator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:sqlserver://qzhp.db.svr;DatabaseName=qzhp;integratedSecurity=false;", "sa", "~lyj!1983519")
                .globalConfig(builder -> {
                    builder.author("mrqin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("F:\\Projects\\evaluation\\code\\server\\generate"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.qzhp") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .entity("entity.po")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("dao")
                            .xml("dao.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "F:\\Projects\\evaluation\\code\\server\\generate")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    builder.mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sDao")
                            .formatXmlFileName("%sMapper")
                            .build();
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.ENTITY)
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .xml("/templates/mapper.xml")
                            .controller("/templates/controller.java")
                            .build();
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
