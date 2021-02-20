package br.com.poupachef.mapper;
import br.com.poupachef.dto.response.UserResponse;
import br.com.poupachef.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    UserResponse toResponse(User user);
}
