package cn.fyy.member.bean.dto;

import cn.fyy.member.bean.bo.ManagerBO;
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
 * 管理员
 *
 * @author fuyy
 */
@Schema(name = "ManagerInternalDTO", description = "管理员 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerInternalDTO implements Serializable {

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID", type = "BigInteger")
    private BigInteger id;

    /**
     * 头像
     */
    @Schema(name = "headImgUrl", description = "头像", type = "String")
    private String headImgUrl;

    /**
     * 姓名
     */
    @Schema(name = "managerName", description = "姓名", type = "String")
    private String managerName;

    /**
     * 年龄
     */
    @Schema(name = "age", description = "年龄", type = "Integer", nullable = true)
    private Integer age;

    /**
     * 性别(0.保密 1.男 2女)
     */
    @Schema(name = "sex", description = "性别(0.保密 1.男 2女)", type = "Integer")
    private Integer sex;

    /**
     * 手机号码
     */
    @Schema(name = "mobile", description = "手机号码", type = "String", nullable = true)
    private String mobile;

    /**
     * 邮箱
     */
    @Schema(name = "mail", description = "邮箱", type = "String")
    private String mail;

    /**
     * 账号
     */
    @Schema(name = "account", description = "账号", type = "String")
    private String account;

    /**
     * 登陆密码
     */
    @Schema(name = "loginPassword", description = "登陆密码", type = "String")
    private String loginPassword;

    /**
     * 最后尝试请求IP
     */
    @Schema(name = "lastAttemptLoginRequestIp", description = "最后尝试请求IP", type = "String", nullable = true)
    private String lastAttemptLoginRequestIp;

    /**
     * 最后尝试登陆时间
     */
    @Schema(name = "lastAttemptLoginTime", description = "最后尝试登陆时间", type = "Date", nullable = true)
    private Date lastAttemptLoginTime;

    /**
     * 尝试登陆次数
     */
    @Schema(name = "attemptLoginNumber", description = "尝试登陆次数", type = "Integer", nullable = true)
    private Integer attemptLoginNumber;

    /**
     * 常用请求IP
     */
    @Schema(name = "requestIp", description = "常用请求IP", type = "String", nullable = true)
    private String requestIp;

    /**
     * 当前请求IP
     */
    @Schema(name = "currentRequestIp", description = "当前请求IP", type = "String", nullable = true)
    private String currentRequestIp;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注", type = "String", nullable = true)
    private String remark;

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
     * 状态(0.正常 1.冻结 99.删除)
     */
    @Schema(name = "state", description = "状态(0.正常 1.冻结 99.删除)", type = "Integer")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 管理员 BO 转换 DTO
     */
    public static ManagerInternalDTO toDTO(ManagerBO bo) {
        if (bo != null) {
            return ManagerInternalDTO.builder()
                    .id(bo.getId())
                    .headImgUrl(bo.getHeadImgUrl())
                    .managerName(bo.getManagerName())
                    .age(bo.getAge())
                    .sex(bo.getSex())
                    .mobile(bo.getMobile())
                    .mail(bo.getMail())
                    .account(bo.getAccount())
                    .loginPassword(bo.getLoginPassword())
                    .lastAttemptLoginRequestIp(bo.getLastAttemptLoginRequestIp())
                    .lastAttemptLoginTime(bo.getLastAttemptLoginTime())
                    .attemptLoginNumber(bo.getAttemptLoginNumber())
                    .requestIp(bo.getRequestIp())
                    .currentRequestIp(bo.getCurrentRequestIp())
                    .remark(bo.getRemark())
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
     * 管理员 BO 数组 转换 DTO List
     */
    public static List<ManagerInternalDTO> toDTO(ManagerBO[] bos) {
        if (bos != null) {
            List<ManagerInternalDTO> dtos = new ArrayList<>();
            for (ManagerBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 BO List 转换 DTO List
     */
    public static List<ManagerInternalDTO> toDTO(List<ManagerBO> bos) {
        if (bos != null) {
            List<ManagerInternalDTO> dtos = new ArrayList<>();
            for (ManagerBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 DTO 转换 BO
     */
    public static ManagerBO toBO(ManagerInternalDTO dto) {
        if (dto != null) {
            return ManagerBO.builder()
                    .id(dto.getId())
                    .headImgUrl(dto.getHeadImgUrl())
                    .managerName(dto.getManagerName())
                    .age(dto.getAge())
                    .sex(dto.getSex())
                    .mobile(dto.getMobile())
                    .mail(dto.getMail())
                    .account(dto.getAccount())
                    .loginPassword(dto.getLoginPassword())
                    .lastAttemptLoginRequestIp(dto.getLastAttemptLoginRequestIp())
                    .lastAttemptLoginTime(dto.getLastAttemptLoginTime())
                    .attemptLoginNumber(dto.getAttemptLoginNumber())
                    .requestIp(dto.getRequestIp())
                    .currentRequestIp(dto.getCurrentRequestIp())
                    .remark(dto.getRemark())
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
     * 管理员 DTO List 转换 BO List
     */
    public static List<ManagerBO> toBO(List<ManagerInternalDTO> dtos) {
        if (dtos != null) {
            List<ManagerBO> bos = new ArrayList<>();
            for (ManagerInternalDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 DTO 转换 BO
     */
    public ManagerBO toBO() {
        return ManagerBO.builder()
                .id(this.getId())
                .headImgUrl(this.getHeadImgUrl())
                .managerName(this.getManagerName())
                .age(this.getAge())
                .sex(this.getSex())
                .mobile(this.getMobile())
                .mail(this.getMail())
                .account(this.getAccount())
                .loginPassword(this.getLoginPassword())
                .lastAttemptLoginRequestIp(this.getLastAttemptLoginRequestIp())
                .lastAttemptLoginTime(this.getLastAttemptLoginTime())
                .attemptLoginNumber(this.getAttemptLoginNumber())
                .requestIp(this.getRequestIp())
                .currentRequestIp(this.getCurrentRequestIp())
                .remark(this.getRemark())
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
