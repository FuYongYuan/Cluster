package cn.fyy.database.config.data.routing;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由
 *
 * @author fuyy
 */
public class DataSourceRouting extends AbstractRoutingDataSource {
    /**
     * 数据源集合
     */
    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获取默认数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 数据源类型
     */
    public enum DataSourceType {
        /**
         * 写连接
         */
        WriteDataSource,
        /**
         * 读连接
         */
        ReadDataSource
    }

    /**
     * 写数据源
     */
    public static void writeDataSource() {
        CONTEXT_HOLDER.set(DataSourceType.WriteDataSource);
    }

    /**
     * 读数据源
     */
    public static void readDataSource() {
        CONTEXT_HOLDER.set(DataSourceType.ReadDataSource);
    }

    /**
     * 设置数据源类型
     */
    public static void setDataSourceType(DataSourceType type) {
        CONTEXT_HOLDER.set(type);
    }

    /**
     * 获取默认数据源类型
     */
    public static DataSourceType getType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清理数据源
     */
    public static void cleanAll() {
        CONTEXT_HOLDER.remove();
    }
}
