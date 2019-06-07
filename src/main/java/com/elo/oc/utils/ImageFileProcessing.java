package com.elo.oc.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
}
