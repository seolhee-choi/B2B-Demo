package com.example.backend.controller;

import com.example.backend.config.ApiResponse;
import com.example.backend.exception.CommonException;
import com.example.backend.service.KydService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Class Name : KydController.java
 * Description : kyd 테스트 파일
 * Modification Infomation
 *
 *  ------------  -------------  ----------------------------------------------
 *  2025. 6. 16.  kyd          최초 작성
 *
 * @since 2025. 6. 16.
 * @version 1.0
 * @author kyd
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/test")
public class KydTestController {

    @Autowired
    KydService kydService;

//    @Autowired
//    private ExcelUtil excelUtil;

    /**
     * Method Name : find_1
     * Description : api호출 테스트
     * @param req
     * @param params
     * @return
     */
    //URL : http://localhost:8080/api/v1/test/find_1
    //curl http://localhost:8080/api/v1/test/find_1
    @GetMapping(value = "find_1")
    public ResponseEntity<?> find_1(HttpServletRequest req) {
        try {
            return new ResponseEntity<>(kydService.find_1(new HashMap<String, Object>()), HttpStatus.OK);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    /**
     * Method Name : find_2
     * Description : 페이징 테스트1
     * @param keyword
     * @param pageable(size = 5, page = 0, sort = "id", direction = Sort.Direction.DESC)
     * @return
     */
    //http://localhost:8080/api/v1/test/find_2?keyword=33&size=3&page=2
    //curl http://localhost:8080/api/v1/test/find_2?keyword=33&size=3&page=2
    @GetMapping("/find_2")
    public ResponseEntity<?> find_2(@RequestParam String keyword,
                                    @PageableDefault(size = 5, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            return new ResponseEntity<>(kydService.find_2(keyword, pageable), HttpStatus.OK);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    /**
     * Method Name : find_3
     * Description : 페이징 테스트2
     * @param keyword
     * @param pageable(size = 5, page = 0, sort = "id", direction = Sort.Direction.DESC)
     * @return
     */
    //http://localhost:8080/api/v1/test/find_3?keyword=33&size=3&find_3
    //curl http://localhost:8080/api/v1/test/find_3?keyword=33&size=5&page=1
    @GetMapping("/find_3")
    public ResponseEntity<?> find_3(@RequestParam String keyword,
                                    @PageableDefault(size = 5, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            return new ResponseEntity<>(kydService.find_3(keyword, pageable), HttpStatus.OK);
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    //http://localhost:8080/api/v1/test/find_4
    //curl http://localhost:8080/api/v1/test/find_4
    @GetMapping("/find_4")
    public @ResponseBody ApiResponse find_4(HttpServletRequest req) {
        try {
            return ApiResponse.of(kydService.find_1(new HashMap<String, Object>()));
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }
    //http://localhost:8080/api/v1/test/find_5
    //curl http://localhost:8080/api/v1/test/find_5
    @GetMapping("/find_5")
    public @ResponseBody ApiResponse find_5(HttpServletRequest req) {
        try {
            Integer.parseInt("H");
            return ApiResponse.of(kydService.find_1(new HashMap<String, Object>()));
        } catch (Exception e) {
            throw new CommonException(e);
        }
    }

    ///**
    // * Method Name : find_6
    // * Description : 엑셀다운로드
    // * @param req
    // * @param res
    // * @param pathMap
    // * @param paramMap
    // * @return
    // */
    ////http://localhost:8080/api/v1/test/find_6?keyword=33&size=3&page=1
    //@GetMapping(value="/find_6")
    //public ResponseEntity<?> find_6(HttpServletRequest req, HttpServletResponse res,
    //                                                      @PathVariable Map<String,Object> pathMap,
    //                                                      @RequestParam Map<String,Object> paramMap,
    //                                                      @PageableDefault(size = 5, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
//
    //    System.out.println("#1");
    //    ResponseEntity<?> resBinary = null;
//
    //    String fileName = (String) paramMap.get("fileName");
    //    String localTimezoneOffset = (String) paramMap.get("localTimezoneOffset");
//
    //    Map<String, String> excelHeader = new LinkedHashMap<String, String>();
    //    Map<String, Short> excelColumnSize = new HashMap<String, Short>();
    //    System.out.println("#2");
//
    //    //List<Map<String, Object>> dataList = kydService.find_4(pageable);
    //    //System.out.println(dataList.size());
//
    //    List<Map<String, Object>> dataList = Strings.convertObjectToList(kydService.find_4(pageable));
    //    for(Map<String, Object> map11 : dataList) {
    //        System.out.println(map11);
    //    }
    //    System.out.println("#3");
//
    //    try {
    //        excelHeader.put("id", "id");
    //        excelHeader.put("name", "name");
    //        excelHeader.put("category", "category");
    //        excelHeader.put("rating", "rating");
//
    //        excelColumnSize.put("id", (short) 50);
    //        excelColumnSize.put("name", (short) 100);
    //        excelColumnSize.put("category", (short) 200);
    //        excelColumnSize.put("rating", (short) 200);
    //        System.out.println("#5");
//
    //        resBinary = excelUtil.excelDownload(res, fileName, localTimezoneOffset, excelHeader, excelColumnSize, dataList);
    //    } catch(Exception e) {
    //        log.error("Exception : {}", e);
    //    }
    //    return resBinary;
    //}
}
