package com.xiamo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiamo.entity.UserHistory;

/**
 * The interface User history service.
 *
 * @Author: AceXiamo
 * @ClassName: IUserHistoryService
 * @Date: 2023 /3/12 14:25
 */
public interface IUserHistoryService extends IService<UserHistory> {

    /**
     * Update his.
     *
     * @param id             the id
     * @param messageContent the message content
     */
    void updateHis(Integer id, String messageContent);

}
