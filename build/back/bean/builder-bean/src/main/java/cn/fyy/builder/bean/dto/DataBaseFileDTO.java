package cn.fyy.builder.bean.dto;

import cn.fyy.builder.bean.bo.DataBaseFileBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库设计文件
 *
 * @author fuyy
 */
@Schema(name = "data_base_file", description = "数据库设计文件 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseFileDTO implements Serializable {
    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger", nullable = false)
    private BigInteger id;

    /**
     * 项目群ID
     */
    @Schema(name = "projectGroupId", description = "项目群ID", type = "BigInteger")
    private BigInteger projectGroupId;

    /**
     * 文件地址
     */
    @Schema(name = "fileUrl", description = "文件地址", type = "String")
    private String fileUrl;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间", type = "Date")
    private Date createTime;

    /**
     * 创建人ID
     */
    @Schema(name = "creatorId", description = "创建人ID", type = "BigInteger")
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    @Schema(name = "creatorName", description = "创建人名称", type = "String")
    private String creatorName;

    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间", type = "Date")
    private Date updateTime;

    /**
     * 修改人ID
     */
    @Schema(name = "updaterId", description = "修改人ID", type = "BigInteger")
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    @Schema(name = "updaterName", description = "修改人名称", type = "String")
    private String updaterName;

    /**
     * 状态(0.正常 99.删除)
     */
    @Schema(name = "state", description = "状态(0.正常 99.删除)", type = "Integer")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 数据库设计文件 BO 转换 DTO
     */
    public static DataBaseFileDTO toDTO(DataBaseFileBO bo) {
        if (bo != null) {
            return DataBaseFileDTO.builder()
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
     * 数据库设计文件 BO 数组 转换 DTO List
     */
    public static List<DataBaseFileDTO> toDTO(DataBaseFileBO[] bos) {
        if (bos != null) {
            List<DataBaseFileDTO> dtos = new ArrayList<>();
            for (DataBaseFileBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 BO List 转换 DTO List
     */
    public static List<DataBaseFileDTO> toDTO(List<DataBaseFileBO> bos) {
        if (bos != null) {
            List<DataBaseFileDTO> dtos = new ArrayList<>();
            for (DataBaseFileBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 DTO 转换 BO
     */
    public static DataBaseFileBO toBO(DataBaseFileDTO dto) {
        if (dto != null) {
            return DataBaseFileBO.builder()
                    .id(dto.getId())
                    .projectGroupId(dto.getProjectGroupId())
                    .fileUrl(dto.getFileUrl())
                    .createTime(dto.getCreateTime())
                    .creatorId(dto.getCreatorId())
                    .creatorName(dto.getCreatorName())
                    .updateTime(dto.getUpdateTime())
                    .updaterId(dto.getUpdaterId())
                    .updaterName(dto.getUpdaterName())
                    .state(dto.getState())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 DTO List 转换 BO List
     */
    public static List<DataBaseFileBO> toBO(List<DataBaseFileDTO> dtos) {
        if (dtos != null) {
            List<DataBaseFileBO> bos = new ArrayList<>();
            for (DataBaseFileDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 数据库设计文件 DTO 转换 BO
     */
    public DataBaseFileBO toBO() {
        return DataBaseFileBO.builder()
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