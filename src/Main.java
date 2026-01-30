import wf.fav.apps.fc.fs.FavCommanderFile;
import wf.fav.apps.fc.fs.local.LocalFavCommanderFileSystem;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    LocalFavCommanderFileSystem localFileSystem = LocalFavCommanderFileSystem.getLocalFavCommanderFileSystemInstance();
    List<? extends FavCommanderFile> listRoots = localFileSystem.listRoots();
    listRoots.forEach(f -> System.out.println(f.getName()));
    List<? extends FavCommanderFile> fileList = listRoots.getFirst().listDirectoryFileList();
//    fileList.sort(Comparator.comparing((a, b) -> Boolean.compare(a.is, b.isDi))
//            .thenComparing((a, b) -> a.getName().compareToIgnoreCase(b.getName())));
    fileList.forEach(f -> System.out.println(f.getName()));
}
