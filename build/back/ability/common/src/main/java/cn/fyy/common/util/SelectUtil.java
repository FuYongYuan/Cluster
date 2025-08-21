package cn.fyy.common.util;

import cn.fyy.common.bean.ao.DataState;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 分页工具类
 *
 * @author fyy
 */
public class SelectUtil {
    //------------------------------------------------------------------------------------------------------------------查询语句排序拼装

    /**
     * 获取排序数组
     *
     * @param root            查询对象
     * @param criteriaBuilder 查询条件生成器
     * @param pageSort        排序
     * @return 排序数组
     */
    public static Order[] getSort(Root<?> root, CriteriaBuilder criteriaBuilder, String pageSort) {
        if (StringUtils.hasText(pageSort)) {
            String[] pageSorts = pageSort.split(",");
            Order[] result = new Order[pageSorts.length];
            for (int i = 0; i < pageSorts.length; i++) {
                if (pageSorts[i] != null) {
                    String[] sortAndOrder = pageSorts[i].split("-");
                    if (sortAndOrder.length == 2) {
                        String sort = sortAndOrder[0];
                        String order = sortAndOrder[1];
                        if (order.contains("asc")) {
                            result[i] = criteriaBuilder.asc(root.get(sort));
                        } else if (order.contains("desc")) {
                            result[i] = criteriaBuilder.desc(root.get(sort));
                        }
                    }
                }
            }
            return result;
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------查询语句条件拼装

    /**
     * 获取条件
     *
     * @param root            查询对象
     * @param criteriaBuilder 查询条件生成器
     * @return 条件
     */
    public static Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.conjunction();
    }


    /**
     * 获取条件-统一封装可以加入如行级权限等封装
     *
     * @param root            查询对象
     * @param criteriaBuilder 查询条件生成器
     * @param state           状态
     * @return 条件
     */
    public static Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, Byte state) {
        Predicate predicate;
        // 条件拼装
        predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));
        // 行级权限条件拼装

        return predicate;
    }
}
