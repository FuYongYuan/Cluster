import {PageSortDTO} from "DTO";

/**
 * 获取分页排序数组
 *
 * @param sorter 排序内容
 */
export const getPageSort = (sorter: any): PageSortDTO[] => {
    // 初始化返回数组
    const pageSorts: PageSortDTO[] = [];
    // 判断是数组还是对象并且获取对应的值
    if (sorter instanceof Array) {
        sorter.forEach((s) => {
            if (s.field !== null && s.order !== null) {
                const pageSort: PageSortDTO = {
                    multiple: s.column.sorter.multiple,
                    field: s.field,
                    order: s.order,
                }
                pageSorts.push(pageSort);
            }
        })
    } else if (sorter instanceof Object) {
        if (sorter.field !== null && sorter.order !== null) {
            const pageSort: PageSortDTO = {
                multiple: sorter.column.sorter.multiple,
                field: sorter.field,
                order: sorter.order,
            }
            pageSorts.push(pageSort);
        }
    }
    // 排序后返回
    return pageSorts.sort((a, b) => {
        return a["multiple"] - b["multiple"];
    });
}

/**
 * 获取序列化后的排序字符串
 *
 * @param pageSorts 排序集合
 */
export const getSerializableSort = (pageSorts: PageSortDTO[]): string => {
    let pageSort = "";
    pageSorts.forEach((sort) => {
        pageSort += sort.field + "-" + sort.order + ",";
    });
    pageSort = pageSort.substring(0, pageSort.lastIndexOf(","));
    return pageSort;
}