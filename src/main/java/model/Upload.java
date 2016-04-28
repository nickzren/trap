package model;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author nick
 */
public class Upload {

    public static String rootPath;
    public static boolean isUpload; // search variants feature
    public static String filePath;
    public static String fileName;
    public static String uploadErrMsg;

    public static void init(HttpServletRequest request) throws Exception {
        reset();

        if (ServletFileUpload.isMultipartContent(request)) {
            isUpload = true;

            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();

                        int index = fileName.lastIndexOf("."); // file extension index

                        if (!item.getContentType().equals("text/plain")) {
                            uploadErrMsg = "Variant file only support text/plain file format.";
                            return;
                        }

                        fileName = fileName.substring(0, index)
                                + "_"
                                + System.currentTimeMillis()
                                + fileName.substring(index);

                        File file = new File(rootPath + File.separator + fileName);

                        filePath = file.getAbsolutePath();

                        item.write(file);

                        checkFileSize(file);
                    }
                }
            } catch (Exception ex) {
                uploadErrMsg = "File Upload Failed due to " + ex;
            }
        }
    }

    // The maximum accepted file size is set at 1MB
    private static void checkFileSize(File file) {
        if (file.length() > 1048576) {
            uploadErrMsg = "The maximum accepted file size is set at 1MB";

            file.delete();
        }
    }

    private static void reset() {
        uploadErrMsg = null;
        isUpload = false;
        fileName = "";
        filePath = "";
    }
}
