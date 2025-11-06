package com.backend.jjj.cinema_api.services;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.response}")
    private String minioUrlResponse;
    public String uploadFile(MultipartFile file) {
        try {
            String objectName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return objectName;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo para MinIO", e);
        }
    }
    public String getFileUrl(String objectName) {
        try {
            if (objectName == null || objectName.isEmpty()) {
                return null;
            }

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .method(Method.GET)
                            .expiry(60 * 60)
                            .build()
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL temporária do arquivo: " + objectName, e);
        }
    }

    public InputStream getObjectStream(String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar arquivo no MinIO", e);
        }
    }

    public Integer getDuration(String objectName) {
        try {
            String videoUrl = getFileUrl(objectName);

            ProcessBuilder pb = new ProcessBuilder(
                    "ffprobe",
                    "-v", "error",
                    "-show_entries", "format=duration",
                    "-of", "default=noprint_wrappers=1:nokey=1",
                    videoUrl
            );

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String durationStr = reader.readLine();
            process.waitFor();

            if (durationStr != null) {
                double seconds = Double.parseDouble(durationStr);
                return (int) Math.round(seconds / 60.0);
            } else {
                throw new RuntimeException("Não foi possível obter a duração do vídeo.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar arquivo no MinIO", e);
        }
    }

}
