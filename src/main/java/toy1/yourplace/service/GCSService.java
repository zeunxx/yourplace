/*
package toy1.yourplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.sql.Blob;

@Service
@RequiredArgsConstructor
public class GCSService {

    private final EmbeddedMongoProperties.Storage storage;

    public Blob downloadFileFromGCS(String bucketName, String downloadFileName, String localFileLocation) {
        Blob blob = storage.get(bucketName, downloadFileName);
        blob.downloadTo(Paths.get(localFileLocation));
        return blob;
    }
}*/
