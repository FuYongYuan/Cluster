package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.dbo.DataBaseFileDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库设计文件
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseFileBO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 项目群ID
     */
    private Long projectGroupId;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updaterId;

    /**
     * 更新人名称
     */
    private String updaterName;

    /**
     * 状态;（0.正常、99.删除）
     */
    private Byte state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 数据库设计文件 DO 转换 BO
     */
    public static DataBaseFileBO toBO(DataBaseFileDO dbo) {
        if (dbo != null) {
            return DataBaseFileBO.builder()
                    .id(dbo.getId())
                    .projectGroupId(dbo.getProjectGroupId())
                    .fileUrl(dbo.getFileUrl())
                    .createTime(dbo.getCreateTime())
                    .creatorId(dbo.getCreatorId())
                    .creatorName(dbo.getCreatorName())
                    .updateTime(dbo.getUpdateTime())
                    .updaterId(dbo.getUpdaterId())
                    .updaterName(dbo.getUpdaterName())
                    .state(dbo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 DO 数组 转换 BO List
     */
    public static List<DataBaseFileBO> toBO(DataBaseFileDO[] dbos) {
        if (dbos != null) {
            List<DataBaseFileBO> bos = new ArrayList<>();
            for (DataBaseFileDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 DO List 转换 BO List
     */
    public static List<DataBaseFileBO> toBO(List<DataBaseFileDO> dbos) {
        if (dbos != null) {
            List<DataBaseFileBO> bos = new ArrayList<>();
            for (DataBaseFileDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 BO 转换 DO
     */
    public static DataBaseFileDO toDO(DataBaseFileBO bo) {
        if (bo != null) {
            return DataBaseFileDO.builder()
                    .id(bo.getId())
                    .projectGroupId(bo.getProjectGroupId())
                    .fileUrl(bo.getFileUrl())
                    .createTime(bo.getCreateTime())
                    .creatorId(bo.getCreatorId())
                    .creatorName(bo.getCreatorName())
                    .updateTime(bo.getUpdateTime())
                    .updaterId(bo.getUpdaterId())
                    .updaterName(bo.getUpdaterName())
                    .state(bo.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 BO List 转换 DO List
     */
    public static List<DataBaseFileDO> toDO(List<DataBaseFileBO> bos) {
        if (bos != null) {
            List<DataBaseFileDO> dbos = new ArrayList<>();
            for (DataBaseFileBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 BO 转换 DO
     */
    public DataBaseFileDO toDO() {
        return DataBaseFileDO.builder()
                .id(this.getId())
                .projectGroupId(this.getProjectGroupId())
                .fileUrl(this.getFileUrl())
                .createTime(this.getCreateTime())
                .creatorId(this.getCreatorId())
                .creatorName(this.getCreatorName())
                .updateTime(this.getUpdateTime())
                .updaterId(this.getUpdaterId())
                .updaterName(this.getUpdaterName())
                .state(this.getState())
                .build();
    }

}