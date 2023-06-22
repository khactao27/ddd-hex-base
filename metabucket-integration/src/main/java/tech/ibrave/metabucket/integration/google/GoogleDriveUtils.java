package tech.ibrave.metabucket.integration.google;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * Author: hungnm
 * Date: 21/06/2023
 */
public class GoogleDriveUtils {

    /**
     * Get all file and its information
     */
    public static List<File> listEverything(Drive drive) throws IOException {
        // Print the names and IDs for up to 10 files.
        var result = drive.files().list()
                .setPageSize(1000) // Limit item want to get
                .setFields("nextPageToken, files(id, name, size, thumbnailLink, shared)") // get field of google drive file
                .execute();
        return result.getFiles();
    }

    /**
     * Get files in a folder
     */
    public static List<File> listFolderContent(Drive drive, String parentId) throws IOException, GeneralSecurityException {
        if (parentId == null) {
            parentId = "root";
        }
        var query = "'" + parentId + "' in parents";
        var result = drive.files().list()
                .setQ(query)
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)") // get field of google drive folder
                .execute();
        return result.getFiles();
    }

    /**
     * Download file by id
     */
    public static void downloadFile(Drive drive, String id, OutputStream outputStream) throws IOException, GeneralSecurityException {
        if (id != null) {
            drive.files()
                    .get(id).executeMediaAndDownloadTo(outputStream);
        }
    }

    /**
     * Delete file
     */
    public static void deleteFileOrFolder(Drive drive, String fileId) throws Exception {
        drive.files().delete(fileId).execute();
    }

    /**
     * Set permission
     */
    private static Permission setPermission(Drive drive, String type, String role) {
        var permission = new Permission();
        permission.setType(type);
        permission.setRole(role);
        return permission;
    }


    /**
     * Upload file
     */
    public static String uploadFile(Drive drive,
                                    MultipartFile file,
                                    String folderName,
                                    String type,
                                    String role) {
        try {
            var folderId = getFolderId(drive, folderName);
            if (null != file) {

                var fileMetadata = new File();
                fileMetadata.setParents(Collections.singletonList(folderId));
                fileMetadata.setName(file.getOriginalFilename());
                var uploadFile = drive
                        .files()
                        .create(fileMetadata, new InputStreamContent(
                                file.getContentType(),
                                new ByteArrayInputStream(file.getBytes()))
                        )
                        .setFields("id").execute();

                if (!type.equals("private") && !role.equals("private")) {
                    // Call Set Permission drive
                    drive.permissions().create(uploadFile.getId(), setPermission(drive, type, role)).execute();
                }

                return uploadFile.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get folder id
     */
    public static String getFolderId(Drive drive, String folderName) throws Exception {
        String parentId = null;
        var folderNames = folderName.split("/");

        for (var name : folderNames) {
            parentId = findOrCreateFolder(parentId, name, drive);
        }
        return parentId;
    }

    /**
     * Find a folder, if not exist, then create folder
     */
    private static String findOrCreateFolder(String parentId,
                                             String folderName,
                                             Drive driveInstance) throws Exception {
        var folderId = searchFolderId(parentId, folderName, driveInstance);
        // Folder already exists, so return id
        if (folderId != null) {
            return folderId;
        }
        //Folder dont exists, create it and return folderId
        var fileMetadata = new File();
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        fileMetadata.setName(folderName);

        if (parentId != null) {
            fileMetadata.setParents(Collections.singletonList(parentId));
        }
        return driveInstance.files().create(fileMetadata)
                .setFields("id")
                .execute()
                .getId();
    }

    /**
     * Search folder
     */
    private static String searchFolderId(String parentId,
                                         String folderName,
                                         Drive service) throws Exception {
        String folderId = null;
        String pageToken = null;
        FileList result = null;

        var fileMetadata = new File();
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        fileMetadata.setName(folderName);

        do {
            String query = " mimeType = 'application/vnd.google-apps.folder' ";
            if (parentId == null) {
                query = query + " and 'root' in parents";
            } else {
                query = query + " and '" + parentId + "' in parents";
            }
            result = service.files().list().setQ(query)
                    .setSpaces("drive")
                    .setFields("nextPageToken, files(id, name)")
                    .setPageToken(pageToken)
                    .execute();

            for (File file : result.getFiles()) {
                if (file.getName().equalsIgnoreCase(folderName)) {
                    folderId = file.getId();
                }
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null && folderId == null);

        return folderId;
    }

    /**
     * Get storage quota
     */
    public static About.StorageQuota getStorageQuota(Drive drive) throws IOException {
        return drive.about().get().setFields("storageQuota").execute().getStorageQuota();
    }
}
