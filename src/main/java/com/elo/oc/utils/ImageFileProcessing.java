package com.elo.oc.utils;


import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;


public class ImageFileProcessing {

    public static byte[] getImageForEntityAddFromForm(MultipartFile file){
        byte[] bytes = null;
        if(!file.isEmpty()){
            try{
                bytes = file.getBytes();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static byte[] getImageForEntityEditFromForm(MultipartFile file){
        byte[] bytes = null;

            try{
                bytes = file.getBytes();
            }catch(IOException e){
                e.printStackTrace();
            }

        return bytes;
    }

    public static boolean checkIfImageIsRightFormat(MultipartFile file){
        boolean format = false;
            try (InputStream input = file.getInputStream()) {
                try {
                    ImageIO.read(input).toString();
                    // It's an image (only BMP, GIF, JPG and PNG are recognized).
                    format = true;
                } catch (Exception e) {
                    format = false;
                }
            } catch (IOException e) {
                format = false;
            }
        return format;
    }

    public static boolean checkIfImageSizeOk(MultipartFile file){
        boolean size = false;
        if(file.getSize() < 5 * 1024 * 1024){
            size = true;
        }
        return size;
    }
}
