package cn.fyy.common.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

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
}
