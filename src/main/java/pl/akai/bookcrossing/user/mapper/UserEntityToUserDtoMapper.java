package pl.akai.bookcrossing.user.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.user.database.UserEntity;
import pl.akai.bookcrossing.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserEntityToUserDtoMapper {

    UserDto from(UserEntity userEntity);

}
