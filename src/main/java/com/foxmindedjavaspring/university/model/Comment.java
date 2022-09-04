package com.foxmindedjavaspring.university.model;

import java.time.LocalDateTime;

public class Comment {

    private final String text;
    private final LocalDateTime creationDate;
    private final LocalDateTime updateDate;

    private Comment(Builder builder) {
        this.text = builder.text;
        this.creationDate = builder.creationDate;
        this.updateDate = builder.updateDate;
    }

    public static final class Builder {
        private String text;
        private LocalDateTime creationDate;
        private LocalDateTime updateDate;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withUpdateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

}
