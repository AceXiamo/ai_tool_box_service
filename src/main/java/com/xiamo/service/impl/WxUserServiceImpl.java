package com.xiamo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiamo.entity.WxUser;
import com.xiamo.mapper.WxUserMapper;
import com.xiamo.service.IWxUserService;
import com.xiamo.utils.RedissonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @Author: AceXiamo
 * @ClassName: WxUserServiceImpl
 * @Date: 2023/3/3 21:19
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {
}
