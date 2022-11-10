package com.cohort10.util;

import org.apache.commons.beanutils.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyDateConverter implements Converter {

   private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy@HH:mm:ss.SSSZ");

   public Object convert(Class type, Object value) {
       if(value == null) {
           return null;

       } else {
           try {
               return format.parse((String) value);

           } catch (ParseException e) {
               throw new RuntimeException(e);

           }

        }
    }
}