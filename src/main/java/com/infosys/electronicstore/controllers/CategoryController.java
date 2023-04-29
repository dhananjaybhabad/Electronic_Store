package com.infosys.electronicstore.controllers;import com.infosys.electronicstore.dtos.ApiResponse;import com.infosys.electronicstore.dtos.CategoryDto;import com.infosys.electronicstore.dtos.ImageResponse;import com.infosys.electronicstore.dtos.PageableResponse;import com.infosys.electronicstore.services.CategoryServiceI;import com.infosys.electronicstore.services.FileServiceI;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Value;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.util.StreamUtils;import org.springframework.web.bind.annotation.*;import org.springframework.web.multipart.MultipartFile;import javax.servlet.http.HttpServletResponse;import javax.validation.Valid;import java.io.FileNotFoundException;import java.io.IOException;import java.io.InputStream;import java.util.List;@RestController@Slf4jpublic class CategoryController {    @Autowired    private CategoryServiceI categoryService;    @Value("${category.profile.image.path}")    private String imageuploadpath;    @Autowired    private FileServiceI fileServiceI;    @PostMapping("/create")    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {        log.info("Initiating request to Create Category");        CategoryDto category = this.categoryService.createCategory(categoryDto);        log.info("Completed request to Create Category");        return new ResponseEntity<>(category, HttpStatus.CREATED);    }    @PutMapping("/update/{catid}")    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long catid) {        log.info("Initiating the request to update the Category :{}", catid);        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catid);        log.info("Completed the request to update the Category :{}", catid);        return new ResponseEntity<>(updateCategory, HttpStatus.CREATED);    }    @GetMapping("/getsingle/{catid}")    public ResponseEntity<CategoryDto> getsinglecategory(@PathVariable Long catid) {        log.info("Initiating the request to Get the Category :{}", catid);        CategoryDto singleCategory = this.categoryService.getSingleCategory(catid);        log.info("Completed the request to Get the Category :{}", catid);        return new ResponseEntity<>(singleCategory, HttpStatus.OK);    }    @GetMapping("/")    public ResponseEntity<PageableResponse> getAllcategories(@RequestParam(value = "pagenumber", defaultValue = "0", required = false) Integer pagenumber,                                                             @RequestParam(value = "pagesize", defaultValue = "10", required = false) Integer pagesize,                                                             @RequestParam(value = "sortBy", defaultValue = "categoryId", required = false) String sortBy,                                                             @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {        log.info("Initiated request to get all the Category details");        PageableResponse<CategoryDto> allcategories = this.categoryService.getAllcategories(pagenumber, pagesize, sortBy, sortDir);        log.info("Completed request to get all the Category details");        return new ResponseEntity<>(allcategories, HttpStatus.OK);    }    @DeleteMapping("/{catid}")    public ResponseEntity<ApiResponse> deletecategory(@PathVariable Long catid) {        log.info("Initiating the request to delete the Category :{}", catid);        this.categoryService.deleteCategory(catid);        ApiResponse apiResponse = ApiResponse.builder().message("Deleted Successfully").status(HttpStatus.OK).success(true).build();        log.info("Completed the request to update the Category :{}", catid);        return new ResponseEntity<>(apiResponse, HttpStatus.OK);    }    @GetMapping("/{keyword}")    public ResponseEntity<List<CategoryDto>> searchCategory(@PathVariable String keyword) {        log.info("Initiating the request to Search the Category :{}", keyword);        List<CategoryDto> searchCategory = this.categoryService.searchCategory(keyword);        log.info("Completed the request to Search the Category :{}", keyword);        return new ResponseEntity<>(searchCategory, HttpStatus.OK);    }//    @PostMapping("/image/{categoryid}/")//    public ResponseEntity<ImageResponse> uploadImage(@PathVariable Long categoryid, @RequestParam("userimage") MultipartFile file) throws IOException {//        log.info("Initiating the request to Upload the image with categoryid:{}", categoryid);//        CategoryDto category = this.categoryService.getSingleCategory(categoryid);////        String uploadImage = this.fileServiceI.uploadFile(file, imageuploadpath);////        category.setCoverImage(uploadImage);//        this.categoryService.updateCategory(category, categoryid);////        ImageResponse imageUploaded = ImageResponse.builder().imageName(uploadImage).message("Image Uploaded").status(true).build();//////        log.info(" Completed the request to upload image process", categoryid);//        return new ResponseEntity<>(imageUploaded, HttpStatus.CREATED);//    }    //    To serve the user image    @GetMapping("/image/{catid}")    public void serveUserimage(@PathVariable Long catid, HttpServletResponse response) throws IOException {        log.info("Initiating the request to Serve the image with categoryid:{}", catid);        CategoryDto category = this.categoryService.getSingleCategory(catid);        InputStream resource = this.fileServiceI.getResource(imageuploadpath, category.getCoverImage());        response.setContentType(MediaType.IMAGE_PNG_VALUE);        StreamUtils.copy(resource, response.getOutputStream());        log.info("Completed the request to Serve the image with categoryid:{}", catid);    }}