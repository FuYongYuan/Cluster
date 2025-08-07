package cn.fyy.member.bean.bo;

import cn.fyy.member.bean.po.ManagerPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Long id;

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
    private Byte age;

    /**
     * 性别;（0.保密、1.男、2女）
     */
    private Byte sex;

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
    private LocalDateTime lastAttemptLoginTime;

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
     * 状态(0.正常 1.冻结 99.删除)
     */
    private Byte state;

    /**
     * 角色ID集合
     */
    private String roleIds;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 管理员 PO 转换 BO
     */
    public static ManagerBO toBO(ManagerPO po) {
        if (po != null) {
            return ManagerBO.builder()
                    .id(po.getId())
                    .headImgUrl(po.getHeadImgUrl())
                    .managerName(po.getManagerName())
                    .age(po.getAge())
                    .sex(po.getSex())
                    .mobile(po.getMobile())
                    .mail(po.getMail())
                    .account(po.getAccount())
                    .loginPassword(po.getLoginPassword())
                    .lastAttemptLoginRequestIp(po.getLastAttemptLoginRequestIp())
                    .lastAttemptLoginTime(po.getLastAttemptLoginTime())
                    .attemptLoginNumber(po.getAttemptLoginNumber())
                    .requestIp(po.getRequestIp())
                    .currentRequestIp(po.getCurrentRequestIp())
                    .remark(po.getRemark())
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
     * 管理员 PO 数组 转换 BO List
     */
    public static List<ManagerBO> toBO(ManagerPO[] pos) {
        if (pos != null) {
            List<ManagerBO> bos = new ArrayList<>();
            for (ManagerPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 PO List 转换 BO List
     */
    public static List<ManagerBO> toBO(List<ManagerPO> pos) {
        if (pos != null) {
            List<ManagerBO> bos = new ArrayList<>();
            for (ManagerPO po : pos) {
                bos.add(toBO(po));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 BO 转换 PO
     */
    public static ManagerPO toPO(ManagerBO bo) {
        if (bo != null) {
            return ManagerPO.builder()
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
     * 管理员 BO List 转换 PO List
     */
    public static List<ManagerPO> toPO(List<ManagerBO> bos) {
        if (bos != null) {
            List<ManagerPO> pos = new ArrayList<>();
            for (ManagerBO bo : bos) {
                pos.add(toPO(bo));
            }
            return pos;
        } else {
            return null;
        }
    }

    /**
     * 管理员 BO 转换 PO
     */
    public ManagerPO toPO() {
        return ManagerPO.builder()
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
