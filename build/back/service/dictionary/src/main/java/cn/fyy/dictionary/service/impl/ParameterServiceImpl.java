package cn.fyy.dictionary.service.impl;

import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.util.BeanUtil;
import cn.fyy.common.util.PageUtil;
import cn.fyy.dictionary.bean.bo.ParameterBO;
import cn.fyy.dictionary.bean.bo.ParameterExcel;
import cn.fyy.dictionary.bean.po.ParameterPO;
import cn.fyy.dictionary.feign.client.member.ManagerFeignClient;
import cn.fyy.dictionary.repository.ParameterRepository;
import cn.fyy.dictionary.service.ParameterService;
import excel.operation.ExcelExport;
import excel.operation.set.SheetSet;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 参数 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class ParameterServiceImpl implements ParameterService {

    //------------------------------------------------------------------------------------------------------------------Repository
    /**
     * 参数 Repository
     */
    @Resource
    private ParameterRepository parameterRepository;

    //------------------------------------------------------------------------------------------------------------------feign
    /**
     * 管理员
     */
    @Resource
    private ManagerFeignClient managerFeignClient;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 参数 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(ParameterBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            ParameterBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改参数错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 参数 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public ParameterBO save(ParameterBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            ParameterPO po;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                po = ParameterBO.toPO(bo);
            } else {
                ParameterPO old = parameterRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                old.setParameterValue(StringUtils.hasText(bo.getParameterValue()) ? bo.getParameterValue() : null);
                old.setParameterExplain(StringUtils.hasText(bo.getParameterExplain()) ? bo.getParameterExplain() : null);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                po = old;
            }

            return ParameterBO.toBO(parameterRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改参数错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 参数
     */
    @Override
    public ParameterBO getById(Long id) throws BusinessException {
        try {
            return ParameterBO.toBO(parameterRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 根据参数编号查询
     *
     * @param parameterCode 参数编号
     * @return 参数
     */
    @Override
    public ParameterBO getByParameterCode(String parameterCode) throws BusinessException {
        try {
            return ParameterBO.toBO(parameterRepository.getByParameterCodeAndState(parameterCode, DataState.NORMAL.getCode()));
        } catch (Exception e) {
            throw new BusinessException("根据参数编号查询错误", e);
        }
    }

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            return parameterRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据参数编码、参数名称查询参数列表
     *
     * @param currentPage   当前页
     * @param eachPageSize  每页多少条
     * @param pageSort      排序
     * @param parameterCode 参数编码
     * @param parameterName 参数名称
     * @param state         状态
     * @return TParameterBO 参数对象
     * @throws BusinessException 错误
     */
    @Override
    public Page<ParameterBO> queryByParameterCodeAndParameterNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String parameterCode,
            String parameterName,
            Byte state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<ParameterPO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));
                if (StringUtils.hasText(parameterCode)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("parameterCode"), "%" + parameterCode + "%"));
                }
                if (StringUtils.hasText(parameterName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("parameterName"), "%" + parameterName + "%"));
                }
                query.where(predicate);
                // 排序拼装
                query.orderBy(PageUtil.getSort(root, criteriaBuilder, pageSort));
                // 生成拼装结果
                return query.getRestriction();
            };
            // 分页信息
            Pageable pageable = PageRequest.of(currentPage, eachPageSize);
            // 执行查询
            Page<ParameterPO> doPage = parameterRepository.findAll(specification, pageable);
            // 返回结果 执行查询
            return new PageImpl<>(ParameterBO.toBO(doPage.getContent()), doPage.getPageable(), doPage.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("根据参数编码、参数名称查询参数列表", e);
        }
    }

    /**
     * 根据参数编码、参数名称查询参数列表
     *
     * @param pageSort      排序
     * @param parameterCode 参数编码
     * @param parameterName 参数名称
     * @param state         状态
     * @return TParameterBO 参数对象
     * @throws BusinessException 错误
     */
    @Override
    public ExcelExport queryExportByParameterCodeAndParameterNameAndState(
            String pageSort,
            String parameterCode,
            String parameterName,
            Byte state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<ParameterPO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));
                if (StringUtils.hasText(parameterCode)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("parameterCode"), "%" + parameterCode + "%"));
                }
                if (StringUtils.hasText(parameterName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("parameterName"), "%" + parameterName + "%"));
                }
                query.where(predicate);
                // 排序拼装
                query.orderBy(PageUtil.getSort(root, criteriaBuilder, pageSort));
                // 生成拼装结果
                return query.getRestriction();
            };
            // 执行全量查询
            List<ParameterPO> doList = parameterRepository.findAll(specification);
            // 拼装Excel导出内容
            ExcelExport excelExport = new ExcelExport(new XSSFWorkbook());
            // 导出页签
            List<SheetSet> sheetSets = new ArrayList<>();
            // 导出数据
            List<ParameterExcel> excelList = ParameterExcel.toExcel(ParameterBO.toBO(doList));
            // 页签初始化
            SheetSet sheetSet = SheetSet.create()
                    .setSheetName("参数")
                    .setSheetData(excelList)
                    .setDataClass(ParameterExcel.class)
                    .build(excelExport.getWorkbook());
            sheetSets.add(sheetSet);
            // 添加导出页签集合
            excelExport.setExcel(sheetSets);

            // 返回结果 执行查询
            return excelExport;
        } catch (Exception e) {
            throw new BusinessException("根据参数编码、参数名称查询参数列表并导出", e);
        }
    }
}
