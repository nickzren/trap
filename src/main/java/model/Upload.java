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

    public static void init(HttpServletRequest request) throws Exception {
        if (ServletFileUpload.isMultipartContent(request)) {
            isUpload = true;

            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();

                        int index = name.lastIndexOf("."); // file extension index

                        if (!item.getContentType().equals("text/plain")) {
                            request.setAttribute("uploadMsg",
                                    "Variant file only support text/plain file format");
                            return;
                        }

                        name = name.substring(0, index)
                                + System.currentTimeMillis()
                                + name.substring(index);

                        File file = new File(rootPath + File.separator + name);

                        filePath = file.getAbsolutePath();

                        item.write(file);
                    }
                }

                //File uploaded successfully
                request.setAttribute("uploadMsg", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("uploadMsg", "File Upload Failed due to " + ex);
            }
        } else {
            isUpload = false;
        }
    }
}
