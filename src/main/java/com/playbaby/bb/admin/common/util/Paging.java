package com.playbaby.bb.admin.common.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.BiFunction;

@Getter
@Setter
public class Paging<T> {
    private int pageNum;
    private int pageSize;
    private int size;
    private long total;
    private int pages;
    private List<T> data;

    private Paging(PageInfo<T> pageInfo) {
        if (pageInfo == null)
            throw new IllegalArgumentException("pageInfo cannot be null");

        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        size = pageInfo.getSize();
        total = pageInfo.getTotal();
        pages = pageInfo.getPages();
        data = pageInfo.getList();
    }

    public static <T, U, R> Paging<R> getInstance(int page, int pageSize, BiFunction<T, U, List<R>> func, T t, U u) {
        try {
            PageHelper.startPage(page, pageSize, true);

            return new Paging<>(PageInfo.of(func.apply(t, u)));
        } finally {
            PageHelper.clearPage();
        }
    }
}
