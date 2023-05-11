package com.circle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.circle.mapper.BorrowerMapper;
import com.circle.pojo.Borrower;
import com.circle.service.BorrowerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author circle
 * @since 2022-06-20
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements BorrowerService {

}
