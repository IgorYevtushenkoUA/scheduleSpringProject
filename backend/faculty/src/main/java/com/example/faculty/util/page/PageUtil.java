package com.example.faculty.util.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtil {

    public static <T> Page<T> getPageFromList(List<T> list, Pageable pageable) {

        if (list.isEmpty()) {
            return new PageImpl<>(list);
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());

        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

}
