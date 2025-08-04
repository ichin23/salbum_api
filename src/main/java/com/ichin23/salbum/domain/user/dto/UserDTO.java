package com.ichin23.salbum.domain.user.dto;

import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.domain.user.UserRole;

public record UserDTO(
        String id,
        String username,
        String image_url,
        int followers_count,
        int following_count
) {

    public UserDTO(User user){
        this(user.getId().toString(), user.getUsername(), user.getImage_url(), user.getFollowers_count(), user.getFollowing_count());
    }
}
