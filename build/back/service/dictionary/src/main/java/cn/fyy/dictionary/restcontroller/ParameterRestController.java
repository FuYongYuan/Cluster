package cn.fyy.dictionary.restcontroller;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import cn.fyy.dictionary.bean.bo.ParameterBO;
import cn.fyy.dictionary.bean.dto.ParameterDTO;
import cn.fyy.dictionary.service.ParameterService;
import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import cn.fyy.jwt.util.JwtTokenUtil;
import dispose.DateDispose;
import enumerate.DateType;
import excel.operation.ExcelExport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 参数 RestController
 *
 * @author fuyy
 */
@Tag(name = "ParameterRestController", description = "参数 RestController")
@RestController
@RequestMapping(value = "/parameter", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParameterRestController {
    /**
     * 参数 Service
     */
    @Resource
    private ParameterService parameterServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * JWT 配置
     */
    @Resource
    private JwtProperties jwtProperties;
    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据参数编码、参数名称查询参数列表
     *
     * @param parameterCode 参数编码
     * @param parameterName 参数名称
     * @return TParameterDTO 参数对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据参数编码、参数名称查询参数列表", description = "TParameterDTO 参数对象集合",
            parameters = {
                    @Parameter(name = "currentPage", description = "当前页", required = true, example = "0"),
                    @Parameter(name = "eachPageSize", description = "每页条数", required = true, example = "10"),
                    @Parameter(name = "pageSort", description = "排序"),
                    @Parameter(name = "parameterCode", description = "参数编码"),
                    @Parameter(name = "parameterName", description = "参数名称"),
                    @Parameter(name = "state", description = "状态", example = "0")

            }
    )
    @GetMapping(value = "/query/{currentPage}/{eachPageSize}")
    public ResultMessage<PageImpl<ParameterDTO>> queryByParameterCodeAndParameterNameAndState(
            @PathVariable(value = "currentPage") int currentPage,
            @PathVariable(value = "eachPageSize") int eachPageSize,
            @RequestParam(value = "pageSort", required = false) String pageSort,
            @RequestParam(value = "parameterCode", required = false) String parameterCode,
            @RequestParam(value = "parameterName", required = false) String parameterName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException {
        Page<ParameterBO> boPage = parameterServiceImpl.queryByParameterCodeAndParameterNameAndState(
                currentPage,
                eachPageSize,
                pageSort,
                parameterCode,
                parameterName,
                state
        );
        return new ResultMessage<>(new PageImpl<>(ParameterDTO.toDTO(boPage.getContent()), boPage.getPageable(), boPage.getTotalElements()));
    }

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids 删除主键 可以使用,分割
     * @return 受影响行数
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键删除 主键可以是多个用,分割", description = "受影响行数",
            parameters = {
                    @Parameter(name = "ids", description = "删除ID", required = true)
            }
    )
    @DeleteMapping(value = "/delete/{ids}")
    public ResultMessage<Integer> delete(
            @PathVariable("ids") String ids
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenUtil.getManagerMessageFromToken(ConstantService.getRequestToken(jwtProperties));
        int i = parameterServiceImpl.updateDelete(ids, managerMessage.getManagerId(), managerMessage.getManagerName());
        if (i > 0) {
            return new ResultMessage<>(i);
        } else {
            return new ResultMessage<>(1, "删除失败!");
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 参数对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "TParameterDTO 菜单对象",
            parameters = {
                    @Parameter(name = "id", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/{id}")
    public ResultMessage<ParameterDTO> getById(
            @PathVariable("id") BigInteger id
    ) throws BusinessException {
        return new ResultMessage<>(ParameterDTO.toDTO(parameterServiceImpl.getById(id)));
    }

    /**
     * 根据参数编号查询
     *
     * @param parameterCode 参数编号
     * @return 参数对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "根据主键查询", description = "TParameterDTO 参数对象",
            parameters = {
                    @Parameter(name = "parameterCode", description = "主键ID", required = true)
            }
    )
    @GetMapping(value = "/get/parameter/code/{parameterCode}")
    public ResultMessage<ParameterDTO> getByParameterCode(
            @PathVariable("parameterCode") String parameterCode
    ) throws BusinessException {
        return new ResultMessage<>(ParameterDTO.toDTO(parameterServiceImpl.getByParameterCode(parameterCode)));
    }

    /**
     * 保存参数
     *
     * @param dto 参数对象
     * @return 参数对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "保存参数", description = "TParameterDTO 参数对象",
            parameters = {
                    @Parameter(name = "dto", description = "参数对象", required = true)
            }
    )
    @PostMapping(value = "/save")
    public ResultMessage<String> save(
            @RequestBody ParameterDTO dto
    ) throws BusinessException {
        ManagerMessage managerMessage = jwtTokenUtil.getManagerMessageFromToken(ConstantService.getRequestToken(jwtProperties));
        return parameterServiceImpl.save(dto.toBO(), managerMessage.getManagerId(), managerMessage.getManagerName());
    }

    /**
     * 导出
     *
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "导出", description = "TParameterExcel 参数对象",
            parameters = {
                    @Parameter(name = "pageSort", description = "排序"),
                    @Parameter(name = "parameterCode", description = "参数编码"),
                    @Parameter(name = "parameterName", description = "参数名称"),
                    @Parameter(name = "state", description = "状态", example = "0")
            }
    )
    @PostMapping(value = "/export")
    public void export(
            HttpServletResponse response,
            @RequestParam(value = "pageSort", required = false) String pageSort,
            @RequestParam(value = "parameterCode", required = false) String parameterCode,
            @RequestParam(value = "parameterName", required = false) String parameterName,
            @RequestParam(value = "state", required = false) Integer state
    ) throws BusinessException {
        try {
            ExcelExport excelExport = parameterServiceImpl.queryExportByParameterCodeAndParameterNameAndState(pageSort, parameterCode, parameterName, state);
            // 开始导出
            String fileName = "参数列表";
            XSSFWorkbook wb = (XSSFWorkbook) excelExport.getWorkbook();
            fileName = fileName + "_" + DateDispose.formattingDate(new Date(), DateType.YearMonthDayHourMinuteSecondMS);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + ".xlsx");
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new BusinessException("根据参数编码、参数名称查询参数列表并导出", e);
        }
    }
}
