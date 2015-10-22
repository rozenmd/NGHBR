package com.kmji.nghbr.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.kmji.nghbr.model.Item;

@Component
public class ItemValidator implements Validator {
    
   public boolean supports(Class<?> clazz) {
       return Item.class.isAssignableFrom(clazz);
   }

   public void validate(Object obj, Errors errors) {
       Item item = (Item) obj;
       MultipartFile file = item.getImageFile();
       
	   //Validate File
       if(file !=null){
    	   String fname = file.getOriginalFilename();
           if (file.getSize() == 0) {
               errors.rejectValue("imageFile", "missing.file");
           }
           else if(!(fname.endsWith("png") || fname.endsWith("jpg") ||
        		   fname.endsWith("jpeg") || fname.endsWith("bmp"))){
        	   errors.rejectValue("imageFile", "incorrect.type");
           }
       }
   }
}
