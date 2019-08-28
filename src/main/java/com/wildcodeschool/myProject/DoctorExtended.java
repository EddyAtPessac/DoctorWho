package com.wildcodeschool.myProject;


public class DoctorExtended extends Doctor {
    int numberOfEpisodes;
    int ageAtStart;

    public DoctorExtended(String name, int nbEpi, int ageStart) {
        super(name);
        this.numberOfEpisodes = nbEpi;
        this.ageAtStart = ageStart;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getAgeAtStart() {
        return ageAtStart;
    }

    public void setAgeAtStart(int ageAtStart) {
        this.ageAtStart = ageAtStart;
    }
}
