package com.wildcodeschool.myProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@SpringBootApplication
public class MyProjectApplication {


    final static int cstLastIncarnate= 9;   // Requested by the quest
    static int cstMaxId;    // Will be initialised by Postconstruct
    private final String[] doctorsNames ={"William Hartnell","Patrick Troughton","Jon Pertwee",
            "Tom Baker","Peter Davison","Colin Baker","Sylvester McCoy","Paul McGann",
            "Christopher Eccleston","David Tennant","Matt Smith","Peter Capaldi","Jodie Whittaker"};
    // Initialise  numberSeries and startAge only for the last cstLastIncarnate
    private final int numberSeries[]={1,3,3,3,1};
    private final int startAge[]={14,34,27,55,35};
    private ArrayList<Doctor> doctors;      // Used by default response
    private ArrayList<DoctorExtended> exDoctors;    // Used for detailled response

    @PostConstruct
    void postConstruct() {
        int i=0,j=0;
        doctors= new ArrayList<Doctor> ();
        exDoctors=new ArrayList<DoctorExtended> ();
        for (String name: doctorsNames) {
            i++;
            doctors.add(new Doctor(name));
            if (i>=cstLastIncarnate) {
                exDoctors.add(new DoctorExtended(name,numberSeries[j],startAge[j]));
                j++;
            }
        }
        cstMaxId=i;
    }

    @RequestMapping(value= "/doctor", method= GET)
    @ResponseBody
    public String getUrlList( ) {
        String htmlLinkList="<p><h1>Doctors url:</h1></p><ul>";
        for (int i=1; i<doctors.size(); i++) {
            htmlLinkList+= String.format("<li><a href=\"/doctor/%d\">(%s)</a></li>", i, doctors.get(i - 1).getName());
        }
        htmlLinkList+="</ul>";
        return(htmlLinkList);
    }

	@RequestMapping(value= "/doctor/{id}", method= GET)
	@ResponseBody
	public Doctor getDoctorbyId(@PathVariable Integer id,
                                @RequestParam (name="details", required = false, defaultValue = "false") String detailReq) {
	    if (id<1 || id>cstMaxId ) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Impossible de récupérer l'incarnation %d", id));
        }
	    else if (id < cstLastIncarnate ) {
            throw new ResponseStatusException(
                    HttpStatus.SEE_OTHER, String.format("/doctor/9"));
        }
	    else {
	        if (detailReq.equals("true")) {
                return exDoctors.get(id-cstLastIncarnate);
            }
	        else {
                return doctors.get(id-1);
            }
        }
	}

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}
}
