package com.backend.jjj.cinema_api.mapper;

import com.backend.jjj.cinema_api.dto.users.RequestUser;
import com.backend.jjj.cinema_api.dto.users.RequestUserUpdate;
import com.backend.jjj.cinema_api.dto.users.ResponseUser;
import com.backend.jjj.cinema_api.models.UsersModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersModel toEntity(RequestUser requestUser);

    ResponseUser toResponse(UsersModel userModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(RequestUserUpdate request, @MappingTarget UsersModel user);
}

