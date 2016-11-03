package com.jamp.app.service;

import com.jamp.app.domain.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
