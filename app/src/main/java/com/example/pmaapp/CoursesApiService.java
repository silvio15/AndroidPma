package com.example.pmaapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoursesApiService {
    @GET("/v1/courses")
    Call<CourseResponse> getCourses();
}
