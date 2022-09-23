package com.example.two.services.serviceInterfaces;

import com.example.two.dto.TagDto;
import com.example.two.models.Tag;

import java.util.List;

public interface TagService {

//    Tag findTagInDbByName(String tagName);
Tag getTagInDbById(Long  roleId);
    Tag findTagInDbByName(String roleName);
    List<TagDto> getTags();

}
