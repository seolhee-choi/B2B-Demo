package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class BookmarkRequest {

  private Long userId;
  private Pageable pageable;
  private Long menuId;

}
