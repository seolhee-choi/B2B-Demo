package com.example.backend.controller;

import com.example.backend.common.model.ApiResponse;
import com.example.backend.dto.BookmarkRequest;
import com.example.backend.dto.MenuItem;
import com.example.backend.service.BookmarkMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bookmark/")
public class BookmarkController {

  @Autowired
  BookmarkMenuService bookmarkMenuService;

  @PostMapping("list")
  public ApiResponse<Page<MenuItem>> list(@RequestBody BookmarkRequest request, Pageable pageable) {
    Page<MenuItem> menuItems = bookmarkMenuService.getBookmarkMenus(pageable, request.getUserId());
    return new ApiResponse<>("success", "MenuItems retrieved successfully", menuItems, menuItems.getTotalPages(), menuItems.getTotalElements());
  }

  @PostMapping("add")
  public ApiResponse<MenuItem> add(@RequestBody BookmarkRequest request) {

    int resultCheck = bookmarkMenuService.addBookmarkMenu(request.getUserId(),request.getMenuId());
if(resultCheck == 1){
  return new ApiResponse<>("success","menuItems Add successfully");
}else{
  return new ApiResponse<>("error","menuItems Add failed");
}


  }

  @PostMapping("delete")
  public ApiResponse<MenuItem> delete(@RequestBody BookmarkRequest request) {

    int resultCheck = bookmarkMenuService.removeBookmarkMenu(request.getUserId(), request.getMenuId());

    if(resultCheck == 1){
      return new ApiResponse<>("success","menuItems Delete successfully");
    }else{
      return new ApiResponse<>("error","menuItems Delete failed");
    }

  }

}
