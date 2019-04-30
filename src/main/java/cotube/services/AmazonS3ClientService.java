package cotube.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AmazonS3ClientService
{
    void uploadMultipartFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess);
    void uploadFileToS3Bucket(File file, boolean enablePublicReadAccess);
    void deleteFileFromS3Bucket(String fileName);
}