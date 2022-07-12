package com.gmail.juliarusakevich.event.service.pagination;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

public class PageResponse<T> {

    private final Metadata metadata;
    private final List<T> content;

    public PageResponse(Metadata metadata, List<T> content) {
        this.metadata = metadata;
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponse<?> that = (PageResponse<?>) o;
        return Objects.equals(content, that.content) && Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, metadata);
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "metadata=" + metadata +
                ", content=" + content +
                '}';
    }

    public static <T> PageResponse<T> of(Page<T> page) {
        var metadata = new Metadata(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast());
        return new PageResponse<>(metadata, page.getContent());
    }

    /*
    {
      "number": 0,
      "size": 0,
      "total_pages": 0,
      "total_elements": 0,
      "first": true,
      "number_of_elements": 0,
      "last": true,
     */
    public static class Metadata {

        private final int number;
        private final int size;
        private final int totalPages;
        private final int totalElements;
        private final boolean first;// true
        private final int numberOfElements;

        private final boolean last; // true

        public Metadata(
                int number,
                int size,
                int totalPages,
                int totalElements,
                boolean first,
                int numberOfElements,
                boolean last) {
            this.number = number;
            this.size = size;
            this.totalPages = totalPages;
            this.totalElements = totalElements;
            this.first = first;
            this.numberOfElements = numberOfElements;
            this.last = last;
        }

        public int getNumber() {
            return number;
        }

        public int getSize() {
            return size;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public boolean isLast() {
            return last;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Metadata metadata = (Metadata) o;
            return number == metadata.number && size == metadata.size && totalPages == metadata.totalPages && totalElements == metadata.totalElements && first == metadata.first && numberOfElements == metadata.numberOfElements && last == metadata.last;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, size, totalPages, totalElements, first, numberOfElements, last);
        }

        @Override
        public String toString() {
            return "Metadata{" +
                    "number=" + number +
                    ", size=" + size +
                    ", totalPages=" + totalPages +
                    ", totalElements=" + totalElements +
                    ", first=" + first +
                    ", numberOfElements=" + numberOfElements +
                    ", last=" + last +
                    '}';
        }
    }
}
