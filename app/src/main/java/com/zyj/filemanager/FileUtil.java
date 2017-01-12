package com.zyj.filemanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zyj.filemanager.bean.FileType;

import java.io.File;

/**
 * Created by ${zhaoyanjun} on 2017/1/11.
 */

public class FileUtil {

    /**
     * 获取文件类型
     * @param file
     * @return
     */
    public static FileType getFileType(File file ){
        if (file.isDirectory()) {
            return FileType.directory ;
        }
        String fileName = file.getName().toLowerCase() ;

        if ( fileName.contains(".mp3")) {
            return FileType.music ;
        }

        if ( fileName.contains(".mp4") || fileName.contains( ".avi")
                || fileName.contains( ".3gp") || fileName.contains( ".mov")
                || fileName.contains( ".rmvb") || fileName.contains( ".mkv")
                || fileName.contains( ".flv") || fileName.contains( ".rm")) {
            return FileType.video ;
        }

        if ( fileName.contains(".txt") || fileName.contains(".log") || fileName.contains(".xml")) {
            return FileType.txt ;
        }

        if ( fileName.contains(".zip") || fileName.contains( ".rar")) {
            return FileType.zip ;
        }

        if ( fileName.contains(".png") || fileName.contains( ".gif")
                || fileName.contains( ".jpeg") || fileName.contains( ".jpg")   ) {
            return FileType.image ;
        }

        if ( fileName.contains(".apk") ) {
            return FileType.apk ;
        }

        return FileType.other ;
    }

    /**
     * 获取文件的子文件个数
     * @param file
     * @return
     */
    public static int getFileChildCount(File file) {
        int count = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isHidden()) continue;
                count ++ ;
            }
        }
        return count;
    }

    /**
     * 文件大小转换
     * @param size
     * @return
     */
    public static String sizeToChange( long size ){
        java.text.DecimalFormat df   =new   java.text.DecimalFormat("#.00");  //字符格式化，为保留小数做准备

        double G = size * 1.0 / 1024 / 1024 /1024 ;
        if ( G >= 1 ){
            return df.format( G ) + " GB";
        }

        double M = size * 1.0 / 1024 / 1024  ;
        if ( M >= 1 ){
            return df.format( M ) + " MB";
        }

        double K = size  * 1.0 / 1024   ;
        if ( K >= 1 ){
            return df.format( K ) + " KB";
        }

        return size + " B" ;
    }

    /**
     * 安装apk
     * @param context
     * @param file
     */
    public static void installApp(Context context , File file ){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile( file ), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 打开word
     * @param param
     * @return
     */
    public static void openWordFileIntent( Context context , String param ) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/msword");
        context.startActivity( intent );
    }

    /**
     * android获取一个用于打开文本文件的intent
     * @param context
     * @param param
     * @param paramBoolean
     */
    public static void openTextFileIntent( Context context , String param, boolean paramBoolean){
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param );
            intent.setDataAndType(uri1, "text/plain");
        }else {
            Uri uri2 = Uri.fromFile(new File(param ));
            intent.setDataAndType(uri2, "text/plain");
        }

        context.startActivity( intent );
    }

}
