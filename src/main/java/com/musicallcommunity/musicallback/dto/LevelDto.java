package com.musicallcommunity.musicallback.dto;

import javax.validation.constraints.NotNull;

public class LevelDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("InstrumentDto [name=").append(name).append("]");
        return builder.toString();
    }


    public static final class LevelDtoBuilder {
        private LevelDto levelDto;

        private LevelDtoBuilder() {
            levelDto = new LevelDto();
        }

        public static LevelDto.LevelDtoBuilder builder() {
            return new LevelDto.LevelDtoBuilder();
        }

        public LevelDto.LevelDtoBuilder id(Long id) {
            levelDto.setId(id);
            return this;
        }

        public LevelDto.LevelDtoBuilder name(String name) {
            levelDto.setName(name);
            return this;
        }

        public LevelDto build() {
            return levelDto;
        }
    }
}
