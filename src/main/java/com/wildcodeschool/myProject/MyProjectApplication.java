package com.wildcodeschool.myProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SpringBootApplication
public class MyProjectApplication {

    private String[] doctors ={"William Hartnell","Patrick Troughton","Jon Pertwee",
            "Tom Baker","Peter Davison","Colin Baker","Sylvester McCoy","Paul McGann",
            "Christopher Eccleston","David Tennant","Matt Smith","Peter Capaldi","Jodie Whittaker"};


    @RequestMapping(value= "/doctor/", method= GET)
    @ResponseBody
    public String getUrlList( ) {
        String htmlLinkList="<p><h1>Doctors url:</h1></p><ul>";
        for (int i=1; i<doctors.length; i++) {
            htmlLinkList+= String.format("<li><a href=\"/doctor/%d\">(%s)</a></li>", i,doctors[i-1]);
        }
        htmlLinkList+="</ul>";
        return(htmlLinkList);
    }

	@RequestMapping(value= "/doctor/{id}", method= GET)
	@ResponseBody
	public String getDoctorbyId( @PathVariable("id") Integer id) {
	    String docName="Unknown Doctor";
        id --;
	    if (id>=0 && id < doctors.length) {
	        docName=this.doctors[id];
        }
		return "This Doctor is "+docName;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}

}
