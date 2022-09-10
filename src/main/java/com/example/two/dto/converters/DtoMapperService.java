package com.example.two.dto.converters;

import java.util.List;

public interface DtoMapperService {


    <S, T> List<T> dtoConverter(List<S> s, Class<T> targetClass);
    public <T> Object dtoClassConverter(Object source,Class<T> baseClass);


}
