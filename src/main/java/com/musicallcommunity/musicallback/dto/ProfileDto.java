package com.musicallcommunity.musicallback.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musicallcommunity.musicallback.model.GenderChar;
import com.sun.istack.NotNull;

import java.util.List;

public class ProfileDto {


    private Long id;

    @NotNull
    private GenderChar gender;

    @NotNull
    private int age;

    @NotNull
    private LevelDto level;

    @NotNull
    private List<InstrumentDto> instruments;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public GenderChar getGender() { return gender; }

    public void setGender(GenderChar gender) { this.gender = gender; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public LevelDto getLevel() { return level; }

    public void setLevel(LevelDto level) { this.level = level; }

    public List<InstrumentDto> getInstruments() { return instruments; }

    public void setInstruments(List<InstrumentDto> instruments) { this.instruments = instruments; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProfileDto [profile_id=").append(id)
                .append(", gender=").append(gender)
                .append(", age=").append(age)
                .append(", level=").append(level).append("]");
        return builder.toString();
    }
    public static final class ProfileDtoBuilder {
        private ProfileDto profileDto;

        private ProfileDtoBuilder() {
            profileDto = new ProfileDto();
        }

        public static ProfileDto.ProfileDtoBuilder builder() {
            return new ProfileDto.ProfileDtoBuilder();
        }

        public ProfileDto.ProfileDtoBuilder id(Long profileId) {
            profileDto.setId(profileId);
            return this;
        }

        public ProfileDto.ProfileDtoBuilder gender(GenderChar gender) {
            profileDto.setGender(gender);
            return this;
        }

        public ProfileDto.ProfileDtoBuilder age(int age) {
            profileDto.setAge(age);
            return this;
        }

        public ProfileDto.ProfileDtoBuilder level(LevelDto level) {
            profileDto.setLevel(level);
            return this;
        }

        public ProfileDto.ProfileDtoBuilder instruments(List<InstrumentDto> instruments) {
            profileDto.setInstruments(instruments);
            return this;
        }


        public ProfileDto build() {
            return profileDto;
        }
    }

}
