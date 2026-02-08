import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.fs.zip.ZipFavCommanderFileSystem;
import wf.fav.apps.fc.sort.FavCommanderFileComparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws IOException {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    new ZipFavCommanderFileSystem(null);

    LocalFavCommanderFileSystem localFileSystem = LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance();
    List<? extends FavCommanderFile> listRoots = localFileSystem.listRoots();
    listRoots.forEach(f -> System.out.println(f.getName()));
    List<? extends FavCommanderFile> fileList = listRoots.getFirst().listDirectoryFileList();
    fileList.sort(FavCommanderFileComparator.NAME);
    fileList.forEach(f -> System.out.println(f.getName()));

    String fileZip = "src/main/resources/unzipTest/compressed.zip";
    File destDir = new File("src/main/resources/unzipTest");

    byte[] buffer = new byte[1024];
    ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
    ZipEntry zipEntry = zis.getNextEntry();
    while (zipEntry != null) {
        File newFile = newFile(destDir, zipEntry);
        if (zipEntry.isDirectory()) {
            if (!newFile.isDirectory() && !newFile.mkdirs()) {
                throw new IOException("Failed to create directory " + newFile);
            }
        } else {
            // fix for Windows-created archives
            File parent = newFile.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                throw new IOException("Failed to create directory " + parent);
            }

            // write file content
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
        }
        zipEntry = zis.getNextEntry();
    }

    zis.closeEntry();
    zis.close();
}

        public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
            File destFile = new File(destinationDir, zipEntry.getName());

            String destDirPath = destinationDir.getCanonicalPath();
            String destFilePath = destFile.getCanonicalPath();

            if (!destFilePath.startsWith(destDirPath + File.separator)) {
                throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
            }

            return destFile;
        }
