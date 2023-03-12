package com.xiamo.enums;

import lombok.Getter;

/**
 * @Author: AceXiamo
 * @ClassName: MaEnum
 * @Date: 2023/3/3 22:15
 */
@Getter
public enum MaEnum {

    AI("wx45e76931e8407283", "")
    ;

    private String appId;
    private String remarks;

    MaEnum(String appId, String remarks) {
        this.appId = appId;
        this.remarks = remarks;
    }
}
