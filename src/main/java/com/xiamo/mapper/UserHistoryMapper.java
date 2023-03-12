package com.xiamo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiamo.entity.UserHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: AceXiamo
 * @ClassName: UserHistoryMapper
 * @Date: 2023/3/12 14:24
 */
@Mapper
public interface UserHistoryMapper extends BaseMapper<UserHistory> {
}
