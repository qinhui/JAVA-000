package com.jkdx.homework.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;



/**
 * @author qinhui
 * @version 1.0 2020/12/2
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware  {




    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    // 如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";

    // 数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<>();

    private static String DB_NAME = "names";
    private static String DB_DEFAULT_VALUE = "custom.datasource";
    @Value("${bi.datasource.defaultname}")
    private String defaultDbname;

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }


    /**
     * 2.0.4 初始化主数据源
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", env.getProperty(DB_DEFAULT_VALUE + "." + "type"));
        dsMap.put("driver-class-name", env.getProperty(DB_DEFAULT_VALUE + "." + "driver-class-name"));
        dsMap.put("url", env.getProperty(DB_DEFAULT_VALUE + "." + "url"));
        dsMap.put("username", env.getProperty(DB_DEFAULT_VALUE + "." + "username"));
        dsMap.put("password", env.getProperty(DB_DEFAULT_VALUE + "." + "password"));
        defaultDataSource = buildDataSource(dsMap);
//        customDataSources.put(defaultDbname,defaultDataSource);//默认数据源放到动态数据源里
//        dataBinder(defaultDataSource, env);
    }



    // 初始化更多数据源
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env,DB_DEFAULT_VALUE+".");
        String dsPrefixs = env.getProperty(DB_DEFAULT_VALUE + "." + DB_NAME);
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            Map<String, Object> dsMap = new HashMap<>();

            dsMap.put("type", env.getProperty(DB_DEFAULT_VALUE + "." +  dsPrefix + ".type"));
            dsMap.put("driver-class-name", env.getProperty(DB_DEFAULT_VALUE + "."  +  dsPrefix + ".driver-class-name"));
            dsMap.put("url", env.getProperty(DB_DEFAULT_VALUE + "."  +  dsPrefix + ".url"));
            dsMap.put("username", env.getProperty(DB_DEFAULT_VALUE + "."  +  dsPrefix + ".username"));
            dsMap.put("password", env.getProperty(DB_DEFAULT_VALUE + "."  +  dsPrefix + ".password"));


            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
        }
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }

    // 创建DataSource
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null)
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }









}
