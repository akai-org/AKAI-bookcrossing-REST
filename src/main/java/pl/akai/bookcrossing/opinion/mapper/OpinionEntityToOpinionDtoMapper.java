package pl.akai.bookcrossing.opinion.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.opinion.database.OpinionEntity;
import pl.akai.bookcrossing.opinion.dto.OpinionDto;
import pl.akai.bookcrossing.user.mapper.UserEntityToUserDtoMapper;

@Mapper(componentModel = "spring", uses = UserEntityToUserDtoMapper.class)
public interface OpinionEntityToOpinionDtoMapper {

    OpinionDto from(OpinionEntity opinionEntity);

}
