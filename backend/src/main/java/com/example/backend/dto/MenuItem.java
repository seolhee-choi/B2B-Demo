package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {
  private int menu_id;
  private String menu_nm;
  private String page_url;
  private int parent_id;
  private int depth;
  private String use_yn;

}
