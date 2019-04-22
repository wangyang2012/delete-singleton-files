import java.io.File;

public class DeleteSingletonFiles {

    public static void main(String[] args) {
        try {
            // Get current Java class file
            File currentFile = new File(DeleteSingletonFiles.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

            // Get current folder
            File currentFolder = currentFile.getParentFile();
            if (!currentFolder.isDirectory()) {
                throw new Exception(currentFolder.getName() + " is not a folder");
            }

            // Get all files of folder
            Integer nbDeletedFiles = 0;
            for (File file: currentFolder.listFiles()) {
                String fileName = file.getName();
                if (fileName == null || fileName.length() == 0 || !fileName.contains(".") || fileName.endsWith(".class") || fileName.endsWith(".java") || fileName.endsWith(".jar") || file.isDirectory()) {
                    continue;
                }
                String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
//                System.out.println("filename: " + fileName + " \t\t withou extension: " + fileNameWithoutExtension);
                File fileJpg = new File(fileNameWithoutExtension + ".JPG");
                File fileRaw = new File(fileNameWithoutExtension + ".CR2");
                if (!fileJpg.exists() || !fileRaw.exists()){
//                    file.delete();
                    System.out.println("Deleting: " + file.getName());
                    nbDeletedFiles ++;
                }
            }
            System.out.println(nbDeletedFiles + " files deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
