package com.circle.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.circle.pojo.Book;
import com.circle.service.BookService;
import com.circle.service.PublisherService;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/allBook")
    public List<Book> allBook(){
        List<Book> list = bookService.list();
        return list;
    }

    @RequestMapping("/selectPageList")
    public IPage<Book> selectPageList(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "3") int pageSize){
        Page<Book> bookPage = new Page<>(currentPage , pageSize);
        IPage<Book> bookIPage = bookService.page(bookPage);
        return bookIPage;
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        book.setPid(book.getPublisher().getId());
        bookService.save(book);
        return "ok";
    }

    @RequestMapping("/queryById")
    public Book queryById(int id){
        Book book = bookService.getById(id);
        return book;
    }

    @PostMapping("/updateBook")
    public String updateBook(@RequestBody Book book){
        book.setPid(book.getPublisher().getId());
        bookService.updateById(book);
        return "ok";
    }

    @RequestMapping("/deleteById")
    public String del(int id){
        bookService.removeById(id);
        return "ok";
    }

}
