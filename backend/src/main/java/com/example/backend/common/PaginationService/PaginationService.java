package com.example.backend.common.PaginationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
  // 페이지네이션 처리 로직을 공통으로 처리하는 메서드
     public <T> Page<T> paginate(Pageable pageable, Page<T> pageData) {
         if (pageData == null) {
             return Page.empty(pageable);
         }
         return pageData;
     }
}
