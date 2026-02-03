import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;
import wf.fav.apps.fc.sort.FavCommanderFileComparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    LocalFavCommanderFileSystem localFileSystem = LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance();
    List<? extends FavCommanderFile> listRoots = localFileSystem.listRoots();
    listRoots.forEach(f -> System.out.println(f.getName()));
    List<? extends FavCommanderFile> fileList = new ArrayList<>(listRoots.getFirst().listDirectoryFileList());
    fileList.sort(FavCommanderFileComparator.NAME);
    fileList.forEach(f -> System.out.println(f.getName()));
}
