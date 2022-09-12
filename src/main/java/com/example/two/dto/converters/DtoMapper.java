package com.example.two.dto.converters;


import com.example.two.dto.ProductDto;
import com.example.two.models.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DtoMapper implements DtoMapperService {

    private ModelMapper modelMapper;

    @Autowired
    public DtoMapper() {
        super();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, T> List<T> dtoConverter(List<S> s, Class<T> targetClass){
        return s.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());

    }

    @Override
    public <T> Object dtoClassConverter(Object source,Class<T> baseClass) {
        return modelMapper.map(source,baseClass);

    }



}
