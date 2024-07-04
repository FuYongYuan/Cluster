package cn.fyy.member.bean.bo;

import cn.fyy.member.bean.dbo.ManagerDO;
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
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerBO implements Serializable {

    /**
     * 主键ID
     */
    private BigInteger id;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 姓名
     */
    private String managerName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别(0.保密 1.男 2女)
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 账号
     */
    private String account;

    /**
     * 登陆密码
     */
    private String loginPassword;

    /**
     * 最后尝试请求IP
     */
    private String lastAttemptLoginRequestIp;

    /**
     * 最后尝试登陆时间
     */
    private Date lastAttemptLoginTime;

    /**
     * 尝试登陆次数
     */
    private Integer attemptLoginNumber;

    /**
     * 常用请求IP
     */
    private String requestIp;

    /**
     * 当前请求IP
     */
    private String currentRequestIp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人ID
     */
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    private String updaterName;

    /**
     * 状态(0.正常 1.冻结 99.删除)
     */
    private Integer state;

    /**
     * 角色ID集合
     */
    private String roleIds;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 管理员 DO 转换 BO
     */
    public static ManagerBO toBO(ManagerDO dbo) {
        if (dbo != null) {
            return ManagerBO.builder()
                    .id(dbo.getId())
                    .headImgUrl(dbo.getHeadImgUrl())
                    .managerName(dbo.getManagerName())
                    .age(dbo.getAge())
                    .sex(dbo.getSex())
                    .mobile(dbo.getMobile())
                    .mail(dbo.getMail())
                    .account(dbo.getAccount())
                    .loginPassword(dbo.getLoginPassword())
                    .lastAttemptLoginRequestIp(dbo.getLastAttemptLoginRequestIp())
                    .lastAttemptLoginTime(dbo.getLastAttemptLoginTime())
                    .attemptLoginNumber(dbo.getAttemptLoginNumber())
                    .requestIp(dbo.getRequestIp())
                    .currentRequestIp(dbo.getCurrentRequestIp())
                    .remark(dbo.getRemark())
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
     * 管理员 DO 数组 转换 BO List
     */
    public static List<ManagerBO> toBO(ManagerDO[] dbos) {
        if (dbos != null) {
            List<ManagerBO> bos = new ArrayList<>();
            for (ManagerDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 DO List 转换 BO List
     */
    public static List<ManagerBO> toBO(List<ManagerDO> dbos) {
        if (dbos != null) {
            List<ManagerBO> bos = new ArrayList<>();
            for (ManagerDO dbo : dbos) {
                bos.add(toBO(dbo));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 BO 转换 DO
     */
    public static ManagerDO toDO(ManagerBO bo) {
        if (bo != null) {
            return ManagerDO.builder()
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
     * 管理员 BO List 转换 DO List
     */
    public static List<ManagerDO> toDO(List<ManagerBO> bos) {
        if (bos != null) {
            List<ManagerDO> dbos = new ArrayList<>();
            for (ManagerBO bo : bos) {
                dbos.add(toDO(bo));
            }
            return dbos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 BO 转换 DO
     */
    public ManagerDO toDO() {
        return ManagerDO.builder()
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
