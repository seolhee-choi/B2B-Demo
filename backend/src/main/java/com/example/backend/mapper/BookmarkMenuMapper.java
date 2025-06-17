package com.example.backend.mapper;

import com.example.backend.dto.BookMark_menu;
import com.example.backend.dto.MenuItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookmarkMenuMapper {

  int addBookmark(BookMark_menu bookmark_menu);

  void removeBookmark(BookMark_menu bookmark_menu);

  List<MenuItem> getBookmarkMenus(@Param("userId") Long userId, @Param("offset") int offset,@Param("limit") int limit);

  int checkBookmark(BookMark_menu bookmark_menu);

  //사용자의 즐겨찾기 메뉴 개수 조회( 총 데이터 수)
  long countBookmarkMenus(@Param("userId") Long userId);

}
