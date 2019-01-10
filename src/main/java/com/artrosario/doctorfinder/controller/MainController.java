package com.artrosario.doctorfinder.controller;


import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.artrosario.doctorfinder.DoctorFinderAppApplication;
import com.artrosario.doctorfinder.entity.UserEntity;
import com.artrosario.doctorfinder.request.model.DoctorCreateRequestModel;
import com.artrosario.doctorfinder.request.model.UserSignUpRequestModel;
import com.artrosario.doctorfinder.request.model.UserUpdateRequestModel;
import com.artrosario.doctorfinder.response.model.DoctorResponse;
import com.artrosario.doctorfinder.response.model.DoctorRest;
import com.artrosario.doctorfinder.response.model.DoctorsResponse;
import com.artrosario.doctorfinder.response.model.UserRest;
import com.artrosario.doctorfinder.service.DoctorService;
import com.artrosario.doctorfinder.service.UserService;
import com.artrosario.doctorfinder.shared.dto.DoctorDto;
import com.artrosario.doctorfinder.shared.dto.UserDto;
import com.artrosario.doctorfinder.utils.Utils;

@Controller 
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(DoctorFinderAppApplication.class);
    
	@Autowired
	Utils utils;

	@Autowired
	UserService userService;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
    /*********
	   PAGES
     **********/
	/**
	 * Home Page
	 */
	@GetMapping(value="/")
	public String home(Model model) {
		model.addAttribute("title", "DoctorFinder");
		return "home";
	}
    /**
     * Sign Up Page
     */
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
    	model.addAttribute("userDetails", new UserSignUpRequestModel());
        return "signup";
    }

    /**
     * Accounts page 
     */
	@GetMapping("/account")
	public String showAccount(Model model, HttpServletRequest request) {
		UserEntity userEntity = new UserEntity();
		/**
		 * Use principal from Spring Security to get email of logged in user
		 * 
		 */
		Principal principal = request.getUserPrincipal();
		String email = principal.getName();
		UserDto userDto = userService.getUser(email);
	    BeanUtils.copyProperties(userDto, userEntity);
	    
		model.addAttribute("user", userEntity );
	
		return "account";
	}
	
    /**
     * About Page
     */
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	/**
	 * Profile Page
	 */
	@RequestMapping("/profile")
	public String profile(Model model, @RequestParam(value ="favorites", required=false) List<DoctorDto> favorites ,HttpServletRequest request) {
		UserEntity userEntity = new UserEntity();
		List<DoctorDto> returnValue = new ArrayList<DoctorDto>();
		Principal principal = request.getUserPrincipal();
		String email = principal.getName();
		UserDto userDto = userService.getUser(email);
	    BeanUtils.copyProperties(userDto, userEntity);
	    String userId = userEntity.getUserId();
	    
	    returnValue = doctorService.getFavoriteDoctors(userId);
		
		model.addAttribute("favorites", returnValue);
		return "profile";
	}

	/**
	 * DoctorProfile Page
	 */
	@GetMapping("/doctor/{uid}")
	public String doctorProfile(@PathVariable("uid") String uid, Model model) {
		DoctorResponse returnValue= new DoctorResponse();
		returnValue= doctorService.getDoctorByUid(uid);
		model.addAttribute("doctor", returnValue);
		//log.info(returnValue.toString());
		return "doctorProfile";
	}
	
    /**
     * Search Page
     * BetterDoctorAPI queries to return json
	 */
	@PostMapping(value="/search")
	public String search(Model model, @RequestParam(value="query", required=false) String query,
			@RequestParam(value="location", required=false, defaultValue="37.773,-122.413,100") String location,
			@RequestParam(value="gender", required= false)String gender,
			@RequestParam(value="results", required=false)DoctorResponse results) {
		model.addAttribute("query", query);
		model.addAttribute("location", location);
		model.addAttribute("gender", gender);
		
		
		/**
		 * check if gender was included in queries and remove from queryParam if user wants 'both' genders
		 */
		UriComponentsBuilder builder;
		if(gender ==null) {
			builder = UriComponentsBuilder.newInstance()
					.scheme("https").host("api.betterdoctor.com")
					.path("2016-03-01/")
					.path("doctors")
			        .queryParam("query", query)
			        .queryParam("location", location)
			        .queryParam("limit", "10")
			        //Get your APIKey at developer.betterdoctor.com
			        .queryParam("user_key", "CODE_SAMPLES_KEY_9d3608187");
		}else {
			builder = UriComponentsBuilder.newInstance()
					.scheme("https").host("api.betterdoctor.com")
					.path("2016-03-01/")
					.path("doctors")
			        .queryParam("query", query)
			        .queryParam("location", location)
			        .queryParam("gender", gender)
			        .queryParam("limit", "10")
			      //Get your APIKey at developer.betterdoctor.com
			        .queryParam("user_key", "CODE_SAMPLES_KEY_9d3608187");
		}
		
	//log.info(builder.toUriString());
		
		/**
		 * Use Doctor Service to return a DoctorResponse Object that maps with Json
		 */
		DoctorsResponse returnValue = new DoctorsResponse();
		returnValue = doctorService.getDoctors(builder.toUriString());
		model.addAttribute("results", returnValue);
		return "search";
	}

	/**
	 * Login Page
	 */
    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "loginPage";
    }
    /**
     * 
     * Logout Page
     */
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "home";
    }
	
    
    
    /*********
  	   CRUD
     **********/
	
	/**
	 * 	Create User
	 */
    @PostMapping(value = "/createuser")
    public String createUser(@Valid @ModelAttribute("userDetails") UserSignUpRequestModel userDetails, BindingResult result, Model model)  throws Exception{
        if (result.hasErrors()) {
        	/**
        	 * Show Validation errors to user 
        	 */
            return "signup";
        }
        UserRest returnValue = new UserRest();
        
        /**
         * Map dto with request model
         */
        UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

        /**
         * Use the Dto with User service to create user in database and return Dto
         */
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        
        /**
         * add back to model 
         */
        model.addAttribute("userDetails", returnValue);
        
        return "home";
    }
    

	/**
	 * Edit User Details and return view and model 
	 */
    @GetMapping("/edit/{userId}")
    public String showUpdateForm(@PathVariable("userId") String userId,  Model model) {

    	/**
    	 * UserDto is created through UserService with userId to get user details
    	 */
    	UserEntity returnValue = new UserEntity();
		UserDto userDto = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(userDto, returnValue);
        
		/**
		 * UserDto with current user details is returned to view to show to user for edit
		 */
        model.addAttribute("user", returnValue);
        return "update-user";
    }

    /**
     * Update User 
     */
    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @Valid @ModelAttribute("user") UserUpdateRequestModel user, BindingResult result, Model model) throws Exception{

    	if (result.hasErrors()) {
    		/**
    		 * show errors to user
    		 */
    		model.addAttribute("user");
           return "update-user";
        }
    	/**
    	 * create Response model to send back view
    	 */
		UserRest returnValue = new UserRest();
		
		/**
		 * UserDto copies properties edited by user from the modelAttribute
		 */
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(user, userDto);
		
		/**
		 * UserDto is sent with userid to UserService to create an updated user and save in database
		 */
		UserDto updatedUser = userService.updateUser(userId,userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);
		model.addAttribute("user", returnValue);
        return "account";
    }
    
    /**
     * Delete user by userId
     */
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId, Model model) {

		userService.deleteUser(userId);
        return "home";
    }


	    
    @GetMapping(value = "/add/doctor/{uid}")
    public String createFavorite(@PathVariable("uid") String uid, @ModelAttribute("doctor") DoctorResponse doctor, HttpServletRequest request,Model model)  throws Exception{
        
    	Principal principal = request.getUserPrincipal();
    	String email = principal.getName();
		doctor = doctorService.getDoctorByUid(uid);
		UserDto userDto = userService.getUser(email);
		
		
        DoctorDto doctorDto = new DoctorDto();
		BeanUtils.copyProperties(doctor.getData(), doctorDto);

		//Put favorite in database
		doctorService.createFavorite(doctorDto, userDto);
        
		UserEntity userEntity = new UserEntity();
		List<DoctorDto> favoriteDoctors = new ArrayList<DoctorDto>();
	    BeanUtils.copyProperties(userDto, userEntity);
	    String userId = userEntity.getUserId();
	    
	    //create list of Favortie doctors
	    favoriteDoctors = doctorService.getFavoriteDoctors(userId);
		
		model.addAttribute("favorites", favoriteDoctors);
        
        
        return "profile";
    }

}
