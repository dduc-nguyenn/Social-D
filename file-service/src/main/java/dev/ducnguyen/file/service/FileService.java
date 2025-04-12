package dev.ducnguyen.file.service;

import dev.ducnguyen.file.dto.response.FileData;
import dev.ducnguyen.file.dto.response.FileResponse;
import dev.ducnguyen.file.exception.AppException;
import dev.ducnguyen.file.exception.ErrorCode;
import dev.ducnguyen.file.mapper.FileManagementMapper;
import dev.ducnguyen.file.repository.FileManagementRepository;
import dev.ducnguyen.file.repository.FileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileService {
    FileRepository fileRepository;
    FileManagementRepository fileManagementRepository;
    FileManagementMapper fileManagementMapper;

    public FileResponse uploadFile(MultipartFile file) throws IOException {
        // Store File
        var fileInfo = fileRepository.store(file);

        // Create file management info
        var fileManagement = fileManagementMapper.toFileManagement(fileInfo);

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        fileManagement.setUserId(userId);

        fileManagementRepository.save(fileManagement);

        return FileResponse.builder()
                .originalFilename(file.getOriginalFilename())
                .url(fileInfo.getUrl())
                .build();
    }

    public FileData downloadFile(String fileName) throws IOException {
        var fileManagement = fileManagementRepository.findById(fileName)
                .orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));

        var resource = fileRepository.read(fileManagement);

        return new FileData(fileManagement.getContentType(), resource);
    }
}
