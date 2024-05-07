//package com.benign.drone.service.impl;
//
//import com.benign.drone.service.ResourceService;
//import com.cloudinary.Cloudinary;
//import com.cloudinary.EagerTransformation;
//import com.cloudinary.utils.ObjectUtils;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import ng.optisoft.rosabon.dto.response.ResourceDto;
//import ng.optisoft.rosabon.exception.BadRequestException;
//import ng.optisoft.rosabon.exception.ConflictException;
//import ng.optisoft.rosabon.service.ResourceService;
//import ng.optisoft.rosabon.util.GeneralUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Map;
//
//@Service
//@Transactional
//public class ResourceServiceImpl implements ResourceService
//{
//	@Autowired
//	private Cloudinary cloudinary;
//
//
//		@Override
//	    public String saveResourceViaCloudinary(ResourceDto dto) {
//
//	        String base64 = reformtBase64(dto);
//
//	        try {
//
//	            Map uploadResult = cloudinary.uploader().upload(base64,
//	                    ObjectUtils.asMap(
//	                            "eager", Collections.singletonList(
//	                                    new EagerTransformation()
//	                                            .quality("auto")
//	                                            .crop("pad"))));
//
//
//
//	            System.out.println(new Gson().toJson(uploadResult));
//
//	            JsonObject obj = (JsonObject) JsonParser.parseString(new Gson().toJson(uploadResult));
//	            JsonObject eagerObject = obj.getAsJsonArray("eager").get(0).getAsJsonObject();
//	            return String.valueOf(eagerObject.get("secure_url").getAsString());
//	        } catch (IOException e) {
//	            e.printStackTrace();
//
//	            throw new ConflictException("Error uploading resource(1)");
//	        }
//	    }
//
//		@Override
//	    public String saveResourceViaCloudinary2(ResourceDto dto) {
//
//	        String base64 = reformtBase64(dto);
//
//	        try {
//
//	            Map uploadResult = cloudinary.uploader().upload(base64,
//	                    ObjectUtils.asMap("resource_type", "raw",
//	                            "eager", Collections.singletonList(
//	                                    new EagerTransformation().width(400).quality("auto").height(300).crop("pad"))));
//
//	            JsonObject obj = (JsonObject) JsonParser.parseString(new Gson().toJson(uploadResult));
//
//	            return obj.getAsJsonPrimitive("secure_url").getAsString();
//
//	        } catch (IOException e) {
//	            e.printStackTrace();
//
//	            throw new ConflictException("Error uploading resource(1)");
//	        }
//	    }
//
//	    private String reformtBase64(ResourceDto dto) {
//
//	        String base64 = dto.getEncodedString();
//	        if (base64.contains("data:image/")) {
//	            return base64;
//	        }
//
//	        return "data:image/" + dto.getResourceExtension().name() + ";base64," + dto.getEncodedString();
//	    }
//}
