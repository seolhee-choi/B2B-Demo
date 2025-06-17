package com.example.backend.service;

import com.example.backend.mapper.BookmarkMenuMapper;
import com.example.backend.dto.BookMark_menu;
import com.example.backend.dto.MenuItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookmarkMenuService {

  @Autowired
  private BookmarkMenuMapper bookmarkMenuMapper;

  public int addBookmarkMenu(Long user_id, Long menu_id) {

    BookMark_menu bookmark_menu = new BookMark_menu();
    bookmark_menu.setUserId(user_id);
    bookmark_menu.setMenuId(menu_id);

    int existingBookmarkCount = bookmarkMenuMapper.checkBookmark(bookmark_menu);

    if (existingBookmarkCount == 0) {
      bookmarkMenuMapper.addBookmark(bookmark_menu);
    }

    return existingBookmarkCount;
  }

  public int removeBookmarkMenu(Long user_id, Long menu_id) {
    BookMark_menu bookmark_menu = new BookMark_menu();
    bookmark_menu.setUserId(user_id);
    bookmark_menu.setMenuId(menu_id);
    int existingBookmarkCount = bookmarkMenuMapper.checkBookmark(bookmark_menu);
    if (existingBookmarkCount > 0) {
      bookmarkMenuMapper.removeBookmark(bookmark_menu);
    }
    return existingBookmarkCount;
  }

  public Page<MenuItem> getBookmarkMenus(Pageable pageable,Long user_id) {

    //페이지 번호, 크기 계산
    int page = pageable.getPageNumber();
    int size = pageable.getPageSize();
    int offset = page * size;

    //Mybatis Mapper 호출(페이징 처리된 데이터 조회)
    List<MenuItem> menuItems = bookmarkMenuMapper.getBookmarkMenus(user_id, offset, size);

    //총 데이터 수를 계산하여 Page 객체 생성
    long total = bookmarkMenuMapper.countBookmarkMenus(user_id);

    return new PageImpl<>(menuItems, pageable, total);
  }


}
