package com.circle.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.circle.pojo.Borrower;
import com.circle.service.BorrowerService;
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
@RequestMapping("/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @GetMapping("/allBorrower")
    public List<Borrower> allBorrower(){
        List<Borrower> list = borrowerService.list();
        return list;
    }

    @RequestMapping("/selectPageList")
    public IPage<Borrower> selectPageList(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "3") int pageSize){
        Page<Borrower> publisherPage = new Page<>(currentPage , pageSize);
        IPage<Borrower> publisherIPage = borrowerService.page(publisherPage);
        return publisherIPage;
    }

    @PostMapping("/addBorrower")
    public String addBorrower(@RequestBody Borrower borrower){
        borrowerService.save(borrower);
        return "ok";
    }

    @RequestMapping("/queryById")
    public Borrower toUpdateBorrower(int id){
        Borrower borrower = borrowerService.getById(id);
        return borrower;
    }

    @RequestMapping("/updateBorrower")
    public String updateBorrower(@RequestBody Borrower borrower){
        borrowerService.updateById(borrower);
        return "ok";
    }

    @RequestMapping("/deleteById")
    public String deleteBorrower(int id){
        borrowerService.removeById(id);
        return "ok";
    }

}
