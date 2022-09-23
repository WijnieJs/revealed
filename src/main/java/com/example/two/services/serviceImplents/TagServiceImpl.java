package com.example.two.services.serviceImplents;


import com.example.two.dto.TagDto;
import com.example.two.dto.converters.DtoMapperService;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.models.Tag;
import com.example.two.repository.TagRepository;
import com.example.two.services.serviceInterfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    @Lazy
   private final TagRepository tagRepository;
    private final DtoMapperService dtoHandler;

    public TagServiceImpl(TagRepository tagRepository, DtoMapperService dtoHandler) {
        this.tagRepository = tagRepository;
        this.dtoHandler = dtoHandler;
    }



    @Override
    public List<TagDto> getTags() {
            try {
            List<Tag> tagList = tagRepository.findAll();
                List<TagDto> dtos = new ArrayList<>();
                for ( Tag tags : tagList) {
                    dtos.add((TagDto) dtoHandler.dtoClassConverter(tags, TagDto.class));
                }
            return dtos;
            } catch (Exception e) {
                throw new ProductNotFoundException("Something is wrong on the connection. ");

            }
    }


    @Override
    public Tag getTagInDbById(Long  roleId) {
        return null;
    }

    @Override
    public Tag findTagInDbByName(String roleName) {
        return null;
    }

}







