package com.xiamo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiamo.entity.UserHistory;
import com.xiamo.mapper.UserHistoryMapper;
import com.xiamo.security.SecurityService;
import com.xiamo.service.IUserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: AceXiamo
 * @ClassName: UserHistoryServiceImpl
 * @Date: 2023/3/12 14:26
 */
@Service
public class UserHistoryServiceImpl extends ServiceImpl<UserHistoryMapper, UserHistory> implements IUserHistoryService {

    @Async
    @Override
    public void updateHis(Integer id, String messageContent) {
        LambdaUpdateWrapper<UserHistory> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserHistory::getId, id);
        wrapper.set(UserHistory::getMessageContent, messageContent);
        update(wrapper);
    }

}
