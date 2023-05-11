package com.circle.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.circle.pojo.Publisher;
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
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/allPublisher")
    public List<Publisher> allPublisher(){
        List<Publisher> list = publisherService.list();
        return list;
    }

    @RequestMapping("/selectPageList")
    public IPage<Publisher> selectPageList(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "3") int pageSize){
        Page<Publisher> publisherPage = new Page<>(currentPage , pageSize);
        IPage<Publisher> publisherIPage = publisherService.page(publisherPage);
        return publisherIPage;
    }

    @PostMapping("/addPublisher")
    public String addPublisher(@RequestBody Publisher publisher){
        publisherService.save(publisher);
        return "ok";
    }

    @RequestMapping("/queryById")
    public Publisher toUpdatePublisher(int id){
        Publisher publisher = publisherService.getById(id);
        return publisher;
    }

    @PostMapping("/updatePublisher")
    public String updatePublisher(@RequestBody Publisher publisher){
        publisherService.updateById(publisher);
        return "ok";
    }

    @RequestMapping("/deleteById")
    public String deleteById(int id){
        publisherService.removeById(id);
        return "ok";
    }


}
