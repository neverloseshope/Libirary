package com.circle.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.circle.pojo.BorrowerBook;
import com.circle.service.BorrowerBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author circle
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/borrowerBook")
public class BorrowerBookController {
    @Autowired
    BorrowerBookService borrowerBookService;


//    @RequestMapping("/allBorrowerBook")
//    public List<BorrowerBook> allBorrowerBook(){
//        List<BorrowerBook> list = borrowerBookService.queryAllBorrowerBook();
//        return list;
//    }

    @RequestMapping("/selectPageList")
    public IPage<BorrowerBook> selectPageList(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "3") int pageSize){
        Page<BorrowerBook> borrowerBookPage = new Page<>(currentPage , pageSize);
        IPage<BorrowerBook> borrowerBookIPage = borrowerBookService.page(borrowerBookPage);
        return borrowerBookIPage;
    }

    @PostMapping("/addBorrowerBook")
    public String addBorrowerBook(@RequestBody BorrowerBook borrowerBook){
        System.out.println(borrowerBook);
        borrowerBookService.save(borrowerBook);
        return "ok";
    }

    @RequestMapping("/queryById")
    public BorrowerBook queryById(int bbId){
        BorrowerBook borrowerBook = borrowerBookService.getById(bbId);
        return borrowerBook;
    }

    @PostMapping("/updateBorrowerBook")
    public String updateBorrowerBook(@RequestBody BorrowerBook borrowerBook){
        borrowerBook.setBookId(borrowerBook.getBook().getId());
        borrowerBook.setBorrowerId(borrowerBook.getBorrower().getId());
        borrowerBookService.updateById(borrowerBook);
        return "ok";
    }

    @GetMapping("/deleteById")
    public String deleteBorrowerBook(int bbId){
        borrowerBookService.removeById(bbId);
        return "ok";
    }

    @PostMapping("/deleteList")
    public String deleteList(@RequestBody List<Integer> bbIdList){
        borrowerBookService.removeByIds(bbIdList);
        return "ok";
    }
}
