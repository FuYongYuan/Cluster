package cn.fyy.builder.bean.bo;

import cn.fyy.builder.bean.po.DataBaseFilePO;
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
     * 数据库设计文件 PO 转换 BO
     */
    public static DataBaseFileBO toBO(DataBaseFilePO po) {
        if (po != null) {
            return DataBaseFileBO.builder()
                    .id(po.getId())
                    .projectGroupId(po.getProjectGroupId())
                    .fileUrl(po.getFileUrl())
                    .createTime(po.getCreateTime())
                    .creatorId(po.getCreatorId())
                    .creatorName(po.getCreatorName())
                    .updateTime(po.getUpdateTime())
                    .updaterId(po.getUpdaterId())
                    .updaterName(po.getUpdaterName())
                    .state(po.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 PO 数组 转换 BO List
     */
    public static List<DataBaseFileBO> toBO(DataBaseFilePO[] pos) {
        if (pos != null) {
            List<DataBaseFileBO> bos = new ArrayList<>();
            for (DataBaseFilePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 PO List 转换 BO List
     */
    public static List<DataBaseFileBO> toBO(List<DataBaseFilePO> pos) {
        if (pos != null) {
            List<DataBaseFileBO> bos = new ArrayList<>();
            for (DataBaseFilePO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 BO 转换 PO
     */
    public static DataBaseFilePO toPO(DataBaseFileBO bo) {
        if (bo != null) {
            return DataBaseFilePO.builder()
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
     * 数据库设计文件 BO List 转换 PO List
     */
    public static List<DataBaseFilePO> toPO(List<DataBaseFileBO> bos) {
        if (bos != null) {
            List<DataBaseFilePO> pos = new ArrayList<>();
            for (DataBaseFileBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 BO 转换 PO
     */
    public DataBaseFilePO toPO() {
        return DataBaseFilePO.builder()
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