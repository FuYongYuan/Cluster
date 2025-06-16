package cn.fyy.message.service.impl;

import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.message.bean.bo.NoticeBO;
import cn.fyy.message.bean.dbo.NoticeDO;
import cn.fyy.message.repository.NoticeRepository;
import cn.fyy.message.service.NoticeService;
import dispose.CopyClass;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * 公告 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    /**
     * 公告 Repository
     */
    @Resource
    private NoticeRepository noticeRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 公告 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(NoticeBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException {
        try {
            NoticeBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改公告错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 公告 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public NoticeBO save(NoticeBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            NoticeDO dbo;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                dbo = NoticeBO.toDO(bo);
            } else {
                NoticeDO old = noticeRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                CopyClass.copyClassGetSet(bo, old, NoticeDO.class, getNull);
                old.setNoticeTitle(StringUtils.hasText(bo.getNoticeTitle()) ? bo.getNoticeTitle() : null);
                old.setNoticeContent(StringUtils.hasText(bo.getNoticeContent()) ? bo.getNoticeContent() : null);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                dbo = old;
            }

            return NoticeBO.toBO(noticeRepository.save(dbo));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改公告错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 公告
     */
    @Override
    public NoticeBO getById(BigInteger id) throws BusinessException {
        try {
            return NoticeBO.toBO(noticeRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int updateDelete(String ids, BigInteger currentManagerId, String currentManagerName) throws BusinessException {
        try {
            return noticeRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(BigInteger::new).toList());
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }
}
