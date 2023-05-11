package com.circle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.circle.mapper.BookMapper;
import com.circle.pojo.Book;
import com.circle.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author circle
 * @since 2022-06-20
 */
@Service
public class BookServiceImpl  extends ServiceImpl<BookMapper, Book> implements BookService {
}
