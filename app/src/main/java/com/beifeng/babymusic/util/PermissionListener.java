package com.beifeng.babymusic.util;

import java.util.List;

/**
 * Created by bei on 2017/4/26.
 * 权限回调接口
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
