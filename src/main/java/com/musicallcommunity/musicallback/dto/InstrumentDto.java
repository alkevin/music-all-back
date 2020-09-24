package com.musicallcommunity.musicallback.dto;

import javax.validation.constraints.NotNull;

public class InstrumentDto {

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


    public static final class InstrumentDtoBuilder {
        private InstrumentDto instrumentDto;

        private InstrumentDtoBuilder() {
            instrumentDto = new InstrumentDto();
        }

        public static InstrumentDto.InstrumentDtoBuilder builder() {
            return new InstrumentDto.InstrumentDtoBuilder();
        }

        public InstrumentDto.InstrumentDtoBuilder id(Long id) {
            instrumentDto.setId(id);
            return this;
        }

        public InstrumentDto.InstrumentDtoBuilder name(String name) {
            instrumentDto.setName(name);
            return this;
        }

        public InstrumentDto build() {
            return instrumentDto;
        }
    }
}
