package dev.ducnguyen.file.mapper;

import dev.ducnguyen.file.dto.FileInfo;
import dev.ducnguyen.file.entity.FileManagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileManagementMapper {
    @Mapping(target = "id", source = "name")
    FileManagement toFileManagement(FileInfo fileInfo);
}
