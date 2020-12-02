package com.jkdx.homework.datasource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 第七周第二课作业
 *基于操作AbstractRoutingDataSource和自定义注解readOnly之类
 * 的，简化自动切换数据源
 * @author qinhui
 * @version 1.0 2020/12/2
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}
