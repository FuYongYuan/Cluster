package cn.fyy.dictionary.service;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.dictionary.bean.bo.ParameterBO;
import excel.operation.ExcelExport;
import org.springframework.data.domain.Page;

import java.math.BigInteger;

/**
 * 参数 Service
 *
 * @author fuyy
 */
public interface ParameterService {
    /**
     * 新增或者修改
     *
     * @param bo                 参数 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(ParameterBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    int updateDelete(String ids, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 新增或者修改
     *
     * @param bo                 管理员 BO
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ParameterBO save(ParameterBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 参数
     * @throws BusinessException 错误
     */
    ParameterBO getById(BigInteger id) throws BusinessException;

    /**
     * 根据参数编号查询
     *
     * @param parameterCode 参数编号
     * @return 参数
     * @throws BusinessException 错误
     */
    ParameterBO getByParameterCode(String parameterCode) throws BusinessException;

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
    Page<ParameterBO> queryByParameterCodeAndParameterNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String parameterCode,
            String parameterName,
            Integer state
    ) throws BusinessException;

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
    ExcelExport queryExportByParameterCodeAndParameterNameAndState(
            String pageSort,
            String parameterCode,
            String parameterName,
            Integer state
    ) throws BusinessException;
}
