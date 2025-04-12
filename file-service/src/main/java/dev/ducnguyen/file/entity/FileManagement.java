package dev.ducnguyen.file.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "file_management")
public class FileManagement {
    @MongoId
    String id;
    String userId;
    long size;
    String contentType;
    String md5Checksum;
    String path;
}
