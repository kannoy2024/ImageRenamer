import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class ImageNameModifier {

    public static void main(String[] args) {
        // 指定文件夹路径
        
        String directoryPath = "写文件的地址";
        File directory = new File(directoryPath);

        // 获取文件夹中的所有文件
        File[] files = directory.listFiles((dir, name) ->
         name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));

        if (files == null || files.length == 0) {
            System.out.println("文件夹中没有图片文件。");
            return;
        }

        // 按照最后修改时间排序
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));

        // 重命名文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String newFileName = String.format("%04d.jpg", i + 1);
            File newFile = new File(directory, newFileName);

            try {
                Files.move(file.toPath(), newFile.toPath());
                System.out.println("重命名文件: " + file.getName() + " -> " + newFileName);
            } catch (IOException e) {
                System.err.println("重命名文件时出错: " + file.getName());
                e.printStackTrace();
            }
        }
    }
}